package com.questronix.danica.codereviewloginapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


import javax.annotation.Generated;


@Generated("org.jsonschema2pojo")
class CityListData {
    /**
     *
     * @return
     * The id
     */
    /**
     *
     * @param id
     * The id
     */
    @SerializedName("id")
    @Expose
    var id: String? = null
    /**
     *
     * @return
     * The name
     */
    /**
     *
     * @param name
     * The name
     */
    @SerializedName("name")
    @Expose
    var name: String? = null
    /**
     *
     * @return
     * The description
     */
    /**
     *
     * @param description
     * The description
     */
    @SerializedName("description")
    @Expose
    var description: String? = null
    /**
     *
     * @return
     * The background
     */
    /**
     *
     * @param background
     * The background
     */
    @SerializedName("background")
    @Expose
    var background: String? = null
}