package br.com.disapps.homepet.ui.imageViewer

import android.os.Bundle

import android.view.View
import br.com.disapps.homepet.R
import br.com.disapps.homepet.data.model.Hotel
import br.com.disapps.homepet.ui.common.AppActivity
import br.com.disapps.homepet.ui.hotels.adapter.ImageViewPagerAdapterFull
import kotlinx.android.synthetic.main.activity_image_viewer.*


class ImageViewerActivity : AppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)

        var hotel = intent.getSerializableExtra("hotel") as Hotel

        image_slide.adapter = ImageViewPagerAdapterFull(this, hotel.images!!)
        pageIndicatorView.setViewPager(image_slide)
        pageIndicatorView.visibility = View.VISIBLE
    }

}
