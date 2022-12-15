package idat.dami.prueba.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import idat.dami.prueba.R
import idat.dami.prueba.databinding.ActivityLoginBinding
import idat.dami.prueba.retrofit.AzSessionManager
import idat.dami.prueba.retrofit.response.ResponseLogin
import idat.dami.prueba.utilitarios.AppMensaje
import idat.dami.prueba.utilitarios.TipoMensaje
import idat.dami.prueba.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding;
    private lateinit var authViewModel: AuthViewModel;
    private lateinit var sessionManager: AzSessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)
        sessionManager = AzSessionManager(this)
        binding.btnlogin.setOnClickListener(this)
        authViewModel.responseLogin.observe(this, Observer {
                response ->
            response?.let {
                obtenerDatosLogin(response)
            };
            AppMensaje.enviarMensaje(binding.root,
                "Usuario y/o contraseña incorrectas.", TipoMensaje.ERROR);
        })
    }

    private fun obtenerDatosLogin(response: ResponseLogin) {
        if(response.access_token.isNotEmpty()) {
            sessionManager.saveAuthToken(response.access_token)
            startActivity(Intent(applicationContext, HomeActivity::class.java))
        }
        binding.btnlogin.isEnabled = true
    }

    override fun onClick(vista: View) {
        when(vista.id){
            R.id.btnlogin -> autenticarUsuario()
        }
    }

    private fun autenticarUsuario() {
        binding.btnlogin.isEnabled = false
        if(validarUsuarioPassword()){
            authViewModel.autenticarUsuario(binding.etusuario.text.toString(), binding.etpassword.text.toString())
            binding.btnlogin.isEnabled = true
        }else{
            AppMensaje.enviarMensaje(binding.root,
                getString(R.string.msgloginincompleto),
                TipoMensaje.ERROR)
            binding.btnlogin.isEnabled = true
        }
    }

    private fun validarUsuarioPassword(): Boolean {
        var okValidacion = true
        if(binding.etusuario.text.toString().trim().isEmpty()){
            binding.etusuario.isFocusableInTouchMode = true
            binding.etusuario.requestFocus()
            okValidacion = false
        } else if(binding.etpassword.text.toString().trim().isEmpty()){
            binding.etpassword.isFocusableInTouchMode = true
            binding.etpassword.requestFocus()
            okValidacion = false
        }
        return okValidacion
    }

    override fun onBackPressed() {
        val cerrar = Intent(applicationContext,LoginActivity::class.java)
        startActivity(cerrar)
    }
}