package br.com.disapps.homepet.ui.hotel.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import br.com.disapps.homepet.ui.comments.CommentsFragment;
import br.com.disapps.homepet.ui.details.HotelDetailsFragment;
import br.com.disapps.homepet.ui.map.MapFragment;

/**
 * Created by diefferson.santos on 31/08/17.
 */

public class HotelViewPagerAdapter  extends FragmentPagerAdapter {

    private static final int FRAGMENT_COUNT = 3;
    private final Context mContext;

    public HotelViewPagerAdapter(FragmentManager fm, Context c) {
        super(fm);
        mContext = c;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return HotelDetailsFragment.newInstance();
            case 1:
                return CommentsFragment.newInstance();
            case 2:
                return MapFragment.newInstance();
        }

       // return LectureDetailsFragment.newInstance();
        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Detalhes";
            case 1:
                return "Comentarios";
            case 2:
                return "Mapa";
        }

        return null;
    }
}
