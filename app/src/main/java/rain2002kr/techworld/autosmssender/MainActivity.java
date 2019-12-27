package rain2002kr.techworld.autosmssender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button serviceStartBtn,serviceStopBtn;
    Button homebtn,sub1btn,sub2btn,sub3btn,sub4btn;
    static final int SMS_RECEIVE_PERMISSON=1;

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
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        permissionCheck();
        userScrChange();
    }

    public void permissionCheck(){
        //권한이 부여되어 있는지 확인
        int permissonCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        if(permissonCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(), "SMS 수신권한 있음", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "SMS 수신권한 없음", Toast.LENGTH_SHORT).show();

            //권한설정 dialog에서 거부를 누르면
            //ActivityCompat.shouldShowRequestPermissionRationale 메소드의 반환값이 true가 된다.
            //단, 사용자가 "Don't ask again"을 체크한 경우
            //거부하더라도 false를 반환하여, 직접 사용자가 권한을 부여하지 않는 이상, 권한을 요청할 수 없게 된다.
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)){
                //이곳에 권한이 왜 필요한지 설명하는 Toast나 dialog를 띄워준 후, 다시 권한을 요청한다.
                Toast.makeText(getApplicationContext(), "SMS권한이 필요합니다", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.RECEIVE_SMS}, SMS_RECEIVE_PERMISSON);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.RECEIVE_SMS}, SMS_RECEIVE_PERMISSON);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int grantResults[]){
        switch(requestCode){
            case SMS_RECEIVE_PERMISSON:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(), "SMS권한 승인함", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "SMS권한 거부함", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void userScrChange(){
        serviceStartBtn = (Button) findViewById( R.id.button );
        serviceStopBtn = (Button) findViewById( R.id.button2 );
        serviceStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SmSService.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startService(intent);
            }
        });
        serviceStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SmSService.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                stopService(intent);
            }
        });

        sub1btn = (Button) findViewById( R.id.button3 );
        sub2btn = (Button) findViewById( R.id.button4 );
        sub3btn = (Button) findViewById( R.id.button5 );
        sub4btn = (Button) findViewById( R.id.button6 );

        sub1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenchange(Sub1Activity.class, 101);
            }
        });
        sub2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenchange(Sub2Activity.class, 102);
            }
        });
        sub3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenchange(Sub3Activity.class, 103);
            }
        });
        sub4btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenchange(Sub4Activity.class, 104);
            }
        });


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

}
