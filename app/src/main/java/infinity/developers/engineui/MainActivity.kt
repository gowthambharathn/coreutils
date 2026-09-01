package infinity.developers.engineui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import infinity.developers.coreutils.Ui.Nova.Components.NovaBackground
import infinity.developers.coreutils.Ui.Nova.Components.NovaButton
import infinity.developers.coreutils.Ui.Nova.Components.NovaCard
import infinity.developers.coreutils.Ui.Nova.Components.NovaTextField
import infinity.developers.coreutils.Ui.Nova.Components.NovaToggle
import infinity.developers.coreutils.Ui.Nova.Graph.BarGraph
import infinity.developers.coreutils.Ui.Nova.Graph.DoubleDonutChart
import infinity.developers.coreutils.Ui.Nova.Graph.MultiLineGraph
import infinity.developers.coreutils.Ui.Nova.Graph.OneLineGraph
import infinity.developers.engineui.ui.theme.EngineUITheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EngineUITheme {
                NovaBackground("topcenter") {
                    TestAll()
                }
            }
        }
    }
}


@Composable
fun TestAll() {
    // ── Local Interactive State ─────────────────────────────────────
    var selectedToggleIndex by remember { mutableIntStateOf(0) }
    var inputText by remember { mutableStateOf("") }
    var submittedText by remember { mutableStateOf("No submission yet") }

    val toggleOptions = listOf("Daily", "Weekly", "Monthly")

    // ── X & Y Axis Configurations for Line Graphs ─────────────────
    val xAxisLabels = remember { listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun") }
    val yAxisLabels = remember { listOf(0f, 25f, 50f, 75f, 100f) }

    // 1. BarGraph Data
    val barGraphData = remember(selectedToggleIndex) {
        when (selectedToggleIndex) {
            0 -> listOf("Physics" to 85f, "Maths" to 92f, "Chemistry" to 78f, "Biology" to 64f)
            1 -> listOf("Physics" to 70f, "Maths" to 88f, "Chemistry" to 82f, "Biology" to 75f)
            else -> listOf("Physics" to 95f, "Maths" to 99f, "Chemistry" to 90f, "Biology" to 88f)
        }
    }

    // 2. DoubleDonutChart Data
    val donutChartData = remember(selectedToggleIndex) {
        when (selectedToggleIndex) {
            0 -> mapOf("Android" to 45f, "iOS" to 30f, "Web" to 15f, "Desktop" to 10f)
            1 -> mapOf("Android" to 50f, "iOS" to 25f, "Web" to 20f, "Desktop" to 5f)
            else -> mapOf("Android" to 60f, "iOS" to 20f, "Web" to 10f, "Desktop" to 10f)
        }
    }

    // 3. OneLineGraph Data
    val singleLineData = remember(selectedToggleIndex) {
        when (selectedToggleIndex) {
            0 -> mapOf("Active Users" to listOf(12f, 45f, 30f, 75f, 60f, 90f, 85f))
            1 -> mapOf("Active Users" to listOf(30f, 40f, 55f, 50f, 70f, 65f, 92f))
            else -> mapOf("Active Users" to listOf(50f, 65f, 70f, 85f, 80f, 95f, 100f))
        }
    }

    // 4. MultiLineGraph Data
    val multiLineData = remember(selectedToggleIndex) {
        when (selectedToggleIndex) {
            0 -> mapOf(
                "Revenue" to listOf(20f, 50f, 40f, 80f, 65f, 95f, 90f),
                "Expenses" to listOf(10f, 25f, 30f, 45f, 40f, 50f, 45f),
                "Profit" to listOf(10f, 25f, 10f, 35f, 25f, 45f, 45f)
            )
            1 -> mapOf(
                "Revenue" to listOf(40f, 60f, 55f, 75f, 85f, 90f, 98f),
                "Expenses" to listOf(20f, 30f, 35f, 40f, 45f, 50f, 52f),
                "Profit" to listOf(20f, 30f, 20f, 35f, 40f, 40f, 46f)
            )
            else -> mapOf(
                "Revenue" to listOf(60f, 75f, 80f, 90f, 92f, 96f, 100f),
                "Expenses" to listOf(25f, 35f, 30f, 40f, 38f, 42f, 40f),
                "Profit" to listOf(35f, 40f, 50f, 50f, 54f, 54f, 60f)
            )
        }
    }

    // ── Content Scroll Container ────────────────────────────────────
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        // Header
        Text(
            text = "Nova UI Suite",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2196F3)
        )

        // 1. NovaToggle Test
        NovaToggle(
            options = toggleOptions,
            selectedIndex = selectedToggleIndex,
            onToggle = { index -> selectedToggleIndex = index }
        )

        // 2. BarGraph Test
        BarGraph(
            title = "Performance Breakdown (${toggleOptions[selectedToggleIndex]})",
            dataList = barGraphData,
            modifier = Modifier.fillMaxWidth()
        )

        // 3. DoubleDonutChart Test
        DoubleDonutChart(
            title = "Platform Distribution",
            data = donutChartData,
            centerInfo = "${donutChartData["Android"]?.toInt()}%",
            modifier = Modifier.fillMaxWidth()
        )

        // 4. OneLineGraph Test
        OneLineGraph(
            title = "User Activity Trend",
            dataValues = singleLineData,
            xAxisValues = xAxisLabels,
            yAxisValues = yAxisLabels,
            modifier = Modifier.fillMaxWidth()
        )

        // 5. MultiLineGraph Test
        MultiLineGraph(
            title = "Financial Overview",
            dataMap = multiLineData,
            xAxisValues = xAxisLabels,
            yAxisValues = yAxisLabels,
            modifier = Modifier.fillMaxWidth()
        )

        // 6. NovaCard Test containing NovaTextField & NovaButton
        NovaCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Interactive Form",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2196F3),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // NovaTextField Test
            NovaTextField(
                value = inputText,
                onValueChange = { inputText = it },
                hint = "Enter component prompt...",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // NovaButton Test
            NovaButton(
                text = "Submit Input",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    submittedText = inputText.ifEmpty { "Empty Submission" }
                }
            )
        }

        // 7. NovaCard Test for Output Display
        NovaCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "State Output",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Selected Option: ${toggleOptions[selectedToggleIndex]}",
                fontSize = 14.sp,
                color = Color(0xFF2196F3)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Submitted Text: $submittedText",
                fontSize = 14.sp,
                color = Color(0xFF2196F3)
            )
        }
    }
}