package com.binkos.starlypancacke.app.ui.main.places

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.common.extensions.*
import com.binkos.starlypancacke.app.di.organizationModule
import com.binkos.starlypancacke.app.ui.admin.AdminRouter
import com.binkos.starlypancacke.app.ui.base.BaseFragment
import com.binkos.starlypancacke.app.ui.main.MainFlowRouter
import com.binkos.starlypancacke.app.ui.menu.FoodFragmentScreen
import com.binkos.starlypancacke.domain.model.Organization
import com.github.terrakok.cicerone.Router
import kotlinx.android.synthetic.main.fragment_organization.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.java.KoinJavaComponent

class OrganizationFragment : BaseFragment() {

    private val organizationViewModel: OrganizationViewModel by viewModel()
    private lateinit var router: Router
    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(organizationModule)
        router = if (tryToGetBoolean(IS_ADMIN_KEY)) {
            KoinJavaComponent.get(AdminRouter::class.java)
        } else {
            KoinJavaComponent.get(MainFlowRouter::class.java)
        }
        super.onCreate(savedInstanceState)
    }

    override fun viewReady() {
        tryToGetStringOrNull(ORGANIZATION_NAME_KEY)?.let {
            organizationViewModel.launchOrgSearch(it)
        }

        organizationViewModel
            .orgLiveData
            .observe(viewLifecycleOwner) {
                initViews(it.first())
            }

        if (tryToGetBoolean(IS_ADMIN_KEY)) {
            createMenuItemView.visible()
            createMenuItemView.onClick {
                router.navigateTo(FoodFragmentScreen())
            }
        } else createMenuItemView.visibility = View.INVISIBLE
    }

    override fun getLayout(): Int {
        return R.layout.fragment_organization
    }

    override fun onBackPressed() {
        router.exit()
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
            router.exit()
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


            putExtra(
                Intent.EXTRA_TEXT,
                "https://starly-pancake/organization?name=${
                    Base64.encode(key.toByteArray(), Base64.DEFAULT)
                }"
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun onDestroy() {
        unloadKoinModules(organizationModule)
        super.onDestroy()
    }

    companion object {
        private const val ORGANIZATION_NAME_KEY = "ORGANIZATION_NAME"
        private const val IS_ADMIN_KEY = "IS_ADMIN"

        fun newInstance(name: String, isAdmin: Boolean = false): OrganizationFragment {
            return OrganizationFragment().apply {
                arguments = bundleOf(Pair(ORGANIZATION_NAME_KEY, name), Pair(IS_ADMIN_KEY, isAdmin))
            }
        }
    }
}