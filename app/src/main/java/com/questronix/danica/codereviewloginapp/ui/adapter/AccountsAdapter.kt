package com.questronix.danica.codereviewloginapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.questronix.danica.codereviewloginapp.R
import com.questronix.danica.codereviewloginapp.model.Users

class AccountsAdapter(context: Context, userList: List<Users>) : RecyclerView.Adapter<AccountsAdapter.AccountsViewHolder>() {
    private val context: Context = context
    private var userList: List<Users> = userList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsViewHolder {
        return AccountsViewHolder(LayoutInflater.from(context).inflate(R.layout.account_list_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: AccountsViewHolder, position: Int) {
        val user: Users = userList[position]
        holder.account_title.text = user.name
        holder.account_body.text = user.phone

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class AccountsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.account_title)
        lateinit var account_title: TextView

        @BindView(R.id.account_body)
        lateinit var account_body: TextView


        init {
            ButterKnife.bind(this, itemView)
        }
    }

}
