package br.com.disapps.homepet.data.ws.response

/**
 * Created by diefferson.santos on 31/08/17.
 */
class ApiSimpleResponse<T>(
        var message: String?,
        var count: Int?,
        var next: Int?,
        var previous: Int?,
        var content: T ?
)