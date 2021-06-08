package com.questronix.danica.codereviewloginapp.ui.fragment.dashboard.accounts

import com.questronix.danica.codereviewloginapp.base.BaseView
import com.questronix.danica.codereviewloginapp.model.Users

interface AccountsView : BaseView {
    fun showloading()
    fun hideloading()
    fun onError(errMsg: String)
    fun onSuccess(userList: List<Users>)


}