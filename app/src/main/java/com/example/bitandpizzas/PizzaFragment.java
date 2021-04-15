package com.example.bitandpizzas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

//Используем ListFragment для вывода списка видов пиццы
public class PizzaFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //ArrayAdapter заполняет компонент ListView фрагмента ListFragment названиями видов пиццы
        ArrayAdapter<String > adapter = new ArrayAdapter<>(
          inflater.getContext(),
          android.R.layout.simple_list_item_1,
          getResources().getStringArray(R.array.pizzas));
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}