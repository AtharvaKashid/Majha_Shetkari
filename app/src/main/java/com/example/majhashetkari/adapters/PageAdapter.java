package com.example.majhashetkari.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.majhashetkari.DailyRates.dailyratesTab;
import com.example.majhashetkari.News.newsTab;
import com.example.majhashetkari.homeTab;
import com.example.majhashetkari.userTab;

public class PageAdapter extends FragmentPagerAdapter
{
    int tabcount;
    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
       switch (position)
       {
           case 0 : return new homeTab();
           case 1 : return new newsTab();
           case 2 : return new dailyratesTab();
           case 3 : return new userTab();
           default: return null;
       }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
