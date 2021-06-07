package com.questronix.danica.codereviewloginapp.network

import com.google.gson.annotations.SerializedName


class Response {
    @SerializedName("status")
    var status: String? = null

    constructor() {}
    constructor(status: String?) {
        this.status = status
    }
}