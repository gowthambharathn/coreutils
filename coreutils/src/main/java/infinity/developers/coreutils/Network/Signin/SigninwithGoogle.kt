package infinity.developers.coreutils.Network.Signin

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

object GoogleAuthManager {

    private const val RC_SIGN_IN = 1001

    fun getSignInClient(
        context: Context,
        webClientId: String
    ): GoogleSignInClient {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)   // 🔥 IMPORTANT (Web Client ID)
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(context, gso)
    }

    fun signIn(activity: Activity, webClientId: String) {
        val client = getSignInClient(activity, webClientId)
        val signInIntent: Intent = client.signInIntent
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun handleSignInResult(
        data: Intent?,
        onSuccess: (email: String, idToken: String) -> Unit,
        onError: (String) -> Unit
    ) {
        val task: Task<GoogleSignInAccount> =
            GoogleSignIn.getSignedInAccountFromIntent(data)

        try {
            val account = task.getResult(ApiException::class.java)

            val email = account?.email
            val idToken = account?.idToken

            if (email != null && idToken != null) {
                onSuccess(email, idToken)
            } else {
                onError("Email or ID Token is null")
            }

        } catch (e: ApiException) {
            onError("Sign-In Failed: ${e.message}")
        }
    }
}