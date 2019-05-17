package dang.marty.googleassistantpoc.views.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dang.marty.googleassistantpoc.R
import dang.marty.googleassistantpoc.models.FakeFriendDataSource
import dang.marty.googleassistantpoc.views.FriendListAdapter


class FriendListFragment : Fragment() {

    companion object {
        fun newInstance(): FriendListFragment {
            return FriendListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_friend_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.friends_list_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.adapter = FriendListAdapter(context!!, FakeFriendDataSource())
        // Inflate the layout for this fragment
        return view
    }


}
