package com.example.bookrecommendation.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookrecommendation.data.Book
import com.example.bookrecommendation.data.Books
import com.example.bookrecommendation.data.RecommendationList
import com.example.bookrecommendation.data.RecommendationReq
import com.example.bookrecommendation.data.Recommendations
import com.notification.odds.retrofitrelated.RetrofitBuilder
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel : ViewModel() {
    var isProgress = ObservableField(false)
    var booksData = ArrayList<Book?>()
    var recommendationData = ArrayList<Recommendations?>()

    private val _booksResponse = MutableLiveData<Books?>()
    val booksResponse :LiveData<Books?> = _booksResponse

    fun fetchBooksFromApi() {
        viewModelScope.launch {
            try {
                val response = RetrofitBuilder.apiService.getBooks()
                if (response.isSuccessful) {
                    _booksResponse.value = response.body()
                } else {
                    isProgress.set(false)
                    throw IOException("Error fetching data")
                }
            } catch (e: Exception) {
                isProgress.set(false)
                e.printStackTrace()
            }
        }
    }

    fun fetchRecommendationsFromApi(title:String) : LiveData<RecommendationList?>? {
        val recommendationReq = RecommendationReq(title)
        val recommendationRes = MutableLiveData<RecommendationList?>()
        viewModelScope.launch {
            try {
                val response = RetrofitBuilder.apiService.getRecommendations(recommendationReq)
                if (response.isSuccessful) {
                    recommendationRes.value = response.body()
                } else {
                    isProgress.set(false)
                    throw IOException("Error fetching data")
                }
            } catch (e: Exception) {
                isProgress.set(false)
                e.printStackTrace()
            }
        }
        return  recommendationRes
    }


}