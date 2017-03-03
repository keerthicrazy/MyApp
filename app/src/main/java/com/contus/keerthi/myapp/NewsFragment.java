package com.contus.keerthi.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.contus.keerthi.myapp.Contract.MyApp;

/**
 * Created by user on 24/2/17.
 */

public class NewsFragment extends Fragment {


    private ViewPager mViewPager;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    public static int items = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.news_fragment,container,false);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        mViewPager = (ViewPager)view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position)
            {
                case 0:
                    FetchNews top =new FetchNews();
                    Bundle args = new Bundle();
                    args.putString("newsType","top");
                    top.setArguments(args);
                    return top;
                case 1:
                    FetchNews latest =new FetchNews();
                    Bundle bundle = new Bundle();
                    bundle.putString("newsType","latest");
                    latest.setArguments(bundle);
                    return latest;
                case 2:
                    FetchNews tech =new FetchNews();
                    Bundle tech_args = new Bundle();
                    tech_args.putString("newsType","tech");
                    tech.setArguments(tech_args);
                    return tech;
            }
            return null;
        }

        @Override
        public int getCount() {
            return MyApp.PagerNames.NEWS_PAGER.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return MyApp.PagerNames.NEWS_PAGER[position];
        }
    }

}
