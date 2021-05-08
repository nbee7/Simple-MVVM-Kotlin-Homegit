package com.example.homegit.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homegit.R
import com.example.homegit.databinding.ActivityMainBinding
import com.example.homegit.model.api.response.UserResponse
import com.example.homegit.ui.adapter.UserAdapter
import com.example.homegit.ui.viewmodel.MainViewModel
import com.example.homegit.util.gone
import com.example.homegit.util.visible

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var userAdapter: UserAdapter
    /*Jika menggunakan ViewBinding usahakan menggunakan nullable seperti ini
    var binding: ActivityMainBinding? = null

    Dan meng-null-kan kembali pada saat activity atau fragment di hancurkan
    override fun onDestroy(){
        binding = null
    }

    Untuk menghindari memory leak*/
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.initRepository(this)
        mainViewModel.getUser().observe(this, {
            if (it != null) {
                updateListUser(it)
                showLoading(false)
            }
        })

        userAdapter = UserAdapter(this, mutableListOf())
        binding.rvUsers.apply {
            layoutManager =
                    LinearLayoutManager(
                            context,
                            LinearLayoutManager.VERTICAL,
                            false
                    )
            setHasFixedSize(true)
            adapter = userAdapter
        }

        getList()


    }

    private fun getList() {
        showLoading(true)
        mainViewModel.followingUser()

    }


    private fun updateListUser(listUser: List<UserResponse>) {
        userAdapter.data.clear()
        userAdapter.data.addAll(listUser)
        userAdapter.notifyDataSetChanged()
        if (listUser.isEmpty()) {
            binding.rvUsers.gone()
             binding.tvEmpty.visible()
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.rvUsers.gone()
            binding.progressBar.visible()
            binding.tvEmpty.gone()
        } else {
            binding.rvUsers.visible()
            binding.progressBar.gone()
        }
    }
}