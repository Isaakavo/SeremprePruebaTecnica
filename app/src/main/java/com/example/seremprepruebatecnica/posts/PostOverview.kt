package com.example.seremprepruebatecnica.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.seremprepruebatecnica.databinding.FragmentPostOverviewBinding
import kotlinx.android.synthetic.main.fragment_post_overview.*


class PostOverview : Fragment() {

    private val viewModel: PostViewModel by lazy {
        ViewModelProvider(this).get(PostViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentPostOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        binding.recyclewView.adapter = PostsAdapter(
            FavoriteClickListener { postFavorite ->
                postFavorite.isFavorite = !postFavorite.isFavorite
                binding.recyclewView.adapter?.notifyDataSetChanged()
            },
            NavigateToDetails { posts ->
                if(posts.id in 1..20 && posts.isRead ) {
                    posts.isRead = !posts.isRead
                    posts.visited = true
                }
                binding.recyclewView.adapter?.notifyDataSetChanged()
                onNavigate(posts.userId)
            }
        )

        viewModel.posts.observe(viewLifecycleOwner, { posts->
            posts.forEach { post->
                if ( post.id in 1..20 && !post.visited ){
                    post.isRead = true
                }
            }
            recyclewView.adapter?.notifyDataSetChanged()
        })

        viewModel.navigateToDetails.observe(viewLifecycleOwner, {
            it?.let{
                view?.findNavController()?.navigate(
                    PostOverviewDirections.actionPostOverviewToDetails(it)
                )
                viewModel.onDetailsNavigated()
            }
        })
        return binding.root
    }

    private fun onNavigate(id: Long) = viewModel.navigateToDetails(id)

}