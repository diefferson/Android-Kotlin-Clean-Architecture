package br.com.disapps.homepet.ui.comments.adapter

import com.chad.library.adapter.base.BaseQuickAdapter

import br.com.disapps.homepet.R
import br.com.disapps.homepet.data.model.Comment
import br.com.disapps.homepet.ui.custom.CustomViewHolder

/**
 * Created by diefferson.santos on 31/08/17.
 */

class CommentAdapter(data: List<Comment>?) : BaseQuickAdapter<Comment, CustomViewHolder>(R.layout.comment_item, data) {

    override fun convert(helper: CustomViewHolder, item: Comment) {
        if(item.avatarUser.isNotEmpty()){
            helper.setCircleImageURI(R.id.user_avatar, item.avatarUser)
        }
        helper.setText(R.id.user_name, item.nameUser)
        helper.setText(R.id.comment, item.comment)
        helper.setText(R.id.comment_date, android.text.format.DateFormat.format("dd/MM/yyyy", item.date))
    }
}
