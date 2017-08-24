package br.com.disapps.homepet.util.rx;

import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;

import retrofit2.HttpException;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class RxHttpError {

    public static final int NO_CONNECTIVITY_CODE = 1000;
    public static final int UNKNOWNHOST_CODE = 1002;
    public static final int SOCKETTIMEOUT_CODE = 1003;

    public String detail;

    public Throwable exception;

    public int errorCode;
    public Object errorObject;
    public String errorMessage;


    private RxHttpError() {

    }

    /**
     * Traduz uma exceção para um RxHttpError
     *
     * @see RxHttpError
     *
     * @param e exceção
     * @return RxHttpError
     * @throws IOException
     */
    @Nullable
    public static RxHttpError parseError(Throwable e) throws IOException {

        HashMap<Integer, Class> typesByCode = new HashMap<>();
        typesByCode.put(HttpURLConnection.HTTP_BAD_REQUEST, FormError.class);

        return RxHttpError.parseError(e, new Gson(), typesByCode);
    }

    /**
     * Traduz uma exceção para um RxHttpError
     *
     * @see RxHttpError
     *
     * @param e
     * @param parser
     * @param typesByCode Classes que serão traduzidas de acordo com o StatusCode retornado da requisição
     * @return
     * @throws IOException
     */
    @Nullable
    public static RxHttpError parseError(Throwable e, Gson parser, HashMap<Integer, Class> typesByCode ) throws IOException {
        RxHttpError error = new RxHttpError();
        error.exception = e;

        if ( e instanceof java.net.UnknownHostException){
            error.errorCode = UNKNOWNHOST_CODE;
        } else if ( e instanceof java.net.ConnectException  ) {
            error.errorCode = NO_CONNECTIVITY_CODE;
        }else if ( e instanceof  java.net.SocketTimeoutException ) {
            error.errorCode = SOCKETTIMEOUT_CODE;
        }else if ( e instanceof HttpException){
            RxHttpError.parseKnownError(error, (HttpException)e, parser, typesByCode);
        }else{
            return null;
        }


        return error;
    }

    private static void parseKnownError(RxHttpError error, HttpException httpError, Gson parser, HashMap<Integer, Class> typesByCode) throws IOException {

        error.errorCode = httpError.code();
        error.errorMessage = httpError.message();

        if (typesByCode.containsKey(error.errorCode) ){
            Class cType = typesByCode.get(error.errorCode);
            error.errorObject = parser.fromJson(httpError.response().errorBody().string(), cType);
        }else{

            try {
                Non400 non400 = parser.fromJson(httpError.response().errorBody().string(), Non400.class);
                error.detail = non400.message;
            }catch (Exception ex){
                ex.printStackTrace();
                error.detail = "";
            }

        }

    }

    private class Non400 {
        public String message;
        public int code;
    }
}
