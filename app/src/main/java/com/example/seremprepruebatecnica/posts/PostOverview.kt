package com.example.seremprepruebatecnica.posts

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.seremprepruebatecnica.R
import com.example.seremprepruebatecnica.SwipeDelete
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

        val itemTouchHelper = ItemTouchHelper(SwipeDelete(context, viewModel))
        itemTouchHelper.attachToRecyclerView(binding.recyclewView)

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

        setHasOptionsMenu(true)
        return binding.root
    }

    //I know there are better form to manage the filter, instead o make a copy of all the list.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("All Posts")
            ?.observe(viewLifecycleOwner, { key ->
                val newList = viewModel.posts.value
                if(key == "Favorites"){
                    viewModel.setPosts(newList?.filter { it.isFavorite })
                }else if(key == "All Posts"){
                    viewModel.setPosts(viewModel.postBackUp.value)
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overview_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.refresh ->{
                viewModel.getPosts()
            }
            R.id.deletePosts -> {
                viewModel.deletePosts()
            }
            R.id.favorites -> {
                view?.findNavController()?.navigate(
                    PostOverviewDirections.actionPostOverviewToFilterFavs()
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onNavigate(id: Long) = viewModel.navigateToDetails(id)

}