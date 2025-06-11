package com.example.modul5.ui.home

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
import com.example.modul5.databinding.FragmentHomeBinding
import com.example.modul5.ui.adapter.ListConstellationsAdapter
import com.example.modul5.viewmodel.MainViewModel
import com.example.modul5.viewmodel.MainViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ListConstellationsAdapter(
            onWebClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.webUrl))
                startActivity(intent)
            },
            onDetailClick = {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                findNavController().navigate(action)
            }
        )

        binding.rvStars.layoutManager = LinearLayoutManager(requireContext())
        binding.rvStars.adapter = adapter

        lifecycleScope.launch {
            viewModel.state.collectLatest { data ->
                adapter.submitList(data)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
