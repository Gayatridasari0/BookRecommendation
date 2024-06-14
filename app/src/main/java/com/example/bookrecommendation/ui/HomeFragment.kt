package com.example.bookrecommendation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookrecommendation.R
import com.example.bookrecommendation.adapter.BooksAdapter
import com.example.bookrecommendation.data.Book
import com.example.bookrecommendation.databinding.FragmentHomeBinding
import com.example.bookrecommendation.viewModel.HomeViewModel
import com.google.gson.Gson


class HomeFragment : Fragment(),BooksAdapter.CustomBookClickListener {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var _binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        _binding.lifecycleOwner = this
        _binding.vm = viewModel

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isProgress.set(true)
        viewModel.fetchBooksFromApi()
        viewModel.booksResponse.observe(viewLifecycleOwner) {
            viewModel.isProgress.set(false)
            viewModel.booksData =
                (it?.books ?: ArrayList()) as ArrayList<Book?>
            viewModel.booksData.let {
                val myRecyclerViewAdapter = BooksAdapter(it, this, this)
                _binding.setMyAdapter(myRecyclerViewAdapter)
            }
        }
    }

    override fun cardClicked(book: Book?) {
        var bundle = Bundle()
        bundle.putString("bookSearch",book?.title)
//        bundle.putString("bookSearch", Gson().toJson(book))
        findNavController().navigate(R.id.bookFragment)
    }

}