package com.example.seremprepruebatecnica.posts

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.seremprepruebatecnica.R
import com.example.seremprepruebatecnica.databinding.FragmentFilterFavsBinding

class FilterFavs : DialogFragment() {


    private val viewModel: FilterViewModel by lazy {
        ViewModelProvider(this).get(FilterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFilterFavsBinding.inflate(inflater)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        viewModel._allPosts.observe(viewLifecycleOwner, {
            if (it){
                viewModel._favorites.value = false
            }
        })

        viewModel._favorites.observe(viewLifecycleOwner, {
            if (it){
                viewModel._allPosts.value = false
            }
        })

        viewModel.returntoOverview.observe(viewLifecycleOwner, {
            it?.let {
                if(it){
                    if (viewModel._allPosts.value == true){
                        Log.d("nel", "En el all posts")
                        findNavController().previousBackStackEntry?.savedStateHandle?.set("All Posts", "All Posts")
                    }else if (viewModel._favorites.value == true){
                        Log.d("nel", "En el favs")
                        findNavController().previousBackStackEntry?.savedStateHandle?.set("All Posts", "Favorites")
                    }
                    dialog?.dismiss()
                }

            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setGravity(Gravity.CENTER_HORIZONTAL)
    }
}