package infinity.developers.coreutils.Ui.Nova.Components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NovaCard(
    modifier: Modifier = Modifier,
    contentPadding: Dp = 16.dp,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable ColumnScope.() -> Unit
) {
    val shape = if (isDarkTheme) RoundedCornerShape(22.dp) else RoundedCornerShape(18.dp)
    val elevation = if (isDarkTheme) 0.dp else 10.dp

    val darkBorderBrush = Brush.linearGradient(
        listOf(
            Color(0xFF0D47A1),
            Color.Transparent
        )
    )

    Card(
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkTheme) Color(0xFF111111) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation)
    ) {
        Box(
            modifier = Modifier
                .then(
                    if (isDarkTheme) {
                        Modifier
                            .fillMaxSize()
                            .clip(shape)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(
                                        Color(0xFF1A1A1A),
                                        Color(0xFF0D0D0D)
                                    ),
                                    radius = 800f
                                )
                            )
                            .border(
                                width = 1.dp,
                                brush = darkBorderBrush,
                                shape = shape
                            )
                    } else {
                        Modifier
                    }
                )
                .padding(if (isDarkTheme) contentPadding else 20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                content = content
            )
        }
    }
}