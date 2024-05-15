package com.example.samsungproj;

import com.example.samsungproj.R;

import java.util.HashMap;

public class IconManager {
    private static final HashMap<String, Integer> iconMap = new HashMap<>();

    static {
        // Здесь добавьте соответствия номера линии и идентификатора ресурса изображения
        iconMap.put("0", R.drawable.baseline_ac_unit_24);
        iconMap.put("1", R.drawable.ic_android_black_24dp);
        iconMap.put("2", R.drawable.baseline_5g_24);
        // Добавьте другие соответствия, если необходимо
    }

    public static int getIconResource(String numLine) {
        Integer iconResId = iconMap.get(numLine);
        if (iconResId != null) {
            return iconResId;
        } else {
            // Если нет соответствующего изображения, возвращаем значение по умолчанию
            return R.drawable.baseline_volume_off_24;
        }
    }
}
