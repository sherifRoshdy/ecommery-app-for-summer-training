package com.example.ecommercyapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ecommercyapp.R;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView rating,name,description,price;
    Button addToCart,buyNow;
    ImageView addItems,removeItems;

    NewProductsModel newProductsModel = null;

    PopularProductsModel popularProductsModel = null;

    ShowAllModel showAllModel = null;

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        firestore = FirebaseFirestore.getInstance();

        final Object obj = getIntent().getSerializableExtra(name:"detailed");

        if(obj instanceof NewProductsModel){
            newProductsModel = (NewProductsModel)obj;
        }else if(obj instanceof PopularProductsModel){
            popularProductsModel = (PopularProductsModel)obj;
        }else if(obj instanceof ShowAllModel){
            showAllModel = (ShowAllModel)obj;
        }
        detailedImg = findViewById(R.id.detailed_img);
        name = findViewById(R.id.detailed_name);
        rating = findViewById(R.id.rating);
        description = findViewById(R.id.detailed_desc);
        price = findViewById(R.id.detailed_price);

        addToCart = findViewById(R.id.add_to_cart);
        buyNow = findViewById(R.id.buy_now);

        addItems = findViewById(R.id.add_item);
        removeItems = findViewById(R.id.remove_item);

        if(newProductsModel != null){
            Glide.with(getApplicationContext()).load(newProductsModel.getImg_url()).into(detailedImg)
            name.setText(newProductsModel.grtName());
            rating.setText(newProductsModel.grtRating());
            description.setText(newProductsModel.grtDescription());
            price.setText(String.valueOf(newProductsModel.grtPrice()));
        }
        if(popularProductsModel != null){
            Glide.with(getApplicationContext()).load(popularProductsModel.getImg_url()).into(detailedImg)
            name.setText(popularProductsModel.grtName());
            rating.setText(popularProductsModel.grtRating());
            description.setText(popularProductsModel.grtDescription());
            price.setText(String.valueOf(popularProductsModel.grtPrice()));
        }
        if(showAllModel != null){
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg)
            name.setText(showAllModel.grtName());
            rating.setText(showAllModel.grtRating());
            description.setText(showAllModel.grtDescription());
            price.setText(String.valueOf(showAllModel.grtPrice()));
        }
    }
}