package com.example.foodordering

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.MenuBook
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.RestaurantMenu
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodordering.ui.theme.Amber
import com.example.foodordering.ui.theme.FoodOrderingTheme
import com.example.foodordering.ui.theme.Sand
import com.example.foodordering.ui.theme.Tomato
import com.example.foodordering.ui.theme.TomatoDeep
import com.example.foodordering.ui.theme.TomatoSoft

@Composable
fun FoodOrderingScreen() {
    val context = LocalContext.current

    // State: setiap kali nilainya berubah, Compose menggambar ulang UI (recomposition)
    var selectedIndex by remember { mutableIntStateOf(0) }
    var quantity by remember { mutableIntStateOf(1) }
    var cartCount by remember { mutableIntStateOf(0) }
    var favorites by remember { mutableStateOf(setOf<Int>()) }
    var showMenu by remember { mutableStateOf(false) }

    val food = menuList[selectedIndex]
    val isFavorite = selectedIndex in favorites

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            FoodTopBar(
                cartCount = cartCount,
                onCartClick = {
                    showToast(
                        if (cartCount == 0) "Keranjang masih kosong"
                        else "Ada $cartCount item di keranjang"
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 20.dp)
        ) {
            FoodImage(food)
            Spacer(Modifier.height(18.dp))
            FoodInfo(food)
            Spacer(Modifier.height(18.dp))
            OrderSummary(
                quantity = quantity,
                total = food.price * quantity,
                onDecrease = { if (quantity > 1) quantity-- },
                onIncrease = { quantity++ }
            )
            Spacer(Modifier.height(16.dp))

            // Button utama: masukkan pesanan ke keranjang
            Button(
                onClick = {
                    cartCount += quantity
                    showToast("${quantity}x ${food.name} masuk keranjang")
                    quantity = 1
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(18.dp)
            ) {
                Icon(
                    Icons.Rounded.ShoppingCart,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text("Tambah ke Keranjang")
            }
            Spacer(Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = { showMenu = true },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    shape = RoundedCornerShape(18.dp),
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary),
                    contentPadding = PaddingValues(horizontal = 12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        Icons.AutoMirrored.Rounded.MenuBook,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Lihat Menu")
                }
                FavoriteButton(
                    isFavorite = isFavorite,
                    onClick = {
                        favorites =
                            if (isFavorite) favorites - selectedIndex else favorites + selectedIndex
                        showToast(
                            if (isFavorite) "${food.name} dihapus dari favorit"
                            else "${food.name} ditambahkan ke favorit"
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                )
            }
        }
    }

    if (showMenu) {
        MenuBottomSheet(
            selectedIndex = selectedIndex,
            onSelect = { index ->
                selectedIndex = index
                quantity = 1
                showMenu = false
            },
            onDismiss = { showMenu = false }
        )
    }
}

@Composable
fun FoodTopBar(cartCount: Int, onCartClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Rounded.RestaurantMenu,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text("FoodHunt", style = MaterialTheme.typography.titleMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Rounded.LocationOn,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(Modifier.width(2.dp))
                Text(
                    "Antar ke Kampus",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        // Ikon keranjang + badge jumlah item
        BadgedBox(
            badge = {
                if (cartCount > 0) {
                    Badge(containerColor = MaterialTheme.colorScheme.primary) {
                        Text("$cartCount")
                    }
                }
            }
        ) {
            OutlinedIconButton(
                onClick = onCartClick,
                modifier = Modifier.size(46.dp),
                border = BorderStroke(1.dp, Sand),
                colors = IconButtonDefaults.outlinedIconButtonColors(containerColor = Color.White)
            ) {
                Icon(Icons.Rounded.ShoppingCart, contentDescription = "Keranjang")
            }
        }
    }
}

@Composable
fun FoodImage(food: Food) {
    Box {
        Image(
            painter = painterResource(food.image),
            contentDescription = food.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(28.dp))
        )
        Row(
            modifier = Modifier
                .padding(14.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Rounded.LocalFireDepartment,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                food.tag,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
fun FoodInfo(food: Food) {
    Column {
        Text(food.name, style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(6.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Rounded.Star,
                contentDescription = null,
                tint = Amber,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text("${food.rating}", style = MaterialTheme.typography.labelMedium)
            Spacer(Modifier.width(14.dp))
            Icon(
                Icons.Rounded.Schedule,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                food.cookTime,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(Modifier.height(10.dp))
        Text(
            formatRupiah(food.price),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(4.dp))
        Text(
            food.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// Kartu jumlah + total harga (total dipisah dari tombol keranjang)
@Composable
fun OrderSummary(
    quantity: Int,
    total: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, Sand)
    ) {
        Column(Modifier.padding(horizontal = 18.dp, vertical = 14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Jumlah",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.weight(1f)
                )
                QuantitySelector(quantity, onDecrease, onIncrease)
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Sand)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Total harga",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
                Text(formatRupiah(total), style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}

@Composable
fun QuantitySelector(quantity: Int, onDecrease: () -> Unit, onIncrease: () -> Unit) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Button − (tidak bisa ditekan kalau jumlahnya tinggal 1)
        FilledIconButton(
            onClick = onDecrease,
            enabled = quantity > 1,
            modifier = Modifier.size(36.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledContainerColor = Color.White.copy(alpha = 0.6f),
                disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f)
            )
        ) {
            Icon(Icons.Rounded.Remove, contentDescription = "Kurangi")
        }
        Text(
            "$quantity",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(min = 40.dp)
        )
        // Button +
        FilledIconButton(
            onClick = onIncrease,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(Icons.Rounded.Add, contentDescription = "Tambah")
        }
    }
}

@Composable
fun FavoriteButton(isFavorite: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(18.dp)
    val border = BorderStroke(1.5.dp, Tomato)
    val padding = PaddingValues(horizontal = 12.dp)

    if (isFavorite) {
        // Sudah favorit: FilledTonalButton dengan hati penuh
        FilledTonalButton(
            onClick = onClick,
            modifier = modifier,
            shape = shape,
            border = border,
            contentPadding = padding,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = TomatoSoft,
                contentColor = TomatoDeep
            )
        ) {
            Icon(
                Icons.Rounded.Favorite,
                contentDescription = null,
                tint = Tomato,
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text("Favorit")
        }
    } else {
        // Belum favorit: OutlinedButton dengan hati kosong
        OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            shape = shape,
            border = border,
            contentPadding = padding,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = TomatoDeep)
        ) {
            Icon(
                Icons.Rounded.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text("Favorit")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBottomSheet(selectedIndex: Int, onSelect: (Int) -> Unit, onDismiss: () -> Unit) {
    // skipPartiallyExpanded: sheet langsung terbuka penuh, semua menu kelihatan
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            Modifier
                .padding(horizontal = 20.dp)
                .padding(bottom = 24.dp)
        ) {
            Text("Menu Hari Ini", style = MaterialTheme.typography.titleLarge)
            Text(
                "Pilih makanan yang mau kamu pesan",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(12.dp))
            menuList.forEachIndexed { index, food ->
                MenuItem(
                    food = food,
                    isSelected = index == selectedIndex,
                    onClick = { onSelect(index) }
                )
            }
        }
    }
}

@Composable
fun MenuItem(food: Food, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(
                if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent
            )
            .clickable(onClick = onClick)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(food.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(14.dp))
        )
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(food.name, style = MaterialTheme.typography.titleSmall)
            Spacer(Modifier.height(2.dp))
            Text(
                formatRupiah(food.price),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Icon(
            Icons.Rounded.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FoodOrderingScreenPreview() {
    FoodOrderingTheme {
        FoodOrderingScreen()
    }
}
