package dang.marty.googleassistantpoc.views.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dang.marty.googleassistantpoc.R

class UpComingBillsFragment : Fragment() {

    companion object {
        fun newInstance(): UpComingBillsFragment {
            return UpComingBillsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_up_comming_bills, container, false)
    }
}
