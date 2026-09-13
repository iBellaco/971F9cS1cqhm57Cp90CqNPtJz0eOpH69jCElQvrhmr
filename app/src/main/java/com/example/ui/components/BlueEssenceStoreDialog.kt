package com.example.ui.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.R
import com.example.data.AccountProfileManager
import com.example.util.AuthManager
import com.example.util.SubscriptionHistoryManager
import com.example.ui.theme.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun BlueEssenceStoreDialog(
    profileId: String,
    isAdmin: Boolean = AuthManager.isCurrentUserAdmin(),
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val profiles by AccountProfileManager.allProfiles.collectAsState()
    val prof = profiles.find { it.id == profileId } ?: profiles.firstOrNull()

    if (!isAdmin) {
        LaunchedEffect(Unit) {
            Toast.makeText(context, "Servicio temporalmente fuera de servicio", Toast.LENGTH_LONG).show()
            onDismiss()
        }
        return
    }

    if (prof == null) return

    // Tabs: 0 = Tienda EA, 1 = Canjear Economía, 2 = Historial
    var selectedTab by remember { mutableStateOf(0) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.85f),
            shape = RoundedCornerShape(16.dp),
            color = HextechDarkBg,
            border = BorderStroke(1.dp, HextechGold)
        ) {
            Column {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(HextechSurface)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_blue_essence),
                            contentDescription = "Esencia Azul",
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Economía de Esencia Azul (Admin)", color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Saldo: ${prof.blueEssence} EA", color = HextechCyan, fontSize = 13.sp)
                        }
                    }
                    HextechAnimatedIconButton(
                        onClick = onDismiss,
                        size = 32.dp,
                        backgroundColor = Color.Transparent,
                        borderColor = Color.Transparent,
                        glowColor = HextechGold
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted, modifier = Modifier.size(18.dp))
                    }
                }
                
                HorizontalDivider(color = HextechGold.copy(alpha = 0.5f))

                // Tabs Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Button(
                        onClick = { selectedTab = 0 },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedTab == 0) HextechGold else HextechSurface,
                            contentColor = if (selectedTab == 0) HextechDarkBg else HextechGold
                        ),
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
                    ) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = null, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Tienda EA", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = { selectedTab = 1 },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedTab == 1) HextechGold else HextechSurface,
                            contentColor = if (selectedTab == 1) HextechDarkBg else HextechGold
                        ),
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
                    ) {
                        Icon(Icons.Default.WorkspacePremium, contentDescription = null, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Canjear Items", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = { selectedTab = 2 },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedTab == 2) HextechGold else HextechSurface,
                            contentColor = if (selectedTab == 2) HextechDarkBg else HextechGold
                        ),
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
                    ) {
                        Icon(Icons.Default.History, contentDescription = null, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Historial", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }

                when (selectedTab) {
                    0 -> {
                        // Tienda EA (Paquetes)
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            contentPadding = PaddingValues(vertical = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            item {
                                Text("Recargar Esencias Azules (Admin)", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                            val packs = listOf(
                                Triple(400, 3.99, "Paquete Básico"),
                                Triple(1000, 8.99, "Paquete Épico"),
                                Triple(2500, 19.99, "Paquete Legendario"),
                                Triple(5300, 39.99, "Cofre de Artesano")
                            )
                            items(packs) { (amount, price, title) ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            AccountProfileManager.buyBlueEssence(context, profileId, amount, price)
                                            Toast.makeText(context, "+$amount EA añadidos con éxito", Toast.LENGTH_SHORT).show()
                                        },
                                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                                    border = BorderStroke(1.dp, HextechCardBorder),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_blue_essence),
                                            contentDescription = null,
                                            modifier = Modifier.size(36.dp)
                                        )
                                        Spacer(modifier = Modifier.width(14.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(title, color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                            Text("+$amount Esencias Azules", color = HextechCyan, fontSize = 12.sp)
                                        }
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(HextechGold)
                                                .padding(horizontal = 10.dp, vertical = 4.dp)
                                        ) {
                                            Text("$$price", color = HextechDarkBg, fontWeight = FontWeight.ExtraBold, fontSize = 13.sp)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    1 -> {
                        // Canjear Economía (Suscripciones, Avatares, Temas basados en precios)
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            contentPadding = PaddingValues(vertical = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            item {
                                Text("Economía de Canje (Basado en Precios de Suscripción)", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Text("Usa tus Esencias Azules para adquirir suscripciones, avatares o temas exclusivos.", color = TextSecondary, fontSize = 11.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                            }

                            val economyItems = listOf(
                                Triple("Suscripción Premium (Mensual)", 1500, "Equivalente a $5.00 USD - Acceso completo por 30 días"),
                                Triple("Suscripción Premium (Anual)", 15000, "Equivalente a $55.00 USD - Acceso completo por 1 año"),
                                Triple("Avatar Exclusivo Coleccionista", 300, "Desbloquea un avatar legendario único para tu perfil"),
                                Triple("Tema Hextech Personalizado", 500, "Desbloquea el tema visual exclusivo para la interfaz")
                            )

                            items(economyItems) { (title, cost, desc) ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = HextechSurface),
                                    border = BorderStroke(1.dp, HextechCardBorder),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(title, color = HextechGold, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.ic_blue_essence),
                                                    contentDescription = null,
                                                    modifier = Modifier.size(18.dp)
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text("$cost EA", color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                            }
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(desc, color = TextMuted, fontSize = 11.sp)
                                        Spacer(modifier = Modifier.height(10.dp))
                                        HextechAnimatedButton(
                                            onClick = {
                                                val success = AccountProfileManager.spendBlueEssence(context, profileId, cost)
                                                if (success) {
                                                    scope.launch {
                                                        val duration = if (title.contains("Mensual")) 30L * 24 * 60 * 60 * 1000L else if (title.contains("Anual")) 365L * 24 * 60 * 60 * 1000L else 0L
                                                        if (duration > 0L) {
                                                            SubscriptionHistoryManager.addRecord(duration, title, "Completado", "Canje por $cost EA")
                                                        }
                                                    }
                                                    Toast.makeText(context, "¡Canje exitoso de '$title'!", Toast.LENGTH_LONG).show()
                                                } else {
                                                    Toast.makeText(context, "Esencias Azules insuficientes (Necesitas $cost EA)", Toast.LENGTH_SHORT).show()
                                                }
                                            },
                                            backgroundColor = HextechGold,
                                            borderColor = HextechCyan,
                                            glowColor = HextechGold,
                                            modifier = Modifier.fillMaxWidth().height(38.dp),
                                            shape = RoundedCornerShape(8.dp),
                                            enableShimmer = true
                                        ) {
                                            Text("Canjear con Esencias Azules", color = HextechDarkBg, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    2 -> {
                        // Historial
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            contentPadding = PaddingValues(vertical = 12.dp)
                        ) {
                            if (prof.purchaseHistory.isEmpty()) {
                                item {
                                    Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                                        Text("No hay registros de compras de esencia", color = TextMuted)
                                    }
                                }
                            } else {
                                items(prof.purchaseHistory.reversed()) { purchase ->
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        colors = CardDefaults.cardColors(containerColor = HextechSurfaceVariant),
                                        border = BorderStroke(1.dp, HextechCardBorder)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(12.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.ic_blue_essence),
                                                    contentDescription = null,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Column {
                                                    Text(
                                                        text = "+${purchase.amount} Esencias Azules",
                                                        color = HextechCyan,
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 13.sp
                                                    )
                                                    val date = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(purchase.timestamp))
                                                    Text(text = date, color = TextMuted, fontSize = 10.sp)
                                                }
                                            }
                                            Text(
                                                text = "$${purchase.price} ${purchase.currency}",
                                                color = HextechGold,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 13.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
