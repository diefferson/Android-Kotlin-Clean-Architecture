package br.com.disapps.homepet.ui.common;

import br.com.disapps.homepet.util.rx.RxHttpError;

/**
 * Created by diefferson.santos on 23/08/17.
 */
public interface IErrorHandlerView {
    /**
     * Quando ocorrer um erro de Http
     * Onde as propriedades de erro estao definidas pela resposta do servidor
     * @param error
     */
    void error(RxHttpError error);

    /**
     * Para erros nao tratados
     * Error generico, geralmente qnd ocorre crash do app ex: parse de objeto errado
     * @param e
     */
    void error(Throwable e);
}
