package com.example.bitandpizzas;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //заменяем панель инструментов панелью приложения
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Связывание SectionsPagerAdapter c ViewPager
        //используются фрагменты из библиотеки поддержки, поэтому адаптеру необходимо передать
        //ссылку на диспетчерфрагментов поддержки
        SectionsPagerAdapter pagerAdapter =
                new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        //Написанный ранее код FragmentPagerAdapter присоединяется к ViewPager
        pager.setAdapter(pagerAdapter);
    }

    //Добавляем меню на панель приложения
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_mein, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        //Получить ссылку на провайдера действия передачи информации и присвоить ее приватной переменной
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("Want to join for pizza?");
        return super.onCreateOptionsMenu(menu);
    }

    //Создаем метод, который создает интент и передает его провайдеру действия передачи информации
    // при помощи его метода setShareIntent()
    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    //Обработка выбора элементов действий

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_create_order:
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //адаптер страничного компонента фрагментов
    private class SectionsPagerAdapter extends FragmentPagerAdapter{

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //необходим для указания какой фрагмент далжен выводиться на каждой странице
        @Override
        public Fragment getItem(int i) {
            switch (i){
                case  0:
                    return  new TopFragment();
                case  1:
                    return new PizzaFragment();
                case 2:
                    return new PastaFragment();
                case 3:
                    return  new StorFragment();
            }
            return null;
        }

        //необходим для определения количества страниц в ViewPager
        @Override
        public int getCount() {
            return 4;
        }
    }
}