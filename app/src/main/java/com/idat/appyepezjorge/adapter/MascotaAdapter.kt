package com.idat.appyepezjorge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idat.appyepezjorge.databinding.ItemMascotaBinding
import com.idat.appyepezjorge.model.Mascota

class MascotaAdapter : RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>() {

    private val lista = mutableListOf<Mascota>()

    fun actualizarLista(nuevaLista: List<Mascota>) {
        lista.clear()
        lista.addAll(nuevaLista)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaViewHolder {
        val binding = ItemMascotaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MascotaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MascotaViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size

    class MascotaViewHolder(private val binding: ItemMascotaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mascota: Mascota) {
            binding.tvCodigo.text = "Código: ${mascota.codigo}"
            binding.tvNombre.text = "Nombre: ${mascota.nombre}"
            binding.tvTipo.text = "Tipo: ${mascota.tipo}"
            binding.tvEdad.text = "Edad: ${mascota.edad}"
        }
    }
}