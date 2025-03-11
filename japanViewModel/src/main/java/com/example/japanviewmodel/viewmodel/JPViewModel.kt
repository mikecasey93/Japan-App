package com.example.japanviewmodel.viewmodel

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class JPViewModel: ViewModel() {

    private val _alphabet = hashMapOf(
        "HIRAGANA" to listOf(
            Pair("A","あ"), Pair("I","い"), Pair("U","う"), Pair("E","え"), Pair("O","お"),
            Pair("KA","か"), Pair("KI","き"), Pair("KU","く"), Pair("KE","け"), Pair("KO","こ"),
            Pair("SA","さ"), Pair("SHI","し"), Pair("SU","す"), Pair("SE","せ"), Pair("SO","そ"),
            Pair("TA","た"), Pair("CHI","ち"), Pair("TSU","つ"), Pair("TE","て"), Pair("TO","と"),
            Pair("NA","な"), Pair("NI","に"), Pair("NU","ぬ"), Pair("NE","ね"), Pair("NO","の"),
            Pair("HA","は"), Pair("HI","ひ"), Pair("FU","ふ"), Pair("HE","へ"), Pair("HO","ほ"),
            Pair("MA","ま"), Pair("MI","み"), Pair("MU","む"), Pair("ME","め"), Pair("MO","も"),
            Pair("RA","ら"), Pair("RI","り"), Pair("RU","る"), Pair("RE","れ"), Pair("RO","ろ"),
            Pair("YA","や"), Pair("YU","ゆ"), Pair("YO","よ"),
            Pair("WA","わ"), Pair("O","を"), Pair("N","ん"),
            Pair("GA","か゛"), Pair("GI","き゛"), Pair("GU","く゛"), Pair("GE","け゛"), Pair("GO","こ゛"),
            Pair("ZA","さ゛"), Pair("JI","し゛"), Pair("ZU","す゛"), Pair("ZE","せ゛"), Pair("ZO","そ゛"),
            Pair("DA","た゛"), Pair("JI","ち゛"), Pair("ZU","つ゛"), Pair("DE","て゛"), Pair("DO","と゛"),
            Pair("BA","は゛"), Pair("BI","ひ゛"), Pair("BU","ふ゛"), Pair("BE","へ゛"), Pair("BO","ほ゛"),
            Pair("PA","は゜"), Pair("PI","ひ゜"), Pair("PU","ふ゜"), Pair("PE","へ゜"), Pair("PO","ほ゜"),
            Pair("KYA","きや"), Pair("KYU","きゆ"), Pair("KYO","きよ"),
            Pair("GYA","き゛や"), Pair("GYU","き゛ゆ"), Pair("GYO","き゛よ"),
            Pair("SHA","しや"), Pair("SHU","しゆ"), Pair("SHO","しよ"),
            Pair("JA","し゛や"), Pair("JU","し゛ゆ"), Pair("JO","し゛よ"),
            Pair("CHA","ちや"), Pair("CHU","ちゆ"), Pair("CHO","ちよ"),
            Pair("NYA","にや"), Pair("NYU","にゆ"), Pair("NYO","によ"),
            Pair("HYA","ひや"), Pair("HYU","ひゆ"), Pair("HYO","ひよ"),
            Pair("BYA","ひ゛や"), Pair("BYU","ひ゛ゆ"), Pair("BYO","ひ゛よ"),
            Pair("PYA","ひ゜や"), Pair("PYU","ひ゜ゆ"), Pair("PYO","ひ゜よ"),
            Pair("MYA","みや"), Pair("MYU","みゆ"), Pair("MYO","みよ"),
            Pair("RYA","りや"), Pair("RYU","りゆ"), Pair("RYO","りよ"),
        ),
        "KATAKANA" to listOf(
            Pair("A","ア"), Pair("I","イ"), Pair("U","ウ"), Pair("E","エ"), Pair("O","オ"),
            Pair("KA","カ"), Pair("KI","キ"), Pair("KU","ク"), Pair("KE","ケ"), Pair("KO","コ"),
            Pair("SA","サ"), Pair("SHI","シ"), Pair("SU","ス"), Pair("SE","セ"), Pair("SO","ソ"),
            Pair("TA","タ"), Pair("CHI","チ"), Pair("TSU","ツ"), Pair("TE","テ"), Pair("TO","ト"),
            Pair("NA","ナ"), Pair("NI","ニ"), Pair("NU","ヌ"), Pair("NE","ネ"), Pair("NO","ノ"),
            Pair("HA","ハ"), Pair("HI","ヒ"), Pair("FU","フ"), Pair("HE","ヘ"), Pair("HO","ホ"),
            Pair("MA","マ"), Pair("MI","ミ"), Pair("MU","ム"), Pair("ME","メ"), Pair("MO","モ"),
            Pair("RA","ラ"), Pair("RI","リ"), Pair("RU","ル"), Pair("RE","レ"), Pair("RO","ロ"),
            Pair("YA","ヤ"), Pair("YU","ユ"), Pair("YO","ヨ"),
            Pair("WA","ワ"), Pair("WO","ヲ"), Pair("N","ン"),
            Pair("GA","カ゛"), Pair("GI","キ゛"), Pair("GU","ク゛"), Pair("GE","ケ゛"), Pair("GO","コ゛"),
            Pair("ZA","サ゛"), Pair("JI","シ゛"), Pair("ZU","ス゛"), Pair("ZE","セ゛"), Pair("ZO","ソ゛"),
            Pair("DA","タ゛"), Pair("JI","チ゛"), Pair("ZU","ツ゛"), Pair("DE","テ゛"), Pair("DO","ト゛"),
            Pair("BA","ハ゛"), Pair("BI","ヒ゛"), Pair("BU","フ゛"), Pair("BE","ヘ゛"), Pair("BO","ホ゛"),
            Pair("PA","ハ゜"), Pair("PI","ヒ゜"), Pair("PU","フ゜"), Pair("PE","ヘ゜"), Pair("PO","ホ゜"),
            Pair("KYA","キヤ"), Pair("KYU","キユ"), Pair("KYO","キヨ"),
            Pair("GYA","キ゛ヤ"), Pair("GYU","キ゛ユ"), Pair("GYO","キ゛ヨ"),
            Pair("SHA","シヤ"), Pair("SHU","シユ"), Pair("SHO","シヨ"),
            Pair("JA","シ゛ヤ"), Pair("JU","シ゛ユ"), Pair("JO","シ゛ヨ"),
            Pair("CHA","チヤ"), Pair("CHU","チユ"), Pair("CHO","チヨ"),
            Pair("NYA","ニヤ"), Pair("NYU","ニユ"), Pair("NYO","ニヨ"),
            Pair("HYA","ヒヤ"), Pair("HYU","ヒユ"), Pair("HYO","ヒヨ"),
            Pair("BYA","ヒ゛ヤ"), Pair("BYU","ヒ゛ユ"), Pair("BYO","ヒ゛ヨ"),
            Pair("PYA","ヒ゜ヤ"), Pair("PYU","ヒ゜ユ"), Pair("PYO","ヒ゜ヨ"),
            Pair("MYA","ミヤ"), Pair("MYU","ミユ"), Pair("MYO","ミヨ"),
            Pair("RYA","リヤ"), Pair("RYU","リユ"), Pair("RYO","リヨ"),
        )
    )

    private val _selectedScript = MutableStateFlow(_alphabet.keys.random())
    val selectedScript: StateFlow<String> = _selectedScript.asStateFlow()

    private val _selectedPair = MutableStateFlow(_alphabet[_selectedScript.value]?.random() ?: Pair("?", "?"))
    val selectedPair: StateFlow<Pair<String, String>> = _selectedPair.asStateFlow()

    private val _answerStatus = MutableStateFlow("")
    val answerStatus: StateFlow<String> = _answerStatus.asStateFlow()

    private val _hiragana = MutableStateFlow(_alphabet["HIRAGANA"])
    val hiragana: StateFlow<List<Pair<String, String>>?> = _hiragana.asStateFlow()

    private val _katakana = MutableStateFlow(_alphabet["KATAKANA"])
    val katakana: StateFlow<List<Pair<String, String>>?> = _katakana.asStateFlow()

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    private val _scoreMessage = MutableStateFlow("")
    val scoreMessage: StateFlow<String> = _scoreMessage.asStateFlow()

    private val _textInput = MutableStateFlow("")
    val textInput: StateFlow<String> = _textInput.asStateFlow()

    private var textToSpeech: TextToSpeech? = null

    fun initTextToSpeech(context: Context) {
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.language = Locale.JAPANESE
                textToSpeech?.setSpeechRate(1.0f)
                Log.d("TTS", "Text To Speech Working")
            } else {
                Log.e("TTS", "Text To Speech Failed")
            }
        }
    }

    fun speakText(text: String) {
        if (text.isBlank()) return

        textToSpeech?.speak(
            text,
            TextToSpeech.QUEUE_FLUSH,
            null,
            null,
        )
        Log.d("TTS", "Speaking: $text")
    }

    override fun onCleared() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        super.onCleared()
    }

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