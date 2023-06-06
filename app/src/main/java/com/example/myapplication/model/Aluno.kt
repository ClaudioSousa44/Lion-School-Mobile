package com.example.myapplication.model

data class Aluno (
    val foto: String? = null,
    val nome: String? = null,
    val status: String? = null,
    val curso: List<CursoList>? = null
        )

