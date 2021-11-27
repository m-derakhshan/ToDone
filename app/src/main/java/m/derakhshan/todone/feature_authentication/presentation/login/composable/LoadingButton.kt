package m.derakhshan.todone.feature_authentication.presentation.login.composable

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@Composable
fun LoadingButton(text: Int, expand: Boolean, clicked: () -> Unit) {

    val rotateDegree = if (expand) 0f else 360f

    val infiniteRotation = rememberInfiniteTransition()
    val rotate by infiniteRotation.animateFloat(
        initialValue = 0f,
        targetValue = rotateDegree,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val buttonWidth by animateDpAsState(
        targetValue = if (expand) 280.dp else 40.dp,
        animationSpec = tween(durationMillis = 100, easing = LinearEasing)
    )

    Button(
        onClick = clicked,
        modifier = Modifier
            .padding(vertical = 20.dp)
            .width(buttonWidth)
            .height(40.dp)
            .rotate(rotate)
    ) {
        Text(text = stringResource(id = text), modifier = Modifier.alpha(if (expand) 1f else 0f))
    }
}
