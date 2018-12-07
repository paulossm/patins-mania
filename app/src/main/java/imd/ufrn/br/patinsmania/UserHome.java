package imd.ufrn.br.patinsmania;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class UserHome extends AppCompatActivity {
//    private FrameLayout mainFrame;
//    private BottomNavigationView mainNav;
//
//    private OperationalFragment operationalFragment;
//    private AdministrativeFragment administrativeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

//        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
//        mainNav = (BottomNavigationView) findViewById(R.id.main_nav);
//
//        operationalFragment = new OperationalFragment();
//        administrativeFragment = new AdministrativeFragment();
//
//        setFragment(operationalFragment);
//
//        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.nav_operational:
//                        setFragment(operationalFragment);
//                        return true;
//
//                    case R.id.nav_administrative:
//                        setFragment(administrativeFragment);
//                        return true;
//
//                    default:
//                        return false;
//                }
//            }
//        });
//    }

//    private void setFragment(Fragment fragment) {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.main_frame, fragment);
//        fragmentTransaction.commit();
    }

    public void navigateOperational(View view) {
        startActivity(new Intent(UserHome.this, OperationalActivity.class));
    }

    public void navigateAdministrative(View view) {
        startActivity(new Intent(UserHome.this, AdministrativeActivity.class));
    }
}
