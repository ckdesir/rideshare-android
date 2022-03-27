package com.cornellappdev.scoop.onboarding

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.components.DenseTextField
import com.cornellappdev.scoop.components.RightArrow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingView4(
    pagerState : PagerState
) {
    
    Scaffold() {
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OnboardingHeader(pagerState = pagerState, title = "During Roadtrips ...")

//            OnboardingHeader(pagerState = pagerState, "About You")
            Spacer(modifier = Modifier.weight(1F))

            Text(
                fontFamily = FontFamily.Default,
                text = "How talkative are you?",
                fontSize = 25.sp,
            )

//          First Slider
            var sliderPosition1 by remember { mutableStateOf(0F)}

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 10.dp,
                    bottom = 10.dp,
                )
            ) {
                VerticalLines(listOf("Quiet", " ", " ", "Talkative"))
                Slider(modifier = Modifier.fillMaxWidth(),
                    value = sliderPosition1,
                    onValueChange = {sliderPosition1 = it},
                    colors = customSliderColors()
                )
            }

            Text(
                fontFamily = FontFamily.Default,
                text = "Do you like music?",
                fontSize = 25.sp,
            )

            //          First Slider
            var sliderPosition2 by remember { mutableStateOf(0F)}

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 10.dp,
                    bottom = 10.dp,
                )
            ) {
                VerticalLines(listOf("No Music", " ", " ", "Music"))
                Slider(modifier = Modifier.fillMaxWidth(),
                    value = sliderPosition2,
                    onValueChange = {sliderPosition2 = it},
                    colors = customSliderColors()
                )
            }

            Spacer(modifier = Modifier.weight(1.25F))

        }

    }
    

}

@Composable
fun VerticalLines(dates: List<String>) {

    val drawPadding = with(LocalDensity.current) { 10.dp.toPx() }
    val textSize = with(LocalDensity.current) {15.dp.toPx() }
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
            val distance = (size.width.minus(2 * drawPadding)).div(dates.size.minus(1))
            dates.forEachIndexed { index, step ->
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
fun ForecastSlider(dates: List<String>, vararg sliderPositin: Float) {

    var sliderPosition by remember { mutableStateOf(0F)}

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.padding(
            start = 20.dp,
            end = 20.dp,
            top = 10.dp,
            bottom = 10.dp,
        )
    ) {
        VerticalLines(dates)
        Slider(modifier = Modifier.fillMaxWidth(),
            value = sliderPosition,
            onValueChange = {sliderPosition = it},
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