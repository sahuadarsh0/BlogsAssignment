package technited.minds.assignment.data.remote

import technited.minds.assignment.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val postsAPI: PostsAPI) : BaseDataSource() {

    //     Get All Posts
    suspend fun getPosts() = getResult { postsAPI.getPosts() }

    //    Get Single Post
    suspend fun getPost(postId: String) = getResult { postsAPI.getPost(postId) }
}