package com.example.samsungproj;

import static android.app.PendingIntent.getActivity;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samsungproj.FormulesCalc.A;
import com.example.samsungproj.FormulesCalc.FArch;
import com.example.samsungproj.FormulesCalc.SorX;
import com.example.samsungproj.R;
import com.example.samsungproj.themes.Dinamika;

import java.util.HashMap;
import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {
    private List<String> itemList;
    Context context;
    private OnItemClickListener listener;
    int[] images;

    public AdapterClass(List<String> itemList, Context context, int[] images) {
        this.itemList = itemList;
        this.context = context;
        this.images = images;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calc_item, parent, false);
        return new ViewHolder(view, listener);
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String item = itemList.get(position);
        holder.imageButton.setImageResource(images[position]);
        final MediaPlayer mediaPlayer1 = MediaPlayer.create(context, R.raw.a2);

        Button button = holder.button.findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, button.getText(), Toast.LENGTH_SHORT).show();

                Intent intent;

                switch (position){
                    case 0:
                        mediaPlayer1.start();
                        intent =  new Intent(context, FArch.class);
                        break;
                    case 1:
                        mediaPlayer1.start();
                        intent =  new Intent(context, SorX.class);
                        break;
                    case 2:
                        mediaPlayer1.start();
                        intent =  new Intent(context, A.class);
                        break;
                    default:
                        intent =  new Intent(context, Dinamika.class);
                        break;
                }
                context.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button button;
        public ImageButton imageButton;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            //textView = itemView.findViewById(R.id.textView);
            button = itemView.findViewById(R.id.button1);
            imageButton = itemView.findViewById(R.id.imageButton);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }




        public void onBindViewHolder(@NonNull AdapterClass.ViewHolder holder, int position) {
        }
        public void bind(String item) {
            // Дополнительная логика привязки данных, если необходимо
            button.setText(String.valueOf(item));
        }
    }
}