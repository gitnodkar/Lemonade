package com.vedant.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vedant.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Scaffold(modifier = Modifier, topBar = { //A Scaffold container for defining the TopAppBar using title and colors subparameters
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Lemonade",
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Yellow)
        )
    }) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorScheme.background
        ) {
            when (currentStep) {
                1 -> {
                    LemonTextAndImage(
                        textLabel = R.string.One,
                        drawableResource = R.drawable.lemon_tree,
                        contentDescription = R.string.Lemon,
                        onImageClick = {
                            currentStep = 2
                            squeezeCount = (2..4).random()
                        }
                    )
                }

                2 -> {
                    LemonTextAndImage(
                        textLabel = R.string.Two,
                        drawableResource = R.drawable.lemon_squeeze,
                        contentDescription = R.string.Lemontree,
                        onImageClick = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                currentStep = 3
                            }

                        }
                    )
                }

                3 -> {
                    LemonTextAndImage(
                        textLabel = R.string.Three,
                        drawableResource = R.drawable.lemon_drink,
                        contentDescription = R.string.Glassfull,
                        onImageClick = { currentStep = 4 })

                }

                4 -> {
                    LemonTextAndImage(
                        textLabel = R.string.Four,
                        drawableResource = R.drawable.lemon_restart,
                        contentDescription = R.string.Glassempty,
                        onImageClick = { currentStep = 1 })

                }
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabel: Int,
    drawableResource: Int,
    contentDescription: Int,
    onImageClick: () -> Unit, //it has no datatype and returns nothing.
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.lemon_background),
                contentScale = ContentScale.Crop
            )
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorScheme.tertiaryContainer)
        ) {
            Image(
                painter = painterResource(drawableResource),
                contentDescription = stringResource(contentDescription),
                modifier = Modifier.wrapContentSize())
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(textLabel),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonApp()
    }
}