package infinity.developers.coreutils.Ui.Nova.Components.TextField

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NovaWhiteTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
) {

    var isFocused by remember { mutableStateOf(false) }

    val borderColor by animateColorAsState(
        if (isFocused) Color(0xFF0D47A1)
        else Color(0xFF0D47A1).copy(alpha = 0.3f),
        label = ""
    )

    Box(
        modifier = modifier
            .shadow(
                elevation = if (isFocused) 12.dp else 6.dp,
                shape = RoundedCornerShape(30.dp),
                ambientColor = Color(0xFF0D47A1).copy(alpha = 0.4f)
            )
            .background(Color.White, RoundedCornerShape(30.dp))
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(30.dp)
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused = it.isFocused
                }
        )

        if (value.isEmpty()) {
            Text(
                text = hint,
                color = Color.Gray,
                fontSize = 16.sp
            )
        }
    }
}