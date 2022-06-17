package technited.minds.assignment.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import technited.minds.assignment.R
import technited.minds.assignment.databinding.ActivityMainBinding
import technited.minds.assignment.model.PostsItem
import technited.minds.assignment.ui.adapters.PostsAdapter
import technited.minds.assignment.utils.Constants.TAG
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
        TODO("Not yet implemented")
    }

    private fun setupRecyclerView() {
        TODO("Not yet implemented")
    }

    private fun setupObservers() {

        postsViewModel.posts.observe(this) {
            when (it) {
                is NetworkResource.Loading -> TODO()
                is NetworkResource.Success -> Log.d(TAG, "onCreate: " + it.data)
                is NetworkResource.Error -> TODO()
            }
        }
    }

    private fun onItemClicked(postsItem: PostsItem) {
        TODO("Not yet implemented")
    }

}