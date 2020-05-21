package com.example.hydrationreminder.sync;


import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.RetryStrategy;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class WaterReminderFirebaseJobService extends JobService {

    private AsyncTask mBackgroundTask;


    @Override
    public boolean onStartJob(final JobParameters params) {

        Log.d("WaterReminderFirebaseJobService","onStartJob inside params: "+params);

        mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Log.d("WaterReminderFirebaseJobService","doInBackground inside objects: "+objects);
                Context context = WaterReminderFirebaseJobService.this;
                ReminderTasks.executeTask(context,ReminderTasks.ACTION_CHARGER_REMINDER);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(params,false);
            }
        };

        Log.d("WaterReminderFirebaseJobService"," mBackgroundTask.execute()");
        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        if (mBackgroundTask!=null){
            mBackgroundTask.cancel(true);
        }
        return true;
    }
}
