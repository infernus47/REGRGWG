package com.youtubelaudio.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.youtubelaudio.app.ui.YoutubeAudioApp
import com.youtubelaudio.app.ui.theme.YoutubeAudioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YoutubeAudioTheme {
                YoutubeAudioApp()
            }
        }
    }
}
