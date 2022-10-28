package br.com.devcapu.beehealthy.login.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.com.devcapu.beehealthy.app.ui.activity.MainActivity
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(STARTED) {
                viewModel.uiState.collect {
                    if (it.loggedIn) {
                        goToHomeActivity()
                    }
                }
            }
        }
       setContent { LoginScreen(viewModel) }
    }

    override fun onResume() {
        super.onResume()
        if (!viewModel.isSignedIn()) return
        goToHomeActivity()
    }

    private fun goToHomeActivity() {
        startActivity(MainActivity.getIntent(this))
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}