package br.com.disapps.homepet.data.ws.response

/**
 * Created by diefferson.santos on 24/08/17.
 */
class ApiResponse<T> (var code: String,var message: String,var content: List<T>)