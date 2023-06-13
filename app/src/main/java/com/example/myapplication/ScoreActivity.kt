package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.model.*
import com.example.myapplication.service.retrofitFactory
import com.example.myapplication.ui.theme.MyApplicationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScoreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dado = intent.getStringExtra("numeroMatricula")
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    ScoreScreen(dado.toString())
                }
            }
        }
    }
}

@Composable
fun ScoreScreen(matricula: String){


    var alunoScore by remember {
        mutableStateOf(StudentScore("","","","", emptyList()))
    }


    val call = retrofitFactory().getScoreAluno().getAlunosByMatricula(matricula)

    call.enqueue(object : Callback<StudentScore> {
        override fun onResponse(
            call: Call<StudentScore>,
            response: Response<StudentScore>
        ) {
            if(response.isSuccessful){
                val studentResponse = response.body()
                if(studentResponse != null){
                    alunoScore = studentResponse
                }
            }


            //listCurso = response.body()!!.curso
           // fotoAluno = response.body()!!.foto
           // statusAluno2 = response.body()!!.status


        }
        override fun onFailure(call: Call<StudentScore>, t: Throwable) {

        }
    })

    Log.i("TAG", "ScoreScreen: ${alunoScore}")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderShape()
        Spacer(modifier = Modifier.height(28.dp))
        Surface(
            modifier = Modifier
                .height(589.dp)
                .width(327.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(getColorStatus(alunoScore.status)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = alunoScore.foto ,
                    contentDescription = ""
                )
                Text(
                    text = alunoScore.nome,
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))


                Card(
                    modifier = Modifier
                        .height(340.dp)
                        .width(256.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        items(alunoScore.disciplinas){
                           Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(
                                    text = it.sigla,
                                    color = getColorScore(it.media.toDouble()),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold

                                )
                                Spacer(modifier = Modifier.width(25.dp))
                                Surface(

                                ) {
                                    Card(
                                        modifier = Modifier
                                            .height(15.dp)
                                            .width(100.dp),
                                        backgroundColor = Color.Gray
                                    ) {}
                                    Card(
                                        modifier = Modifier
                                            .height(15.dp)
                                            .width(it.media.toDouble().dp),
                                        backgroundColor = getColorScore(it.media.toDouble())
                                    ) {}

                                }

                                Spacer(modifier = Modifier.width(25.dp))
                                Text(
                                    text = it.media,
                                    color = getColorScore(it.media.toDouble()),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }


            }
        }

    }
}

fun getColorScore (nota: Double): Color {
    return if (nota > 69){
        Color(51, 71, 176)
    }else if(nota > 49 && nota < 70){
        Color(229,182,87)
    }else{
        Color(193,16,16)
    }
}


