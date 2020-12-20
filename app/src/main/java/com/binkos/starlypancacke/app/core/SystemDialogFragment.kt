package com.binkos.starlypancacke.app.core

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.common.extensions.tryToGetString
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
            .load(
                QRGEncoder(
                    tryToGetString(key = ORGANIZATION_ID_KEY),
                    null,
                    QRGContents.Type.TEXT,
                    400
                ).encodeAsBitmap()
            )
            .into(qrCodeImageView)
    }

    override fun onResume() {
        super.onResume()

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
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