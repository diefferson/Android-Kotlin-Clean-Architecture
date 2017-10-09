package br.com.disapps.homepet.ui.includeComment

import br.com.disapps.homepet.ui.common.AppView

/**
 * Created by diefferson on 04/10/17.
 **/
interface IIncludeCommentView : AppView{

    fun fillSuccesInclude()
    fun fillErrorInclude(message:String)
}