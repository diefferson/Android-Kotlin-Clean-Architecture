package br.com.diefferson.data.entity.response

import br.com.diefferson.data.entity.Auth
import br.com.diefferson.data.entity.Coordinate
import br.com.diefferson.data.entity.Hotel
import br.com.diefferson.data.entity.User


/**
 * Created by diefferson.santos on 31/08/17.
 */
abstract class ApiSimpleResponse<T>(
            var message: String = "",
            var count: Int = 0,
            var next: Int = 0,
            var previous: Int = 0,
            var content: T? = null
        ){

    class AuthResponse: ApiSimpleResponse<Auth>()
    class CoordinateResponse : ApiSimpleResponse<Coordinate>()
    class HotelResponse : ApiSimpleResponse<Hotel>()
    class SignUpResponse : ApiSimpleResponse<String>()
    class UserResponse : ApiSimpleResponse<User>()

}

