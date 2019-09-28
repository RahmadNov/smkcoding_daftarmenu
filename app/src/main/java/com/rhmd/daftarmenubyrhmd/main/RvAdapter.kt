package com.rhmd.daftarmenubyrhmd.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rhmd.daftarmenubyrhmd.R
import com.rhmd.daftarmenubyrhmd.data.MenyuModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_menu.harga
import kotlinx.android.synthetic.main.item_menu.img
import kotlinx.android.synthetic.main.item_menu.judul

class RvAdapter(private val data: List<MenyuModel>) :
    RecyclerView.Adapter<RvAdapter.MenuViewHolder>() {

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.binData(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu,parent, false)
        )
    }

    class MenuViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView)
        , LayoutContainer
    {
        fun binData(item:MenyuModel){
            judul.text=item.namaMenu
            harga.text=item.hargaMenu
            img.setImageResource(item.gambarMenu)

            itemView.setOnClickListener {
                Toast.makeText(containerView.context,item.namaMenu,Toast.LENGTH_SHORT).show()
            }
        }
    }
}