package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderShape() {
    Surface(
        modifier = Modifier
            .height(101.dp)
            .fillMaxWidth()

    ) {
        Column(
            modifier = Modifier.height(101.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color(51, 71, 176))
                    .height(96.dp),
                verticalArrangement = Arrangement.Center

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text = "Lion",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(19.dp))
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.height(50.dp)
                    )
                    Spacer(modifier = Modifier.width(19.dp))
                    Text(
                        text = "School",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(229, 182, 87))
                    .height(5.dp)
            ) {

            }
        }


    }
}

@Preview
@Composable
fun HeaderShapePreview() {
    HeaderShape()
}

@Composable
fun FooterShape(){
    Surface(
        modifier = Modifier
            .height(101.dp)
            .fillMaxWidth()
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(229, 182, 87))
                    .height(5.dp)
            ) {}

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
                    .background(Color(51, 71, 176))

            ) {

            }
        }
    }
}


@Preview
@Composable
fun FooterShapePreview(){
    FooterShape()
}
