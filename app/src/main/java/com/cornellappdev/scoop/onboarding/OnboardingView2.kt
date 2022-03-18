package com.cornellappdev.scoop.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.scoop.components.DenseTextField
import com.cornellappdev.scoop.components.RightArrow

@Composable
fun OnboardingView2(){

    val (nameText, setNameText) = rememberSaveable { mutableStateOf("") }
    val (pronounsText, setPronounsText) = rememberSaveable { mutableStateOf("") }
    val (hometownText, setHometownText) = rememberSaveable { mutableStateOf("") }
    val (yearText, setYearText) = rememberSaveable { mutableStateOf("") }

    Scaffold(

        modifier = Modifier.padding(
            start = 40.dp,
            end = 40.dp,
        )
            .fillMaxWidth()
    ) {

        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Spacer(modifier = Modifier.weight(1F))

            Box(){
                Column(Modifier.fillMaxWidth(),) {
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = "Name",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(
                            bottom = 10.dp
                        )
                    )
                    DenseTextField(value = nameText, setValue = setNameText, placeholderText = "Enter Name")
                }

            }

            Box(Modifier.fillMaxWidth()){
                Column() {
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = "Pronouns",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(
                            bottom = 10.dp
                        )
                    )
                    DenseTextField(value = pronounsText, setValue = setPronounsText, placeholderText = "Enter Pronouns")
                }

            }

            Box(Modifier.fillMaxWidth()){

                Column() {
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = "Name",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(
                            bottom = 10.dp
                        )
                    )
                    DenseTextField(value = hometownText, setValue = setHometownText, placeholderText = "Enter Hometown")
                }

            }

            Box(Modifier.fillMaxWidth()){
                Column() {
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = "Class Year",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(
                            bottom = 10.dp
                        )
                    )
                    DenseTextField(value = yearText, setValue = setYearText, placeholderText = "Enter Class Year")
                }

            }

            Box(

                modifier = Modifier.align(Alignment.End)
            ){
                RightArrow()
            }

            Spacer(modifier = Modifier.weight(1F))

        }
    }
}


@Preview
@Composable
fun PreviewOnboard(){
    OnboardingView2()
}