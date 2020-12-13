package com.example.chat.base

import android.Manifest
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.chat.ui.login.LoginViewModel

abstract class BaseActivity<DB:ViewDataBinding,VM:BaseViewModel<*>> :AppCompatActivity() {

    lateinit var dataBinding: DB
    lateinit var viewModel:VM
    var dialog : AlertDialog? = null
    var loader :ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this,getLayoutId())
        viewModel = initializeViewModel()

        viewModel.message.observe(this, Observer {
            showMessage(message = it,posButton = "ok",posAction = DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
        })
        viewModel.loader.observe(this, Observer {
            if (it){
                showLoader("loading...")
            }else{
                hideLoader()
            }
        })



    }
    abstract fun getLayoutId():Int
    abstract fun initializeViewModel():VM

    fun showMessage(title :String? = null,message :String? = null,posButton :String? = null,
                    negButton :String? = null,posAction:DialogInterface.OnClickListener?= null
                    ,negAction:DialogInterface.OnClickListener?= null,cancelable:Boolean=false){

            dialog = AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(posButton,posAction)
                .setNegativeButton(negButton,negAction)
                .setCancelable(cancelable)
                .show()
    }
    fun hideMessage(){
        dialog?.dismiss()
    }
    fun showLoader(message:String){
        loader = ProgressDialog(this)
        loader?.setMessage(message)
        loader?.setCancelable(false)
        loader?.show()

    }
    fun hideLoader(){
        loader?.dismiss()
    }


}