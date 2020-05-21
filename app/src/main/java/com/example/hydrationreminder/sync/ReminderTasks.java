package com.example.hydrationreminder.sync;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import com.example.hydrationreminder.utilities.NotificationUtils;
import com.example.hydrationreminder.utilities.PreferenceUtilities;

public class ReminderTasks {

    public static final String ACTION_INCREMENT_WATER_COUNT = "increment-water-count";

    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notfication";

    public static final String ACTION_CHARGER_REMINDER = "charging-reminder";




    public static void executeTask(Context context,String action){
        Log.d("ReminderTask","executeTask() inside action: "+action);
        if (ACTION_INCREMENT_WATER_COUNT.equals(action)){
            incrementWaterCount(context);
        }else if (ACTION_DISMISS_NOTIFICATION.equals(action)){
            NotificationUtils.clearAllNotifications(context);
        } else if (ACTION_CHARGER_REMINDER.equals(action)){

            issueChargingReminder(context);
        }
    }


    private static void issueChargingReminder(Context context) {
        PreferenceUtilities.incrementChargingReminderCount(context);
        NotificationUtils.remindUsersBecauseCharging(context);
    }

    private static  void incrementWaterCount(Context context){
        Log.d("ReminderTask","incrementWaterCount() inside context: "+context);
        PreferenceUtilities.incrementWaterCount(context);
        NotificationUtils.clearAllNotifications(context);

    }
}
