package dang.marty.googleassistantpoc.models


class AccountModel(savingsAccountBalance: Double, primaryAccountBalance: Double) {

    private var currentSavingsAccountBalance = 0.0
    private var currentPrimaryAccountBalance = 0.0

    init {
        currentSavingsAccountBalance = savingsAccountBalance
        currentPrimaryAccountBalance = primaryAccountBalance
    }

    fun showCurrentSavingsAccountBalance(): Double {
        return currentSavingsAccountBalance
    }

    fun showCurrentPrimaryAccountBalance(): Double {
        return currentPrimaryAccountBalance
    }

    fun subtractMoneyFromSavingsAccount(amount: Double): Double {
        return currentSavingsAccountBalance - amount
    }

    fun addMoneyToSavingsAccount(amount: Double): Double {
        return currentSavingsAccountBalance + amount
    }

    fun subtractMoneyFromPrimaryAccount(amount: Double): Double {
        return currentPrimaryAccountBalance - amount
    }

    fun addMoneyToPrimaryAccount(amount: Double): Double {
        return currentPrimaryAccountBalance + amount
    }



}