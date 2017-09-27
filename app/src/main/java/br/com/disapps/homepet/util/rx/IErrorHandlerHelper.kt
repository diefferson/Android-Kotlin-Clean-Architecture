package br.com.disapps.homepet.util.rx

import java.io.IOException

import br.com.disapps.homepet.ui.common.IErrorHandlerView

/**
 * Created by diefferson.santos on 23/08/17.
 */

object IErrorHandlerHelper {

    /**
     * Helper para parse generico do erro
     * @param iView
     * *
     * @param e
     */
    fun defaultErrorResolver(iView: IErrorHandlerView, e: Throwable) {
        try {
            val error = RxHttpError.parseError(e)
            if (error != null) {
                iView.error(error)
            } else {
                iView.error(e)
            }
        } catch (e1: IOException) {
            iView.error(e)
        }

    }
}
