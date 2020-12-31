package com.binkos.starlypancacke.app.ui.admin

import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.common.extensions.tryToGetString
import com.binkos.starlypancacke.app.core.SystemDialogFragment
import com.binkos.starlypancacke.app.ui.base.BaseFragment
import com.binkos.starlypancacke.domain.model.Failure
import com.binkos.starlypancacke.domain.model.Progress
import com.binkos.starlypancacke.domain.model.Success
import kotlinx.android.synthetic.main.fragment_admin_organizations.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AdminOrganizationsFragment : BaseFragment() {

    private val vm: AdminViewModel by viewModel { parametersOf(tryToGetString(ADMIN_ID_KEY)) }
    private lateinit var adapter: AdminOrganizationsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = AdminOrganizationsAdapter(
            requireActivity(),
            { id ->
                val linkForQrContent = "app://starly-pancake/admin?id=${
                    Base64.encode(id.toByteArray(), Base64.DEFAULT)
                }"
                SystemDialogFragment.newInstance(linkForQrContent).show(childFragmentManager, null)
            },
            { name -> vm.toOrganization(name) })
    }

    override fun viewReady() {
        initViews()
        initObservers()

        vm.launch()
    }

    override fun getLayout() = R.layout.fragment_admin_organizations

    override fun onBackPressed() {
        vm.exit()
    }

    private fun initViews() {
        organizationsRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = this@AdminOrganizationsFragment.adapter
        }

        adminOrganizationToolbar
            .menu
            .getItem(0)
            .setOnMenuItemClickListener {
                vm.logout()
                true
            }

        adminOrganizationFloatingActionButton.setOnClickListener {
            vm.toCreateOrganization()
        }
    }

    private fun initObservers() {
        vm
            .orgLiveData
            .observe(viewLifecycleOwner) {
                when (it) {
                    is Success -> {
                        adapter.updateList(it.data)
                    }
                    is Failure -> {
                        Toast.makeText(requireContext(), "FAIL", Toast.LENGTH_LONG).show()
                    }
                    is Progress -> {
                        Toast.makeText(requireContext(), "PROGRESS", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }

    companion object {
        private const val ADMIN_ID_KEY = "ADMIN_ID_KEY"

        fun getInstance(adminID: String) = AdminOrganizationsFragment().apply {
            arguments = bundleOf(Pair(ADMIN_ID_KEY, adminID))
        }
    }
}