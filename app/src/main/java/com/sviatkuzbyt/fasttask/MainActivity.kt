package com.sviatkuzbyt.fasttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tool = findViewById<Toolbar>(R.id.mainToolbar)
        setSupportActionBar(tool)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.kkkk -> Toast.makeText(this, item.itemId.toString(), Toast.LENGTH_SHORT).show()
//        }
//        return super.onOptionsItemSelected(item)
//
//
//    }
}