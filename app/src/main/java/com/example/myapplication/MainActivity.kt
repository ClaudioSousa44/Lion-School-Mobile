package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val context = LocalContext.current
    Column(
        modifier =
        Modifier.background(Color(51,71,176))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 27.dp, top = 20.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                          var openCurses = Intent(context, CursesActivityy::class.java)
                    context.startActivity(openCurses)
                },
                colors = ButtonDefaults.buttonColors(Color(229,182,87)),
                modifier = Modifier
                    .width(83.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.start),
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(161.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .width(150.dp)
                    .height(169.17.dp)
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(85.dp))

            Card(
                modifier = Modifier
                    .height(2.dp)
                    .width(302.dp),
                backgroundColor = Color(229,182,87)
            ) {}

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = stringResource(id = R.string.text_initial1 ),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp

            )
            Text(
                text = stringResource(id = R.string.text_initial2 ),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = stringResource(id = R.string.text_initial3),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting()
    }
}