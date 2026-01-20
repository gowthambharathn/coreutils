package infinity.management.coreengine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import infinity.management.coreengine.ui.theme.CoreEngineTheme
import androidx.compose.runtime.*
import infinity.developers.coreutils.extensions.isValidEmail
import infinity.management.coreengine.EngineUI.Backgrounds.Quantum.QuantumBlueBg
import infinity.management.coreengine.EngineUI.Backgrounds.Quantum.QuantumRedBg
import infinity.management.coreengine.EngineUI.Components.Quantum.Button.BlueButton
import infinity.management.coreengine.EngineUI.Components.Quantum.Button.RedButton
import infinity.management.coreengine.EngineUI.Components.Quantum.Doggle.QuantumBlueToggle
import infinity.management.coreengine.EngineUI.Components.Quantum.Doggle.QuantumRedToggle
import infinity.management.coreengine.EngineUI.Components.Quantum.TextField.QuantumBlueTextField
import infinity.management.coreengine.EngineUI.Components.Quantum.TextField.QuantumRedTextField

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoreEngineTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        QuantumRedBg()

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            var selected by remember { mutableIntStateOf(0) }
            var a = remember { mutableStateOf("") }

            var name by remember { mutableStateOf("") }

            QuantumRedTextField(
                value = name,
                onValueChange = { name = it },
                valueToString = { it },          // String -> String
                stringToValue = { it },
                modifier = Modifier
                    .width(100.dp)
            )

            Spacer(modifier = Modifier.padding(5.dp))

            RedButton(
                text = "ACTIVATE",
                modifier = Modifier.width(100.dp)
            ) {
                // click action
            }

            Spacer(modifier = Modifier.padding(5.dp))

            QuantumRedToggle(
            options = listOf("Buy", "Sell", "Hold"),
            selectedIndex = selected,
            onToggle = { index ->
                selected = index
            }
            )
        }
        val email = "test@gmail.com"
        if (email.isValidEmail()) {
            // valid
        }

    }
}
