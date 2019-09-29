package com.rhmd.daftarmenubyrhmd.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rhmd.daftarmenubyrhmd.R.layout
import com.rhmd.daftarmenubyrhmd.adapter.RvAdapterMakanan
import com.rhmd.daftarmenubyrhmd.data.MenuDB
import com.rhmd.daftarmenubyrhmd.data.MenuDB.Companion
import com.rhmd.daftarmenubyrhmd.data.MenuModelMakan
import kotlinx.android.synthetic.main.makanan_fragmen.rv_makan

class MakananFragment : Fragment() {

    companion object {
        fun getInstance(): MakananFragment {
            return MakananFragment()
        }
    }

    val dataMakanan = mutableListOf<MenuModelMakan>()
    //    lateinit var mRvAdapterMakanan: RvAdapterMakanan
    val mRvAdapterMakanan = RvAdapterMakanan(dataMakanan)
    var db: MenuDB? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout.makanan_fragmen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_makan.adapter = mRvAdapterMakanan
        rv_makan.layoutManager = LinearLayoutManager(context)

        db = MenuDB.getInstance(context!!)
        getMenuMakanan()
    }

    private fun getMenuMakanan() {
        db?.menuDao()?.ambilMenuMakanan()
            ?.observe(this, Observer { hasil ->
                when (hasil.size == 0) {
                    true -> {
                        Toast.makeText(context, "Data Makanan Masih Kosong", Toast.LENGTH_SHORT).show()
                    }
                    false -> {
                        dataMakanan.clear()
                        dataMakanan.addAll(hasil)
                        mRvAdapterMakanan
                            .notifyDataSetChanged()
                    }
                }
            })
    }
}