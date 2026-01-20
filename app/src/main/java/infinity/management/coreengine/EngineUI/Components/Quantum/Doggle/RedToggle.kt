package infinity.management.coreengine.EngineUI.Components.Quantum.Doggle


import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import infinity.management.coreengine.EngineUI.Utils.Red
import infinity.management.coreengine.EngineUI.Utils.SecondaryText
import infinity.management.coreengine.EngineUI.Utils.WhiteText
import infinity.management.coreengine.EngineUI.Utils.doggleRedCard

@Composable
fun QuantumRedToggle(
    options: List<String>,
    selectedIndex: Int,
    onToggle: (Int) -> Unit
) {
    val containerWidth = 320.dp
    val optionWidth = containerWidth / options.size

    val offset by animateDpAsState(
        targetValue = optionWidth * selectedIndex,
        animationSpec = tween(300, easing = LinearOutSlowInEasing),
        label = ""
    )

    doggleRedCard(
        modifier = Modifier
            .width(containerWidth)
            .height(52.dp)
    ) {
        Box {
            // Sliding glass pill
            Box(
                modifier = Modifier
                    .offset(x = offset)
                    .width(optionWidth)
                    .fillMaxHeight()
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Red.copy(alpha = 0.45f),
                                Red.copy(alpha = 0.25f)
                            )
                        ),
                        shape = RoundedCornerShape(30.dp)
                    )
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                options.forEachIndexed { index, text ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable { onToggle(index) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = text,
                            color = if (index == selectedIndex)
                                WhiteText
                            else
                                SecondaryText,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}
