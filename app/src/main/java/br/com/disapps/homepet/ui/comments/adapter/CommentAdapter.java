package br.com.disapps.homepet.ui.comments.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.data.model.Comment;
import br.com.disapps.homepet.ui.custom.CustomViewHolder;

/**
 * Created by diefferson.santos on 31/08/17.
 */

public class CommentAdapter extends BaseQuickAdapter<Comment, CustomViewHolder> {

    public CommentAdapter(@Nullable List<Comment> data) {
        super(R.layout.comment_item, data);
    }

    @Override
    protected void convert(CustomViewHolder helper, Comment item) {
        helper.setImageURI(R.id.user_avatar, item.getAvatarUser());
        helper.setText(R.id.user_name, item.getNameUser());
        helper.setText(R.id.comment, item.getComment());
        helper.setText(R.id.comment_date, android.text.format.DateFormat.format("dd/MM/yyyy",item.getDate()));
    }
}
