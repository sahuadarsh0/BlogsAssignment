package technited.minds.assignment.ui

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import technited.minds.assignment.R
import technited.minds.assignment.databinding.ActivityMainBinding
import technited.minds.assignment.model.PostsItem
import technited.minds.assignment.ui.adapters.PostsAdapter
import technited.minds.assignment.utils.NetworkResource

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val postsViewModel: PostsViewModel by viewModels()
    private val postsAdapter = PostsAdapter(this::onItemClicked)


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        setTheme(R.style.Theme_BlogsAssignment)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        setupSearch()
        setupRecyclerView()
        setupObservers()

    }

    private fun setupSearch() {
        binding.searchPosts.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchText = binding.searchPosts.text.toString()
                if (searchText.isNotBlank()) {
                    postsViewModel.getPost(searchText)
                } else {
                    Toast.makeText(applicationContext, "Type something", Toast.LENGTH_SHORT).show()
                }

                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun setupRecyclerView() {
        binding.postList.adapter = postsAdapter
    }

    private fun setupObservers() {

        // All Posts
        postsViewModel.posts.observe(this) {
            when (it) {
                is NetworkResource.Loading -> TODO()
                is NetworkResource.Success -> {
                    postsAdapter.submitList(it.data)
                }
                is NetworkResource.Error -> TODO()
            }
        }

//        Single Post
        postsViewModel.post.observe(this) {
            when (it) {
                is NetworkResource.Loading -> TODO()
                is NetworkResource.Success -> {
                    postsAdapter.submitList(listOf(it.data))
                }
                is NetworkResource.Error -> TODO()
            }
        }
    }

    private fun onItemClicked(postsItem: PostsItem) {
//        TODO("Not yet implemented")
    }

}