package rain2002kr.techworld.autosmssender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class Sub2Activity extends AppCompatActivity {
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
        setContentView( R.layout.activity_sub2 );
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
