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
import com.rhmd.daftarmenubyrhmd.adapter.RvAdapterMinuman
import com.rhmd.daftarmenubyrhmd.data.MenuDB
import com.rhmd.daftarmenubyrhmd.data.MenuModelMakan
import com.rhmd.daftarmenubyrhmd.data.MenuModelMinum
import kotlinx.android.synthetic.main.minuman_fragmen.rv_minum


class MinumanFragment: Fragment() {

    companion object{
        fun getInstance(): MinumanFragment {
            return MinumanFragment()
        }
    }
        val dataMinuman= mutableListOf<MenuModelMinum>()
        val mrvAdapter= RvAdapterMinuman(dataMinuman)
        var db: MenuDB? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout.minuman_fragmen,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_minum.adapter=mrvAdapter
        rv_minum.layoutManager=LinearLayoutManager(context)

        db = MenuDB.getInstance(context!!)
        getMenuMinuman()
    }
    private fun getMenuMinuman() {
        db?.menuDao()?.ambilMenuMinuman()
            ?.observe(this, Observer {hasil ->
                when (hasil.size==0) {
                    true -> {
                        Toast.makeText(context, "Data Minuman Masih Kosong", Toast.LENGTH_SHORT).show()
                    }
                    false -> {
                        dataMinuman.clear()
                        dataMinuman.addAll(hasil)
                        mrvAdapter
                            .notifyDataSetChanged()
                    }}})}}

