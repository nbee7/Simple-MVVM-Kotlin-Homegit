package com.example.homegit.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//Jika kelas ini tidak digunakan sebaiknya dihapus
abstract class BaseAdapter<T>(private val layoutId: Int) :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    var data = ArrayList<T>()

    fun setData(listItem: List<T>?) {
        if (listItem.isNullOrEmpty()) return
        data.clear()
        data.addAll(listItem)
        notifyDataSetChanged()
    }

    fun getSwipedAdapter(swipedPosition: Int): T = data[swipedPosition]

    var onItemClickListener: ((T) -> Unit)? = null

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
