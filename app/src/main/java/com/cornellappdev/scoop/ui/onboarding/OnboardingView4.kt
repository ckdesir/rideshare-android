package com.cornellappdev.scoop.onboarding

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.components.RightArrow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingView4(
    pagerState: PagerState
) {

    Column(
        Modifier.background(Color.White)
    ) {

        OnboardingHeader(pagerState = pagerState, title = "During Roadtrips")

        Column(
            modifier = Modifier
                .padding(
                    start = 40.dp,
                    end = 40.dp,
                )
                .fillMaxWidth()
        ) {


            Column(
                modifier = Modifier
                    .height(500.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                var sliderPosition1 = remember { mutableStateOf(0F) }
                var sliderPosition2 = remember { mutableStateOf(0F) }
                Text(
                    fontFamily = FontFamily.Default,
                    text = "How talkative are you?",
                    fontSize = 20.sp,
                )

                OnboardingSlider(
                    values = listOf("Quiet", " ", " ", "Talkative"),
                    sliderPosition = sliderPosition1
                )

                Text(
                    fontFamily = FontFamily.Default,
                    text = "Do you like music?",
                    fontSize = 20.sp,
                )

                OnboardingSlider(
                    values = listOf("No Music", " ", " ", "Music"),
                    sliderPosition = sliderPosition2
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(20.dp)
            ) {
                RightArrow(pagerState)
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center,
            ) {
                OnboardingFooter(carIndex = pagerState.currentPage)
            }
        }
    }
}

@Composable
fun VerticalLines(values: List<String>) {

    val drawPadding = with(LocalDensity.current) { 10.dp.toPx() }
    val textSize = with(LocalDensity.current) { 15.dp.toPx() }
    val lineHeightDp = 10.dp
    val lineHeightPx = with(LocalDensity.current) { lineHeightDp.toPx() }
    val canvasHeight = 50.dp
    val textPaint = android.graphics.Paint().apply {
        color = if (isSystemInDarkTheme()) 0xffffffff.toInt() else 0xffff47586B.toInt()
        textAlign = android.graphics.Paint.Align.CENTER
        this.textSize = textSize
    }

    Box(contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier
                .height(canvasHeight)
                .fillMaxWidth()
                .padding(
                    top = canvasHeight
                        .div(2)
                        .minus(lineHeightDp.div(2))
                )
        ) {
            val yStart = 0f
            val distance = (size.width.minus(2 * drawPadding)).div(values.size.minus(1))
            values.forEachIndexed { index, step ->
                drawLine(
                    color = Color.Black,
                    start = Offset(x = drawPadding + index.times(distance), y = yStart),
                    end = Offset(x = drawPadding + index.times(distance), y = lineHeightPx),
                    strokeWidth = 8F,
                )
                if (index.rem(3) == 0) {
                    this.drawContext.canvas.nativeCanvas.drawText(
                        step,
                        drawPadding + index.times(distance),
                        size.height,
                        textPaint
                    )
                }
            }
        }
    }
}

@Composable
fun OnboardingSlider(values: List<String>, sliderPosition: MutableState<Float>) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(
            start = 20.dp,
            end = 20.dp,
            top = 10.dp,
            bottom = 10.dp,
        )
    ) {
        VerticalLines(values)
        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = sliderPosition.value,
            onValueChange = {
                sliderPosition.value = it
            },
            colors = customSliderColors()
        )
    }
}

@Composable
private fun customSliderColors(): SliderColors = SliderDefaults.colors(
    activeTickColor = Color.Transparent,
    inactiveTickColor = Color.Transparent,
    inactiveTrackColor = Color.Black,
    activeTrackColor = Color.Black,
    thumbColor = Color.Black
)
//
//
//@Preview
//@Composable
//fun PreviewOnboard(){
//    OnboardingView4()
//}