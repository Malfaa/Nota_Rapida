package com.malfaa.notaapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.malfaa.notaapp.R
import com.malfaa.notaapp.databinding.MainNotasFragmentBinding
import com.malfaa.notaapp.room.Nota
import com.malfaa.notaapp.room.NotaDatabase

class MainNotasFragment : Fragment() {

    companion object {
        fun newInstance() = MainNotasFragment()
    }

    private lateinit var viewModel: MainNotasViewModel
    private lateinit var binding: MainNotasFragmentBinding
    private lateinit var manager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_notas_fragment, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dataSource = NotaDatabase.getDatabase(application).notaDao()
        val viewModelFactory = MainNotasViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainNotasViewModel::class.java)

        viewModel.data()

        binding.adcBtn.setOnClickListener {
            viewModel.adicionandoDatabase(Nota(binding.notaTexto.toString()))
            binding.notaTexto.setText("")
        }

        // TODO: 21/07/2021 recyclerview
        manager= LinearLayoutManager(context)

//        binding.notarRecycler.apply {
//            viewModel.dataList.observe(viewLifecycleOwner, {
//                adapter = MainAdapter(viewModel.dataList)
//                layoutManager = manager
//            })
//
//        }
    }

}