package br.com.diefferson.domain.model

/**
* Created by diefferson.santos on 23/08/17.
*/
class Auth (
       var accessToken: String,
       var tokenType: String,
       var expiresIn: String,
       var refreshToken: String
    )