package com.example.bookrecommendation.data
import com.example.bookrecommendation.R
import com.example.bookrecommendation.utils.WidgetViewModel
import com.google.gson.annotations.SerializedName

data class RecommendationReq(
    @SerializedName("book_name")
    var bookName: String?
)
data class RecommendationList(
    @SerializedName("recommendations")
    var recommendations: List<Recommendations>?
)

data class Recommendations(
    @SerializedName("author")
    var author: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("title")
    var title: String?
): WidgetViewModel {
    override fun layoutId(): Int {
        return R.layout.recommend
    }
}