package com.sally.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sally.studentapp.R
import com.squareup.picasso.Picasso
import com.sally.studentapp.databinding.FragmentStudentDetailBinding
import com.sally.studentapp.model.Student
import com.sally.studentapp.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonDetailClickListener {
    private lateinit var binding: FragmentStudentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var student: Student

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.student = Student("", "", "", "", "https://randomuser.me/api/portraits/women/79.jpg")

        if(arguments != null){
            val studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(studentId)

            binding.listener = this
            observeViewModel()
        }
    }

    fun observeViewModel(){
//        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
//            var student = it
//
//            binding.txtID.setText(viewModel.studentLD.value?.id)
//            binding.txtName.setText(viewModel.studentLD.value?.name)
//            binding.txtBod.setText(viewModel.studentLD.value?.bod)
//            binding.txtPhone.setText(viewModel.studentLD.value?.phone)
//
//            var url = viewModel.studentLD.value?.photoUrl
//            val builder = Picasso.Builder(requireContext())
//            builder.listener { picasso, uri, exception ->
//                exception.printStackTrace() }
//            builder.build().load(url).into(binding.imageView2)
//
//            binding.btnUpdate?.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.showNotification(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.baseline_person_24)
//                    }
//            }
//        })

        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            binding.student = it
        })
    }

    override fun onButtonDetailClick(v: View) {
//        Observable.timer(5, TimeUnit.SECONDS)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                Log.d("Messages", "five seconds")
//                MainActivity.showNotification(student.name.toString(),
//                    "A new notification created",
//                    R.drawable.baseline_person_24)
//            }
        Toast.makeText(context, "Student updated", Toast.LENGTH_SHORT).show()
    }
}