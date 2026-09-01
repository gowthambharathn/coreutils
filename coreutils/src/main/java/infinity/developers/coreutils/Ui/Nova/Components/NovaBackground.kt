package infinity.developers.coreutils.Ui.Nova.Components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun NovaBackground(
    position: String = "TopRight",
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // ── THEME COLORS ──────────────────────────────────────────────────
    val baseBackgroundColor = if (isDarkTheme) Color(0xFF090C10) else Color(0xFFFAFAFA)
    val accentGlowColor = if (isDarkTheme) Color(0xFF0D47A1) else Color(0xFF29B6F6)
    val coreGlowColor = if (isDarkTheme) Color(0xFF1E88E5) else Color(0xFF0288D1)

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // 1. Fullscreen Ambient Background Layer
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            // Solid Base Background
            drawRect(color = baseBackgroundColor)

            // Dynamic Radial Glow Offset
            val glowCenter = when (position.lowercase().trim()) {
                "topright", "top", "right" -> Offset(width * 1.1f, height * -0.1f)
                "bottomleft", "bottom", "left" -> Offset(width * -0.1f, height * 1.1f)
                "topcenter", "center" -> Offset(width * 0.5f, height * -0.2f)
                else -> Offset(width * 1.1f, height * -0.1f)
            }

            val glowRadius = size.maxDimension * 0.95f

            // Radial Glow Gradient
            val ambientBrush = Brush.radialGradient(
                colors = listOf(
                    coreGlowColor.copy(alpha = if (isDarkTheme) 0.45f else 0.25f),
                    accentGlowColor.copy(alpha = if (isDarkTheme) 0.20f else 0.10f),
                    Color.Transparent
                ),
                center = glowCenter,
                radius = glowRadius
            )

            drawCircle(
                brush = ambientBrush,
                radius = glowRadius,
                center = glowCenter
            )
        }

        // 2. Foreground Screen Content (Dashboard, Cards, Graphs)
        content()
    }
}