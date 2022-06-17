package technited.minds.assignment.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import technited.minds.assignment.data.repository.PostRepository
import technited.minds.assignment.model.PostsItem
import technited.minds.assignment.utils.NetworkResource
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    val posts = liveData { emit(repository.getPosts()) }
    val post: MutableLiveData<NetworkResource<PostsItem>> = MutableLiveData()

    fun getPost(cid: String) = viewModelScope.launch {
        post.postValue(repository.getPost(cid))
    }
}