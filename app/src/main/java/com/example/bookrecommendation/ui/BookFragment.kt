package com.example.bookrecommendation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookrecommendation.utils.setUpMultiViewRecyclerAdapter
import com.example.bookrecommendation.BR
import com.example.bookrecommendation.R
import com.example.bookrecommendation.data.Book
import com.example.bookrecommendation.data.Recommendations
import com.example.bookrecommendation.databinding.FragmentBookBinding
import com.example.bookrecommendation.utils.WidgetViewModel
import com.example.bookrecommendation.viewModel.HomeViewModel


class BookFragment : Fragment(){

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var _binding : FragmentBookBinding
    private var bundle = Bundle()

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
        arguments?.getString("bookSearch")?.let {
//            val bookSearch = bundle.getString("bookSearch").toString()
            viewModel.fetchRecommendationsFromApi(it)?.observe(viewLifecycleOwner) {
                viewModel.isProgress.set(false)
                viewModel.recommendationData = it?.recommendations as ArrayList<Recommendations>
                viewModel.recommendationData.let {
                    _binding.rvBooks.setUpMultiViewRecyclerAdapter(
                        it
                    ) { item: WidgetViewModel, binder: ViewDataBinding, position: Int ->
                        binder.setVariable(BR.item, item)
                        binder.setVariable(BR.clickListener,View.OnClickListener {
                            when(it.id) {
                                R.id.cl_book -> {
                                    var bundle = Bundle()
                                    bundle.putString("bookSearch",(item as Recommendations).title)
                                    //bundle.putString("bookSearch", Gson().toJson(book))
                                    findNavController().navigate(R.id.bookFragment,bundle)
                                }
                            }
                        })
                    }

                }

            }

        }
       /* arguments?.containsKey("bookSearch")?.let {
            bookSearch = Gson().fromJson(
                bundle.getString("bookSearch"),
                Book::class.java
            )
        }*/

    }


}