package rain2002kr.techworld.autosmssender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sub4Activity extends AppCompatActivity {
    Button btSend;
    EditText edPhone,edPhone2,edPhone3,edPhone4,edPhone5,edData;
    TextView textLog;
    String[] phoneNo = new String[10];
    String smsMessage;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.main_menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        switch (curId){
            case R.id.refresh:
                printToast("새로고침 메뉴 선택됨");
                break;
            case R.id.home:
                printToast("홈 메뉴 선택됨");
                screenchange(MainActivity.class, 100);
                break;
            case R.id.serch:
                printToast("검색 메뉴 선택됨");
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected( item );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub4);
        UserFindViewById();
        UserFunction();
    }
    //TODO UserFindViewById
    public void UserFindViewById(){
        btSend = (Button) findViewById(R.id.btSend);
        edPhone = (EditText) findViewById(R.id.edPhone);
        edPhone2 = (EditText) findViewById(R.id.edPhone2);
        edPhone3 = (EditText) findViewById(R.id.edPhone3);
        edPhone4 = (EditText) findViewById(R.id.edPhone4);
        edPhone5 = (EditText) findViewById(R.id.edPhone5);
        edData = (EditText) findViewById(R.id.edData);
        textLog = (TextView) findViewById(R.id.textLog);

    }
    //TODO UserFunction
    public void UserFunction(){
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNo[0] = "01046973907";//edPhone.getText().toString();
                phoneNo[1] = edPhone2.getText().toString();
                phoneNo[2] = edPhone3.getText().toString();
                phoneNo[3] = edPhone4.getText().toString();
                phoneNo[4] = edPhone5.getText().toString();

                smsMessage = "test1";//edData.getText().toString();
                sendSMS(phoneNo[0],smsMessage);
                //smsSend();

            }
        });
    }

    protected void sendSMSMessage() {


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo[0], null, smsMessage, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
    //using builtin sms message
    public void smsSend(){
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , new String ("01046973907"));
        smsIntent.putExtra("sms_body"  , "Test ");

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    protected void sendSMS(String phoneNo, String smsMessage) {
        println("Send SMS");
           try {
               SmsManager smsManager = SmsManager.getDefault();
               smsManager.sendTextMessage(phoneNo, null, smsMessage, null, null);
                println("Finished sending SMS...");

        } catch (android.content.ActivityNotFoundException ex) {
            println("SMS faild, please try again later.");
        }


    }

    //TODO UserAPI Blocks
    public void screenchange(Class cls,int requestCode){
        Intent intent = new Intent(getApplicationContext(),cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivityForResult(intent,requestCode);
    }
    public void printToast(String data){
        Toast.makeText(getApplicationContext(),data, Toast.LENGTH_LONG).show();
    }
    public void println(String data){
        textLog.append(data + "\n");
    }
}
