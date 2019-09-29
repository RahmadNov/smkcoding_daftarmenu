package com.rhmd.daftarmenubyrhmd.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rhmd.daftarmenubyrhmd.R.layout
import com.rhmd.daftarmenubyrhmd.adapter.RvAdapterMakanan.MenuViewHolder
import com.rhmd.daftarmenubyrhmd.data.MenuModelMakan
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_menu.harga
import kotlinx.android.synthetic.main.item_menu.img
import kotlinx.android.synthetic.main.item_menu.judul

class RvAdapterMakanan(private val data: List<MenuModelMakan>) :
    RecyclerView.Adapter<MenuViewHolder>() {

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.binData(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(layout.item_menu, parent, false)
        )
    }

    class MenuViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView)
        , LayoutContainer
    {
        fun binData(item:MenuModelMakan){
            judul.text=item.namaMenu
            harga.text=item.hargaMenu
            Glide.with(containerView)
                .load(item.gambarMenu)
                .into(img)

            itemView.setOnClickListener {
                Toast.makeText(containerView.context,item.namaMenu,Toast.LENGTH_SHORT).show()
            }
        }
    }
}