package com.binkos.starlypancacke.app.core

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.binkos.starlypancacke.app.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.dialog_fragment_system.*

class SystemDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_system, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        systemFragmentDialogButtonPositive.setOnClickListener { dismiss() }
        systemFragmentDialogButtonClose.setOnClickListener { dismiss() }
        
        Glide
            .with(this)
            .load("qrCodeImageView")
            .into(qrCodeImageView)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        super.onCreateDialog(savedInstanceState).apply {
//            window?.decorView?.setBackgroundResource(R.color.transparent)
        }


    companion object {
        private const val ORGANIZATION_ID_KEY = "ORGANIZATION_ID_KEY"

        fun newInstance(id: String): SystemDialogFragment {
            return SystemDialogFragment()
                .apply {
                    arguments = bundleOf(Pair(ORGANIZATION_ID_KEY, id))
                }
        }
    }
}