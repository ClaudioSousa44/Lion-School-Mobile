package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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


    var alunoList by remember {
        mutableStateOf(Aluno())
    }


    val call = retrofitFactory().getScoreAluno().getAlunosByMatricula(matricula)

    call.enqueue(object : Callback<AlunoList> {
        override fun onResponse(
            call: Call<AlunoList>,
            response: Response<AlunoList>
        ) {
            alunoList = response.body()!!.aluno

            //listCurso = response.body()!!.curso
           // fotoAluno = response.body()!!.foto
           // statusAluno2 = response.body()!!.status


        }
        override fun onFailure(call: Call<AlunoList>, t: Throwable) {

        }
    })

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderShape()
        Spacer(modifier = Modifier.height(28.dp))
        Surface(
            modifier = Modifier
                .height(528.dp)
                .width(327.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(getColorStatus("${alunoList.status}")),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = alunoList.foto ,
                    contentDescription = ""
                )
                Text(
                    text = "${alunoList.nome}",
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )



//                LazyColumn(){
//                    items(2){
//                        Text(text = "" )
//                    }
//                }
            }
        }

    }
}


