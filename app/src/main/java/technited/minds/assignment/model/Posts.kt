package technited.minds.assignment.model


import com.google.gson.annotations.SerializedName

class Posts : ArrayList<PostsItem>()

data class PostsItem(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)