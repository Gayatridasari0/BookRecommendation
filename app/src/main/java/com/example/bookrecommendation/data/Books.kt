package com.example.bookrecommendation.data
import com.example.bookrecommendation.R
import com.example.bookrecommendation.utils.WidgetViewModel
import com.google.gson.annotations.SerializedName


data class Books(
    @SerializedName("books")
    var books: ArrayList<Book>?
)


data class Book(
    @SerializedName("author")
    var author: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("rating")
    var rating: Double?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("votes")
    var votes: Int?
): WidgetViewModel {
    override fun layoutId(): Int {
        return R.layout.book
    }
}