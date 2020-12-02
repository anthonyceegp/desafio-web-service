package com.example.marvelheroes.comiclist.views

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes.R
import com.example.marvelheroes.api.utils.Constant
import com.example.marvelheroes.comiclist.models.Comic
import com.example.marvelheroes.comiclist.models.GridDecoration
import com.example.marvelheroes.comiclist.repository.ComicRepository
import com.example.marvelheroes.comiclist.viewmodel.ComicViewModel
import com.google.android.material.appbar.MaterialToolbar

class ComicsFragment : Fragment(), OnComicClickListener {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var recycler: RecyclerView
    private lateinit var comicsAdapter: ComicsAdapter
    private lateinit var comicViewModel: ComicViewModel
    private val comics = mutableListOf<Comic>()
    private var offset = 0
    private var total = 0
    private val limit = 20

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager = GridLayoutManager(view.context, 3)

        initToolbar(view)

        recycler = view.findViewById(R.id.comics)
        comicsAdapter = ComicsAdapter(view.context, comics, this)

        recycler.apply {
            layoutManager = manager
            adapter = comicsAdapter
            addItemDecoration(GridDecoration(3, 30, true))
        }

        comicViewModel = ViewModelProvider(
            this,
            ComicViewModel.ComicViewModelFactory(ComicRepository())
        ).get(ComicViewModel::class.java)

        getComics()

        Handler(Looper.getMainLooper()).postDelayed({
            setInfiniteScroll()
        },3000)
    }

    private fun getComics() {
        comicViewModel.getComics(offset, limit).observe(viewLifecycleOwner, {
            comics.addAll(it.data.results)
            comicsAdapter.notifyDataSetChanged()

            offset = it.data.offset + it.data.count
            total = it.data.total
        })
    }

    private fun initToolbar(view: View) {
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        val activity = activity as AppCompatActivity

        activity.setSupportActionBar(toolbar)

        activity.supportActionBar?.setDisplayShowTitleEnabled(false)

        navController = findNavController()
        appBarConfiguration = AppBarConfiguration(navController.graph)

        NavigationUI.setupActionBarWithNavController(activity, navController, appBarConfiguration)
    }

    private fun setInfiniteScroll() {
        recycler.run {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val target = recyclerView.layoutManager as GridLayoutManager
                    val totalItemCount = target.itemCount
                    val lastVisible = target.findLastVisibleItemPosition()

                    if (totalItemCount - lastVisible < 10 && totalItemCount < total) {
                        getComics()
                    }
                }
            })
        }
    }

    override fun onComicClick(position: Int) {
        val bundle = bundleOf(Constant.COMIC to comics[position])
        findNavController().navigate(R.id.comicDetailsFragment, bundle)
    }
}