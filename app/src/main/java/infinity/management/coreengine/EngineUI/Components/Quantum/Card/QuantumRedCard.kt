package infinity.management.coreengine.EngineUI.Components.Quantum.Card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import infinity.management.coreengine.EngineUI.Utils.RedBorderColor

@Composable
fun QuantumRedCard(
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
                    shape = RoundedCornerShape(20.dp)
                )
        ){
            Column(content = content)
        }
    }
}