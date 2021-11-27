package m.derakhshan.todone.feature_authentication.presentation.main.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import m.derakhshan.todone.R
import m.derakhshan.todone.ui.theme.Gray

@Composable
fun Version(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(vertical = 15.dp), onDraw = {
                val rect = Rect(Offset.Zero, size = size)
                drawLine(
                    color = Gray,
                    start = rect.bottomLeft,
                    end = rect.bottomRight,
                    strokeWidth = 3f
                )
            })
        Text(
            text = stringResource(id = R.string.version),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .align(Alignment.BottomCenter)
                .padding(8.dp)
        )
    }
}
