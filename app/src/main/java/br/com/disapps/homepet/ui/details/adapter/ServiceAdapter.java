package br.com.disapps.homepet.ui.details.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.data.model.Service;

/**
 * Created by diefferson on 05/09/17.
 */

public class ServiceAdapter extends BaseQuickAdapter<Service, BaseViewHolder>{

    public ServiceAdapter(@Nullable List<Service> data) {
        super(R.layout.service_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Service item) {
        helper.setText(R.id.service_name, item.getName());
    }
}
