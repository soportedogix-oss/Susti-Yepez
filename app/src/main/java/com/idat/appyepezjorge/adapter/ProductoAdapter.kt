package com.idat.appyepezjorge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idat.appyepezjorge.databinding.ItemProductoBinding
import com.idat.appyepezjorge.model.Producto

class ProductoAdapter : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    private val lista = mutableListOf<Producto>()

    fun actualizarLista(nuevaLista: List<Producto>) {
        lista.clear()
        lista.addAll(nuevaLista)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size

    class ProductoViewHolder(private val binding: ItemProductoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(producto: Producto) {
            binding.tvTitle.text = producto.title
            binding.tvPrice.text = "Precio: ${producto.price}"
            binding.tvCategory.text = "Categoría: ${producto.category}"

            Glide.with(binding.root.context)
                .load(producto.thumbnail)
                .into(binding.ivThumbnail)
        }
    }
}