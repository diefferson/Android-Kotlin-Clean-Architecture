package br.com.disapps.homepet.data.ws.response

/**
 * Created by diefferson.santos on 31/08/17.
 */
abstract class ApiSimpleResponse<T> {
    var message: String? = null
    var count: Int?= null
    var next: Int?= null
    var previous: Int?= null
    var content: T? = null
}