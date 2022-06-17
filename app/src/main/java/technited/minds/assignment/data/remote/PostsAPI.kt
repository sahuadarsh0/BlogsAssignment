package technited.minds.assignment.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import technited.minds.assignment.model.Posts
import technited.minds.assignment.model.PostsItem

interface PostsAPI {
    @GET("posts")
    suspend fun getPosts() : Response<Posts>

    @GET("posts/{postId}")
    suspend fun getPost(@Path("postId") postId:String) : Response<PostsItem>
}