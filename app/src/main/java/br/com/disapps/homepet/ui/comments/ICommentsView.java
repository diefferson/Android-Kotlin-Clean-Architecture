package br.com.disapps.homepet.ui.comments;

import java.util.List;

import br.com.disapps.homepet.data.model.Comment;
import br.com.disapps.homepet.ui.common.AppView;

/**
 * Created by diefferson.santos on 31/08/17.
 */

public interface ICommentsView extends AppView {

    void fillComments(List<Comment> comments);
}
