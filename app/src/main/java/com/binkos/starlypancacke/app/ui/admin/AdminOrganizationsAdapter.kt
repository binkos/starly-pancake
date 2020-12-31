package com.binkos.starlypancacke.app.ui.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.ui.main.places.PhotoOrganizationAdapter
import com.binkos.starlypancacke.domain.model.Organization
import java.util.*

class AdminOrganizationsAdapter(
    private val fragmentActivity: FragmentActivity,
    private val longClickListener: (id: String) -> Unit,
    private val clickListener: (id: String) -> Unit
) : RecyclerView.Adapter<AdminOrganizationsAdapter.AdminOrganizationViewHolder>() {

    private val organizationsList: LinkedList<Organization> = LinkedList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminOrganizationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_admin_organization, parent, false)
        return AdminOrganizationViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdminOrganizationViewHolder, position: Int) {
        holder.apply {
            organizationsList[position]
            itemView
                .setOnLongClickListener {
                    longClickListener.invoke(organizationsList[position].name)
                    true
                }

            itemView
                .setOnClickListener {
                    clickListener.invoke(organizationsList[position].name)
                }

            nameOrganizationTextView.text = organizationsList[position].name
            countPointsMenuOrganizationTextView.text =
                "menu size is ${organizationsList[position].menu.size}"
            organizationsPhotoViewPager.apply {
                adapter =
                    PhotoOrganizationAdapter(fragmentActivity, organizationsList[position].photos)
            }
        }

    }

    override fun getItemCount(): Int {
        return organizationsList.size
    }

    fun updateList(list: List<Organization>) {
        organizationsList.clear()
        organizationsList.addAll(list)
        notifyDataSetChanged()
    }

    class AdminOrganizationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nameOrganizationTextView: TextView =
            itemView.findViewById(R.id.nameOrganizationTextView)
        val countPointsMenuOrganizationTextView: TextView =
            itemView.findViewById(R.id.countPointsMenuOrganizationTextView)
        val organizationsPhotoViewPager: ViewPager2 =
            itemView.findViewById(R.id.organizationsPhotoViewPager)
    }
}