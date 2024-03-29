package com.rhmd.daftarmenubyrhmd.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rhmd.daftarmenubyrhmd.R.layout
import kotlinx.android.synthetic.main.activity_main.vp_main
import kotlinx.android.synthetic.main.activity_main.tabs_main


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        vp_main.adapter=ViewPagerAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(vp_main)
    }
    inner class ViewPagerAdapter(fm: FragmentManager)
        : FragmentPagerAdapter(fm) {

        private val pages = listOf(
            MakananFragment.getInstance(),
            MinumanFragment.getInstance(),
            AddFragment.getInstance()
        )

        override fun getItem(position: Int): Fragment {
            return pages[position]
        }

        override fun getCount(): Int {
            return pages.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when(position){
                0->"Makanan"
                1->"Minuman"
                else->"Tambah Data"
            }
        }
    }
}

