package com.example.modul4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul4.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    private lateinit var adapter: ListConstellationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListConstellationsAdapter(
            onWebClick = { constellation ->
                viewModel.onWebClicked(constellation)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(constellation.webUrl))
                startActivity(intent)
            },
            onDetailClick = { constellation ->
                viewModel.onDetailClicked(constellation)
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(constellation)
                findNavController().navigate(action)
            }
        )

        binding.rvStars.layoutManager = LinearLayoutManager(requireContext())
        binding.rvStars.adapter = adapter

        lifecycleScope.launch {
            viewModel.constellations.collectLatest {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
