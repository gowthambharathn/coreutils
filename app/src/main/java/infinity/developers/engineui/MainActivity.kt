package infinity.developers.engineui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import infinity.developers.coreutils.Ui.Nova.Components.Background.NovaBottomLeftBackground
import infinity.developers.coreutils.Ui.Nova.Components.Background.NovaTopRightBackground
import infinity.developers.coreutils.Ui.Nova.Components.Background.NovaWhiteBackground
import infinity.developers.coreutils.Ui.Nova.Components.Button.NovaBlackButton
import infinity.developers.coreutils.Ui.Nova.Components.Card.NovaBlackCard
import infinity.developers.coreutils.Ui.Nova.Components.Doggle.NovaBlackDoggle
import infinity.developers.coreutils.Ui.Nova.Components.TextField.NovaBlackTextField
import infinity.developers.engineui.ui.theme.EngineUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EngineUITheme {
                Main()
            }
        }
    }
}
@Composable
fun Main(){
    NovaTopRightBackground()
    var a = remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        NovaBlackDoggle(){}
        NovaBlackTextField(
            value = a.value,
            onValueChange = {a.value = it}
        )

        NovaBlackCard(modifier = Modifier
            .height(100.dp)
            .width(100.dp)
        ) {

        }
        NovaBlackButton(
            onClick = {

            },
            text = "Submit",
            modifier = Modifier.fillMaxWidth()
        )
    }
}
