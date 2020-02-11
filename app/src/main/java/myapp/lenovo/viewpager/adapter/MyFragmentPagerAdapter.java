package myapp.lenovo.viewpager.adapter;

//import androidx.core.app.FragmentManageragment;
//import androidx.core.app.FragmentManager;
//import androidx.core.app.FragmentPagerAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Lenovo on 2016/11/3.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> myfragments){
        super(fm);
        this.fragments=myfragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
