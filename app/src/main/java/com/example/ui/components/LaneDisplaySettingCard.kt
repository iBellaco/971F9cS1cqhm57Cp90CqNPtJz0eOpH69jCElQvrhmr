package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.LaneRole
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGoldLight
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.util.tr

/**
 * Representación oficial del panel de selección de posiciones de Wild Rift.
 * Selección automática e instantánea de la posición principal (1) y secundaria (2),
 * completamente traducido según el idioma activo del usuario.
 */
@Composable
fun LaneDisplaySettingCard(
    mainRole: LaneRole,
    onMainRoleChange: (LaneRole) -> Unit,
    secondRole: LaneRole,
    onSecondRoleChange: (LaneRole) -> Unit,
    modifier: Modifier = Modifier
) {
    val roles = listOf(
        Triple(LaneRole.TOP, "SOLO NIV. 1", R.drawable.ic_wr_role_solo),
        Triple(LaneRole.JUNGLE, "JUNGLA NIV. 1", R.drawable.ic_wr_role_jungle),
        Triple(LaneRole.MID, "CENTRAL NIV. 1", R.drawable.ic_wr_role_mid),
        Triple(LaneRole.ADC, "DÚO NIV. 1", R.drawable.ic_wr_role_duo),
        Triple(LaneRole.SUPPORT, "SOPORTE NIV. 1", R.drawable.ic_wr_role_support)
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("lane_display_setting_card"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF07111E)),
        border = BorderStroke(
            width = 1.5.dp,
            brush = Brush.linearGradient(
                listOf(HextechGold, Color(0xFF785A28), HextechGold)
            )
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Bar Traducido
            Text(
                text = tr("Ajustes de Posición").uppercase(),
                color = HextechGoldLight,
                fontSize = 14.5.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.2.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = tr("Se pueden seleccionar hasta 2 posiciones"),
                color = Color(0xFF94A3B8),
                fontSize = 11.5.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 5 Column Roles en disposición horizontal
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                roles.forEach { (role, labelKey, iconRes) ->
                    val isMain = role == mainRole
                    val isSecond = role == secondRole
                    val isSelected = isMain || isSecond

                    val borderColor by animateColorAsState(
                        targetValue = when {
                            isMain -> HextechGold
                            isSecond -> HextechCyan
                            else -> Color(0xFF1E2A3A)
                        },
                        label = "roleBorderColor"
                    )

                    val bgColor by animateColorAsState(
                        targetValue = when {
                            isMain -> Color(0xFF152238)
                            isSecond -> Color(0xFF0F1E2E)
                            else -> Color(0xFF0A121D)
                        },
                        label = "roleBgColor"
                    )

                    val iconTint by animateColorAsState(
                        targetValue = when {
                            isMain -> Color(0xFFF0E6D2)
                            isSecond -> HextechCyan
                            else -> Color(0xFF6B7280)
                        },
                        label = "roleIconTint"
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(bgColor)
                            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
                            .clickable {
                                when {
                                    isMain -> {
                                        // Si tocamos el rol principal, alternar con el secundario
                                        val oldSecond = secondRole
                                        onMainRoleChange(oldSecond)
                                        onSecondRoleChange(role)
                                    }
                                    isSecond -> {
                                        // Si tocamos el secundario, ascenderlo a principal
                                        val oldMain = mainRole
                                        onMainRoleChange(role)
                                        onSecondRoleChange(oldMain)
                                    }
                                    else -> {
                                        // Asignar automáticamente como nuevo rol secundario
                                        onSecondRoleChange(role)
                                    }
                                }
                            }
                            .padding(vertical = 10.dp, horizontal = 2.dp)
                            .testTag("role_col_${role.name.lowercase()}"),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Icono Oficial de Cresta
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = tr(labelKey),
                            tint = iconTint,
                            modifier = Modifier
                                .size(34.dp)
                                .padding(2.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Nombre de Rol y Nivel Traducido
                        Text(
                            text = tr(labelKey),
                            color = if (isSelected) TextPrimary else TextMuted,
                            fontSize = 9.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Casilla / Indicador Numérico de Prioridad (1 y 2)
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(
                                    when {
                                        isMain -> HextechGold.copy(alpha = 0.25f)
                                        isSecond -> HextechCyan.copy(alpha = 0.25f)
                                        else -> Color(0xFF070D15)
                                    }
                                )
                                .border(
                                    width = 1.dp,
                                    color = when {
                                        isMain -> HextechGold
                                        isSecond -> HextechCyan
                                        else -> Color(0xFF334155)
                                    },
                                    shape = RoundedCornerShape(4.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            when {
                                isMain -> {
                                    Text(
                                        text = "1",
                                        color = HextechGold,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Black
                                    )
                                }
                                isSecond -> {
                                    Text(
                                        text = "2",
                                        color = HextechCyan,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Black
                                    )
                                }
                                else -> {
                                    // Casilla vacía
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
