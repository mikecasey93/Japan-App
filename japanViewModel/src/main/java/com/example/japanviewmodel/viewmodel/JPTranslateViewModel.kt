package com.example.japanviewmodel.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.ExperimentalCoroutinesApi

class JPTranslateViewModel: ViewModel() {

    private val _translationResult = MutableStateFlow("")
    val translationResult: StateFlow<String> = _translationResult

    fun translateText(input: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _translationResult.value = "Translating..."
            _translationResult.value = performTranslation(input)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun performTranslation(text: String): String = withContext(Dispatchers.IO) {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.JAPANESE)
            .build()

        val client = Translation.getClient(options)

        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()

        try {
            suspendCancellableCoroutine { cont ->
                Log.d("TranslationHelper", "Starting model download...")
                client.downloadModelIfNeeded(conditions)
                    .addOnSuccessListener {
                        Log.d("TranslationHelper", "Model downloaded.")
                        cont.resume(Unit) {}
                    }
                    .addOnFailureListener {
                        Log.e("TranslationHelper", "Model download failed", it)
                        cont.resumeWith(Result.failure(it))
                    }
            }

            suspendCancellableCoroutine { cont ->
                Log.d("TranslationHelper", "Starting translation...")
                client.translate(text)
                    .addOnSuccessListener {
                        Log.d("TranslationHelper", "Translation successful: $it")
                        cont.resume(it) {}
                    }
                    .addOnFailureListener {
                        Log.e("TranslationHelper", "Translation failed", it)
                        cont.resumeWith(Result.failure(it))
                    }
            }
        } catch (e: Exception) {
            Log.e("Translate VM", "Translation failed: ${e.localizedMessage}")
            "Translation failed"
        }
    }
}