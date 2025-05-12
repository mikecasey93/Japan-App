package com.example.japanviewmodel.viewmodel

import androidx.lifecycle.ViewModel
import com.example.japanviewmodel.data.DataSource

class JPCharactersViewModel: ViewModel() {

    private val alphabet = DataSource.alphabet

    val hiragana: List<Pair<String, String>>? = alphabet["HIRAGANA"]
    val katakana: List<Pair<String, String>>? = alphabet["KATAKANA"]

}