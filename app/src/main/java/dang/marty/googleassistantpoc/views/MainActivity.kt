package dang.marty.googleassistantpoc.views

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import dang.marty.googleassistantpoc.interfaces.MainActivityInterface
import dang.marty.googleassistantpoc.presenters.MainActivityPresenter
import dang.marty.googleassistantpoc.R
import dang.marty.googleassistantpoc.RepositoryImpl
import dang.marty.googleassistantpoc.views.fragments.AccountFragment
import dang.marty.googleassistantpoc.views.fragments.TransferMoneyFragment
import dang.marty.googleassistantpoc.views.fragments.UpComingBillsFragment
import timber.log.Timber

class MainActivity : AppCompatActivity(), MainActivityInterface {

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

        mainActPresenter.userHasSelectedNavigationItem("Account")
    }


    override fun accountSelected() {
        val bundle = Bundle()
        Timber.plant(Timber.DebugTree())
        Timber.d("HII %s %s", mainActPresenter.getPrimaryAccountBalance(),mainActPresenter.getSavingsAccountBalance())
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
            .replace(R.id.fragment_holder, TransferMoneyFragment.newInstance(), "transferMoneyFragment")
            .commit()
    }

    override fun upcomingBillsSelected() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_holder, UpComingBillsFragment.newInstance(), "upcomingBillsFragment()")
            .commit()
    }
}
