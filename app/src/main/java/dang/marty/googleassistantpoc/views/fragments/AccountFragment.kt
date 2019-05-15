package dang.marty.googleassistantpoc.views.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import dang.marty.googleassistantpoc.R
import dang.marty.googleassistantpoc.interfaces.AccountFragPresenterInterface
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 *
 */
class AccountFragment : Fragment(), AccountFragPresenterInterface {

    var primaryAcccountBalance = 0.0
    var savingsAcccountBalance = 0.0

    companion object {
        fun newInstance(): AccountFragment {
            return AccountFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        primaryAcccountBalance = arguments?.getDouble("primaryAccountBalance") ?: 100.00
        savingsAcccountBalance = arguments?.getDouble("savingsAccountBalance") ?: 100.00


        Timber.plant(Timber.DebugTree())

        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val currentAmount = view.findViewById<TextView>(R.id.primary_account_amount)
        currentAmount?.text = primaryAcccountBalance.toString()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun updatePrimaryAccountAmount(amount: Double) {
        val currentAmount = view?.findViewById<TextView>(R.id.primary_account_amount)
        currentAmount?.text = amount.toString()
    }

    override fun updateSavingsAccountAmount(amount: Double){
        val currentAmount = view?.findViewById<TextView>(R.id.savings_amount)
        currentAmount?.text = amount.toString()
    }


}
