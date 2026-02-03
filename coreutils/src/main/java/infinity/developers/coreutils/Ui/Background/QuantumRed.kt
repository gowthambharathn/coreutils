package infinity.developers.coreutils.Ui.Background


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import infinity.developers.coreutils.utils.CircleData
import infinity.developers.coreutils.utils.Red
import kotlin.random.Random

@Composable
fun QuantumRedBg() {
    val circleData = listOf(
        CircleData(180.dp, (-30).dp, (-30).dp, 0.2f, 0.05f),
        CircleData(180.dp, 250.dp, (-50).dp, 0.15f, 0.03f),
        CircleData(150.dp, (-40).dp, 300.dp, 0.1f, 0.02f),
        CircleData(200.dp, 280.dp, 400.dp, 0.25f, 0.06f),
        CircleData(160.dp, 100.dp, 650.dp, 0.12f, 0.01f),
        CircleData(140.dp, 320.dp, 120.dp, 0.18f, 0.04f),
        CircleData(190.dp, (-120).dp, 520.dp, 0.22f, 0.05f)
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ) {
        circleData.forEach { data ->
            RedMovingCircle(data)
        }
    }
}

@Composable
fun RedMovingCircle(data: CircleData) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val infiniteTransition = rememberInfiniteTransition(label = "")

    // randomize direction & duration slightly per circle
    val durationX = remember { Random.nextInt(20000, 40000) }
    val durationY = remember { Random.nextInt(25000, 45000) }

    val xOffset by infiniteTransition.animateFloat(
        initialValue = -screenWidth.value / 2,
        targetValue = screenWidth.value / 2,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationX, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val yOffset by infiniteTransition.animateFloat(
        initialValue = -screenHeight.value / 2,
        targetValue = screenHeight.value / 2,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationY, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .size(data.size)
            .offset(x = data.baseX + xOffset.dp, y = data.baseY + yOffset.dp)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Red.copy(alpha = data.alpha1),
                        Red.copy(alpha = data.alpha2),
                        Color.Transparent
                    )
                ),
                shape = CircleShape
            )
    )
}

