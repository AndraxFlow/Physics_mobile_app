package com.example.samsungproj;

import static android.app.PendingIntent.getActivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samsungproj.FormulesCalc.FArch;
import com.example.samsungproj.FormulesCalc.SorX;
import com.example.samsungproj.R;
import com.example.samsungproj.themes.Dinamika;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {
    private List<String> itemList;
    Context context;
    private OnItemClickListener listener;

    public AdapterClass(List<String> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calc_item, parent, false);
        return new ViewHolder(view, listener);
    }


    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String item = itemList.get(position);
        holder.bind(item);

        Button button = holder.button.findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, button.getText(), Toast.LENGTH_SHORT).show();

                Intent intent;// = new Intent(getActivity(), Themes.class);
                //intent.putExtra("ключ", dataList.get(position));

                //String receivedValue = getIntent().getStringExtra("ключ");
                switch (position){
                    case 0:
                        intent =  new Intent(context, FArch.class);
                        break;
                    case 1:
                        intent =  new Intent(context, SorX.class);
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
        public TextView textView;
        public Button button;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            //textView = itemView.findViewById(R.id.textView);
            button = itemView.findViewById(R.id.button1);

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

        public void bind(String item) {
            // Дополнительная логика привязки данных, если необходимо
            button.setText(String.valueOf(item));
        }
    }
}