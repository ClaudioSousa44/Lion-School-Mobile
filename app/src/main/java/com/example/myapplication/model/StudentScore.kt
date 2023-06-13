package com.example.myapplication.model

data class StudentScore(
    var nome: String,
    var foto: String,
    var matricula: String,
    var status: String,
    var disciplinas: List<Disciplina>
)