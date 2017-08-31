package br.com.disapps.homepet.data.ws.response

/**
 * Created by diefferson.santos on 24/08/17.
 */
class ApiListResponse<T>(
                    var message: String?,
                    var count: Int?,
                    var next: Int?,
                    var previous: Int?,
                    var content: List<T>?
                )