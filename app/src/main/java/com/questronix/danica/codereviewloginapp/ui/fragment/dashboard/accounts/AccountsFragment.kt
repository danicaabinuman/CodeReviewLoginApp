package com.questronix.danica.codereviewloginapp.ui.fragment.dashboard.accounts




import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.questronix.danica.codereviewloginapp.PopUpLoading
import com.questronix.danica.codereviewloginapp.R
import com.questronix.danica.codereviewloginapp.base.BaseFragment
import com.questronix.danica.codereviewloginapp.model.Users
import com.questronix.danica.codereviewloginapp.ui.adapter.AccountsAdapter
import java.util.*

class AccountsFragment : BaseFragment<AccountsPresenter>(),AccountsView {
    lateinit var popUpLoading: PopUpLoading

    @BindView(R.id.accountList)
    lateinit var accountList: RecyclerView

    @BindView(R.id.empty_view)
    lateinit var emptyView: RelativeLayout
    private lateinit var accountsAdapter: AccountsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.account_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
        initialize()
    }


    fun initialize() {
        presenter.getUsers()
    }


    override fun instantiatePresenter(): AccountsPresenter {
        return AccountsPresenter(this)
    }

    override fun showloading() {
        popUpLoading = PopUpLoading(requireActivity())
        popUpLoading.show()

    }

    override fun hideloading() {
        if (popUpLoading.isShowing()) {
            popUpLoading.dismiss()
        }
    }

    private fun setAdapter(accountsList: List<Users>) {
        emptyView.visibility = if (accountsList == null || accountsList.isEmpty()) View.VISIBLE else View.GONE

        //accounts recycler view and adapter setup
        accountList.layoutManager = LinearLayoutManager(requireActivity())
        accountList.setHasFixedSize(true)
        accountsAdapter = AccountsAdapter(requireActivity(), accountsList)
        accountList.adapter = accountsAdapter
        accountList.setItemViewCacheSize(accountsList.size)

    }

    override fun onError(errMsg: String) {
        Toast.makeText(requireActivity(), errMsg, Toast.LENGTH_LONG).show()
    }

    override fun onSuccess(userList: List<Users>) {
        setAdapter(userList)
        hideloading()
        /*if(userList.isEmpty()){
            empty_view.visibility = View.VISIBLE
            accountList.visibility =View.GONE
        }else{
            empty_view.visibility = View.GONE
            accountList.visibility =View.VISIBLE
            accountsAdapter = AccountsAdapter(requireActivity(), userList)
            accountsAdapter.notifyDataSetChanged()
            accountList.adapter = accountsAdapter
            accountList.layoutManager = LinearLayoutManager(requireActivity())
            accountList.setHasFixedSize(true)
            hideloading()
        }*/
    }
}

