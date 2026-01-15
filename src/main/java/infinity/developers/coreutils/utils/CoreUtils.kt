package infinity.developers.coreutils.utils

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class CircleData(
    val size: Dp,
    val baseX: Dp,
    val baseY: Dp,
    val alpha1: Float,
    val alpha2: Float
)

val BlueBorderColor = Color(0x802196F3)
val AccentBlue = Color(0xFF2196F3)
val PrimaryBlack = Color(0xFF000000)
val DarkSurface = Color(0xFF121212)
val CardBlack = Color(0xFF1E1E1E)
val LightBlue = Color(0xFF64B5F6)
val WhiteText = Color(0xFFFFFFFF)
val SecondaryText = Color(0xFFB0B0B0)
val Red = Color(0xFFFF0000)
val RedBorderColor = Color(0xFF770000)


@Composable
fun doggleRedCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
){

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = RedBorderColor.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(30.dp)
                )
        ){
            Column(content = content)
        }
    }
}


@Composable
fun doggleBlueCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
){

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            modifier = Modifier
                .border(
                    1.dp,
                    BlueBorderColor.copy(alpha = 0.3f),
                    RoundedCornerShape(30.dp)
                )
        ){
            Column(content = content)
        }
    }
}
