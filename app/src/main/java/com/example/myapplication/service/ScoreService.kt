package com.example.myapplication.service

import com.example.myapplication.model.Aluno
import com.example.myapplication.model.AlunoList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ScoreService {
    @GET("alunos/{matricula}")
    fun getAlunosByMatricula(@Path("matricula") matricula: String): Call<AlunoList>
}