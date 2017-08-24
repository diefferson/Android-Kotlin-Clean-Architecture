package br.com.disapps.homepet.ui.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.data.model.Hotel;
import br.com.disapps.homepet.ui.custom.CustomViewHolder;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class HotelAdapter extends BaseQuickAdapter<Hotel, CustomViewHolder> {

    public HotelAdapter(@Nullable List<Hotel> data) {
        super(R.layout.hotel_item, data);
    }

    @Override
    protected void convert(CustomViewHolder helper, Hotel item) {
        helper.setImageURI(R.id.hotel_image, item.getUrlImagemCapa());
        helper.setText(R.id.hotel_name, item.getNome());
    }
}
