import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.components.RightArrow
import com.cornellappdev.scoop.onboarding.OnboardingFooter
import com.cornellappdev.scoop.ui.theme.Gray
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState


@OptIn(ExperimentalPagerApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun OnboardingProfile(pagerState: PagerState) {

    Column() {

        Column(
            modifier = Modifier
                .padding(
                    start = 40.dp,
                    end = 40.dp,
                )
                .fillMaxWidth(),
        ) {

            Column(
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1F))
                WelcomeText()
                ProfileImage()
                Box(
                    modifier = Modifier
                        .padding(
                            start = 200.dp,
                            top = 50.dp,
                        ),
                ) {
                    RightArrow(pagerState)
                }
                Spacer(modifier = Modifier.weight(1F))
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileImage() {

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    if (imageUri == null) {
        Box(
        ) {
            Card(
                modifier = Modifier.size(120.dp),
                shape = CircleShape,
                elevation = 2.dp,
                backgroundColor = Gray,
            ) {}

            Card(
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.BottomEnd),
                shape = CircleShape,
                elevation = 2.dp,
                backgroundColor = Gray,
                border = BorderStroke(width = 2.dp, color = Color.White),
                onClick = {
                    launcher.launch("image/*")
                }
            ) {
                Image(
                    painterResource(R.drawable.ic_baseline_add_24),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    } else {

        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)

            } else {
                val source = ImageDecoder
                    .createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->
                Box(
                ) {
                    Card(
                        modifier = Modifier.size(120.dp),
                        shape = CircleShape,
                        elevation = 2.dp,
                        backgroundColor = Gray
                    ) {
                        Image(
                            bitmap = btm.asImageBitmap(),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Card(
                        modifier = Modifier
                            .size(25.dp)
                            .align(Alignment.BottomEnd),
                        shape = CircleShape,
                        elevation = 2.dp,
                        backgroundColor = Gray,
                        border = BorderStroke(width = 2.dp, color = Color.White),
                        onClick = {
                            launcher.launch("image/*")
                        }
                    ) {
                        Image(
                            painterResource(R.drawable.ic_baseline_add_24),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }


                }
            }
        }

    }

}

@Composable
fun WelcomeText(
) {
    Card(
        border = BorderStroke(width = 1.dp, color = Gray),
        shape = RoundedCornerShape(14.dp),
        backgroundColor = Gray,
        modifier = Modifier
            .padding(
                bottom = 20.dp
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .width(150.dp)
        ) {
            Text(
                text = "Hi Lia! Let’s begin by adding a profile picture. "
            )
        }
    }

}
