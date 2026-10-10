package com.example.audio

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SoundManager(private val context: Context) {

    private var toneGenerator: ToneGenerator? = null
    private val vibrator: Vibrator? by lazy {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                vibratorManager?.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            }
        } catch (_: Exception) {
            null
        }
    }

    private val audioScope = CoroutineScope(Dispatchers.Default)

    init {
        try {
            toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 75)
        } catch (_: Exception) {
            toneGenerator = null
        }
    }

    fun playCorrect(soundEnabled: Boolean = true, hapticsEnabled: Boolean = true) {
        if (hapticsEnabled) {
            vibrate(longArrayOf(0, 60, 60, 100))
        }
        if (!soundEnabled) return

        audioScope.launch {
            try {
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 120)
                delay(140)
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP2, 220)
            } catch (_: Exception) {}
        }
    }

    fun playIncorrect(soundEnabled: Boolean = true, hapticsEnabled: Boolean = true) {
        if (hapticsEnabled) {
            vibrate(longArrayOf(0, 150))
        }
        if (!soundEnabled) return

        audioScope.launch {
            try {
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_NACK, 280)
            } catch (_: Exception) {}
        }
    }

    fun playTimerTick(soundEnabled: Boolean = true, hapticsEnabled: Boolean = true) {
        if (hapticsEnabled) {
            vibrate(longArrayOf(0, 20))
        }
        if (!soundEnabled) return

        audioScope.launch {
            try {
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 40)
            } catch (_: Exception) {}
        }
    }

    fun playTimeExpired(soundEnabled: Boolean = true, hapticsEnabled: Boolean = true) {
        if (hapticsEnabled) {
            vibrate(longArrayOf(0, 100, 80, 180))
        }
        if (!soundEnabled) return

        audioScope.launch {
            try {
                toneGenerator?.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, 350)
            } catch (_: Exception) {}
        }
    }

    fun playFanfare(soundEnabled: Boolean = true, hapticsEnabled: Boolean = true) {
        if (hapticsEnabled) {
            vibrate(longArrayOf(0, 80, 60, 80, 60, 140))
        }
        if (!soundEnabled) return

        audioScope.launch {
            try {
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 100)
                delay(120)
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 100)
                delay(120)
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP2, 350)
            } catch (_: Exception) {}
        }
    }

    private fun vibrate(timings: LongArray) {
        try {
            vibrator?.let { v ->
                if (v.hasVibrator()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createWaveform(timings, -1))
                    } else {
                        @Suppress("DEPRECATION")
                        v.vibrate(timings, -1)
                    }
                }
            }
        } catch (_: Exception) {}
    }

    fun release() {
        try {
            toneGenerator?.release()
            toneGenerator = null
        } catch (_: Exception) {}
    }
}
