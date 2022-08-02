package com.trainee.appinventiv.cryptocurrency

import android.hardware.biometrics.BiometricManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.trainee.appinventiv.cryptocurrency.databinding.ActivityMainBinding
import java.util.concurrent.Executor
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //init biometric

        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this@MainActivity, executor, object : BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)

                Toast.makeText(this@MainActivity,"Authentication Error",Toast.LENGTH_SHORT).show()
                moveTaskToBack(true)
                exitProcess(-1)
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(this@MainActivity,"Authentication Success",Toast.LENGTH_SHORT).show()

            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(this@MainActivity,"Authentication Failed",Toast.LENGTH_SHORT).show()
                moveTaskToBack(true)
                exitProcess(-1)

            }
        })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Login using FingerPrint")
            .setNegativeButtonText("Use Password")
            .build()

        biometricPrompt.authenticate(promptInfo)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        val navController = navHostFragment!!.findNavController()

        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.bottom_nav_menu)
        binding.bottomBar.setupWithNavController(popupMenu.menu, navController)
    }
}