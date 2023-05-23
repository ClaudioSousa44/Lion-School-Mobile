package com.example.myapplication.service
import com.example.myapplication.model.CoursesList
import retrofit2.Call
import retrofit2.http.GET

interface CourseService{
    @GET("cursos")
    fun getCursos(): Call<CoursesList>
}