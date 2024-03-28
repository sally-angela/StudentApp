package com.sally.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sally.studentapp.databinding.FragmentDogListBinding
import com.sally.studentapp.viewmodel.DogViewModel

class DogListFragment : Fragment() {
    private lateinit var binding: FragmentDogListBinding
    private lateinit var viewModel: DogViewModel
    private val dogListAdapter  = DogListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDogListBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DogViewModel::class.java)
        viewModel.refresh()

        binding.dogRecView.layoutManager = LinearLayoutManager(context)
        binding.dogRecView.adapter = dogListAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.dogsLD.observe(viewLifecycleOwner, Observer {
            dogListAdapter.updateDogList(it)
        })
    }
}