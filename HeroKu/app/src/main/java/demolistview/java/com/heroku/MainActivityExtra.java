package demolistview.java.com.heroku;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import demolistview.java.com.heroku.NetworkCallComponent.NetworkCalls;
import demolistview.java.com.heroku.PojoCategoryData.PojoProduct;
import demolistview.java.com.heroku.UtilComponent.Utils;

public class MainActivityExtra extends AppCompatActivity implements NetworkCalls.NetworkResponseInterface{

    TabLayout tb;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    ArrayList<Integer> image = new ArrayList<>();
   // NetworkCallsFragment networkCalls;
    NetworkCalls networkCalls;
    public static PojoProduct mPojoProduct;

    Utils utils;
    public static ProgressDialog progressDialog;

    ArrayList<String> strCatagory = null;
    ArrayList<String> strProduct = null;
    public static int tabPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.tabViewPager);
        tb.bringToFront();
        tb.setupWithViewPager(viewPager);
        tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabPosition = tab.getPosition();
                Toast.makeText(MainActivityExtra.this,"Position : "+tab.getPosition(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        strCatagory = new ArrayList<>();
        strProduct = new ArrayList<>();
        utils = new Utils(getApplicationContext());
        progressDialog = ProgressDialog.show(MainActivityExtra.this, "ProgressDialog", "Please Wait");

        networkCalls = new NetworkCalls(MainActivityExtra.this);
        networkCalls.NetworkReqRes(getApplicationContext());
    }

    public void setFragment() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

       for(int i=0 ; i<mPojoProduct.getCategories().length;i++)
       {
           viewPagerAdapter.addFragment(new CategoryFragment(i),mPojoProduct.getCategories()[i].getName());
       }
       viewPager.setAdapter(viewPagerAdapter);

    }

    @Override
    public void handleNetworkResponse(PojoProduct pojoProduct) {
        mPojoProduct = pojoProduct;
        System.out.println(pojoProduct.toString());
            strCatagory = new ArrayList<>();

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

            }

        setFragment();
    }

}
