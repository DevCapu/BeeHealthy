package br.com.devcapu.beehealthy.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import br.com.devcapu.beehealthy.ui.screen.LoginScreen
import br.com.devcapu.beehealthy.ui.viewModel.LoginViewModel

class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.signedIn.observe(this) { isSignedIn ->
            if (isSignedIn) {
                goToHomeActivity()
            }
        }

        setContent {
            LoginScreen(viewModel)
        }
    }

    override fun onResume() {
        super.onResume()
        if (!viewModel.isSignedIn()) return
        goToHomeActivity()
    }

    private fun goToHomeActivity() {
        startActivity(MainActivity.getIntent(this))
    }

    private fun goToRegisterActivity() {
        startActivity(RegisterActivity.getIntent(this))
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}