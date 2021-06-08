package com.questronix.danica.codereviewloginapp.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.questronix.danica.codereviewloginapp.PopUpLoading
import com.questronix.danica.codereviewloginapp.R
import com.questronix.danica.codereviewloginapp.base.BaseActivity
import com.questronix.danica.codereviewloginapp.model.Users
import com.questronix.danica.codereviewloginapp.ui.activity.BottomNav
import com.questronix.danica.codereviewloginapp.utils.SOURCE_FILE_NAME
import com.questronix.danica.codereviewloginapp.utils.Util.readFile


class Login : BaseActivity<LoginPresenter>(), LoginView{
    lateinit var popUpLoading: PopUpLoading
    @BindView(R.id.username_et)
    lateinit var username : EditText
    @BindView(R.id.login_btn)
    lateinit var login_btn : Button
    private lateinit var user_name : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)


    }

    @OnClick(R.id.login_btn)
    public fun logIn(){
        user_name = username.text.toString()
        presenter.signIn(user_name)

        //check if input username exists in json file
        /*if(getUsers().any{ it.username == user_name}){
            val intent = Intent(this, BottomNav::class.java)
            startActivity(intent)
        }else{
            showSnackBar("username Error!")
        }*/
    }


    //get users from json file from assets
    fun getUsers(): List<Users> {
        val jsonString = applicationContext.assets.readFile(SOURCE_FILE_NAME)
        return GsonBuilder().create().fromJson(jsonString, object : TypeToken<List<Users>>() {}.type)
    }

    override fun onSuccess(users: List<Users>) {
        if(users.any{ it.username == user_name}){
            onError("Login Successful!")
            val intent = Intent(this, BottomNav::class.java)
            startActivity(intent)
        }else{
            onError("Username Error!")
        }
    }


    override fun onError(s: String) {
    runOnUiThread { Snackbar.make(login_btn, s, BaseTransientBottomBar.LENGTH_LONG).show()
     }
    }

    override fun showloading() {
        popUpLoading = PopUpLoading(this)
        popUpLoading.show()
    }

    override fun hideloading() {
        if(popUpLoading.isShowing()){
            popUpLoading.dismiss()
            }
        }



    override fun emptyCredentials() {if(TextUtils.isEmpty(username.text.toString())) {username.setError("Required")}}

    override fun instantiatePresenter(): LoginPresenter {
        return LoginPresenter(this)
    }



}

