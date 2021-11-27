package m.derakhshan.todone.feature_authentication.presentation.main.composable

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.airbnb.lottie.compose.*
import m.derakhshan.todone.R
import m.derakhshan.todone.ui.theme.Gray


@Composable
fun LottieWelcome(modifier: Modifier = Modifier, alignment: Alignment = Alignment.TopCenter) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.welcome)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = progress,
        alignment = alignment
    )
}


@Composable
fun RoundedTriangle(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
    ) {

        val width = size.minDimension

        val path = Path().apply {
            moveTo(width / 2.0f, 0.0f)
            lineTo(width, width - 150)
            lineTo(0f, width - 150)
            lineTo(width / 2.0f, 0.0f)
            lineTo(width, width - 150)
        }
        drawIntoCanvas { canvas ->
            canvas.drawOutline(
                outline = Outline.Generic(path = path),
                paint = Paint().apply {
                    color = Gray
                    pathEffect = PathEffect.cornerPathEffect(width / 3f)
                }
            )
        }
    }
}
