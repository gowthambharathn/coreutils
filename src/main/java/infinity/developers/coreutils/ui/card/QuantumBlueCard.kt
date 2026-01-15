package infinity.developers.coreutils.ui.card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import infinity.developers.coreutils.utils.BlueBorderColor

@Composable
fun QuantumBlueCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()   // 🔑 THIS
                .clip(RoundedCornerShape(20.dp))
                .border(
                    1.dp,
                    BlueBorderColor.copy(alpha = 0.3f),
                    RoundedCornerShape(20.dp)
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(), // 🔑 AND THIS
                content = content
            )
        }
    }
}
