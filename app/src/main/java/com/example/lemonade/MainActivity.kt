package com.example.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.foundation.clickable

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {

                var currentStep by remember { mutableIntStateOf(1) }
                var squeezeCount by remember { mutableIntStateOf(0) }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Lemonade",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFFFFEB3B)
                            )
                        )
                    }
                ) { innerPadding ->

                    val imageRes = when (currentStep) {
                        1 -> R.drawable.lemon_tree
                        2 -> R.drawable.lemon_squeeze
                        3 -> R.drawable.lemon_drink
                        else -> R.drawable.lemon_restart
                    }

                    val textRes = when (currentStep) {
                        1 -> R.string.lemon_tree
                        2 -> R.string.lemon
                        3 -> R.string.glass_of_lemonade
                        else -> R.string.empty_glass
                    }

                    val contentDescRes = when (currentStep) {
                        1 -> R.string.lemon_tree_cd
                        2 -> R.string.lemon_cd
                        3 -> R.string.lemonade_cd
                        else -> R.string.empty_glass_cd
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Box(
                            modifier = Modifier
                                .background(Color(0xFFC8E6C9), shape = RoundedCornerShape(24.dp))
                                .padding(24.dp)
                        ) {
                            Image(
                                painter = painterResource(id = imageRes),
                                contentDescription = stringResource(contentDescRes),
                                modifier = Modifier.clickable {
                                    when (currentStep) {
                                        1 -> {
                                            currentStep = 2
                                            squeezeCount = (2..4).random()
                                        }
                                        2 -> {
                                            squeezeCount--
                                            if (squeezeCount == 0) {
                                                currentStep = 3
                                            }
                                        }
                                        3 -> {
                                            currentStep = 4
                                        }
                                        4 -> {
                                            currentStep = 1
                                        }
                                    }
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = stringResource(textRes),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
        }
    }

private fun MainActivity.Scaffold(
    modifier: Any,
    content: (PaddingValues) -> Unit
) {
}




