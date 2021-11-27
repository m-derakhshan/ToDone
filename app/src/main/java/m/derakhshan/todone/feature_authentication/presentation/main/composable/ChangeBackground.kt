package m.derakhshan.todone.feature_authentication.presentation.main.composable


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import m.derakhshan.todone.feature_authentication.presentation.util.mirror
import m.derakhshan.todone.ui.theme.VeryLightBlue


@ExperimentalAnimationApi
@Composable
fun ChangeBackground(show: Boolean, color: Color = VeryLightBlue, backListener: () -> Unit) {
    val radius by animateFloatAsState(
        targetValue = if (show) 1500f else 0f,
        animationSpec = tween(durationMillis = 700)
    )
    Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
        val rect = Rect(Offset.Zero, size)
        drawCircle(
            color = color,
            radius = radius,
            center = rect.center
        )
    })
    AnimatedVisibility(
        visible = show, enter = slideInVertically(),
        exit = slideOutVertically()
    ) {
        IconButton(onClick = backListener) {
            Icon(
                imageVector = Icons.Filled.ArrowBack, contentDescription = "back",
                modifier = Modifier.mirror()
            )
        }
    }


}