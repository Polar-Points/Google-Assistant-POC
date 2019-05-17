package dang.marty.googleassistantpoc.views.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import dang.marty.googleassistantpoc.R
import timber.log.Timber


class AccountDetailView : Fragment() {

    companion object {
        fun newInstance(): AccountDetailView{
            return AccountDetailView()
        }
    }

    var accountType = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        accountType = arguments?.getString("accountType") ?: ""
        Timber.plant(Timber.DebugTree())
        Timber.d("TESTING %s", accountType)
        return inflater.inflate(R.layout.fragment_account_detail_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val accountType2 = view.findViewById<TextView>(R.id.test)
        accountType2.text = accountType
        super.onViewCreated(view, savedInstanceState)
    }


}
