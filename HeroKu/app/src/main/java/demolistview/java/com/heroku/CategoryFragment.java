package demolistview.java.com.heroku;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demolistview.java.com.heroku.PojoCategoryData.Products;
import demolistview.java.com.heroku.RecyclerViewComponent.CategoryRecyclerViewAdapter;

public class CategoryFragment extends Fragment implements CategoryRecyclerViewAdapter.ItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    CategoryRecyclerViewAdapter adapter;

//    ProductDetails fragmentProductDetails;


    public CategoryFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public CategoryFragment(int position) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        fragment.setArguments(args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_category, container, false);
        // data to populate the RecyclerView with
        // set up the RecyclerView
        recyclerView = v.findViewById(R.id.rvCategory);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));


        return v;
    }

    public void setDataInAdapter(int tabPosition){
        System.out.println("Tab Position "+ CategoryTabBarActionActivity.tabPosition);
        String categoryName = null;
        Products[] productsArray = null;
        String categoryDateIssued = null;


        categoryName = CategoryTabBarActionActivity.mPojoProduct.getCategories()[CategoryTabBarActionActivity.tabPosition].getName();
        productsArray = CategoryTabBarActionActivity.mPojoProduct.getCategories()[CategoryTabBarActionActivity.tabPosition].getProducts();

        for(int i = 0; i< CategoryTabBarActionActivity.mPojoProduct.getCategories()[CategoryTabBarActionActivity.tabPosition].getProducts().length; i++)
        {
            categoryDateIssued = CategoryTabBarActionActivity.mPojoProduct.getCategories()[CategoryTabBarActionActivity.tabPosition].getProducts()[i].getDate_added();
            Log.d(" Date Added ", categoryDateIssued);

        }

        if(productsArray != null && productsArray.length != 0)
        {

            for(int k = 0 ; k<productsArray.length ;k++)
            {
                for (int j = 0; j < productsArray[k].getVariants().length; j++) {
                    String price_ = productsArray[k].getVariants()[j].getPrice();
                    String color_ = productsArray[k].getVariants()[j].getColor();
                    String size_ = productsArray[k].getVariants()[j].getSize();
                    String id_ = productsArray[k].getVariants()[j].getId();
                    Log.d(" Varient ", "Price " + price_ + " Color " + color_ + " Size " + size_ + " ID " + id_);
                }
            }
        }







            adapter = new CategoryRecyclerViewAdapter(getActivity(), productsArray,categoryName,categoryDateIssued);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, Products products)
    {
//        fragmentProductDetails = new ProductDetails();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.layoutcatefory, fragmentProductDetails);
//        fragmentTransaction.commit();

        Bundle bundle = new Bundle();
        Intent in = new Intent(getActivity(),ShowProductDetailsActivity.class);
        bundle.putSerializable("KEY_PRODUCT",products);
        in.putExtras(bundle);
        startActivity(in);

    }


}
