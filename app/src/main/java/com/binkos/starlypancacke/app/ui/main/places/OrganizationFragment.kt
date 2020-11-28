package com.binkos.starlypancacke.app.ui.main.places

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.ui.base.BaseFragment
import com.binkos.starlypancacke.app.ui.main.MainMapViewModel
import com.binkos.starlypancacke.domain.model.Organization
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OrganizationFragment : BaseFragment() {

    private val vm: MainMapViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val orgName = arguments?.getString(ORGANIZATION_NAME_KEY)
        orgName?.let {
            vm
                .findOrganization(it)
                .observe(viewLifecycleOwner) { org ->
                    initViews(org)
                }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun viewReady() {
    }

    override fun getLayout(): Int {
        return R.layout.fragment_onboarding
    }

    override fun onBackPressed() {
        vm.backToMap()
    }

    private fun initViews(o: Organization) {
        Toast.makeText(requireContext(), "uyuiyitiuyuyiuyi", Toast.LENGTH_LONG).show()
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