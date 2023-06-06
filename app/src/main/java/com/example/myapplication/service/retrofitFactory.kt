package com.example.myapplication.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class retrofitFactory{
    private val BASE_URL = "https://tired-slug-hat.cyclic.app/v1/lion-school/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCoursesService(): CourseService{
        return retrofitFactory.create(CourseService::class.java)
    }
    fun getStudentsService(): StudentService{
        return  retrofitFactory.create(StudentService::class.java)
    }

    fun getScoreAluno(): ScoreService{
        return  retrofitFactory.create(ScoreService::class.java)
    }

}