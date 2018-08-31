package com.example.madhushri.navigationdrawerandroid;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import static android.view.View.TRANSLATION_Y;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigation;
    private RelativeLayout relativeLayout;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private boolean isDrawerOpened;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstances();
    }

    private void initInstances() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigation = findViewById(R.id.navigation_view);
        relativeLayout = findViewById(R.id.relative_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
                isDrawerOpened = false;
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
                isDrawerOpened = true;
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                relativeLayout.setBackground(getDrawable(R.drawable.relativebackground));
              //  relativeLayout.setTranslationX(slideOffset * drawerView.getWidth());
              //  relativeLayout.setTranslationY(212);
//                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_corner);
//                relativeLayout.startAnimation(animation);
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("translationX", slideOffset * drawerView.getWidth());
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("translationY", 200f);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(relativeLayout,pvhX,pvhY);
                animation.setDuration(0);
                animation.start();

                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
                actionBar.setBackgroundDrawable(getDrawable(R.drawable.action_bar_background));
                drawerLayout.bringChildToFront(drawerView);
                drawerLayout.requestLayout();
                drawerLayout.setScrimColor(Color.TRANSPARENT);
                if (isDrawerOpened) {
//                    relativeLayout.setTranslationX(drawerView.getTranslationX());
//                    relativeLayout.setTranslationY(drawerView.getTranslationY());
                    PropertyValuesHolder pvhX1 = PropertyValuesHolder.ofFloat("translationX", slideOffset * drawerView.getWidth());
                    PropertyValuesHolder pvhY1 = PropertyValuesHolder.ofFloat("translationY", slideOffset*drawerView.getHeight()/200f);
                    ObjectAnimator animation1 = ObjectAnimator.ofPropertyValuesHolder(relativeLayout,pvhX1,pvhY1);
                    animation1.setDuration(0);
                    animation1.start();
                    relativeLayout.setBackground(getDrawable(R.drawable.backtonormal));
                }
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
//                    case R.id.navigation_item_1:
//                        //Do some thing here
//                        // add navigation drawer item onclick method here
//                        break;
//                    case R.id.navigation_item_2:
//                        //Do some thing here
//                        // add navigation drawer item onclick method here
//                        break;
//                    case R.id.navigation_item_3:
//                        //Do some thing here
//                        // add navigation drawer item onclick method here
//                        break;
//                    case R.id.navigation_item_4:
//                        //Do some thing here
//                        // add navigation drawer item onclick method here
//                        break;
//                    case R.id.navigation_item_5:
//                        //Do some thing here
//                        // add navigation drawer item onclick method here
//                        break;

                    default:
                        break;
                }
                return false;
            }
        });
        drawerToggle.syncState();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.menu_navi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action0) {
            return true;
        }

        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
