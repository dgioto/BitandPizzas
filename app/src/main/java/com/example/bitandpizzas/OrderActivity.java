package com.example.bitandpizzas;

import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //заменяем панель инструментов панелью приложения
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Добавление кнопки "Вверх"
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    //этот метод вызывается, когда пользователь щелкает на ФАВ кнопке
    public void onClickDone(View view){
        CharSequence text = "Your order has been updater";
        int duration = Snackbar.LENGTH_SHORT;
        //создаем снейк бар
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator),
                text, duration);
        //С уведомлением Snackbar связывается действие
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //если пользователь щелкает на действии Snackbar, вівести уведомление Toast
                Toast toast = Toast.makeText(OrderActivity.this, "Undone!",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        // Отображает уведомление Snackbar
        snackbar.show();
    }
}