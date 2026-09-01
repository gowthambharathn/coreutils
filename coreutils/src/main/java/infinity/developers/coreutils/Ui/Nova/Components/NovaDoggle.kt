package infinity.developers.coreutils.Ui.Nova.Components


import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NovaToggle(
    options: List<String>,
    selectedIndex: Int,
    onToggle: (Int) -> Unit,
    modifier: Modifier = Modifier,
    containerWidth: Dp = 320.dp,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    val optionWidth = containerWidth / options.size.coerceAtLeast(1)

    val offset by animateDpAsState(
        targetValue = optionWidth * selectedIndex,
        animationSpec = tween(320),
        label = "NovaToggleOffset"
    )

    val shape = RoundedCornerShape(30.dp)
    val noRipple = remember { MutableInteractionSource() }

    // ── THEME STYLING ────────────────────────────────────────────────
    val containerBackground = if (isDarkTheme) {
        Brush.horizontalGradient(
            listOf(
                Color(0xFF151515),
                Color(0xFF0D0D0D)
            )
        )
    } else {
        Brush.horizontalGradient(
            listOf(
                Color.White.copy(alpha = 0.95f),
                Color(0xFFF5F7FA)
            )
        )
    }

    val containerBorder = if (isDarkTheme) {
        Brush.linearGradient(
            listOf(
                Color(0x802196F3),
                Color.Transparent
            )
        )
    } else {
        Brush.linearGradient(
            listOf(
                Color(0x220D47A1),
                Color(0x220D47A1)
            )
        )
    }

    val indicatorBackground = if (isDarkTheme) {
        Brush.horizontalGradient(
            listOf(
                Color(0xFF2196F3),
                Color(0x802196F3)
            )
        )
    } else {
        Brush.horizontalGradient(
            listOf(
                Color(0xFF2196F3),
                Color(0xFF1565C0)
            )
        )
    }

    // ── LAYOUT ───────────────────────────────────────────────────────
    Box(
        modifier = modifier
            .width(containerWidth)
            .height(52.dp)
            .clip(shape)
            .background(containerBackground)
            .border(1.dp, containerBorder, shape)
            .padding(4.dp)
    ) {

        // Animated Active Pill Indicator
        Box(
            modifier = Modifier
                .offset(x = offset)
                .width(optionWidth - 4.dp)
                .fillMaxHeight()
                .clip(shape)
                .background(indicatorBackground)
        )

        // Text Items Row
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEachIndexed { index, text ->
                val isSelected = index == selectedIndex

                val textColor = when {
                    isSelected -> Color.White
                    isDarkTheme -> Color.White.copy(alpha = 0.7f)
                    else -> Color(0xFF2196F3)
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(
                            interactionSource = noRipple,
                            indication = null
                        ) {
                            onToggle(index)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        color = textColor,
                        fontSize = 14.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
                    )
                }
            }
        }
    }
}