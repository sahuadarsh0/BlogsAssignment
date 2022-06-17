package technited.minds.assignment.data.remote

import technited.minds.assignment.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val postsAPI: PostsAPI) : BaseDataSource() {
}