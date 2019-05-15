package dang.marty.googleassistantpoc

import android.content.SharedPreferences
import dang.marty.googleassistantpoc.interfaces.RepositoryInterface

class RepositoryImpl(private val sharedPrefs: SharedPreferences): RepositoryInterface {

    override fun grabPrimaryAccountBalance(): Double {
        val primaryAccountBalance = sharedPrefs.getString("primaryAccountBalance", null)?.toDouble()
        return primaryAccountBalance ?: 100.00
    }

    override fun grabSavingsAccountBalance(): Double {
        val savingsAccountBalance = sharedPrefs.getString("savingsAccountBalance", null)?.toDouble()
        return savingsAccountBalance ?: 100.00
    }
}