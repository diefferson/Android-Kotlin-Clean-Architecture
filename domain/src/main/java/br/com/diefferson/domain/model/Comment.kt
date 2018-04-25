package br.com.diefferson.domain.model

import java.util.*

/**
* Created by diefferson.santos on 23/08/17.
*/
class Comment(
                var codeHotel: Int,
                var codeUser: Int,
                var nameUser: String,
                var avatarUser: String,
                var comment: String,
                var date : Date
            )
