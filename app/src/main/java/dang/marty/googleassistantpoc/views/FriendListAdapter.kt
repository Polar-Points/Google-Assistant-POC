package dang.marty.googleassistantpoc.views

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import dang.marty.googleassistantpoc.R
import dang.marty.googleassistantpoc.interfaces.FriendListFragPresenterInterface
import dang.marty.googleassistantpoc.models.FakeFriendDataSource
import dang.marty.googleassistantpoc.presenters.FriendListFragPresenter
import timber.log.Timber



class FriendListAdapter(context: Context, data: FakeFriendDataSource): RecyclerView.Adapter<FriendListAdapter.ViewHolder>(), FriendListFragPresenterInterface {

    private val inflater = LayoutInflater.from(context)
    private val dataList = data
    private val friendListFragPresenter = FriendListFragPresenter(this)

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = inflater.inflate(R.layout.friend_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.names.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = dataList.names[position]
        val picture = dataList.images[position]

        holder.textView.text = name
        holder.imageView.setImageResource(picture)

    }

    override fun goToTransferMoneyFragment() {
        // tell main act to f
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }

        var textView: TextView = itemView.findViewById(R.id.friend_name)
        var imageView: ImageView = itemView.findViewById(R.id.friend_picture)


        override fun onClick(v: View?) {
            Timber.plant(Timber.DebugTree())
            Timber.d("YOO %s", textView.text)
            friendListFragPresenter.friendSelected(textView.text.toString())

        }
    }
}