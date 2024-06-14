package com.example.bookrecommendation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookrecommendation.BR

import com.example.bookrecommendation.data.Book
import com.example.bookrecommendation.databinding.BookBinding
import com.example.bookrecommendation.ui.HomeFragment



class BooksAdapter(private val booksList: ArrayList<Book?>,
                   private val context: HomeFragment,
                   private val clickListener: CustomBookClickListener) :
    RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    interface CustomBookClickListener {
        fun cardClicked(book: Book?)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: BookBinding = BookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book: Book? = booksList[position]
        if (book != null) {
            holder.bind(book, clickListener = clickListener)
        }
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    inner class ViewHolder(val itemRowBinding: BookBinding) :
        RecyclerView.ViewHolder(itemRowBinding.root) {

        fun bind(obj: Book, clickListener: CustomBookClickListener) {
            itemRowBinding.setVariable(BR.item, obj)
            itemRowBinding.executePendingBindings()
            clickListener.cardClicked(obj)
        }
    }

}
