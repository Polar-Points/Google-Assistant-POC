package dang.marty.googleassistantpoc.views.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import dang.marty.googleassistantpoc.R
import dang.marty.googleassistantpoc.interfaces.AccountFragPresenterInterface
import dang.marty.googleassistantpoc.interfaces.AccountFragmentInterface
import timber.log.Timber
import kotlin.Exception


/**
 * A simple [Fragment] subclass.
 *
 */
class AccountFragment : Fragment(), AccountFragPresenterInterface, View.OnClickListener {

    var primaryAcccountBalance = 0.0
    var savingsAcccountBalance = 0.0

    lateinit var primaryAccountBox: CardView
    lateinit var savingsAccountBox: CardView

    private lateinit var accountFragInterface: AccountFragmentInterface

    companion object {
        fun newInstance(): AccountFragment {
            return AccountFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            accountFragInterface = context as AccountFragmentInterface

        } catch (e:Exception){}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        primaryAcccountBalance = arguments?.getDouble("primaryAccountBalance") ?: 100.00
        savingsAcccountBalance = arguments?.getDouble("savingsAccountBalance") ?: 100.00

        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        updateSavingsAccountAmount(100.0)
        updatePrimaryAccountAmount(200.0)

        primaryAccountBox = view.findViewById(R.id.primary_account_card_view)
        primaryAccountBox.setOnClickListener(this)
        savingsAccountBox = view.findViewById(R.id.savings_account_card_view)
        savingsAccountBox.setOnClickListener(this)


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View?) {
        Timber.plant(Timber.DebugTree())
        when(v?.id){
            R.id.savings_account_card_view -> accountFragInterface.userSelectedSavingsAccount()
            R.id.primary_account_card_view -> accountFragInterface.userSelectedPrimaryAccount()
        }
    }

    override fun updatePrimaryAccountAmount(amount: Double) {
        val currentAmount = view?.findViewById<TextView>(R.id.primary_account_amount)
        currentAmount?.text = amount.toString()
    }

    override fun updateSavingsAccountAmount(amount: Double){
        val currentAmount = view?.findViewById<TextView>(R.id.savings_account_amount)
        currentAmount?.text = amount.toString()
    }


}
