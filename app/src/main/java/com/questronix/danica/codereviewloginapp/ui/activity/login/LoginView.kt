package com.questronix.danica.codereviewloginapp.ui.activity.login

import com.questronix.danica.codereviewloginapp.base.BaseView
import com.questronix.danica.codereviewloginapp.model.Users


interface LoginView : BaseView{
        fun onError(errMsg: String)
        fun onSuccess(userList: List<Users>)
        fun showloading()
        fun hideloading()
        fun emptyCredentials()

}

