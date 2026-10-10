package com.example

import android.annotation.SuppressLint
import android.graphics.Color as AndroidColor
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.QuizPhase
import com.example.ui.screens.IntroScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.ResultScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.SavannahAmber
import com.example.ui.theme.SavannahCream
import com.example.ui.theme.SavannahDarkBg
import com.example.viewmodel.QuizViewModel

enum class AppViewMode {
    WEB_APP,
    NATIVE_COMPOSE
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                SavannahMainContainer()
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun SavannahMainContainer(
    viewModel: QuizViewModel = viewModel()
) {
    var viewMode by remember { mutableStateOf(AppViewMode.WEB_APP) }
    var webViewInstance by remember { mutableStateOf<WebView?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = SavannahDarkBg
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Mode Switcher Banner (Web App Mode vs Native Android Mode)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF1E120A),
                border = BorderStroke(1.dp, Color(0xFF3B2414)),
                tonalElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (viewMode == AppViewMode.WEB_APP) "🌐 Web App View" else "📱 Native Android View",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = SavannahCream
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    if (viewMode == AppViewMode.WEB_APP) {
                        IconButton(
                            onClick = { webViewInstance?.reload() },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Reload Web App",
                                tint = SavannahAmber,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                    }

                    // Toggle Button
                    Button(
                        onClick = {
                            viewMode = if (viewMode == AppViewMode.WEB_APP) {
                                AppViewMode.NATIVE_COMPOSE
                            } else {
                                AppViewMode.WEB_APP
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF382315),
                            contentColor = SavannahAmber
                        ),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = if (viewMode == AppViewMode.WEB_APP) Icons.Default.PhoneAndroid else Icons.Default.Language,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (viewMode == AppViewMode.WEB_APP) "Switch to Native" else "Switch to Web App",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Body Content based on View Mode
            Box(modifier = Modifier.fillMaxSize()) {
                if (viewMode == AppViewMode.WEB_APP) {
                    SavannahWebAppView(
                        onWebViewCreated = { webViewInstance = it }
                    )
                } else {
                    SavannahNativeQuizView(viewModel = viewModel)
                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun SavannahWebAppView(
    onWebViewCreated: (WebView) -> Unit
) {
    var webViewRef by remember { mutableStateOf<WebView?>(null) }

    BackHandler(enabled = webViewRef?.canGoBack() == true) {
        webViewRef?.goBack()
    }

    AndroidView(
        factory = { ctx ->
            WebView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                setBackgroundColor(AndroidColor.parseColor("#120B06"))

                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    allowFileAccess = true
                    mediaPlaybackRequiresUserGesture = false
                    useWideViewPort = true
                    loadWithOverviewMode = true
                    cacheMode = WebSettings.LOAD_NO_CACHE
                }

                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                    }
                }
                webChromeClient = WebChromeClient()

                loadUrl("file:///android_asset/index.html")
                webViewRef = this
                onWebViewCreated(this)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun SavannahNativeQuizView(
    viewModel: QuizViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.phase) {
        QuizPhase.INTRO -> {
            IntroScreen(
                settings = uiState.settings,
                onStartQuiz = { viewModel.startQuiz() },
                onToggleSound = { viewModel.toggleSound() },
                onToggleHaptics = { viewModel.toggleHaptics() },
                onSetTimerDuration = { viewModel.setTimerDuration(it) }
            )
        }
        QuizPhase.PLAYING,
        QuizPhase.QUESTION_FEEDBACK -> {
            QuizScreen(
                uiState = uiState,
                onSelectOption = { viewModel.selectOption(it) },
                onNextQuestion = { viewModel.nextQuestion() },
                onToggleSound = { viewModel.toggleSound() },
                onExitQuiz = { viewModel.returnToIntro() }
            )
        }
        QuizPhase.COMPLETED -> {
            ResultScreen(
                uiState = uiState,
                onRetakeQuiz = { viewModel.retakeQuiz() },
                onReturnToCamp = { viewModel.returnToIntro() }
            )
        }
    }
}
