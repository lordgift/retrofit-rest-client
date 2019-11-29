package th.co.cdgs.training.retrofitrestclient.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_friend.view.*
import th.co.cdgs.training.retrofitrestclient.R

class FriendListRecyclerAdapter(
    var dataList: List<Friend>,
    private val onButtonClicked: (item: Friend) -> Unit?
) : RecyclerView.Adapter<FriendListRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = dataList[position]
        holder.nameTextView?.text = friend.name
        holder.telTextView?.text = friend.tel
        holder.iconStatus?.visibility = if (friend.status) View.VISIBLE else View.INVISIBLE

        holder.itemView.setOnClickListener {
            onButtonClicked(friend)
        }

    }

    override fun getItemCount(): Int {
        return if (dataList.isEmpty()) 1 else dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView? = itemView.nameTextView
        var telTextView: TextView? = itemView.telTextView
        var iconStatus: ImageView? = itemView.statusIcon

    }

}

