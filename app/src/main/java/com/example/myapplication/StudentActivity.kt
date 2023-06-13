package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ToggleButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.model.Student
import com.example.myapplication.model.StudentList
import com.example.myapplication.service.retrofitFactory
import com.example.myapplication.ui.theme.MyApplicationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StudentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dado = intent.getStringExtra("sigla")
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    StudentScreen(dado.toString())
                }
            }
        }
    }
}

@Composable
fun StudentScreen(sigla: String){
    val context = LocalContext.current

    var listStudent by remember {
        mutableStateOf(listOf<Student>())
    }
    var listStudent2 by remember {
        mutableStateOf(listOf<Student>())
    }


    var name by remember {
        mutableStateOf("")
    }

    val call = retrofitFactory().getStudentsService().getAlunosByCourse(sigla)

    call.enqueue(object : Callback<StudentList> {
        override fun onResponse(
            call: Call<StudentList>,
            response: Response<StudentList>
        ) {
            listStudent = response.body()!!.aluno
            listStudent2 = response.body()!!.aluno

            name = response.body()!!.NomeCurso


        }

        override fun onFailure(call: Call<StudentList>, t: Throwable) {

        }
    })


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HeaderShape()
        Column(
            modifier = Modifier
                .fillMaxSize()
               // .verticalScroll(rememberScrollState())
                //.weight(weight = 1f),
           , horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row() {
                Button(
                    onClick = {

                              listStudent2 = listStudent
                    },
                    colors = ButtonDefaults.buttonColors(Color(51,71,176))
                ) {
                    Text(
                        text = "Todos",
                        color = Color.White
                    )
                }
                Button(
                    onClick = {
                              listStudent2 = listStudent.filter { it.status == "Cursando" }
                    },
                    colors = ButtonDefaults.buttonColors(Color(51,71,176))
                ) {
                    Text(
                        text = "Cursando",
                        color = Color.White
                    )
                }
                Button(
                    onClick = {
                              listStudent2 = listStudent.filter { it.status == "Finalizado" }
                    },
                    colors = ButtonDefaults.buttonColors(Color(51,71,176))
                ) {
                    Text(
                        text = "Finalizado",
                        color = Color.White
                    )
                }
            }
        Text(
            text = name,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(51,71,176),
            textAlign = TextAlign.Center
        )
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(listStudent2){
                    Button(
                        onClick = {
                                  var openScore = Intent(context, ScoreActivity::class.java)
                                    openScore.putExtra("numeroMatricula", it.matricula)
                                    context.startActivity(openScore)
                                  },
                        modifier = Modifier
                            .height(129.dp)
                            .width(327.dp),
                        colors = ButtonDefaults.buttonColors(getColorStatus(it.status)),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Row( verticalAlignment = Alignment.CenterVertically) {
                            AsyncImage(
                                model = it.foto,
                                contentDescription = ""
                            )
                            Text(
                                text = it.nome,
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }


    }
}

fun getColorStatus(status: String): Color{
    return if(status != "Finalizado") {
        Color(51, 71, 176)
    }else{
        Color(229,182,87)
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun StudentScreenPreview(){
//    StudentScreen(dado.toString())
//}

