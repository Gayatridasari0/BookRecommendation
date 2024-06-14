package com.example.bookrecommendation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookrecommendation.R
import com.example.bookrecommendation.adapter.RecommendationAdapter
import com.example.bookrecommendation.data.Recommendations
import com.example.bookrecommendation.databinding.FragmentBookBinding
import com.example.bookrecommendation.viewModel.HomeViewModel


class BookFragment : Fragment(), RecommendationAdapter.CustomRecommendClickListener {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var _binding : FragmentBookBinding
    private var bundle = Bundle()
    private var bookSearch : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBookBinding.inflate(layoutInflater)
        _binding.lifecycleOwner = this
        _binding.vm = viewModel

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isProgress.set(true)
        arguments?.containsKey("bookSearch")?.let {
            bookSearch = bundle.getString("bookSearch").toString()
        }
       /* arguments?.containsKey("bookSearch")?.let {
            bookSearch = Gson().fromJson(
                bundle.getString("bookSearch"),
                Book::class.java
            )
        }*/
        bookSearch.let { title ->
            viewModel.fetchRecommendationsFromApi(title)?.observe(viewLifecycleOwner) {
                viewModel.isProgress.set(false)
                viewModel.recommendationData =
                    (it?.recommendations ?: ArrayList()) as ArrayList<Recommendations?>
                viewModel.recommendationData.let {
                    val myRecyclerViewAdapter = RecommendationAdapter(it, this, this)
                    _binding.setMyAdapter(myRecyclerViewAdapter)
                }
            }
        }
    }

    override fun cardClicked(recommend: Recommendations?) {
        bundle.putString("bookSearch",recommend?.title)
//        bundle.putString("bookSearch",Gson().toJson(recommend))
        findNavController().navigate(R.id.bookFragment)
    }

}