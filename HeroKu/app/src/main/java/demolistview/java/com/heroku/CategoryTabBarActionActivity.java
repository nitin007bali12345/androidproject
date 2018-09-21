package demolistview.java.com.heroku;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import demolistview.java.com.heroku.NetworkCallComponent.NetworkCalls;
import demolistview.java.com.heroku.PojoCategoryData.PojoProduct;

public class CategoryTabBarActionActivity extends AppCompatActivity implements NetworkCalls.NetworkResponseInterface{
    public static CategoryTabBarActionActivity instance;
    private CategoryFragment fragmentOne;
    private TabLayout allTabs;
    NetworkCalls networkCalls;
    ArrayList<String> strCatagory = null;
    ArrayList<String> strProduct = null;

    public static int tabPosition = 0;

    public static PojoProduct mPojoProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_tab_bar_action);
        instance=this;
        getAllWidgets();
        bindWidgetsWithAnEvent();
        setupTabLayout();
        networkCalls = new NetworkCalls(CategoryTabBarActionActivity.this);
        networkCalls.NetworkReqRes(getApplicationContext());
    }
    public static CategoryTabBarActionActivity getInstance() {
        return instance;
    }
    private void getAllWidgets() {
        allTabs = (TabLayout) findViewById(R.id.tabs);
    }
    private void setupTabLayout() {
        fragmentOne = new CategoryFragment();
        addFragment(fragmentOne);
//        allTabs.addTab(allTabs.newTab().setText("ONE"),true);
//        allTabs.addTab(allTabs.newTab().setText("TWO"));
//        allTabs.addTab(allTabs.newTab().setText("THREE"));
//        allTabs.addTab(allTabs.newTab().setText("FOUR"));
//        allTabs.addTab(allTabs.newTab().setText("FIVE"));
//        allTabs.addTab(allTabs.newTab().setText("SIX"));
//        allTabs.addTab(allTabs.newTab().setText("SEVEN"));
//        allTabs.addTab(allTabs.newTab().setText("EIGHT"));
//        allTabs.addTab(allTabs.newTab().setText("NINE"));

    }
    private void bindWidgetsWithAnEvent()
    {
        allTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabPosition = tab.getPosition();
                setCurrentTabFragment(tabPosition);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    private void setCurrentTabFragment(int tabPosition)
    {
        if(strCatagory!=null && strCatagory.size()>0)
            fragmentOne.setDataInAdapter(tabPosition);

    }
    public void addFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @Override
    public void handleNetworkResponse(PojoProduct pojoProduct) {
        mPojoProduct = pojoProduct;
        System.out.println(pojoProduct.toString());
        strCatagory = new ArrayList<>();
        strProduct = new ArrayList<>();

        for(int i = 0 ;i < pojoProduct.getCategories().length ; i++)
        {
            String strName = pojoProduct.getCategories()[i].getName();
            Log.e("Category",pojoProduct.getCategories()[i].getName());
            strCatagory.add(strName);

            for(int j=0;j<pojoProduct.getCategories()[i].getProducts().length;j++)
            {
                Log.e("Category With Product",pojoProduct.getCategories()[i].getName()+"  ******   "+pojoProduct.getCategories()[i].getProducts()[j].getName());
                strProduct.add(pojoProduct.getCategories()[i].getProducts()[j].getName());
            }

            allTabs.addTab(allTabs.newTab().setText(strCatagory.get(i)));
        }



        fragmentOne.setDataInAdapter(tabPosition);
    }
}
