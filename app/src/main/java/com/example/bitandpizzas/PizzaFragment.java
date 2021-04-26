package com.example.bitandpizzas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

//Используем Fragment вместо ListFragment для вывода списка пицц
public class PizzaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        //ArrayAdapter заполняет компонент ListView фрагмента ListFragment названиями видов пиццы
//        ArrayAdapter<String > adapter = new ArrayAdapter<>(
//          inflater.getContext(),
//          android.R.layout.simple_list_item_1,
//          getResources().getStringArray(R.array.pizzas));
//        setListAdapter(adapter);
//        return super.onCreateView(inflater, container, savedInstanceState);

        //создае RecyclerView
        RecyclerView pizzaRecycle = (RecyclerView) inflater.inflate(
                R.layout.fragment_pizza, container, false);
        //названия видов пицц добавляются в массив строк
        String[] pizzaNames = new String[Pizza.pizzas.length];
        for (int i=0; i < pizzaNames.length; i++){
            pizzaNames[i] = Pizza.pizzas[i].getName();
        }
        //изображения добавляются в массив элементами int
        int[] pizzaImages = new int[Pizza.pizzas.length];
        for (int i = 0; i < pizzaImages.length; i++){
            pizzaImages[i] = Pizza.pizzas[i].getImageResourceId();
        }
        //массивы передаются адаптеру
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pizzaNames, pizzaImages);
        pizzaRecycle.setAdapter(adapter);
        //используем для отображения карточек в виде таблицы из двух столбцов
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        pizzaRecycle.setLayoutManager(layoutManager);

        //реализация интерфейса Listener из CaptionImagesAdapter, так что бы при щелчке на карточу
        // в RecyclerView будет запускаться PizzaDetailActivity
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), PizzaDetailActivity.class);
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_ID, position);
                //реализация метода onClick() интерфейса Listener запускает
                // активность PizzaDetailActivity, передавая ей идентификатор пиццы,
                // выбранной пользователем
                getActivity().startActivity(intent);
            }
        });

        return  pizzaRecycle;
    }
}