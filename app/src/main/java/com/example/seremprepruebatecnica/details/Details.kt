package com.example.seremprepruebatecnica.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.seremprepruebatecnica.R
import com.example.seremprepruebatecnica.databinding.FragmentDetailsBinding
import com.example.seremprepruebatecnica.posts.PostViewModel

class Details : Fragment() {

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = DetailsArgs.fromBundle(requireArguments())

        val binding = FragmentDetailsBinding.inflate(inflater)
        binding.user = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getUser(args.userId)



        return binding.root
    }
}