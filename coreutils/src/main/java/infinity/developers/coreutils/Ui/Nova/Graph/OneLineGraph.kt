package infinity.developers.coreutils.Ui.Nova.Graph

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import infinity.developers.coreutils.Ui.Nova.Components.NovaCard
import java.util.Locale
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

@Composable
fun OneLineGraph(
    title: String,
    dataValues: Map<String, List<Float>>,
    xAxisValues: List<String>,
    yAxisValues: List<Float>,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    // ── THEME ADAPTIVE COLORS (Matching DoubleDonutChart) ──────────────
    val titleColor = if (isDarkTheme) Color(0xFF2196F3) else Color(0xFF1976D2)
    val axisColor = if (isDarkTheme) Color(0xFF444444) else Color(0xFFB0BEC5)
    val gridLineColor = if (isDarkTheme) Color(0x33FFFFFF) else Color(0x1F000000)
    val labelTextColor = if (isDarkTheme) android.graphics.Color.parseColor("#B0BEC5") else android.graphics.Color.parseColor("#455A64")
    val legendTextColor = if (isDarkTheme) Color(0xFFCFD8DC) else Color(0xFF37474F)

    val lineColor = if (isDarkTheme) Color(0xFF00E5FF) else Color(0xFF0288D1)

    // ── DATA EXTRACTION & EMPTY CHECK ─────────────────────────────────
    val entry = dataValues.entries.firstOrNull()
    if (entry == null || entry.value.isEmpty()) {
        NovaCard(modifier = modifier, isDarkTheme = isDarkTheme) {
            Text(
                text = title,
                color = titleColor,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "No Data Available",
                color = if (isDarkTheme) Color(0xFF757575) else Color(0xFF9E9E9E)
            )
        }
        return
    }

    val shareName = entry.key
    val values = entry.value

    // ── SCALE CALCULATION ───────────────────────────────────────────────
    val dataMin = values.minOrNull() ?: 0f
    val dataMax = values.maxOrNull() ?: 0f
    val labelMin = yAxisValues.minOrNull() ?: dataMin
    val labelMax = yAxisValues.maxOrNull() ?: dataMax

    var minY = min(dataMin, labelMin)
    var maxY = max(dataMax, labelMax)

    if (maxY - minY == 0f) {
        val pad = if (maxY == 0f) 1f else abs(maxY) * 0.1f
        minY -= pad
        maxY += pad
    }

    val yLabelsToDraw = if (yAxisValues.isNotEmpty()) {
        yAxisValues
    } else {
        (0..4).map { i -> minY + (maxY - minY) * (i / 4f) }
    }

    val textPaint = remember {
        Paint().apply {
            isAntiAlias = true
        }
    }

    NovaCard(modifier = modifier, isDarkTheme = isDarkTheme) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                fontWeight = FontWeight.Bold,
                color = titleColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.35f)
                    .padding(4.dp)
            ) {
                val width = size.width
                val height = size.height

                val leftPadding = width * 0.12f
                val bottomPadding = height * 0.14f
                val topPadding = height * 0.08f
                val rightPadding = width * 0.04f

                val yAxisX = leftPadding
                val xAxisY = height - bottomPadding
                val graphRight = width - rightPadding
                val graphTop = topPadding

                val graphWidth = graphRight - yAxisX
                val graphHeight = xAxisY - graphTop

                val baseTextSize = (width / 34f).coerceIn(18f, 26f)

                // Grid Lines & Y Axis Labels
                yLabelsToDraw.forEach { value ->
                    val clamped = value.coerceIn(minY, maxY)
                    val yPosition = xAxisY - ((clamped - minY) / (maxY - minY)) * graphHeight

                    drawLine(
                        color = gridLineColor,
                        start = Offset(yAxisX, yPosition),
                        end = Offset(graphRight, yPosition),
                        strokeWidth = 1f
                    )

                    drawContext.canvas.nativeCanvas.drawText(
                        formatLabel(value),
                        yAxisX - 12f,
                        yPosition + baseTextSize * 0.35f,
                        textPaint.apply {
                            color = labelTextColor
                            textSize = baseTextSize
                            textAlign = Paint.Align.RIGHT
                        }
                    )
                }

                // Axes Baseline
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

                // Points Calculation
                val points = values.mapIndexed { index, value ->
                    val x = yAxisX + (index.toFloat() / (values.size - 1).coerceAtLeast(1)) * graphWidth
                    val y = xAxisY - ((value - minY) / (maxY - minY)) * graphHeight
                    Offset(x, y)
                }

                // Bézier Curved Line Drawing
                if (points.size >= 2) {
                    val path = Path().apply {
                        moveTo(points.first().x, points.first().y)
                        for (i in 0 until points.size - 1) {
                            val p1 = points[i]
                            val p2 = points[i + 1]
                            val controlPoint1 = Offset(p1.x + (p2.x - p1.x) / 2f, p1.y)
                            val controlPoint2 = Offset(p1.x + (p2.x - p1.x) / 2f, p2.y)
                            cubicTo(
                                controlPoint1.x, controlPoint1.y,
                                controlPoint2.x, controlPoint2.y,
                                p2.x, p2.y
                            )
                        }
                    }

                    drawPath(
                        path = path,
                        color = lineColor,
                        style = Stroke(width = 4f, cap = StrokeCap.Round)
                    )
                }

                // Glow Ring Data Points
                points.forEach { point ->
                    drawCircle(color = lineColor.copy(alpha = 0.2f), radius = 8f, center = point)
                    drawCircle(color = lineColor, radius = 4f, center = point)
                    drawCircle(
                        color = if (isDarkTheme) Color(0xFF111111) else Color.White,
                        radius = 2f,
                        center = point
                    )
                }

                // X Axis Labels
                val totalXPoints = xAxisValues.size
                if (totalXPoints > 0) {
                    val maxLabels = (graphWidth / (baseTextSize * 4.5f)).toInt().coerceAtLeast(2)
                    val labelStep = (totalXPoints / maxLabels).coerceAtLeast(1)

                    xAxisValues.forEachIndexed { i, label ->
                        if (i % labelStep != 0 && i != xAxisValues.lastIndex) return@forEachIndexed

                        val xPosition = yAxisX + (i.toFloat() / (totalXPoints - 1).coerceAtLeast(1)) * graphWidth

                        drawContext.canvas.nativeCanvas.drawText(
                            label,
                            xPosition,
                            xAxisY + baseTextSize * 1.4f,
                            textPaint.apply {
                                color = labelTextColor
                                textSize = baseTextSize * 0.85f
                                textAlign = Paint.Align.CENTER
                            }
                        )
                    }
                }
            }

            // Single Line Legend Bar
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(lineColor, CircleShape)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = shareName,
                        color = legendTextColor,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

private fun formatLabel(value: Float): String {
    return if (value == value.toInt().toFloat()) {
        value.toInt().toString()
    } else {
        String.format(Locale.US, "%.2f", value)
    }
}