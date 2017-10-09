package br.com.disapps.homepet.ui.hotels.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.facebook.drawee.view.SimpleDraweeView

import br.com.disapps.homepet.R

/**
 * Created by diefferson.santos on 04/09/17.
 */

class ImageViewPagerAdapterFull(internal var mContext: Context, internal var images: List<String>) : PagerAdapter() {
    internal var mLayoutInflater: LayoutInflater

    init {
        mLayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = mLayoutInflater.inflate(R.layout.image_slide_item_full, container, false)

        val imageView = itemView.findViewById<View>(R.id.image) as SimpleDraweeView
        imageView.setImageURI(images[position])

        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}