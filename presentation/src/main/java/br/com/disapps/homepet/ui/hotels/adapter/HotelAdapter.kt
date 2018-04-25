package br.com.disapps.homepet.ui.hotels.adapter

import br.com.diefferson.domain.model.Hotel
import com.chad.library.adapter.base.BaseQuickAdapter
import br.com.disapps.homepet.R
import br.com.disapps.homepet.ui.custom.CustomViewHolder

/**
 * Created by diefferson.santos on 23/08/17.
 */

class HotelAdapter(data: List<Hotel>?) : BaseQuickAdapter<Hotel, CustomViewHolder>(R.layout.hotel_item, data) {

    override fun convert(helper: CustomViewHolder, item: Hotel) {
        helper.setText(R.id.hotel_name, item.name)
        helper.setImageURI(R.id.hotel_image, item.coverImage)
        helper.setRating(R.id.rating, item.rating)
        helper.setText(R.id.ratings, item.ratingsNumber.toString())
        helper.setText(R.id.comments, item.commentsNumber.toString())
        helper.setText(R.id.price, item.price.toString())
        helper.setText(R.id.distance, item.address)
    }
}
