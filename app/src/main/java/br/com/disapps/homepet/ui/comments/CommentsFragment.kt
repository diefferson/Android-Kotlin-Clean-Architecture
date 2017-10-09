package br.com.disapps.homepet.ui.comments

import android.os.Bundle
import android.view.View
import br.com.disapps.homepet.R
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.data.model.Comment
import br.com.disapps.homepet.ui.comments.adapter.CommentAdapter
import br.com.disapps.homepet.ui.common.AppFragment
import kotlinx.android.synthetic.main.fragment_comments.*

/**
 * Created by diefferson.santos on 31/08/17.
 **/
class CommentsFragment : AppFragment<ICommentsView, CommentsPresenter>(), ICommentsView {

    private var commentAdapter: CommentAdapter? = null

    override val fragmentTag: String
        get() = CommentsFragment::class.java.simpleName

    override val fragmentLayout = R.layout.fragment_comments

    override fun createPresenter() = CommentsPresenter(HomePet.instance!!.hotelRepository!!)
    
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLoadingFragment(loading_view)

        val codeHotel = arguments.getInt("codeHotel")

        getPresenter().loadComments(codeHotel)
    }

    override fun fillComments(comments: List<Comment>) {
        commentAdapter = CommentAdapter(comments)
        comments_recycler.adapter = commentAdapter
    }

    companion object {

        fun newInstance(codeHotel: Int): CommentsFragment {

            val commentsFragment = CommentsFragment()

            val args = Bundle()
            args.putInt("codeHotel", codeHotel)
            commentsFragment.arguments = args

            return commentsFragment
        }
    }

}
