package br.com.disapps.homepet.ui.details

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import br.com.disapps.homepet.R
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.data.model.Hotel
import br.com.disapps.homepet.ui.common.AppFragment
import br.com.disapps.homepet.ui.details.adapter.ServiceAdapter
import br.com.disapps.homepet.ui.includeComment.IncludeCommentFragment
import kotlinx.android.synthetic.main.fragment_hotel_details.*
import kotlinx.android.synthetic.main.fragment_include_comment.*

/**
 * Created by diefferson.santos on 31/08/17.
* */
class HotelDetailsFragment : AppFragment<IHotelDetailsView, HotelDetailsPresenter>(), IHotelDetailsView {

    private var serviceAdapter: ServiceAdapter? = null

    override val fragmentTag: String
        get() = HotelDetailsFragment::class.java.simpleName

    override val fragmentLayout = R.layout.fragment_hotel_details

    var codeHotel : Int = 0


    override fun createPresenter() = HotelDetailsPresenter(HomePet.instance!!.hotelRepository!!, HomePet.instance!!.restApi!!, HomePet.instance!!.preferences!!)

    override fun onResume() {
        super.onResume()
        getPresenter().loadHotel(codeHotel)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLoadingFragment(loading_view)

        codeHotel = arguments.getInt("codeHotel")

        comment_bt.setOnClickListener{  appActivityListener!!.replaceAndBackStackFragment(IncludeCommentFragment.newInstance(codeHotel)) }
        rating_bt.setOnClickListener{ showRatingDialog()}
        validateLogin()
    }

    private fun validateLogin() {
        if (!HomePet.instance!!.preferences!!.isLogged) {
            rating_bt.visibility = View.INVISIBLE
            comment_bt.visibility = View.INVISIBLE
        }
    }

    override fun fillHotelDetails(hotel: Hotel) {
        hotel_description.text = hotel.description
        hotel_address.text = hotel.getCompleteAddress()
        ratings.text = hotel.rating.toString()
        comments.text = hotel.commentsNumber.toString()
        rating.rating = hotel.rating
        content.visibility = View.VISIBLE
        serviceAdapter = ServiceAdapter(hotel.services)
        service_recycler.adapter = serviceAdapter
    }

    fun showRatingDialog() {

        val ratingDialog = Dialog(context)
        ratingDialog.setContentView(R.layout.ratting_dialog)

        val confirmButtom = ratingDialog.findViewById<Button>(R.id.send_rating_btn)
        val cancelButtom = ratingDialog.findViewById<Button>(R.id.cancel_rating_bt)
        val ratingBar = ratingDialog.findViewById<RatingBar>(R.id.ratingBar)

        cancelButtom.setOnClickListener { v: View -> ratingDialog.dismiss() }

        confirmButtom.setOnClickListener { v: View ->
            if(ratingBar.rating >0){
                getPresenter().sendRating(ratingBar.rating, codeHotel)
                ratingDialog.dismiss()
            }
        }

        ratingDialog.show()

    }

    override fun fillErrorInclude(message: String) {
        Snackbar.make(commentText,message, Snackbar.LENGTH_SHORT).show()
    }

    override fun fillSuccesInclude() {
        Snackbar.make(content,getString(R.string.sucess_rating), Snackbar.LENGTH_SHORT).show()
        getPresenter().loadHotel(codeHotel)
    }

    companion object {

        fun newInstance(codeHotel: Int): HotelDetailsFragment {

            val hotelDetailsFragment = HotelDetailsFragment()

            val args = Bundle()
            args.putInt("codeHotel", codeHotel)
            hotelDetailsFragment.arguments = args

            return hotelDetailsFragment

        }
    }
}
