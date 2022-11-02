package com.practice.gameapp.ui.fragments.home

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practice.gameapp.databinding.FragmentHomeBinding
import com.practice.gameapp.domain.models.GameModel
import com.practice.gameapp.ui.adapters.GameAdapter
import com.practice.gameapp.ui.viewmodels.HomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment : Fragment() {


    //RecyclerView and Adapter
    private lateinit var recycler: RecyclerView
    private lateinit var gameAdapter: GameAdapter
    //ViewModel
    private val homeViewModel: HomeViewModel by sharedViewModel()
    //ViewBinding
    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //Observers
    private val gameListObserver = Observer<List<GameModel>> {
        gameAdapter.notifyDataSetChanged()
    }

    private val gameOfTheDayObserver = Observer<Uri> { newUri ->
        Picasso.get().load(newUri).into(binding.ivGameOfTheDay)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
         */

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            homeViewModel.fillGamesList()
        }

        homeViewModel.beerImageUrl.observe(viewLifecycleOwner,gameOfTheDayObserver)

        buildRecyclerView()


        return binding.root
    }

    private fun buildRecyclerView() {
        recycler = binding.rvGameList
        recycler.setHasFixedSize(true)
        recycler.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        gameAdapter = GameAdapter(homeViewModel.allGamesList)
        recycler.adapter = gameAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}