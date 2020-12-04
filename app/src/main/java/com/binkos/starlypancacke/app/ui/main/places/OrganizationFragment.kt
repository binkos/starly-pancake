package com.binkos.starlypancacke.app.ui.main.places

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.common.extensions.tryToGetStringOrNull
import com.binkos.starlypancacke.app.ui.base.BaseFragment
import com.binkos.starlypancacke.app.ui.main.MainMapViewModel
import com.binkos.starlypancacke.domain.model.Organization
import com.bumptech.glide.Glide
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
        Glide
            .with(requireContext())
            .load(o.icon)
            .centerCrop()
            .into(iconImageView)

        toolbar_organization.setNavigationOnClickListener {
            vm.backToMap()
        }

        toolbar_organization
            .menu
            .getItem(0)
            .setOnMenuItemClickListener {
                Toast.makeText(requireContext(), "Sharing", Toast.LENGTH_LONG).show()
                true
            }

        descriptionTextView.text = o.description
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