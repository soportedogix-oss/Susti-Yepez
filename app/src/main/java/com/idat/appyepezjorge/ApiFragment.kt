package com.idat.appyepezjorge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.idat.appyepezjorge.adapter.ProductoAdapter
import com.idat.appyepezjorge.api.RetrofitClient
import com.idat.appyepezjorge.databinding.FragmentApiBinding
import com.idat.appyepezjorge.model.ProductoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiFragment : Fragment() {

    private var _binding: FragmentApiBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ProductoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductoAdapter()

        binding.rvProductos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProductos.adapter = adapter

        cargarProductos()
    }

    private fun cargarProductos() {
        RetrofitClient.apiService.obtenerProductos().enqueue(object : Callback<ProductoResponse> {
            override fun onResponse(
                call: Call<ProductoResponse>,
                response: Response<ProductoResponse>
            ) {
                if (response.isSuccessful) {
                    val lista = response.body()?.products ?: emptyList()
                    val listaFiltrada = lista.filter { it.price > 100 }
                    adapter.actualizarLista(listaFiltrada)
                } else {
                    Toast.makeText(requireContext(), "Error al obtener productos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProductoResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}