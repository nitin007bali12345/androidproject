package demolistview.java.com.heroku.NetworkCallComponent;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

import demolistview.java.com.heroku.CategoryTabBarActionActivity;
import demolistview.java.com.heroku.PojoCategoryData.PojoProduct;
import demolistview.java.com.heroku.UtilComponent.Utils;

public class NetworkCalls {

    private String url_category = "https://stark-spire-93433.herokuapp.com/json";
    RequestQueue requestQueue;
    StringRequest stringRequest;
    Utils utils;
    CategoryTabBarActionActivity mainActivity;


    //ArrayList
    public static ArrayList<String> strCatagory;


    public NetworkCalls(Context con)
    {
        utils = new Utils(con);
        mainActivity = (CategoryTabBarActionActivity)con;
    }

    public void NetworkReqRes(final Context con)
    {

        requestQueue = Volley.newRequestQueue(con);
        stringRequest = new StringRequest(Request.Method.GET, url_category, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                PojoProduct pojoProduct = ResponseCategory(response);
                mainActivity.handleNetworkResponse(pojoProduct);
                Toast.makeText(con ,""+response,Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });

        requestQueue.add(stringRequest);

    }

    PojoProduct ResponseCategory(String str)
    {
        //CategoryTabBarActionActivity.progressDialog.cancel();
        PojoProduct pojoProduct = null;
        try {

            Gson gson = new Gson();
            pojoProduct = gson.fromJson(str,PojoProduct.class);

            /*System.out.println(pojoProduct.toString());
            strCatagory = new ArrayList<>();

            for(int i = 0 ;i < pojoProduct.getCategories().length ; i++)
            {
                String strName = pojoProduct.getCategories()[i].getName();
                strCatagory.add(strName);

                //pojoProduct.getRankings()[i].getProducts();
                Log.e("Product Category Data",""+pojoProduct.getRankings()[i].getProducts());

            }*/

            //listView.setAdapter(new VollyAdaper(getApplicationContext(),arrayPojoVolly));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pojoProduct;
    }


    public interface NetworkResponseInterface{
        void handleNetworkResponse(PojoProduct pojoProduct);
    }

}
