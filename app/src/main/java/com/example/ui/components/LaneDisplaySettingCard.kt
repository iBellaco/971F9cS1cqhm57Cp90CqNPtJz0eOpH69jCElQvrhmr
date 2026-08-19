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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.example.ui.theme.HextechCardBorder
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.HextechGoldLight
import com.example.ui.theme.HextechSurface
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.util.tr

/**
 * Representación oficial del panel "LANE DISPLAY SETTING" de Wild Rift.
 * Permite seleccionar de forma táctil y visual las posiciones preferidas (Línea Main y Secundaria).
 */
@Composable
fun LaneDisplaySettingCard(
    mainRole: LaneRole,
    onMainRoleChange: (LaneRole) -> Unit,
    secondRole: LaneRole,
    onSecondRoleChange: (LaneRole) -> Unit,
    modifier: Modifier = Modifier,
    onSaveConfirmed: (() -> Unit)? = null
) {
    val roles = listOf(
        Triple(LaneRole.TOP, "SOLO", R.drawable.ic_wr_role_solo),
        Triple(LaneRole.JUNGLE, "JUNGLE", R.drawable.ic_wr_role_jungle),
        Triple(LaneRole.MID, "MID", R.drawable.ic_wr_role_mid),
        Triple(LaneRole.ADC, "DUO", R.drawable.ic_wr_role_duo),
        Triple(LaneRole.SUPPORT, "SUPPORT", R.drawable.ic_wr_role_support)
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
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Bar: Centered title with gold cross 'X' on top right
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "LANE DISPLAY SETTING",
                    color = HextechGoldLight,
                    fontSize = 14.5.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.2.sp,
                    textAlign = TextAlign.Center
                )
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cerrar",
                    tint = HextechGold.copy(alpha = 0.8f),
                    modifier = Modifier
                        .size(18.dp)
                        .align(Alignment.CenterEnd)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = tr("Up to 2 positions can be displayed"),
                color = Color(0xFF94A3B8),
                fontSize = 11.5.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 5 Column Roles in horizontal arrangement
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                roles.forEach { (role, label, iconRes) ->
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
                                        // Si es main y tocamos de nuevo, no hacemos nada o swap si existe secundario
                                    }
                                    isSecond -> {
                                        // Intercambiar secundario a main
                                        val oldMain = mainRole
                                        onMainRoleChange(role)
                                        onSecondRoleChange(oldMain)
                                    }
                                    else -> {
                                        // Seleccionar como secundario o main
                                        onSecondRoleChange(role)
                                    }
                                }
                            }
                            .padding(vertical = 10.dp, horizontal = 2.dp)
                            .testTag("role_col_${label.lowercase()}"),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Official Crest Icon
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = label,
                            tint = iconTint,
                            modifier = Modifier
                                .size(34.dp)
                                .padding(2.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Role Name & Level
                        Text(
                            text = "$label LV. 1",
                            color = if (isSelected) TextPrimary else TextMuted,
                            fontSize = 9.5.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Selection Checkbox / Badge Indicator
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
                                    // Empty unselected box
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Save / Confirm Action Button
            Button(
                onClick = {
                    onSaveConfirmed?.invoke()
                },
                modifier = Modifier
                    .width(130.dp)
                    .height(34.dp)
                    .testTag("save_lane_display_button"),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D1B2A)),
                border = BorderStroke(1.dp, HextechGold),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = tr("SAVE"),
                    color = HextechGold,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}
