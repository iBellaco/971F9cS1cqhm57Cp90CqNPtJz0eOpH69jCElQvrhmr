package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Champion
import com.example.ui.theme.HextechCyan
import com.example.ui.theme.HextechDarkBg
import com.example.ui.theme.HextechGold
import com.example.ui.theme.TierAColor
import com.example.ui.theme.TierSColor
import com.example.ui.theme.TierSPlusColor

@Composable
fun ChampionAvatar(
    champion: Champion,
    size: Dp = 56.dp,
    showTierBadge: Boolean = true,
    modifier: Modifier = Modifier
) {
    // Generate harmonious colors for champion portrait background based on name
    val avatarBrush = when (champion.id) {
        "morgana" -> Brush.radialGradient(listOf(Color(0xFF8B5CF6), Color(0xFF2E1065), Color(0xFF0F051D)))
        "viego" -> Brush.radialGradient(listOf(Color(0xFF00F2FE), Color(0xFF005A82), Color(0xFF071426)))
        "nautilus" -> Brush.radialGradient(listOf(Color(0xFFD97706), Color(0xFF78350F), Color(0xFF1E1B18)))
        "yasuo" -> Brush.radialGradient(listOf(Color(0xFF38BDF8), Color(0xFF0369A1), Color(0xFF082F49)))
        "sett" -> Brush.radialGradient(listOf(Color(0xFFF43F5E), Color(0xFF881337), Color(0xFF2E0514)))
        "aatrox" -> Brush.radialGradient(listOf(Color(0xFFEF4444), Color(0xFF7F1D1D), Color(0xFF290808)))
        "darius" -> Brush.radialGradient(listOf(Color(0xFFB91C1C), Color(0xFF450A0A), Color(0xFF1A0404)))
        "fiora" -> Brush.radialGradient(listOf(Color(0xFFEC4899), Color(0xFF831843), Color(0xFF280715)))
        "janna" -> Brush.radialGradient(listOf(Color(0xFF67E8F9), Color(0xFF0E7490), Color(0xFF082F49)))
        "lulu" -> Brush.radialGradient(listOf(Color(0xFFA855F7), Color(0xFF581C87), Color(0xFF1E0734)))
        "chogath" -> Brush.radialGradient(listOf(Color(0xFF10B981), Color(0xFF064E3B), Color(0xFF021B14)))
        "vayne" -> Brush.radialGradient(listOf(Color(0xFF6366F1), Color(0xFF312E81), Color(0xFF110E38)))
        "caitlyn" -> Brush.radialGradient(listOf(Color(0xFF3B82F6), Color(0xFF1E3A8A), Color(0xFF081534)))
        "vi" -> Brush.radialGradient(listOf(Color(0xFFF43F5E), Color(0xFF9F1239), Color(0xFF3B0716)))
        "rell" -> Brush.radialGradient(listOf(Color(0xFFF59E0B), Color(0xFF78350F), Color(0xFF1F0D03)))
        "ahri" -> Brush.radialGradient(listOf(Color(0xFFF472B6), Color(0xFF9D174D), Color(0xFF38071B)))
        "zed" -> Brush.radialGradient(listOf(Color(0xFFEF4444), Color(0xFF330808), Color(0xFF110202)))
        "kaisa" -> Brush.radialGradient(listOf(Color(0xFFA855F7), Color(0xFF3B0764), Color(0xFF130122)))
        else -> Brush.radialGradient(listOf(HextechCyan, Color(0xFF003852), HextechDarkBg))
    }

    Box(
        modifier = modifier
            .size(size)
            .padding(2.dp)
    ) {
        // Champion Face Icon with stylized initial and Hextech border
        Box(
            modifier = Modifier
                .size(size - 4.dp)
                .clip(CircleShape)
                .background(avatarBrush)
                .border(1.5.dp, HextechGold, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            // Elegant champion monogram / stylized portrait
            Text(
                text = champion.name.take(2).uppercase(),
                color = Color.White,
                fontSize = (size.value * 0.32).sp,
                fontWeight = FontWeight.Black
            )
        }

        // Tier Badge in bottom corner
        if (showTierBadge) {
            val tierColor = when (champion.tier) {
                "S+" -> TierSPlusColor
                "S" -> TierSColor
                else -> TierAColor
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 2.dp, y = 2.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(tierColor)
                    .border(1.dp, HextechDarkBg, RoundedCornerShape(4.dp))
                    .padding(horizontal = 4.dp, vertical = 1.dp)
            ) {
                Text(
                    text = champion.tier,
                    color = HextechDarkBg,
                    fontSize = (size.value * 0.18).coerceAtLeast(9.0).sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}
