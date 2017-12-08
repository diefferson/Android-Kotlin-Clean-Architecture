package br.com.disapps.homepet.data.ws.response

import br.com.disapps.homepet.data.model.Comment
import br.com.disapps.homepet.data.model.Hotel

/**
 * Created by diefferson.santos on 24/08/17.
 */
abstract class ApiListResponse<T> (
            var message: String =  "",
            var count: Int = 0,
            var next: Int = 0,
            var previous: Int = 0,
            var content: List<T>? = null
        ){

    class ListCommentResponse : ApiListResponse<Comment>()
    class ListHotelResponse : ApiListResponse<Hotel>()

}