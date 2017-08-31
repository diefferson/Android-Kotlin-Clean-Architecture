package br.com.disapps.homepet.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.app.HomePet;
import br.com.disapps.homepet.ui.common.AppActivity;
import br.com.disapps.homepet.ui.hotels.HotelsFragment;
import br.com.disapps.homepet.ui.login.LoginFragment;
import br.com.disapps.homepet.ui.map.MapFragment;
import br.com.disapps.homepet.ui.profile.ProfileFragment;
import butterknife.BindView;

public class MainActivity extends AppActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @BindView(R.id.container)
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();

        setContainer(container);

        Menu menu = navigation.getMenu();
        setInitialFragment(menu.getItem(0));

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                        replaceFragment(HotelsFragment.newInstance());
                    return true;
                case R.id.navigation_map:
                        replaceFragment(MapFragment.newInstance());
                    return true;
                case R.id.navigation_profile:
                        if(HomePet.Companion.getInstance().getPreferences().isLogged()){
                            replaceFragment(ProfileFragment.newInstance());
                        }else{
                            replaceFragment(LoginFragment.newInstance());
                        }
                    return true;
            }
            return false;
        }
    };

    private void setInitialFragment(MenuItem menuItem) {
        mOnNavigationItemSelectedListener.onNavigationItemSelected(menuItem);
    }
}
