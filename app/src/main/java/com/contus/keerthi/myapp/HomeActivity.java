package com.contus.keerthi.myapp;
/**
 * This activity is used to display the news with categories. Also it checks signin with firebase
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private TextView tv_userName,tv_userEmail;
    private ImageView iv_userimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        tv_userName = (TextView) header.findViewById(R.id.mUsername);
        tv_userEmail = (TextView) header.findViewById(R.id.mUseremail);
        iv_userimage = (ImageView) header.findViewById(R.id.mUserimage);

        initListener();
        setUserDetails();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_home, new NewsFragment())
                .commit();
        this.setTitle("News");
    }

    /**
     * Listener for Firebase
     */
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

    private void setUserDetails()
    {
        FirebaseUser mUser = mFirebaseAuth.getCurrentUser();
        tv_userName.setText(mUser.getDisplayName());
        tv_userEmail.setText(mUser.getEmail());
        Glide.with(this).load(mUser.getPhotoUrl()).into(iv_userimage);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finishAffinity();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment=null;
        Class fragementClass = null;
        switch (item.getItemId())
        {
            case R.id.gallery:
                fragementClass=GalleryFragment.class;
                break;
            case R.id.news:
                fragementClass=NewsFragment.class;
                break;
            case R.id.about:
                fragementClass=profileActivity.class;
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
            fragmentManager.beginTransaction().replace(R.id.content_home,fragment).commit();
            item.setChecked(true);
            setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        FirebaseUser mUser = mFirebaseAuth.getCurrentUser();
        if (mUser != null) {
            mFirebaseAuth.signOut();
        }
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
