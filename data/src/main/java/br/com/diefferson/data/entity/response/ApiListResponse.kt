package br.com.diefferson.data.entity.response

import br.com.diefferson.data.entity.Hotel
import org.w3c.dom.Comment


/**
 * Created by diefferson.santos on 24/08/17.
 */
abstract class ApiListResponse<T> (
            var message: String =  "",
            var count: Int = 0,
            var next: Int = 0,
            var previous: Int = 0,
            var content: List<T> = ArrayList()
        ){

    class ListCommentResponse : ApiListResponse<Comment>()
    class ListHotelResponse : ApiListResponse<Hotel>()

}