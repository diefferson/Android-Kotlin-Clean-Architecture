package br.com.disapps.homepet.ui.comments

import br.com.disapps.homepet.data.model.Comment
import br.com.disapps.homepet.ui.common.AppView

/**
 * Created by diefferson.santos on 31/08/17.
 */

interface ICommentsView : AppView {

    fun fillComments(comments: List<Comment>)
}
