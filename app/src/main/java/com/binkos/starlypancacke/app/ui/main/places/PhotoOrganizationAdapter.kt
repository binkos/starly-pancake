package com.binkos.starlypancacke.app.ui.main.places

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PhotoOrganizationAdapter(
    activity: FragmentActivity,
    private val listPhotos: List<String>
) : FragmentStateAdapter(activity) {

    override fun getItemCount() = listPhotos.size

    override fun createFragment(position: Int): Fragment {
        return PhotoFragment.getInstance(listPhotos[position])
    }
}