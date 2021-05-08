package com.example.seremprepruebatecnica.posts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.seremprepruebatecnica.R
import com.example.seremprepruebatecnica.databinding.FragmentPostOverviewBinding


class PostOverview : Fragment() {

    private val viewModel: PostViewModel by lazy {
        ViewModelProvider(this).get(PostViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentPostOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        binding.recyclewView.adapter = PostsAdapter(
            FavoriteClickListener { postFavorite ->  Log.d("fav", "$postFavorite")},
            NavigateToDetails { id -> onNavigate(id) }
        )

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