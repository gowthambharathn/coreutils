package infinity.developers.coreutils.Ui.Nova.Components.TextField


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun NovaBlackTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(18.dp)

    Box(
        modifier = modifier
            .clip(shape)
            .background(
                Brush.horizontalGradient(
                    listOf(
                        Color(0xFF1A1A1A),
                        Color(0xFF0F0F0F)
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
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                color = Color.White
            ),
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = Color(0xFF888888)
                    )
                }
                innerTextField()
            }
        )
    }
}