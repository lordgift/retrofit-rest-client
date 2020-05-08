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
    var dataList: List<Friend?>,
    private val onItemClicked: (item: Friend?, position: Int) -> Unit?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEWTYPE_NORMAL = 0x0a
    private val VIEWTYPE_DELETED = 0x0b

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        return when (viewType) {
            VIEWTYPE_NORMAL -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)
                ViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_friend_deleted, parent, false)
                object : RecyclerView.ViewHolder(view) {

                }
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val friend = dataList[position]
        if (holder is ViewHolder) {
            holder.nameTextView?.text = friend?.name
            holder.telTextView?.text = friend?.tel
            holder.iconStatus?.visibility =
                if (friend != null && friend.status) View.VISIBLE else View.INVISIBLE

            holder.itemView.setOnClickListener {
                onItemClicked(friend, position)
            }
        }

    }

    override fun getItemCount(): Int = if (dataList.isEmpty()) 1 else dataList.size

    override fun getItemViewType(position: Int): Int {
        val friend = dataList[position]
        return if (friend != null) {
            VIEWTYPE_NORMAL
        } else {
            VIEWTYPE_DELETED
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView? = itemView.nameTextView
        var telTextView: TextView? = itemView.telTextView
        var iconStatus: ImageView? = itemView.statusIcon

    }

}

