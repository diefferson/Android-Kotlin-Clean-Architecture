package br.com.disapps.homepet.util.rx;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class FormError extends HashMap<String, ArrayList<String>> {

    public String getFirstError(String key){
        return this.get(key).get(0);
    }

}
