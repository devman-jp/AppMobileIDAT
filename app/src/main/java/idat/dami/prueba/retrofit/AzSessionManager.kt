package idat.dami.prueba.retrofit

import android.content.Context
import android.content.SharedPreferences
import idat.dami.prueba.R

class AzSessionManager (context: Context){

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);

    companion object {
        const val USER_TOKEN = "user_token"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.commit()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}