package infinity.developers.coreutils.Ui.Nova.Components.Doggle

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NovaBlackDoggle(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val shape = RoundedCornerShape(30.dp)

    Box(
        modifier = modifier
            .clip(shape)
            .background(
                Brush.horizontalGradient(
                    listOf(
                        Color(0xFF151515),
                        Color(0xFF0D0D0D)
                    )
                )
            )
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    listOf(
                        Color(0x802196F3),
                        Color.Transparent
                    )
                ),
                shape = shape
            )
            .padding(4.dp) // space for sliding pill
    ) {
        content()
    }
}