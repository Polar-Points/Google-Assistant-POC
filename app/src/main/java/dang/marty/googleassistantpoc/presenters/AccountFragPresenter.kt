package dang.marty.googleassistantpoc.presenters

import dang.marty.googleassistantpoc.interfaces.AccountFragPresenterInterface

class AccountFragPresenter(val accountFragPresenterMethods:AccountFragPresenterInterface) {

    fun updatePrimaryAmount(amount: Double) {
        accountFragPresenterMethods.updatePrimaryAccountAmount(amount)
    }

    fun updateSavingsAmount(amount: Double) {
        accountFragPresenterMethods.updateSavingsAccountAmount(amount)
    }

}