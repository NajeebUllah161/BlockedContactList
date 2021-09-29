package com.example.blockedcontactsdemo

import android.database.Cursor
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BlockedNumberContract.BlockedNumbers
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blockedcontactsdemo.Adapter.CustomAdapter

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Calling function to fetch Blocked Contacts

        fetchBlockedContact()
    }

    private fun fetchBlockedContact() {

        var c: Cursor? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            contentResolver.query(
                BlockedNumbers.CONTENT_URI, arrayOf(
                    BlockedNumbers.COLUMN_ID, BlockedNumbers.COLUMN_ORIGINAL_NUMBER,
                    BlockedNumbers.COLUMN_E164_NUMBER
                ), null, null, null
            )
        } else {
            TODO("VERSION.SDK_INT < N")
        }


        var name: ArrayList<String> = ArrayList()

        if (c != null) {
            Log.i("Blocked List: ", c.moveToFirst().toString())
            c.moveToFirst()
            do {
                name.add(c.getString(1))

            } while (c.moveToNext())

        }

        val customAdapter = CustomAdapter(name, this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = customAdapter

    }


}