package rain2002kr.techworld.autosmssender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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
        userScrChange();
    }

    public void userScrChange(){
        serviceStartBtn = (Button) findViewById( R.id.button );
        serviceStopBtn = (Button) findViewById( R.id.button2 );

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
