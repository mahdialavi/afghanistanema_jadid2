package Async;

import android.os.AsyncTask;

import java.util.ArrayList;

import StructPerson.StructPerson;
import web.Commands;

/**
 * Created by alavi on 11/20/2017.
 */

public class AsyncRead extends AsyncTask<Integer, Void, ArrayList<StructPerson>> {
    private Runnable startAction;
    private OnDataReceivedListener onDataReceivedListener;

    @Override
    protected void onPreExecute() {
        if (startAction != null) {
            startAction.run();
        }
    }

    @Override
    protected ArrayList<StructPerson> doInBackground(Integer... integers) {
        return Commands.read(integers[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<StructPerson> result) {
        if (onDataReceivedListener != null) {
            onDataReceivedListener.OnDataReceived(result);
        }
    }

    public AsyncRead setOnDataReceivedListener(OnDataReceivedListener onDataReceivedListener) {
        this.onDataReceivedListener = onDataReceivedListener;
        return this;
    }

    public AsyncRead setStartAction(Runnable startAction) {
        this.startAction = startAction;
        return this;
    }

    public interface OnDataReceivedListener {
        void OnDataReceived(ArrayList<StructPerson> result);
    }
}