package com.questronix.danica.codereviewloginapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("org.jsonschema2pojo")
class CityListResponse {
    /**
     *
     * @return
     * The data
     */
    /**
     *
     * @param data
     * The data
     */
    @SerializedName("data")
    @Expose
    var data: List<CityListData> = ArrayList()
    /**
     *
     * @return
     * The message
     */
    /**
     *
     * @param message
     * The message
     */
    @SerializedName("message")
    @Expose
    var message: String? = null
    /**
     *
     * @return
     * The status
     */
    /**
     *
     * @param status
     * The status
     */
    @SerializedName("status")
    @Expose
    var status = 0
}