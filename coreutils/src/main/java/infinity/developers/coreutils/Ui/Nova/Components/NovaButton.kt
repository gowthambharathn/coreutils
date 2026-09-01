package infinity.developers.coreutils.Ui.Nova.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NovaButton(
    text: String,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    onClick: () -> Unit
) {
    if (isDarkTheme) {
        val darkShape = RoundedCornerShape(18.dp)

        Box(
            modifier = modifier
                .clip(darkShape)
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
                            Color(0xFF0D47A1),
                            Color.Transparent
                        )
                    ),
                    shape = darkShape
                )
                .clickable { onClick() }
                .padding(vertical = 14.dp, horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color(0xFF2196F3)
            )
        }
    } else {
        val lightShape = RoundedCornerShape(14.dp)

        Button(
            onClick = onClick,
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF2196F3)
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 4.dp
            ),
            shape = lightShape
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
    }
}