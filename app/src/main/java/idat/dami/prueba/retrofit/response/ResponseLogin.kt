package idat.dami.prueba.retrofit.response

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("access_token")
    var access_token: String,

    @SerializedName("token_type")
    var token_type: String,

    @SerializedName("refresh_token")
    var refresh_token: String,

    @SerializedName("expires_in")
    var expires_in: Int,

    @SerializedName("scope")
    var scope: String,

    @SerializedName("jti")
    var jti: String
)
