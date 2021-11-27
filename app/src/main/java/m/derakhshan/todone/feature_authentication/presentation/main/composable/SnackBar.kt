package m.derakhshan.todone.feature_authentication.presentation.main.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.dp
import m.derakhshan.todone.ui.theme.LightBlack


@Composable
fun ShowSnackBar(msg: String) {

    if (msg.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(5.dp), onDraw = {
                val rect = Rect(Offset.Zero, size)
                drawRoundRect(
                    color = LightBlack,
                    topLeft = rect.topLeft,
                    size = rect.size,
                    cornerRadius = CornerRadius(x = 10f, y = 10f),
                    alpha = 0.50f
                )
            })
            Text(text = msg, modifier = Modifier.padding(15.dp))
        }
    }
}
