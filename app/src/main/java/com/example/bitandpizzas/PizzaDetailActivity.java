package com.example.bitandpizzas;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class PizzaDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PIZZA_ID = "pizzaId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);

        //Set the toolbar as the activity's app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //добавляем кнопку вверх
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Display details of the pizza
        //Идентификатор используется для заполнения TextView и ImageView
        //получаем пиццу выбранную из интента
        int pizzaId = (Integer) getIntent().getExtras().get(EXTRA_PIZZA_ID);
        String pizzaName = Pizza.pizzas[pizzaId].getName();
        TextView textView = (TextView) findViewById(R.id.pizza_text);
        textView.setText(pizzaName);
        int pizzaImage = Pizza.pizzas[pizzaId].getImageResourceId();
        ImageView imageView = (ImageView) findViewById(R.id.pizza_image);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, pizzaImage));
        imageView.setContentDescription(pizzaName);
    }
}