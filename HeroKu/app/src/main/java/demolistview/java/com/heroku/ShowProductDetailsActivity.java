package demolistview.java.com.heroku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import demolistview.java.com.heroku.PojoCategoryData.Products;
import demolistview.java.com.heroku.RecyclerViewComponent.VarientRecyclerViewAdapter;

public class ShowProductDetailsActivity extends AppCompatActivity implements VarientRecyclerViewAdapter.ItemClickListener {

    VarientRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product_details);



        Products value_varient = (Products) getIntent().getExtras().getSerializable("KEY_PRODUCT");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new VarientRecyclerViewAdapter(this, value_varient.getVariants());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + position + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}