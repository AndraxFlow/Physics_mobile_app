package com.example.samsungproj;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.example.samsungproj.database.entity.models.UserInfo;
import com.example.samsungproj.databinding.HomeItemBinding;

import java.util.List;


public class AdapterHome extends ListAdapter<UserInfo, AdapterHome.UserViewHolder> {
    private static final String DATABASE_NAME = "database/stations.db";
    private String url;
    private OnSwitchChangeListener switchChangeListener;

    public interface OnSwitchChangeListener {
        void onSwitchChanged(int position, boolean isChecked);
    }

    public AdapterHome(@NonNull DiffUtil.ItemCallback<UserInfo> diffCallback) {
        super(diffCallback);
    }

    public void setStations(List<UserInfo> stations) {
        submitList(stations);
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.home_item,       ///////////////////////////////////////////////////////////////- твой лэаяут
                parent,
                false
        );
        return new UserViewHolder(view);      /////////////////////////////////////////// - возвращаешь view holder
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(getItem(position)); /////////////////- это вроде тебе не надо

    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private HomeItemBinding addStationItemBinding;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            addStationItemBinding = HomeItemBinding.bind(itemView);
        }

        @SuppressLint("SetTextI18n")
        public void bind(UserInfo userInfo) {
            addStationItemBinding.station.setText(userInfo.getDatetime());  // добавление времени
        }
    }
    public static class StationDiff extends DiffUtil.ItemCallback<UserInfo> {

        @Override
        public boolean areItemsTheSame(@NonNull UserInfo oldItem, @NonNull UserInfo newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserInfo oldItem, @NonNull UserInfo newItem) {
            return (oldItem.getId() == newItem.getId());
        }
    }
    public void updateUserInfosList(List<UserInfo> userInfosList) {
        submitList(userInfosList);
    }
}
