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
import kotlinx.android.synthetic.main.makanan_fragmen.rv_makan

class MakananFragment: Fragment() {

    companion object{
        fun getInstance(): MakananFragment {
            return MakananFragment()
        }
    }
    val dataMakanan= mutableListOf<MenyuModel>()
    val rvAdapter=RvAdapter(dataMakanan)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout.makanan_fragmen,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_makan.adapter=rvAdapter
        rv_makan.layoutManager=LinearLayoutManager(context)
        addDummyData()
    }
    private fun addDummyData(){
        dataMakanan.add(MenyuModel("Kalong Bakar","Rp. 15.000",R.drawable.kalongbakar ))
        dataMakanan.add(MenyuModel("Sate Tikus","Rp. 12.000",R.drawable.satetikos))
        dataMakanan.add(MenyuModel("Sego Kucing","Rp. 10.000",R.drawable.segokucing))
        rvAdapter.notifyDataSetChanged()
    }
}