package com.example.japanviewmodel.viewmodel

import androidx.lifecycle.ViewModel
import com.example.japanviewmodel.data.DataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class JPGameViewModel: ViewModel() {

    private val _alphabet = DataSource.alphabet

    private val _selectedScript = MutableStateFlow(_alphabet.keys.random())
    val selectedScript: StateFlow<String> = _selectedScript.asStateFlow()

    private val _selectedPair = MutableStateFlow(_alphabet[_selectedScript.value]?.random() ?: Pair("?", "?"))
    val selectedPair: StateFlow<Pair<String, String>> = _selectedPair.asStateFlow()

    private val _answerStatus = MutableStateFlow("")
    val answerStatus: StateFlow<String> = _answerStatus.asStateFlow()

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    private val _scoreMessage = MutableStateFlow("")
    val scoreMessage: StateFlow<String> = _scoreMessage.asStateFlow()

    private val _textInput = MutableStateFlow("")
    val textInput: StateFlow<String> = _textInput.asStateFlow()

    fun verifyCharacter(input: String) {
        if (input.uppercase() == _selectedPair.value.first) {
            _answerStatus.value = "THAT'S CORRECT!! \uD83D\uDE03"
            _score.value += 1
            if (_score.value % 5 == 0) {
                _scoreMessage.value = "${_score.value} IN A ROW!!"
            }
        } else {
            _answerStatus.value = "SORRY TRY AGAIN \uD83D\uDE22"
            _score.value = 0
        }
    }

    fun randomPair() {
        _selectedScript.value = _alphabet.keys.random()

        val newPair = _alphabet[_selectedScript.value]!!.random()
        _selectedPair.value = newPair

        _answerStatus.value = ""
    }

    fun clearAnswerStatus() {
        _answerStatus.value = ""
    }

    fun clearScoreMessage() {
        _scoreMessage.value = ""
    }

    fun changeInput(newText: String) {
        _textInput.value = newText
    }

    fun resetTextInput() {
        _textInput.value = ""
    }
}