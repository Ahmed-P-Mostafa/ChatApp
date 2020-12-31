package com.example.chat.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chat.util.Constants

abstract class BaseActivity<DB:ViewDataBinding,VM:BaseViewModel<*>> :MyApplication() {

    lateinit var dataBinding: DB
    lateinit var viewModel:VM
    var dialog : AlertDialog? = null
    var loader :ProgressDialog? = null
    var message:AlertDialog? = null
    lateinit var user : SharedPreferences
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         user = getSharedPreferences(Constants.USER_SHARED_PREFERENCES_FILE_NAME, MODE_PRIVATE)


        dataBinding = DataBindingUtil.setContentView(this,getLayoutId())
        viewModel = ViewModelProvider(this).get(initializeViewModel())

        viewModel.dialog.observe(this, Observer {
            showDialog(title = it.title,message = it.message,posButton = it.posButton ,posAction = it.posAction,negButton = it
                .negButton,negAction = it.negAction,cancelable = it.cancelable)
        })
        viewModel.loader.observe(this, Observer {
            if (it){
                showLoader("loading...")
            }else{
                hideLoader()
            }
        })
        viewModel.message.observe(this, Observer {
            showMessage(it)
        })



    }
    abstract fun getLayoutId():Int
     abstract fun initializeViewModel():Class<VM>

    fun showDialog(title :String? = null, message :String? = null, posButton :String? = null,
                   negButton :String? = null, posAction:DialogInterface.OnClickListener?= null
                   , negAction:DialogInterface.OnClickListener?= null, cancelable:Boolean=false){

            dialog = AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(posButton,posAction)
                .setNegativeButton(negButton,negAction)
                .setCancelable(cancelable)
                .show()
    }
    fun showMessage(textt: String?){
        message = AlertDialog.Builder(this)
            .setMessage(textt)
            .setPositiveButton("ok") { dialogInterface, i ->
                dialogInterface.dismiss()
            }.show()
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