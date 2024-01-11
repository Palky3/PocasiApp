package com.example.pocasiapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity(){

    /*private lateinit var mUserViewModel: UserViewModel
    private lateinit var recyclerView: RecyclerView*/
    //private lateinit var adapter: ListAdapter
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_history)

        /*recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)*/

        /*val userList = mUserViewModel.readAllData
        adapter = ListAdapter(userList)
        recyclerView.adapter = adapter*/

        /*mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(this, Observer { user -> ListAdapter(user) })*/

    }

    /*private fun generateSupplierData(): List<User> {
        val suppliers = mutableListOf<User>()
        for (i in 1..1000) {
            suppliers.add(User("Beer Supplier $i", "Description of suplier $i"))
        }
        return suppliers
    }*/
}