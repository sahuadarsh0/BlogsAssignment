package technited.minds.assignment.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
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
import technited.minds.assignment.model.Posts
import technited.minds.assignment.model.PostsItem
import technited.minds.assignment.ui.adapters.PostsAdapter
import technited.minds.assignment.utils.Constants.TAG
import technited.minds.assignment.utils.NetworkResource

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val postsViewModel: PostsViewModel by viewModels()
    private val postsAdapter = PostsAdapter(this::onItemClicked)
    private lateinit var allPosts: Posts

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

//        when Search field is empty
        binding.searchPosts.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (s.isNullOrEmpty()) {
                    postsAdapter.submitList(allPosts)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun setupRecyclerView() {
        binding.postList.adapter = postsAdapter
    }

    private fun setupObservers() {

        // All Posts
        postsViewModel.posts.observe(this) {
            when (it) {
                is NetworkResource.Loading -> binding.progressCircular.visibility = View.VISIBLE
                is NetworkResource.Success -> {
                    it.data?.let { allPosts = it }
                    postsAdapter.submitList(it.data)
                    binding.progressCircular.visibility = View.GONE
                }
                is NetworkResource.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "setupObservers: all posts error ${it.message}")
                }
            }
        }

//        Single Post
        postsViewModel.post.observe(this) {
            when (it) {
                is NetworkResource.Loading -> binding.progressCircular.visibility = View.VISIBLE
                is NetworkResource.Success -> {
                    postsAdapter.submitList(listOf(it.data))
                    binding.progressCircular.visibility = View.GONE
                }
                is NetworkResource.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "setupObservers: post error ${it.message}")
                }
            }
        }
    }

    private fun onItemClicked(postsItem: PostsItem) {
        Toast.makeText(applicationContext, "Post Clicked ${postsItem.id}", Toast.LENGTH_SHORT).show()
    }

}