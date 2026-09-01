package infinity.developers.coreutils.Ui.Nova.Graph

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import infinity.developers.coreutils.Ui.Nova.Components.NovaCard
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

// ── PUBLIC API OVERLOADS ───────────────────────────────────────────────

@Composable
@JvmName("DDChartWithStr")
fun DoubleDonutChart(
    title: String,
    dataLabels: List<String>,
    dataValues: List<Float>,
    centerInfo: String,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    var activeTooltipIndex by remember { mutableStateOf(-1) }
    DoubleDoughnutChartInternal(
        title = title,
        shareNames = dataLabels,
        percentages = dataValues,
        centerText = centerInfo,
        centerImage = null,
        activeTooltipIndex = activeTooltipIndex,
        onInfoIconClick = { index -> activeTooltipIndex = if (activeTooltipIndex == index) -1 else index },
        modifier = modifier,
        isDarkTheme = isDarkTheme
    )
}

@Composable
@JvmName("DDChartWithPainter")
fun DoubleDonutChart(
    title: String,
    dataLabels: List<String>,
    dataValues: List<Float>,
    centerInfo: Painter,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    var activeTooltipIndex by remember { mutableStateOf(-1) }
    DoubleDoughnutChartInternal(
        title = title,
        shareNames = dataLabels,
        percentages = dataValues,
        centerText = null,
        centerImage = centerInfo,
        activeTooltipIndex = activeTooltipIndex,
        onInfoIconClick = { index -> activeTooltipIndex = if (activeTooltipIndex == index) -1 else index },
        modifier = modifier,
        isDarkTheme = isDarkTheme
    )
}

@Composable
@JvmName("DDChartWithBitmap")
fun DoubleDonutChart(
    title: String,
    dataLabels: List<String>,
    dataValues: List<Float>,
    centerInfo: Bitmap,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    val painter = remember(centerInfo) { BitmapPainter(centerInfo.asImageBitmap()) }
    var activeTooltipIndex by remember { mutableStateOf(-1) }
    DoubleDoughnutChartInternal(
        title = title,
        shareNames = dataLabels,
        percentages = dataValues,
        centerText = null,
        centerImage = painter,
        activeTooltipIndex = activeTooltipIndex,
        onInfoIconClick = { index -> activeTooltipIndex = if (activeTooltipIndex == index) -1 else index },
        modifier = modifier,
        isDarkTheme = isDarkTheme
    )
}

@Composable
@JvmName("DDChartWithStrMap")
fun DoubleDonutChart(
    title: String,
    data: Map<String, Float>,
    centerInfo: String,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    var activeTooltipIndex by remember { mutableStateOf(-1) }
    DoubleDoughnutChartInternal(
        title = title,
        shareNames = data.keys.toList(),
        percentages = data.values.toList(),
        centerText = centerInfo,
        centerImage = null,
        activeTooltipIndex = activeTooltipIndex,
        onInfoIconClick = { index -> activeTooltipIndex = if (activeTooltipIndex == index) -1 else index },
        modifier = modifier,
        isDarkTheme = isDarkTheme
    )
}

@Composable
@JvmName("DDChartWithBitmapMap")
fun DoubleDonutChart(
    title: String,
    data: Map<String, Float>,
    centerInfo: Bitmap,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    val painter = remember(centerInfo) { BitmapPainter(centerInfo.asImageBitmap()) }
    var activeTooltipIndex by remember { mutableStateOf(-1) }
    DoubleDoughnutChartInternal(
        title = title,
        shareNames = data.keys.toList(),
        percentages = data.values.toList(),
        centerText = null,
        centerImage = painter,
        activeTooltipIndex = activeTooltipIndex,
        onInfoIconClick = { index -> activeTooltipIndex = if (activeTooltipIndex == index) -1 else index },
        modifier = modifier,
        isDarkTheme = isDarkTheme
    )
}

@Composable
@JvmName("DDChartWithMap")
fun DoubleDonutChart(
    title: String,
    data: Map<String, Float>,
    centerInfo: Painter,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    var activeTooltipIndex by remember { mutableStateOf(-1) }
    DoubleDoughnutChartInternal(
        title = title,
        shareNames = data.keys.toList(),
        percentages = data.values.toList(),
        centerText = null,
        centerImage = centerInfo,
        activeTooltipIndex = activeTooltipIndex,
        onInfoIconClick = { index -> activeTooltipIndex = if (activeTooltipIndex == index) -1 else index },
        modifier = modifier,
        isDarkTheme = isDarkTheme
    )
}

// ── INTERNAL IMPLEMENTATION ───────────────────────────────────────────

@Composable
private fun DoubleDoughnutChartInternal(
    title: String,
    shareNames: List<String>,
    percentages: List<Float>,
    size: Dp = 270.dp,
    centerText: String? = null,
    centerImage: Painter? = null,
    activeTooltipIndex: Int = -1,
    onInfoIconClick: (Int) -> Unit = { _ -> },
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    require(shareNames.size == percentages.size) { "shareNames and percentages must have the same size" }

    // Theme Adaptive Colors
    val titleColor = if (isDarkTheme) Color(0xFF2196F3) else Color(0xFF1976D2)
    val centerTextColor = if (isDarkTheme) Color(0xFFCFD8DC) else Color(0xFF37474F)

    // Modern Vibrant Colors
    val baseColors = if (isDarkTheme) {
        listOf(
            Color(0xFF00E5FF), // Cyan
            Color(0xFFFFD600), // Gold
            Color(0xFFD500F9), // Neon Purple
            Color(0xFF00E676), // Emerald
            Color(0xFFFF3D00)  // Coral Red
        )
    } else {
        listOf(
            Color(0xFF0288D1), // Deep Blue
            Color(0xFFD81B60), // Vivid Pink
            Color(0xFF00897B), // Teal
            Color(0xFFF57C00), // Amber
            Color(0xFF7B1FA2)  // Deep Purple
        )
    }

    NovaCard(
        modifier = modifier,
        isDarkTheme = isDarkTheme
    ) {
        if (percentages.isEmpty() || percentages.all { it <= 0f }) {
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
            val sumPercent = percentages.sum()
            val normalizedPercentages = if (sumPercent != 100f && sumPercent != 0f) {
                percentages.map { (it / sumPercent) * 100f }
            } else {
                percentages
            }

            val iconPositions = remember { mutableStateMapOf<Int, Offset>() }

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

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(size)
                ) {
                    val density = LocalDensity.current

                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                detectTapGestures { tapOffset ->
                                    iconPositions.forEach { (index, position) ->
                                        val iconSize = size.toPx() * 0.05f
                                        val distance = (tapOffset - position).getDistance()
                                        if (distance <= iconSize * 1.5f) {
                                            onInfoIconClick(index)
                                            return@detectTapGestures
                                        }
                                    }
                                }
                            }
                    ) {
                        val canvasWidth = size.toPx()
                        val canvasHeight = size.toPx()
                        val centerX = canvasWidth / 2
                        val centerY = canvasHeight / 2
                        val minDimension = min(canvasWidth, canvasHeight)

                        val outerStrokeWidth = minDimension / 6f
                        val innerStrokeWidth = minDimension / 9f
                        val outerRadius = minDimension * 0.42f
                        val innerRadius = outerRadius * 0.68f

                        var startAngle = -90f
                        iconPositions.clear()

                        // Layer 1: Inner Semi-Transparent Donut & Percentage Text
                        normalizedPercentages.forEachIndexed { i, percent ->
                            val sweep = (percent / 100f) * 360f
                            val color = baseColors[i % baseColors.size].copy(alpha = 0.35f)

                            drawArc(
                                color = color,
                                startAngle = startAngle,
                                sweepAngle = sweep,
                                useCenter = false,
                                style = Stroke(innerStrokeWidth),
                                topLeft = Offset(centerX - innerRadius, centerY - innerRadius),
                                size = Size(innerRadius * 2, innerRadius * 2)
                            )

                            val percentageTextSize = minDimension * 0.04f
                            val midAngle = startAngle + sweep / 2
                            val textRadius = innerRadius
                            val x = textRadius * cos(midAngle * PI.toFloat() / 180f)
                            val y = textRadius * sin(midAngle * PI.toFloat() / 180f)

                            drawContext.canvas.nativeCanvas.apply {
                                val paint = Paint().apply {
                                    this.color = if (isDarkTheme) android.graphics.Color.parseColor("#CFD8DC") else android.graphics.Color.parseColor("#37474F")
                                    textSize = percentageTextSize
                                    textAlign = Paint.Align.CENTER
                                    isAntiAlias = true
                                }

                                val text = "${percent.toInt()}%"
                                val textBounds = Rect()
                                paint.getTextBounds(text, 0, text.length, textBounds)
                                val textOffset = (textBounds.top + textBounds.bottom) / 2f

                                drawText(
                                    text,
                                    centerX + x,
                                    centerY + y - textOffset,
                                    paint
                                )
                            }
                            startAngle += sweep
                        }

                        // Layer 2: Outer Donut Ring & Share Name Arc Labels
                        startAngle = -90f
                        normalizedPercentages.forEachIndexed { i, percent ->
                            val sweep = (percent / 100f) * 360f
                            val color = baseColors[i % baseColors.size]

                            drawArc(
                                color = color,
                                startAngle = startAngle,
                                sweepAngle = sweep,
                                useCenter = false,
                                style = Stroke(outerStrokeWidth),
                                topLeft = Offset(centerX - outerRadius, centerY - outerRadius),
                                size = Size(outerRadius * 2, outerRadius * 2)
                            )

                            if (i < shareNames.size) {
                                val iconPosition = drawOuterLabel(
                                    canvas = drawContext.canvas.nativeCanvas,
                                    center = Offset(centerX, centerY),
                                    outerRadius = outerRadius,
                                    outerStrokeWidth = outerStrokeWidth,
                                    startAngle = startAngle,
                                    sweepAngle = sweep,
                                    label = shareNames[i],
                                    color = color,
                                    textSize = minDimension * 0.055f,
                                    minDimension = minDimension
                                )
                                if (iconPosition != null) {
                                    iconPositions[i] = iconPosition
                                }
                            }
                            startAngle += sweep
                        }
                    }

                    // Interactive Tooltips Overlay
                    iconPositions.forEach { (index, position) ->
                        if (activeTooltipIndex == index) {
                            Box(
                                modifier = Modifier
                                    .offset(
                                        x = with(density) { position.x.toDp() - 48.dp },
                                        y = with(density) { position.y.toDp() - 48.dp }
                                    )
                                    .background(
                                        color = if (isDarkTheme) Color(0xEB1E1E1E) else Color(0xFDF0F4F8),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = baseColors[index % baseColors.size],
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                                    .zIndex(2f)
                            ) {
                                Text(
                                    text = shareNames[index],
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = if (isDarkTheme) Color.White else Color.Black,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                    // Center Content Ring (Image / Text)
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(size * 0.35f)
                    ) {
                        when {
                            centerImage != null -> {
                                Image(
                                    painter = centerImage,
                                    contentDescription = "Center graphic",
                                    modifier = Modifier
                                        .fillMaxSize(0.85f)
                                        .clip(CircleShape)
                                )
                            }

                            centerText != null -> {
                                Text(
                                    text = centerText,
                                    style = MaterialTheme.typography.headlineMedium.copy(
                                        fontSize = (size.value * 0.08).sp
                                    ),
                                    fontWeight = FontWeight.Bold,
                                    color = centerTextColor,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// ── HELPER FUNCTIONS ──────────────────────────────────────────────────

private fun drawOuterLabel(
    canvas: Canvas,
    center: Offset,
    outerRadius: Float,
    outerStrokeWidth: Float,
    startAngle: Float,
    sweepAngle: Float,
    label: String,
    color: Color,
    textSize: Float,
    minDimension: Float
): Offset? {
    val textPaint = Paint().apply {
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
        style = Paint.Style.FILL
        this.color = if (color.isLightColor()) Color.Black.toArgb() else Color.White.toArgb()
        this.textSize = textSize * 0.9f
    }

    val textRadius = outerRadius - outerStrokeWidth * 0.1f
    val midAngle = startAngle + sweepAngle / 2
    val x = textRadius * cos(midAngle * PI.toFloat() / 180f)
    val y = textRadius * sin(midAngle * PI.toFloat() / 180f)
    val iconPosition = Offset(center.x + x, center.y + y)

    val arcLength = (2 * PI * textRadius * sweepAngle / 360).toFloat()
    val textWidth = textPaint.measureText(label)

    if (textWidth > arcLength * 1.0f) {
        val iconSize = minDimension * 0.05f
        val iconPaint = Paint().apply {
            this.color = color.toArgb()
            style = Paint.Style.FILL
            isAntiAlias = true
        }

        canvas.drawCircle(iconPosition.x, iconPosition.y, iconSize, iconPaint)

        val iconTextPaint = Paint().apply {
            this.color = if (color.isLightColor()) Color.Black.toArgb() else Color.White.toArgb()
            this.textSize = iconSize * 1.2f
            textAlign = Paint.Align.CENTER
            isAntiAlias = true
        }

        val textBounds = Rect()
        iconTextPaint.getTextBounds("ⓘ", 0, 1, textBounds)
        val textOffset = (textBounds.top + textBounds.bottom) / 2f

        canvas.drawText(
            "ⓘ",
            iconPosition.x,
            iconPosition.y - textOffset,
            iconTextPaint
        )

        return iconPosition
    } else {
        val path = android.graphics.Path()
        val oval = RectF(
            center.x - textRadius,
            center.y - textRadius,
            center.x + textRadius,
            center.y + textRadius
        )

        path.addArc(oval, startAngle + sweepAngle * 0.05f, sweepAngle * 0.9f)
        canvas.drawTextOnPath(label, path, 0f, textPaint.textSize / 3f, textPaint)
        return null
    }
}

private fun Color.isLightColor(): Boolean {
    val brightness = (red * 299 + green * 587 + blue * 114) / 1000
    return brightness > 0.5
}