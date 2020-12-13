package com.binkos.starlypancacke.app.ui.main.places

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.app.common.extensions.tryToGetStringOrNull
import com.binkos.starlypancacke.app.ui.base.BaseFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.screen_gallery_photo.*

class PhotoFragment : BaseFragment() {

    override fun viewReady() {
        val link = tryToGetStringOrNull(PHOTO_LINK_KEY)

        val drawable = Drawable.createFromPath("http://gph.is/1XRTmuh")

        Glide
            .with(requireContext())
            .load(link)
            .placeholder(drawable)
            .error(
                Glide
                    .with(requireContext())
                    .load("https://blog.thomasnet.com/hs-fs/hubfs/shutterstock_774749455.jpg")
                    .centerInside()
            )
            .centerCrop()
            .into(galleryPhotoImageView)
    }

    override fun getLayout() = R.layout.screen_gallery_photo

    override fun onBackPressed() {}

    companion object {
        const val PHOTO_LINK_KEY = "PHOTO_LINK"

        fun getInstance(link: String): PhotoFragment {
            return PhotoFragment()
                .apply { arguments = Bundle().apply { putString(PHOTO_LINK_KEY, link) } }
        }
    }
}