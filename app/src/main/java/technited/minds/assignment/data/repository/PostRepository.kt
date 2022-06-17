package technited.minds.assignment.data.repository

import technited.minds.assignment.data.remote.RemoteDataSource
import javax.inject.Inject

class PostRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    //     Get All Posts
    suspend fun getPosts() = remoteDataSource.getPosts()

    //    Get Single Post
    suspend fun getPost(postId: String) = remoteDataSource.getPost(postId)
}