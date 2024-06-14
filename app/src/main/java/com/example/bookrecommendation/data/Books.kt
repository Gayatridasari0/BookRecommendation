package com.example.bookrecommendation.data
import com.google.gson.annotations.SerializedName


data class Books(
    @SerializedName("books")
    var books: List<Book?>?
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
)