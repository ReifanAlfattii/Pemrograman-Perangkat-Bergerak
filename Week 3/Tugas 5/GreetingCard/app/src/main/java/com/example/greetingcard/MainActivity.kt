package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greetingcard.ui.theme.BiruTombol
import com.example.greetingcard.ui.theme.GreetingCardTheme
import com.example.greetingcard.ui.theme.LatarKartu
import com.example.greetingcard.ui.theme.NavyJudul

// Identitas pembuat
private const val NAMA = "Reifan Al-fattii Cahyadewa"
private const val NRP = "5053251046"

/**
 * Satu buah ucapan yang ditampilkan di kartu.
 * Dipisah jadi data class biar gampang ditambah isinya.
 */
private data class Ucapan(
    val emoji: String,
    val judul: String,
    val pesan: String
)

private val daftarUcapan = listOf(
    Ucapan("👋", "Hello, Android!", "Welcome to Jetpack Compose"),
    Ucapan("🎉", "Selamat Datang!", "Semoga harimu menyenangkan"),
    Ucapan("💪", "Semangat Terus!", "Jangan lupa istirahat ya"),
    Ucapan("🙏", "Terima Kasih!", "Sudah mampir ke kartu ini")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // dynamicColor dimatikan supaya warnanya sama di semua HP
            GreetingCardTheme(dynamicColor = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingCardApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Composable yang memegang state: ucapan ke berapa yang sedang tampil.
 */
@Composable
fun GreetingCardApp(modifier: Modifier = Modifier) {
    // remember -> nilainya tetap disimpan walaupun UI digambar ulang
    var indeks by remember { mutableIntStateOf(0) }
    val ucapan = daftarUcapan[indeks]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        KartuUcapan(
            emoji = ucapan.emoji,
            judul = ucapan.judul,
            pesan = ucapan.pesan,
            teksTombol = if (indeks == 0) "Start" else "Ucapan Berikutnya",
            onTombolDiklik = {
                // % daftarUcapan.size -> balik lagi ke awal kalau sudah ucapan terakhir
                indeks = (indeks + 1) % daftarUcapan.size
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "$NAMA - $NRP",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * Tampilan kartunya saja. Tidak menyimpan state sendiri, semua dikirim
 * lewat parameter supaya composable ini gampang dipakai ulang.
 */
@Composable
fun KartuUcapan(
    emoji: String,
    judul: String,
    pesan: String,
    teksTombol: String,
    onTombolDiklik: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = LatarKartu),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = emoji,
                fontSize = 64.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = judul,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = NavyJudul,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = pesan,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onTombolDiklik,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BiruTombol)
            ) {
                Text(
                    text = teksTombol,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingCardAppPreview() {
    GreetingCardTheme(dynamicColor = false) {
        GreetingCardApp()
    }
}
