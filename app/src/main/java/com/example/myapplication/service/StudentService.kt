package com.example.myapplication.service

import com.example.myapplication.model.StudentList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentService {
    @GET("alunos")
    fun getAlunosByCourse(@Query("curso") curso: String): Call<StudentList>
}