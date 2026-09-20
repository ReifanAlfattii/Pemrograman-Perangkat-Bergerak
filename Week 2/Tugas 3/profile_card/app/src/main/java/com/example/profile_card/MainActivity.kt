package com.example.profile_card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profile_card.ui.theme.CardBackground
import com.example.profile_card.ui.theme.CardBorder
import com.example.profile_card.ui.theme.DividerColor
import com.example.profile_card.ui.theme.HeaderBlue
import com.example.profile_card.ui.theme.IconBackground
import com.example.profile_card.ui.theme.Profile_cardTheme
import com.example.profile_card.ui.theme.ScreenBackground
import com.example.profile_card.ui.theme.TextPrimary
import com.example.profile_card.ui.theme.TextSecondary

// Data mahasiswa
private const val NAMA_KAMPUS_ATAS = "INSTITUT TEKNOLOGI"
private const val NAMA_KAMPUS_BAWAH = "SEPULUH NOPEMBER"
private const val INISIAL_KAMPUS = "ITS"

private const val NAMA = "Reifan Al-fattii Cahyadewa"
private const val NRP = "5053251046"
private const val PROGRAM_STUDI = "Rekayasa Perangkat Lunak"
private const val STATUS = "Mahasiswa"
private const val SEMESTER = "Semester 3"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Profile_cardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        ProfileCard()
    }
}

@Composable
fun ProfileCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.width(340.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        border = BorderStroke(width = 1.dp, color = CardBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            HeaderKampus()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. Foto Profil
                FotoProfil()

                Spacer(modifier = Modifier.height(16.dp))

                // 2. Judul
                Text(
                    text = "PROFIL MAHASISWA",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    letterSpacing = 1.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                // 3. Informasi
                Column(modifier = Modifier.fillMaxWidth()) {
                    InfoRow(
                        icon = Icons.Default.Person,
                        label = "Nama",
                        value = NAMA
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    InfoRow(
                        icon = Icons.Default.AccountBox,
                        label = "NRP",
                        value = NRP
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    InfoRow(
                        icon = Icons.Default.Info,
                        label = "Program Studi",
                        value = PROGRAM_STUDI
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // 4. Divider
                HorizontalDivider(
                    thickness = 1.dp,
                    color = DividerColor
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 5. Status
                StatusRow()
            }
        }
    }
}

@Composable
fun HeaderKampus(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(HeaderBlue)
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = INISIAL_KAMPUS,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = HeaderBlue
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = NAMA_KAMPUS_ATAS,
                fontSize = 9.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White.copy(alpha = 0.85f),
                letterSpacing = 1.sp
            )
            Text(
                text = NAMA_KAMPUS_BAWAH,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 0.5.sp
            )
        }
    }
}

@Composable
fun FotoProfil(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.foto_profil),
        contentDescription = "Foto Profil Mahasiswa",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
    )
}

@Composable
fun InfoRow(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(IconBackground),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = HeaderBlue,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = TextSecondary
            )
            Text(
                text = value,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                color = TextPrimary
            )
        }
    }
}

@Composable
fun StatusRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        StatusItem(icon = Icons.Default.CheckCircle, text = STATUS)

        VerticalDivider(
            modifier = Modifier.height(22.dp),
            thickness = 1.dp,
            color = DividerColor
        )

        StatusItem(icon = Icons.Default.DateRange, text = SEMESTER)
    }
}

@Composable
fun StatusItem(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = HeaderBlue,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = TextPrimary
        )
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun ProfileCardPreview() {
    Profile_cardTheme {
        MainContent()
    }
}
