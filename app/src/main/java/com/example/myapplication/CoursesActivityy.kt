package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.Courses
import com.example.myapplication.model.CoursesList
import com.example.myapplication.service.retrofitFactory
import com.example.myapplication.service.CourseService
import com.example.myapplication.ui.theme.MyApplicationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import coil.compose.AsyncImage

class CursesActivityy : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CursesScreen()
                }
            }
        }
    }
}


@Composable
fun CursesScreen() {
    val context = LocalContext.current
    var listCourses by remember {
        mutableStateOf(listOf<Courses>())
    }
    var curseState by remember{
        mutableStateOf("")
    }
    val call = retrofitFactory().getCoursesService().getCursos()
    //Executar a chamada
    call.enqueue(object : Callback<CoursesList>{
        override fun onResponse(
            call: Call<CoursesList>,
            response: Response<CoursesList>
        ) {
            listCourses = response.body()!!.cursos

        }

        override fun onFailure(call: Call<CoursesList>, t: Throwable) {

        }

    })
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderShape()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = curseState ,
                onValueChange = { curseState = it },
                label = {Text(
                    text = stringResource(id = R.string.find_your_curses ),
                    color = Color(51,71,176)
                )},
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = "Search",
                        tint = Color(51,71,176)
                    )
                },
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = stringResource(id = R.string.Curses),
                color = Color(51,71,176),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )

        }

            LazyColumn(){
                items(listCourses){
                    Button(
                        onClick = {
                            var openStudents = Intent(context, StudentActivity::class.java)
                            openStudents.putExtra("sigla", it.sigla)
                            context.startActivity(openStudents)
                        },
                        modifier = Modifier
                            .height(129.dp)
                            .width(327.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(Color(51,71,176))
                    ) {

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = it.nome,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(25.dp))

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        model = it.icone,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .clip(shape = CircleShape)
                                            .width(80.dp)

                                    )
                                    Spacer(modifier = Modifier.width(80.dp))

                                    Text(
                                        text = it.sigla,
                                        color = Color(229,182,87),
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                }

                            }
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                }
            }

        FooterShape()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    MyApplicationTheme {
        CursesScreen()
    }
}

