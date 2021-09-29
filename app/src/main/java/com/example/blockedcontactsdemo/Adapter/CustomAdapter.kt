package com.example.blockedcontactsdemo.Adapter

import android.content.Context
import android.os.Build
import android.provider.BlockedNumberContract.unblock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.blockedcontactsdemo.R

class CustomAdapter(private val dataSet: ArrayList<String>, private val context: Context) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.N)
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.textView)

        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.single_view, viewGroup, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position]

        // OnLongClickListener to remove a contact from the Database
        // and also deleting from arrayList(dataSet)
        viewHolder.textView.setOnLongClickListener {
            Log.i("Contact to be removed :", viewHolder.textView.text as String)
            unblock(context, viewHolder.textView.text.toString())
            notifyItemRemoved(position)
            dataSet.removeAt(position)

            true
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet.size
    }
}