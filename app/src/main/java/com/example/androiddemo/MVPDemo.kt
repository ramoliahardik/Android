package com.example.androiddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_mvpdemo.*
import java.util.regex.Pattern

interface IUser {
    val email: String
    val password: String
    val isDataValid: Boolean
}

class People(override val email: String, override val password: String): IUser {
    override val isDataValid: Boolean
        get() = (!TextUtils.isEmpty(email) &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                password.length > 6)
}

interface ILoginView {
    fun onLoginResult(message: String)
}

interface ILoginPresenter {
    fun onLogin(email: String, password: String)
}

class LoginPresenter(internal var iLoginView: ILoginView) : ILoginPresenter{
    override fun onLogin(email: String, password: String) {
        val user = People(email, password)
        val isLoginSuccess = user.isDataValid
        if (isLoginSuccess)
            iLoginView.onLoginResult("Login success")
        else
            iLoginView.onLoginResult("Login failed")
    }
}

class MVPDemo : AppCompatActivity(), ILoginView {

    internal lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvpdemo)

        loginPresenter = LoginPresenter(this)

        btnMVPLogin.setOnClickListener {
            loginPresenter.onLogin(etMVPEmail.text.toString(), etMVPPwd.text.toString())
        }
    }

    override fun onLoginResult(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
