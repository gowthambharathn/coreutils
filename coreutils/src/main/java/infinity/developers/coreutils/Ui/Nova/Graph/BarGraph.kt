package infinity.developers.coreutils.Ui.Nova.Graph

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import infinity.developers.coreutils.Ui.Nova.Components.NovaCard
import kotlin.math.max

@Composable
fun BarGraph(
    title: String,
    dataList: List<Pair<String, Float>>,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    val titleColor = if (isDarkTheme) Color(0xFF2196F3) else Color(0xFF1976D2)
    val textColor = if (isDarkTheme) Color(0xFFCFD8DC) else Color(0xFF37474F)
    val axisColor = if (isDarkTheme) Color(0x66868686) else Color(0x66B0BEC5)

    val barGradients = if (isDarkTheme) {
        listOf(
            listOf(Color(0xFF00E5FF), Color(0xFF00838F)), // Cyan
            listOf(Color(0xFFD500F9), Color(0xFF4A148C)), // Purple
            listOf(Color(0xFFFFD600), Color(0xFFF57F17)), // Gold
            listOf(Color(0xFF00E676), Color(0xFF1B5E20))  // Emerald
        )
    } else {
        listOf(
            listOf(Color(0xFF0288D1), Color(0xFF01579B)),
            listOf(Color(0xFFD81B60), Color(0xFF880E4F)),
            listOf(Color(0xFF00897B), Color(0xFF004D40)),
            listOf(Color(0xFFF57C00), Color(0xFFE65100))
        )
    }

    NovaCard(
        modifier = modifier,
        isDarkTheme = isDarkTheme
    ) {
        if (dataList.isEmpty()) {
            Text(
                text = title,
                color = titleColor,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "No Data Available",
                color = if (isDarkTheme) Color(0xFF757575) else Color(0xFF9E9E9E)
            )
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = title,
                    color = titleColor,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                val maxVal = max(100f, dataList.maxOfOrNull { it.second } ?: 100f)

                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.3f)
                        .padding(8.dp)
                ) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    val leftPadding = canvasWidth * 0.14f
                    val bottomPadding = canvasHeight * 0.14f
                    val topPadding = canvasHeight * 0.12f
                    val rightPadding = canvasWidth * 0.05f

                    val yAxisX = leftPadding
                    val xAxisY = canvasHeight - bottomPadding
                    val graphRight = canvasWidth - rightPadding
                    val graphTop = topPadding

                    val graphWidth = (graphRight - yAxisX).coerceAtLeast(0f)
                    val graphHeight = (xAxisY - graphTop).coerceAtLeast(0f)

                    val baseTextSize = (canvasWidth / 32f).coerceIn(18f, 28f)

                    // Draw Axes
                    drawLine(
                        color = axisColor,
                        start = Offset(yAxisX, graphTop),
                        end = Offset(yAxisX, xAxisY),
                        strokeWidth = 2f
                    )
                    drawLine(
                        color = axisColor,
                        start = Offset(yAxisX, xAxisY),
                        end = Offset(graphRight, xAxisY),
                        strokeWidth = 2f
                    )

                    // Y-Axis Grid & Labels
                    val yStepCount = 5
                    for (step in 0..yStepCount) {
                        val value = maxVal * step / yStepCount
                        val y = xAxisY - (value / maxVal) * graphHeight

                        drawContext.canvas.nativeCanvas.drawText(
                            "${value.toInt()}%",
                            yAxisX - 12f,
                            y + baseTextSize * 0.35f,
                            Paint().apply {
                                color = textColor.toArgb()
                                textSize = baseTextSize
                                textAlign = Paint.Align.RIGHT
                                isAntiAlias = true
                            }
                        )

                        drawLine(
                            color = axisColor.copy(alpha = 0.2f),
                            start = Offset(yAxisX, y),
                            end = Offset(graphRight, y),
                            strokeWidth = 1f
                        )
                    }

                    // Draw Bars & X-Axis Labels
                    val barCount = dataList.size
                    val totalSlotWidth = graphWidth / barCount
                    val barWidth = totalSlotWidth * 0.45f

                    dataList.forEachIndexed { index, pair ->
                        val barHeight = (pair.second / maxVal) * graphHeight
                        val slotLeft = yAxisX + index * totalSlotWidth
                        val barLeft = slotLeft + (totalSlotWidth - barWidth) / 2f
                        val barTop = xAxisY - barHeight

                        val colors = barGradients[index % barGradients.size]

                        drawRoundRect(
                            brush = Brush.verticalGradient(
                                colors = colors,
                                startY = barTop,
                                endY = xAxisY
                            ),
                            topLeft = Offset(barLeft, barTop),
                            size = Size(barWidth, barHeight),
                            cornerRadius = CornerRadius(barWidth * 0.2f, barWidth * 0.2f)
                        )

                        // X-Axis Label
                        drawContext.canvas.nativeCanvas.drawText(
                            pair.first,
                            slotLeft + totalSlotWidth / 2f,
                            xAxisY + baseTextSize * 1.2f,
                            Paint().apply {
                                color = textColor.toArgb()
                                textSize = baseTextSize
                                textAlign = Paint.Align.CENTER
                                isAntiAlias = true
                            }
                        )
                    }
                }
            }
        }
    }
}