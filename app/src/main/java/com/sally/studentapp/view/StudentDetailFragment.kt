package com.sally.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.sally.studentapp.databinding.FragmentStudentDetailBinding
import com.sally.studentapp.viewmodel.DetailViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var binding: FragmentStudentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            binding.txtID.setText(viewModel.studentLD.value?.id)
            binding.txtName.setText(viewModel.studentLD.value?.name)
            binding.txtBod.setText(viewModel.studentLD.value?.bod)
            binding.txtPhone.setText(viewModel.studentLD.value?.phone)

            var url = viewModel.studentLD.value?.photoUrl
            val builder = Picasso.Builder(requireContext())
            builder.listener { picasso, uri, exception ->
                exception.printStackTrace() }
            builder.build().load(url).into(binding.imageView2)
        })
    }
}