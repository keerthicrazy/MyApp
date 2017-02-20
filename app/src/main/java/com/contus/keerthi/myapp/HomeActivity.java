package com.contus.keerthi.myapp;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by user on 6/2/17.
 */
public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nv);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        selectDrawerItem(item);
                        return true;
                    }
                });

        initListener();
    }

    private void initListener() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mUser = firebaseAuth.getCurrentUser();
                if (mUser == null) {
                    startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                    HomeActivity.this.finish();
                }
            }
        };
    }


    public void selectDrawerItem(MenuItem menuItem)
    {
        Fragment fragment=null;
        Class fragementClass = null;

        switch (menuItem.getItemId())
        {
            case R.id.gallery:
                fragementClass=GalleryFragment.class;
                break;
            case R.id.news:
                fragementClass=NewsFragment.class;
                break;
            case R.id.movies:
                fragementClass=GalleryFragment.class;
                break;
            case R.id.logout:
                logout();
                break;
            default:
                fragementClass=GalleryFragment.class;
                break;
        }

        try{
            fragment=(Fragment) fragementClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }

        if(fragment != null){

            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fl,fragment).commit();

            menuItem.setChecked(true);
            setTitle(menuItem.getTitle());
        }

        drawerLayout.closeDrawers();
    }

    private void logout() {
        FirebaseUser mUser = mFirebaseAuth.getCurrentUser();
        if (mUser != null) {
            mFirebaseAuth.signOut();
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }
}
