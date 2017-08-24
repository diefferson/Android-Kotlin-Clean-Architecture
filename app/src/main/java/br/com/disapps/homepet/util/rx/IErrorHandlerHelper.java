package br.com.disapps.homepet.util.rx;

import java.io.IOException;

import br.com.disapps.homepet.ui.common.IErrorHandlerView;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class IErrorHandlerHelper {

    /**
     * Helper para parse generico do erro
     * @param iView
     * @param e
     */
    public static void defaultErrorResolver(IErrorHandlerView iView, Throwable e) {
        try {
            RxHttpError error = RxHttpError.parseError(e);
            if (error != null) {
                iView.error(error);
            } else {
                iView.error(e);
            }
        } catch (IOException e1) {
            iView.error(e);
        }
    }
}
