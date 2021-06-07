package com.questronix.danica.codereviewloginapp.model

data class Users(val id:Int, val name: String, val username: String, val address: Address,
                 val phone: String, val website: String, val company: Company)