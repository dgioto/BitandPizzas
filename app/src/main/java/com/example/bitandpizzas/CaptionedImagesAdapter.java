package com.example.bitandpizzas;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.net.sip.SipSession;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//Компонент ViewHolder указывает какие представления должны использоваться для каждого элеменит
public class CaptionedImagesAdapter extends
        RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    private String[] captions;
    private int[] imageIds;
    private Listener listener;

    interface Listener {
        void onClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;

        //В компоненте RecyclerView должны отображаться карточки, поэтому мы указываем,
        // что ViewHolder содержит представления CardView
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public CaptionedImagesAdapter(String[] captions, int[] imageIds) {
        this.captions = captions;
        this.imageIds = imageIds;
    }

    @Override
    public int getItemCount() {
        //длина массива равна количнству элементов данных в RecyclerView
        return captions.length;
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    //вызывается тогда, когда RecyclerView потребуется создать ViewHolder
    @Override
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //создание экземпляра ViewHolder
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    //заполняем карточки данными
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = cardView.findViewById(R.id.info_image);
        //Изображение выводится в графическом представлении ImageView
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView = (TextView) cardView.findViewById(R.id.info_text);
        //Название выводится в графическом предствалении ImageView
        textView.setText(captions[position]);

        //интерфейс добавляется к CardView
        cardView.setOnClickListener(new View.OnClickListener() {
            //при щелчке на CardView візвать метод onClick() интерфейса Listener
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onClick(position);
                }
            }
        });
    }
}
