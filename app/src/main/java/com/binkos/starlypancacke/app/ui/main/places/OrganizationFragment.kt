package com.binkos.starlypancacke.app.ui.main.places

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.common.extensions.gone
import com.binkos.starlypancacke.app.common.extensions.tryToGetStringOrNull
import com.binkos.starlypancacke.app.common.extensions.visible
import com.binkos.starlypancacke.app.ui.base.BaseFragment
import com.binkos.starlypancacke.app.ui.main.MainMapViewModel
import com.binkos.starlypancacke.domain.model.Organization
import kotlinx.android.synthetic.main.fragment_organization.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OrganizationFragment : BaseFragment() {

    private val vm: MainMapViewModel by sharedViewModel()

    override fun viewReady() {
        tryToGetStringOrNull(ORGANIZATION_NAME_KEY)?.let {
            vm.launchOrgSearch(it)
        }

        vm
            .orgLiveData
            .observe(viewLifecycleOwner) {
                initViews(it.first())
            }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_organization
    }

    override fun onBackPressed() {
        vm.backToMap()
    }

    private fun initViews(o: Organization) {
        toolbar_title.text = o.name
        if (o.photos.isEmpty()) {
            currentPhotoIndexTextView.gone()
        }
        galleryViewPager.apply {
            visible()
            adapter = PhotoOrganizationAdapter(requireActivity(), o.photos)
            currentPhotoIndexTextView.text =
                getString(R.string.indexes_photo_format, 1, o.photos.size)

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    currentPhotoIndexTextView.text =
                        getString(R.string.indexes_photo_format, position + 1, o.photos.size)
                    super.onPageSelected(position)
                }
            })
        }

        menuRecyclerView.apply {
            adapter = OrganizationMenuAdapter(o.menu)

            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }


        toolbar_organization.setNavigationOnClickListener {
            vm.backToMap()
        }

        toolbar_organization
            .menu
            .getItem(0)
            .setOnMenuItemClickListener {
                shareOrganization(o.name)
                true
            }

        descriptionTextView.text = o.description
    }

    private fun shareOrganization(key: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "https://starly-pancake/organization?name=$key")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    companion object {
        const val ORGANIZATION_NAME_KEY = "ORGANIZATION_NAME"

        fun newInstance(name: String): OrganizationFragment {
            return OrganizationFragment().apply {
                arguments = Bundle().apply {
                    putString(ORGANIZATION_NAME_KEY, name)
                }
            }
        }
    }
}