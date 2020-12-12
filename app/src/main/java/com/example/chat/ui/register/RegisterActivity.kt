package com.example.chat.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityRegisterBinding
import com.example.chat.ui.home.HomeActivity
import com.example.chat.ui.login.LoginActivity
import com.example.chat.ui.login.Navigator

class RegisterActivity : BaseActivity<ActivityRegisterBinding,RegisterViewMdel>(),com.example.chat.ui.register.Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.vm = viewModel
        viewModel.navigator = this
        observers()

        viewModel.dataValid.observe(this, Observer {
            if (it){
                dataBinding.emailEditText.setError("email is empty")
                Toast.makeText(this,"data valid ",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"data not Valid",Toast.LENGTH_LONG).show()
            }
        })


    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initializeViewModel(): RegisterViewMdel {
        return ViewModelProvider(this).get(RegisterViewMdel::class.java)
    }

    override fun openLoginActivity() {
        val loginIntent = Intent(this,LoginActivity::class.java)
        startActivity(loginIntent)
    }

    override fun openHomeActivity() {
        val homeIntent = Intent(this,HomeActivity::class.java)
        startActivity(homeIntent)
    }
    fun observers(){
        viewModel.nameText.observe(this, Observer {
            if (it== false){dataBinding.nameEditText.setError("name is empty")
            }
        })
        viewModel.emailText.observe(this, Observer {
            if (!it){
                dataBinding.emailEditText.setError("email is not valid")
            }
        })
        viewModel.phoneText.observe(this, Observer {
            if (!it)dataBinding.phoneEditText.setError("phone is empty")
        })
        viewModel.passwordText.observe(this, Observer {
            if (!it)dataBinding.passwordEditText.setError("password is empty")
        })

    }


}