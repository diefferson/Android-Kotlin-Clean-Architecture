package br.com.disapps.homepet.ui.details.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import br.com.disapps.homepet.R
import br.com.disapps.homepet.data.model.Service

/**
 * Created by diefferson on 05/09/17.
 */

class ServiceAdapter(data: List<Service>?) : BaseQuickAdapter<Service, BaseViewHolder>(R.layout.service_item, data) {

    override fun convert(helper: BaseViewHolder, item: Service) {
        helper.setText(R.id.service_name, item.name)
    }
}
