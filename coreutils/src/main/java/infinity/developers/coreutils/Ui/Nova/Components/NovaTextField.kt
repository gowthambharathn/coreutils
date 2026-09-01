package infinity.developers.coreutils.Ui.Nova.Components


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NovaTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String? = null,
    singleLine: Boolean = true,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    var isFocused by remember { mutableStateOf(false) }
    val shape = RoundedCornerShape(16.dp)

    // Dynamic Focus Border Styling
    val animatedLightBorderColor by animateColorAsState(
        targetValue = if (isFocused) Color(0xFF2196F3) else Color(0xFF2196F3).copy(alpha = 0.18f),
        animationSpec = tween(300),
        label = "BorderColorAnimation"
    )

    val darkBorderBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF2196F3).copy(alpha = if (isFocused) 1f else 0.45f),
            Color.Transparent
        )
    )

    // Theme Configurations
    val textStyleColor = if (isDarkTheme) Color.White else Color.Black
    val hintColor = if (isDarkTheme) Color.Gray else Color(0xFF2196F3).copy(alpha = 0.7f)
    val shadowElevation = if (isDarkTheme) 0.dp else if (isFocused) 10.dp else 4.dp

    Box(
        modifier = modifier
            .shadow(
                elevation = shadowElevation,
                shape = shape,
                clip = false
            )
            .clip(shape)
            .then(
                if (isDarkTheme) {
                    Modifier.background(
                        Brush.horizontalGradient(
                            listOf(
                                Color(0xFF1A1A1A),
                                Color(0xFF0F0F0F)
                            )
                        )
                    )
                } else {
                    Modifier.background(Color.White)
                }
            )
            .then(
                if (isDarkTheme) {
                    Modifier.border(width = 1.dp, brush = darkBorderBrush, shape = shape)
                } else {
                    Modifier.border(width = 1.dp, color = animatedLightBorderColor, shape = shape)
                }
            )
            .padding(horizontal = 18.dp, vertical = 16.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = TextStyle(
                color = textStyleColor,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused = it.isFocused
                }
        )

        if (value.isEmpty() && hint != null) {
            Text(
                text = hint,
                color = hintColor,
                fontSize = 16.sp
            )
        }
    }
}