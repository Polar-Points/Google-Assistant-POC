package dang.marty.googleassistantpoc.interfaces

import android.content.SharedPreferences

interface RepositoryInterface {

    fun grabPrimaryAccountBalance(): Double

    fun grabSavingsAccountBalance(): Double
}