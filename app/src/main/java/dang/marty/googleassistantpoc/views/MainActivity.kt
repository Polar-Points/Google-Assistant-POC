package dang.marty.googleassistantpoc.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import dang.marty.googleassistantpoc.DeepLink
import dang.marty.googleassistantpoc.interfaces.MainActivityInterface
import dang.marty.googleassistantpoc.presenters.MainActivityPresenter
import dang.marty.googleassistantpoc.R
import dang.marty.googleassistantpoc.RepositoryImpl
import dang.marty.googleassistantpoc.interfaces.AccountFragmentInterface
import dang.marty.googleassistantpoc.views.fragments.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), MainActivityInterface, AccountFragmentInterface {

    lateinit var mainActPresenter: MainActivityPresenter


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        mainActPresenter.userHasSelectedNavigationItem(item.title.toString())
        return@OnNavigationItemSelectedListener true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_bar)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val sharedPrefs = this.getSharedPreferences("primaryAccountAmount", Context.MODE_PRIVATE)
        val prefs = RepositoryImpl(sharedPrefs)
        mainActPresenter = MainActivityPresenter(this, prefs)

        mainActPresenter.userHasSelectedNavigationItem("Transfer Money")

        // Get the action and data from the intent to handle it.
        val action: String? = intent?.action
        val data: Uri? = intent?.data

        when (action) {
            // When the action is triggered by a deep-link, Intent.Action_VIEW will be used
            Intent.ACTION_VIEW -> handleDeepLink(data)
            // When the action is triggered by the Google search action, the ACTION_SEARCH will be used
            //SearchIntents.ACTION_SEARCH -> handleSearchIntent(intent.getStringExtra(SearchManager.QUERY))
            // Otherwise start the app as you would normally do.
           // else -> //showDefaultView()
        }
    }

    private fun handleDeepLink(data: Uri?) {
        var actionHandled = true
        when (data?.path) {
            DeepLink.ACCOUNT -> {
                val accountType = data.getQueryParameter(DeepLink.Params.ACCOUNT_TYPE).orEmpty()

                val bundle = Bundle()
                bundle.putString("accountType", accountType)

                val accountTypeFrag = AccountDetailView.newInstance()
                accountTypeFrag.arguments = bundle

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_holder, accountTypeFrag, "accountDetailView")
                    .commit()

            }
            else  -> {

            }
        }

        //notifyActionSuccess(actionHandled)
    }

    /**
     * Log a success or failure of the received action based on if your app could handle the action
     *
     * Required to help giving Assistant visibility over success or failure of an action sent to the app.
     * Otherwise, it can’t confidently send user’s to your app for fulfillment.
     */
//    private fun notifyActionSuccess(succeed: Boolean) {
//        intent.getStringExtra(DeepLink.Actions.ACTION_TOKEN_EXTRA)?.let { actionToken ->
//            val actionStatus = if (succeed) {
//                Action.Builder.STATUS_TYPE_COMPLETED
//            } else {
//                Action.Builder.STATUS_TYPE_FAILED
//            }
//            val action = AssistActionBuilder()
//                .setActionToken(actionToken)
//                .setActionStatus(actionStatus)
//                .build()
//
//            // Send the end action to the Firebase app indexing.
//            FirebaseUserActions.getInstance().end(action)
//        }
//    }


    override fun userSelectedSavingsAccount() {
        val bundle = Bundle()
        bundle.putString("accountType", "Savings")
        val accountTypeFrag = AccountDetailView.newInstance()
        accountTypeFrag.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_holder, accountTypeFrag, "accountDetailView")
            .commit()
    }

    override fun userSelectedPrimaryAccount() {
        val bundle = Bundle()
        bundle.putString("accountType", "Primary")

        val accountTypeFrag = AccountDetailView.newInstance()
        accountTypeFrag.arguments = bundle


        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_holder, accountTypeFrag, "accountDetailView")
            .commit()
    }

    override fun accountSelected() {
        val bundle = Bundle()
        Timber.plant(Timber.DebugTree())
        bundle.putDouble("primaryAccountBalance", mainActPresenter.getPrimaryAccountBalance())
        bundle.putDouble("savingsAccountBalance", mainActPresenter.getSavingsAccountBalance())

        val accountFrag = AccountFragment.newInstance()
        accountFrag.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_holder, accountFrag, "accountFragment")
            .commit()
    }

    override fun trasnferMoneySelected() {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_holder, FriendListFragment.newInstance(), "friendListFragment")
            .commit()
    }

    override fun upcomingBillsSelected() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_holder, UpComingBillsFragment.newInstance(), "upcomingBillsFragment()")
            .commit()
    }
}
