package com.example.intermediate_baru.STORY.RESEP.VIEWPAGER

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.intermediate_baru.STORY.RESEP.step_resep

class SectionPage(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = bahan_makanan()
            1 -> fragment = step_resep()
        }
        return fragment as Fragment
    }
}