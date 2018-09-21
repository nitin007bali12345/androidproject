package demolistview.java.com.heroku;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

class ViewPagerAdapter extends FragmentPagerAdapter
{
    ArrayList<Fragment> arrayFragmentList =  new ArrayList<>();
    ArrayList<String> arrayFragmentName =  new ArrayList<>();


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        return arrayFragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        return arrayFragmentList.size();
    }

    public void addFragment(Fragment fr,String str)
    {
        arrayFragmentList.add(fr);
        arrayFragmentName.add(str);

    }

    @Override
    public CharSequence getPageTitle(int position) {

        return arrayFragmentName.get(position);
    }

}