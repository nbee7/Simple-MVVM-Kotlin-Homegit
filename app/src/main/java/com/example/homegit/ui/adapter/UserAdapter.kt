package com.example.homegit.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homegit.R
import com.example.homegit.databinding.ActivityMainBinding
import com.example.homegit.databinding.ItemListUserBinding
import com.example.homegit.model.api.response.UserResponse
import com.example.homegit.util.setImageUrl

class UserAdapter(
        val context: Context, //Variabel context dapat diubah menjadi private
                                //jika hanya diakses di kelas ini
        val data: MutableList<UserResponse> = mutableListOf()
) : RecyclerView.Adapter<UserAdapter.UserHorizontalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHorizontalViewHolder {
        return UserHorizontalViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_user, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserHorizontalViewHolder, position: Int) {
        val user = data[position]
        val binding = ItemListUserBinding.bind(holder.itemView)
        with(binding) {
            imgItemPhoto.setImageUrl(context, user.avatar_url, pbUser)
            tvId.text = user.type
            tvName.text = user.login

        }
    }

   inner  class UserHorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}