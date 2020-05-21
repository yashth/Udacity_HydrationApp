package com.example.hydrationreminder.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class PreferenceUtilities {

    public static final String KEY_WATER_COUNT = "water-count";
    public static final String KEY_CHARGING_REMINDER_COUNT = "charging-reminder-count";

    private static final int DEFAULT_COUNT = 0;

    synchronized private static void setWaterCount(Context context, int glassesOfWater) {
        Log.d("PreferenceUtilities","setWaterCount() inside glassesOfWater: "+glassesOfWater);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_WATER_COUNT, glassesOfWater);
        editor.apply();
    }

    public static int getWaterCount(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int glassesOfWater = prefs.getInt(KEY_WATER_COUNT, DEFAULT_COUNT);
        Log.d("PreferenceUtilities","getWaterCount() inside glassesOfWater: "+glassesOfWater);
        return glassesOfWater;
    }

    synchronized public static void incrementWaterCount(Context context) {

        Log.d("PreferenceUtilities","incrementWaterCount() inside");
        int waterCount = PreferenceUtilities.getWaterCount(context);
        Log.d("PreferenceUtilities","incrementWaterCount() inside waterCount: "+waterCount);
        PreferenceUtilities.setWaterCount(context, ++waterCount);
    }

    synchronized public static void incrementChargingReminderCount(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int chargingReminders = prefs.getInt(KEY_CHARGING_REMINDER_COUNT, DEFAULT_COUNT);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_CHARGING_REMINDER_COUNT, ++chargingReminders);
        editor.apply();
    }

    public static int getChargingReminderCount(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int chargingReminders = prefs.getInt(KEY_CHARGING_REMINDER_COUNT, DEFAULT_COUNT);
        return chargingReminders;
    }
}
