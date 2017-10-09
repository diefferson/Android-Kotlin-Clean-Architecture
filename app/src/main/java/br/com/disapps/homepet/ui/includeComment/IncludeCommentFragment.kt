package br.com.disapps.homepet.ui.includeComment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.*
import br.com.disapps.homepet.R
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.ui.common.AppFragment
import br.com.disapps.homepet.ui.details.HotelDetailsFragment
import kotlinx.android.synthetic.main.fragment_include_comment.*
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.toast

/**
 * Created by diefferson on 04/10/17.
 **/
class IncludeCommentFragment: AppFragment<IIncludeCommentView, IncludeCommentPresenter>(), IIncludeCommentView {

    var codeHotel:Int? = null

    override val fragmentTag: String
        get() = IncludeCommentFragment::class.java.simpleName

    override val fragmentLayout: Int
        get() = R.layout.fragment_include_comment

    override fun createPresenter() =  IncludeCommentPresenter(HomePet.instance!!.restApi!!, HomePet.instance!!.preferences!!)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         codeHotel = arguments.getInt("codeHotel")
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.include_comment_menu, menu)
        menu!!.findItem(R.id.full_screem).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.include_comment) {

            includeComment()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private  fun includeComment(){

        if (TextUtils.isEmpty(commentText.text.toString())) {
            commentText.error = "Campo obrigatorio"
        }else{
            presenter.includeComment(codeHotel!! ,commentText.text.toString() )
        }
    }

    override fun fillSuccesInclude() {
        Snackbar.make(commentText,getString(R.string.sucess_comment), Snackbar.LENGTH_SHORT).show()
        this@IncludeCommentFragment.activity.onBackPressed()
    }

    override fun fillErrorInclude(message: String) {
        Snackbar.make(commentText,message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(codeHotel: Int): IncludeCommentFragment {

            val includeCommentFragment = IncludeCommentFragment()

            val args = Bundle()
            args.putInt("codeHotel", codeHotel)
            includeCommentFragment.arguments = args

            return includeCommentFragment

        }
    }


}