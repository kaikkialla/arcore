package com.kuelye.banana.kotlin.arcore.model

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kuelye.banana.kotlin.arcore.R
import kotlinx.android.synthetic.main.view_ratings.view.*

class RatingsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var adapter: Adapter? = null
    var ratings: Ratings? = null
        set(value) {
            field = value;
            titleTextView.text = value?.title
            adapter?.ratings = value?.ratings
        }

    init {
        View.inflate(getContext(), R.layout.view_ratings, this)
        adapter = Adapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(getContext())
    }

    private class Adapter() : RecyclerView.Adapter<Adapter.ViewHolder>() {

        var ratings: List<Rating>? = null
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
            val textView = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false) as TextView;
            return ViewHolder(textView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = ratings?.get(position)?.userName
        }

        override fun getItemCount() = ratings?.size ?: 0

        private class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {
            // TODO
        }

    }

}