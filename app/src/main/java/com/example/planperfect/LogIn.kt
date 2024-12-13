package com.example.planperfect

import android.content.Intent
import android.credentials.CredentialManager
import android.credentials.GetCredentialRequest
import android.credentials.GetCredentialRequest.Builder
import kotlinx.coroutines.CoroutineScope

class LogIn {
    companion object{
        fun doGoogleSignIn(context:Context,
                           scope: CoroutineScope,
                           launcher:ManagedActivityResultLauncher<Intent.ActivityResult>?,
                           login:()->Unit
        ){
            val credentialManager = CredentialManager(context)

            val request = Builder()
                .addCredentialOption()
                .build()


                    }
            }
    }
