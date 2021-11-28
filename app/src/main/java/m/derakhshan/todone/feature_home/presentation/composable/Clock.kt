package m.derakhshan.todone.feature_home.presentation.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.rotate
import m.derakhshan.todone.ui.theme.*


@Composable
fun Clock(clock: HomeClock, modifier: Modifier = Modifier) {

    val second = clock.second
    val minute = clock.minute
    val hour = clock.hour

    val clockBackgroundColor = Gray
    val clockLittleCircleColor = Red
    val clockLineDefaultColor = if (isSystemInDarkTheme()) Black else DarkGray
    val clockLinePassedColor = if (isSystemInDarkTheme()) DarkBlue else Blue
    val clockHourHandColor = Red
    val clockMinuteHandColor = DarkBlue
    val clockSecondHandColor = Blue

    Box(modifier = modifier) {

        Canvas(modifier = Modifier
            .fillMaxSize(), onDraw = {
            drawCircle(
                color = clockBackgroundColor,
                radius = size.minDimension / 2.5f,
                center = Offset(x = size.width / 2f, y = size.height / 2f)
            )

            //--------------------(clock around lines)--------------------//
            for (i in 1..60) {
                rotate(
                    i * 6.0f,
                    pivot = Offset(x = size.width / 2f, y = size.height / 2f)
                ) {
                    drawRect(
                        topLeft = Offset(x = size.width / 2f, 0f),
                        color = if (i <= second || i == 60) clockLinePassedColor else clockLineDefaultColor,
                        size = Size(
                            width = size.minDimension / 100f,
                            height = size.minDimension / 15f
                        )
                    )
                }
            }

            //--------------------(second hand)--------------------//
            rotate(
                degrees = second * 6f,
                pivot = Offset(x = size.width / 2f, y = size.height / 2f)
            ) {
                drawRect(
                    color = clockSecondHandColor,
                    topLeft = Offset(
                        x = size.width / 2f,
                        size.height / 2 - size.minDimension / 2.0f
                    ),
                    size = Size(
                        width = size.minDimension / 100f,
                        height = size.height / 2.00f
                    ),
                )
            }

            //--------------------(minute hand)--------------------//
            rotate(
                degrees = minute * 6f,
                pivot = Offset(x = size.width / 2f, y = size.height / 2f)
            ) {
                drawRect(
                    color = clockMinuteHandColor,
                    topLeft = Offset(
                        x = size.width / 2f,
                        size.height / 2 - size.minDimension / 2.8f
                    ),
                    size = Size(
                        width = size.minDimension / 100f,
                        height = size.height / 2.80f
                    ),
                )
            }

            //--------------------(hour hand)--------------------//
            rotate(
                degrees = (hour % 12) * 30 + minute * 0.5f,
                pivot = Offset(x = size.width / 2f, y = size.height / 2f)
            ) {
                drawRect(
                    color = clockHourHandColor,
                    topLeft = Offset(
                        x = size.width / 2f,
                        size.height / 2 - size.minDimension / 3.5f
                    ),
                    size = Size(
                        width = size.minDimension / 100f,
                        height = size.height / 3.5f
                    ),
                )
            }

            //--------------------(little center circle)--------------------//
            drawCircle(
                color = clockLittleCircleColor,
                radius = size.minDimension / 30f,
                center = Offset(x = size.width / 2f, y = size.height / 2f)
            )


        })
    }

}