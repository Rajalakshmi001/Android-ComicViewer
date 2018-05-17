package com.example.somasur.comicviewer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.somasur.comicviewer.Fragments.ComicFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class ComicsPagerAdapter extends FragmentPagerAdapter {

    public static final int count = 5;

    public ComicsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a ComicFragment (defined as a static inner class below).
        return ComicFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ComicFragment.getPageTitle();
    }

    @Override
    public int getCount() {
        // Show 5 total pages.
        return count;
    }

}