package rain2002kr.techworld.autosmssender;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class SmSService extends Service {
    public SmSService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        printToast("onCreate()  호출됨");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        printToast("onStartCommand() 호출됨");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        printToast("onDestroy() 호출됨");

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //TODO UserAPI Blocks
    public void printToast(String data){
        Toast.makeText(getApplicationContext(),data, Toast.LENGTH_LONG).show();
    }

}
