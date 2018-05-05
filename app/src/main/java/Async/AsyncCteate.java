package Async;

import android.os.AsyncTask;

import StructPerson.StructPerson;
import web.Commands;

/**
 * Created by alavi on 11/20/2017.
 */

public class AsyncCteate extends AsyncTask<StructPerson, Void, Integer> {
    private Runnable startAction;
    private OnDataReceivedListener onDataReceivedListener;

    @Override
    protected void onPreExecute() {
        if (startAction != null) {
            startAction.run();
        }
    }

    @Override
    protected Integer doInBackground(StructPerson... structPersons) {
        return Commands.create(structPersons[0]);
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (onDataReceivedListener != null) {
            onDataReceivedListener.OnDataReceived(result);
        }
    }

    public AsyncCteate setOnDataReceivedListener(OnDataReceivedListener onDataReceivedListener) {
        this.onDataReceivedListener = onDataReceivedListener;
        return this;
    }

    public AsyncCteate setStartAction(Runnable startAction) {
        this.startAction = startAction;
        return this;
    }

    public interface OnDataReceivedListener {
        void OnDataReceived(int result);
    }}