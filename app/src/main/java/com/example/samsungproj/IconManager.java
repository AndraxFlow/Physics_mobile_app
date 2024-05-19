package com.example.samsungproj;
import java.util.HashMap;

public class IconManager {
    private static final HashMap<String, Integer> iconMap = new HashMap<>();

    static {
        iconMap.put("0", R.drawable.baseline_ac_unit_24);
        iconMap.put("1", R.drawable.ic_android_black_24dp);
        iconMap.put("2", R.drawable.baseline_5g_24);
    }

    public static int getIconResource(String numLine) {
        Integer iconResId = iconMap.get(numLine);
        if (iconResId != null) {
            return iconResId;
        } else {
            return R.drawable.baseline_volume_off_24;
        }
    }
}
