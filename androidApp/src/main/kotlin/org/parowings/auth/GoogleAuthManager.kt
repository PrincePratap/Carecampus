package org.parowings.auth

import android.app.Activity
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class GoogleAuthManager(private val webClientId: String) {

    fun getSignInIntent(activity: Activity): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()

        val client = GoogleSignIn.getClient(activity, gso)
        return client.signInIntent
    }

    suspend fun handleSignInResult(data: Intent?): AuthUser =
        suspendCancellableCoroutine { cont ->
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                val acct = try {
                    task.getResult(ApiException::class.java)
                } catch (e: Exception) {
                    cont.resumeWithException(e)
                    return@suspendCancellableCoroutine
                }

                val idToken = acct?.idToken
                if (idToken.isNullOrEmpty()) {
                    cont.resumeWithException(Exception("Missing ID token"))
                    return@suspendCancellableCoroutine
                }

                val credential = GoogleAuthProvider.getCredential(idToken, null)
                val auth = FirebaseAuth.getInstance()
                auth.signInWithCredential(credential).addOnCompleteListener { taskAuth ->
                    if (taskAuth.isSuccessful) {
                        val user = auth.currentUser
                        if (user != null) {
                            val result = AuthUser(
                                uid = user.uid,
                                displayName = user.displayName,
                                email = user.email,
                                photoUrl = user.photoUrl?.toString()
                            )
                            cont.resume(result)
                        } else {
                            cont.resumeWithException(Exception("Firebase user null"))
                        }
                    } else {
                        cont.resumeWithException(taskAuth.exception ?: Exception("Auth failed"))
                    }
                }
            } catch (e: Exception) {
                cont.resumeWithException(e)
            }
        }

    suspend fun signOut(activity: Activity) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()
        val client = GoogleSignIn.getClient(activity, gso)
        client.signOut().addOnCompleteListener { }
        FirebaseAuth.getInstance().signOut()
    }
}
