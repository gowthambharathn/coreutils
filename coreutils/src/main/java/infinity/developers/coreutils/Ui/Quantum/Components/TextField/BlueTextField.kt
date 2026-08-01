package infinity.developers.coreutils.Ui.Quantum.Components.TextField

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import infinity.developers.coreutils.Ui.Utils.AccentBlue
import infinity.developers.coreutils.Ui.Utils.SecondaryText
import infinity.developers.coreutils.Ui.Utils.WhiteText

@Composable
fun QuantumBlueTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String? = null,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }

    val borderColor by animateColorAsState(
        targetValue = if (isFocused)
            Color(0xFF2196F3)
        else
            Color(0xFF2196F3).copy(alpha = 0.45f),
        label = ""
    )

    val shape = RoundedCornerShape(30.dp)

    Box(
        modifier = modifier
            .height(52.dp)
            .clip(shape)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color(0xFF2196F3).copy(alpha = 0.45f),
                        Color(0xFF2196F3).copy(alpha = 0.25f)
                    )
                )
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape
            )
            .padding(horizontal = 18.dp)
    ) {

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 14.sp
            ),
            modifier = Modifier
                .fillMaxSize()
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            decorationBox = { innerTextField ->

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty() && hint != null) {
                        Text(
                            text = hint,
                            color = Color.White.copy(alpha = 0.65f),
                            fontSize = 14.sp
                        )
                    }

                    innerTextField()
                }
            }
        )
    }
}