package technited.minds.assignment.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import technited.minds.assignment.R
import technited.minds.assignment.databinding.ActivityMainBinding
import technited.minds.assignment.utils.Constants.TAG
import technited.minds.assignment.utils.NetworkResource

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val postsViewModel: PostsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        setTheme(R.style.Theme_BlogsAssignment)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postsViewModel.posts.observe(this) {
            when (it) {
                is NetworkResource.Loading -> TODO()
                is NetworkResource.Success -> Log.d(TAG, "onCreate: " + it.data)
                is NetworkResource.Error -> TODO()
            }
        }
    }
}