package br.com.disapps.homepet.data.ws.response

/**
 * Created by diefferson.santos on 24/08/17.
 */
abstract class ApiListResponse<T> {
    var message: String?= null
    var count: Int?= null
    var next: Int?= null
    var previous: Int?= null
    var content: List<T>? = null
}