package technited.minds.assignment.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import technited.minds.assignment.databinding.ItemListPostsBinding
import technited.minds.assignment.model.PostsItem

class PostsAdapter(private val onItemClicked: (PostsItem) -> Unit) :
    ListAdapter<PostsItem, PostsAdapter
    .PostsViewHolder>(DIFFUTIL_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder =
        PostsViewHolder(
            ItemListPostsBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )


    companion object {
        private val DIFFUTIL_CALLBACK = object : DiffUtil.ItemCallback<PostsItem>() {
            override fun areItemsTheSame(oldItem: PostsItem,newItem: PostsItem): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: PostsItem,newItem: PostsItem): Boolean =
                oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    inner class PostsViewHolder(private val binding: ItemListPostsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            post: PostsItem,
            onItemClicked: (PostsItem) -> Unit
        ) {
            binding.post = post
            binding.root.setOnClickListener { onItemClicked(post) }
        }
    }
}