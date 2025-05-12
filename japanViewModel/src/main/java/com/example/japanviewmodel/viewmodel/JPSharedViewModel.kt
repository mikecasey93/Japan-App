package com.example.japanviewmodel.viewmodel

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.Locale

class JPSharedViewModel: ViewModel() {

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

    override fun onCleared() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        super.onCleared()
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
}