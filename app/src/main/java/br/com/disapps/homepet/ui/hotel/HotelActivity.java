package br.com.disapps.homepet.ui.hotel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.app.HomePet;
import br.com.disapps.homepet.data.model.Hotel;
import br.com.disapps.homepet.ui.common.AppActivity;
import br.com.disapps.homepet.ui.custom.LoadingView;
import br.com.disapps.homepet.ui.hotel.hotelFragment.HotelFragment;
import br.com.disapps.homepet.ui.hotels.adapter.ImageViewPagerAdapter;
import butterknife.BindView;

/**
 * Created by diefferson.santos on 31/08/17.
 */

public class HotelActivity  extends AppActivity
        implements AppBarLayout.OnOffsetChangedListener, IHotelView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.image_slide)
    ViewPager bannerSlider;

    @BindView(R.id.container)
    FrameLayout container;

    @BindView(R.id.pageIndicatorView)
    PageIndicatorView pageIndicatorView;

    @BindView(R.id.loading_view)
    ConstraintLayout loadingView;

    private HotelPresenter hotelPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        bindView();

        setToolbar(toolbar);
        setContainer(container);

        int hotelCode = getIntent().getExtras().getInt("hotelCode");

        getPresenter().loadHotel(hotelCode);

        replaceFragment(HotelFragment.newInstance(hotelCode));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading(boolean cancelable) {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        loadingView.setVisibility(View.GONE);
    }

    public void fillHeaderHotel(Hotel hotel) {

        setTitle(hotel.getName());

        bannerSlider.setAdapter(new ImageViewPagerAdapter(this,hotel.getImages() ));
        pageIndicatorView.setViewPager(bannerSlider);
        pageIndicatorView.setVisibility(View.VISIBLE);

    }

    private HotelPresenter getPresenter(){

        if(hotelPresenter == null){
            createPresenter();
        }

        return hotelPresenter;
    }

    private void createPresenter() {
        hotelPresenter =  new HotelPresenter(HomePet.Companion.getInstance().getHotelRepository());
        hotelPresenter.attachView(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }

}