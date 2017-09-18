package br.com.disapps.homepet.data.ws.request;

import java.io.Serializable;

/**
 * Created by diefferson on 18/09/17.
 */

public class HotelsRequest implements Serializable{

    private String sort;
    private String sense;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSense() {
        return sense;
    }

    public void setSense(String sense) {
        this.sense = sense;
    }
}
