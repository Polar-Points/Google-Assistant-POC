package dang.marty.googleassistantpoc.presenters

import dang.marty.googleassistantpoc.interfaces.MainActivityInterface
import dang.marty.googleassistantpoc.interfaces.RepositoryInterface
import dang.marty.googleassistantpoc.models.AccountModel

class MainActivityPresenter(private var mainActInterface: MainActivityInterface, private val prefs: RepositoryInterface) {

    var accountBalanceModel = AccountModel(prefs.grabSavingsAccountBalance(), prefs.grabPrimaryAccountBalance())


    fun userHasSelectedNavigationItem(itemTitle: String) {
        when(itemTitle){
            "Account" -> mainActInterface.accountSelected()
            "Transfer Money" -> mainActInterface.trasnferMoneySelected()
            "Upcoming Bills" -> mainActInterface.upcomingBillsSelected()
        }
    }

    fun getPrimaryAccountBalance(): Double {
        return accountBalanceModel.showCurrentPrimaryAccountBalance()
    }

    fun getSavingsAccountBalance(): Double {
        return accountBalanceModel.showCurrentSavingsAccountBalance()
    }


}