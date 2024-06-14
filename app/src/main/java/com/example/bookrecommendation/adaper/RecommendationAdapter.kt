package com.example.bookrecommendation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookrecommendation.BR
import com.example.bookrecommendation.data.Recommendations
import com.example.bookrecommendation.databinding.RecommendBinding
import com.example.bookrecommendation.ui.BookFragment



class RecommendationAdapter(private val recommendationList: ArrayList<Recommendations?>,
                            private val context: BookFragment,
                            private val clickListener: CustomRecommendClickListener) :
    RecyclerView.Adapter<RecommendationAdapter.ViewHolder>() {

        interface CustomRecommendClickListener {
        fun cardClicked(recommend: Recommendations?)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RecommendBinding = RecommendBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recommend: Recommendations? = recommendationList[position]
        holder.bind(recommend,clickListener)
    }

    override fun getItemCount(): Int {
        return recommendationList.size
    }

    inner class ViewHolder(val itemRowBinding: RecommendBinding) :
        RecyclerView.ViewHolder(itemRowBinding.root) {

        fun bind(obj: Recommendations?, clickListener: CustomRecommendClickListener) {
            itemRowBinding.setVariable(BR.item, obj)
            itemRowBinding.executePendingBindings()
            clickListener.cardClicked(obj)
        }
    }


}
