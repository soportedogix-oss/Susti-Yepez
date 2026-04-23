package com.idat.appyepezjorge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.idat.appyepezjorge.adapter.MascotaAdapter
import com.idat.appyepezjorge.databinding.FragmentMascotasBinding
import com.idat.appyepezjorge.db.MascotaDatabase

class MascotasFragment : Fragment() {

    private var _binding: FragmentMascotasBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MascotaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMascotasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MascotaAdapter()

        binding.rvMascotas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMascotas.adapter = adapter

        val db = MascotaDatabase.getDatabase(requireContext())

        db.mascotaDao().listarMascotas().observe(viewLifecycleOwner) { lista ->
            adapter.actualizarLista(lista)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}