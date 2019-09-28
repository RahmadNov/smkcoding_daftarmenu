package com.rhmd.daftarmenubyrhmd.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rhmd.daftarmenubyrhmd.R
import com.rhmd.daftarmenubyrhmd.R.layout
import com.rhmd.daftarmenubyrhmd.data.MenyuModel
import kotlinx.android.synthetic.main.minuman_fragmen.rv_minum

class MinumanFragment: Fragment() {

    companion object{
        fun getInstance(): MinumanFragment {
            return MinumanFragment()
        }
    }
        val dataMinuman= mutableListOf<MenyuModel>()
        val rvAdapter=RvAdapter(dataMinuman)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout.minuman_fragmen,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_minum.adapter=rvAdapter
        rv_minum.layoutManager=LinearLayoutManager(context)
        addmyData()
    }
    private fun addmyData(){
        dataMinuman.add(MenyuModel("Es Kopi","Rp. 3.000", R.drawable.kopi))
        dataMinuman.add(MenyuModel("Es Teh","Rp. 3.000",R.drawable.teh))
        dataMinuman.add(MenyuModel("Es Teler","Rp. 5.000",R.drawable.teler))
        rvAdapter.notifyDataSetChanged()
    }
    }
