package com.moataz.ramadan.main.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moataz.ramadan.R;
import com.moataz.ramadan.library.ui.fragment.LibraryFragment;
import com.moataz.ramadan.main.ui.fragment.HomeFragment;
import com.moataz.ramadan.main.utils.IOnBackPressed;

public class MainActivity extends AppCompatActivity {

    final Fragment fragment1 = new HomeFragment();
    final Fragment fragment2 = new LibraryFragment();
    final Fragment fragment3 = new HomeFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();
        initializeBottomNavigation();
    }

    private void initializeView() {
        //make the status bar white with black icons
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        // make the app support only arabic "Right to left", even if the language of the device on english or others
        ViewCompat.setLayoutDirection(getWindow().getDecorView(), ViewCompat.LAYOUT_DIRECTION_RTL);

    }

    private void initializeBottomNavigation() {
        // first one transaction to add each Fragment
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_layout, fragment3, "3").hide(fragment3);
        ft.add(R.id.fragment_layout, fragment2, "2").hide(fragment2);
        ft.add(R.id.fragment_layout, fragment1, "1");
        // commit once! to finish the transaction
        ft.commit();

        // show and hide them when click on BottomNav items
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // start a new transaction
                FragmentTransaction ft = fm.beginTransaction();
                // animations
                ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                switch (item.getItemId()) {
                    case R.id.home_item:
                        ft.hide(active).show(fragment1).commit();
                        active = fragment1;
                        return true;

                    case R.id.books_item:
                        ft.hide(active).show(fragment2).commit();
                        active = fragment2;
                        return true;

                    case R.id.image_item:
                        ft.hide(active).show(fragment3).commit();
                        active = fragment3;
                        return true;
                }
                return false;
            }
        });
    }

    public static void setHomeItem(Activity activity) {
        //to select the right bottom navigation when press back
        BottomNavigationView bottomNavigationView =
                activity.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home_item);
    }

    @Override
    public void onBackPressed() {
        // start your fragment:
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_layout);
        if (!(fragment instanceof IOnBackPressed)) {
            super.onBackPressed();
        }

        //to select the right bottom navigation when press back
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        int seletedItemId = bottomNavigationView.getSelectedItemId();
        if (R.id.home_item != seletedItemId) {
            setHomeItem(MainActivity.this);
        } else {
            super.onBackPressed();
        }
    }
}