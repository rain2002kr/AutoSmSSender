package rain2002kr.techworld.autosmssender;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import rain2002kr.techworld.autosmssender.Tel_item.TelItem;

public class Sub1Activity extends AppCompatActivity {
    Button btEnter,btDelete;
    EditText edName, edPhone;
    RecyclerView recyclerView;
    recyclerAdpter adpter;
    SharedPreferences pref;
    SharedPreferences.Editor preEditor;
    Gson gson; String json;
    ArrayList<TelItem> itemss = new ArrayList<TelItem>();
    SQLiteDatabase database;
    String dbName = "custom";
    String tableName = "telList";

    String TAG = "sub1Activity";
    TextView textLog;

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
               // printToast("홈 메뉴 선택됨");
                screenchange(MainActivity.class, 100);
                break;
            case R.id.serch:
                //printToast("검색 메뉴 선택됨");
                tableName = "telList";
                executeQuery(tableName);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected( item );
    }

    @Override
    protected void onPause() {
        super.onPause();
        printToast("onPause 호출됨");

        preEditor.putString("name", edName.getText().toString());
        preEditor.putString("phone", edPhone.getText().toString());
        preEditor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        edName.setText(pref.getString("name",edName.getText().toString()));
        edPhone.setText(pref.getString("phone",edPhone.getText().toString()));
        executeQuery(tableName);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sub1 );
        UserFindViewById();
        UserFunction();
        createOrOpenDataBase(dbName);

        createTable(tableName);


    }

    //TODO UserFindViewById
    public void UserFindViewById(){
        btEnter = (Button) findViewById(R.id.btEnter);
        btDelete = (Button) findViewById(R.id.btDelete);
        edName = (EditText) findViewById(R.id.edName);
        edPhone = (EditText) findViewById(R.id.edPhone);
        recyclerView = (RecyclerView) findViewById(R.id.recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adpter = new recyclerAdpter();
        recyclerView.setAdapter(adpter);
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        preEditor = pref.edit();
        textLog = (TextView) findViewById(R.id.textLog);

    }
    //TODO UserFunction
    public void UserFunction(){

        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString();
                String phone = edPhone.getText().toString();
                adpter.addItem(new TelItem(R.drawable.star, name, phone));
                itemss.add(new TelItem(R.drawable.star, name, phone));
                adpter.notifyDataSetChanged();
                insertData(tableName, new TelItem(R.drawable.star, name, phone));

            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delSeletedItem(tableName);
            }
        });
    }
    //TODO SQLiteDataBase code
    //1. Create or open DataBase the method
    public void createOrOpenDataBase(String dbName){
        DataBaseHelper helper = new DataBaseHelper(this, dbName, null, 2, tableName );
        database = helper.getWritableDatabase();

    }
    //2. Create table
    public void createTable(String tableName){
        if(database != null) {
            String sql = "CREATE TABLE IF NOT EXISTS "
                    + tableName + "(_id integer PRIMARY KEY autoincrement, " +
                    "image int, name text, phone text)";
            database.execSQL(sql);
            println("DataBase table 생성됨." + tableName);
        } else {
            println("DataBase 먼저 생성하세요");
        }
    }

    //3. insert value into the table at DB
    public void insertData(String tableName, int image, String name, String phone){
        println("insertData 호출됨");
        if (database != null) {
            String sql = "INSERT INTO " + tableName + "(image, name, phone)" + "values(?, ?, ?)";
            Object[] params = {image, name, phone};
            database.execSQL(sql, params);
            println("insertData 추가됨");
        } else {
            println("Database 생성하세요.");
        }
    }
    public void insertData(String tableName, TelItem item){
        int image = item.getResId();
        String name = item.getName();
        String phone = item.getPhone();
        println("insertData 호출됨");
        if (database != null) {
            String sql = "INSERT INTO " + tableName + "(image, name, phone)" + "values(?, ?, ?)";
            Object[] params = {image, name, phone};
            database.execSQL(sql, params);
            println("insertData 추가됨");
        } else {
            println("Database 생성하세요.");
        }
    }
    //4. data serch and load
    public void executeQuery(String tableName) {
        println("executeQuery 호출됨");
        try {
            if (database != null) {
                String sql = "select _id, image, name, phone from " + tableName;
                Cursor cursor = database.rawQuery(sql, null);

                println("레코드 개수 : " + cursor.getCount());

                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToNext();
                    int id = cursor.getInt(0);
                    int image = cursor.getInt(1);
                    String name = cursor.getString(2);
                    String phone = cursor.getString(3);

                    println("레코드 #: " + id  + " name : " + name + " , phone :" + phone );
                    adpter.addItem(new TelItem(R.drawable.star, name, phone));
                }
                println("executeQuery 마무리됨");
                cursor.close();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    //5. Delete item
    public void delSeletedItem(String tableName) {
        println("delSeletedItem 호출됨");
        if (database != null) {
            int id = 0;
            try {
            String sql = "select _id from " + tableName;
            Cursor cursor = database.rawQuery(sql, null);
                for (int i = 0; i < cursor.getCount(); i++) {
                    //cursor.moveToNext();
                    cursor.moveToLast();
                    id = cursor.getInt(0);
                }
                sql ="delete from " + tableName + " WHERE _id =" + id;

            database.execSQL(sql);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //DataBaseHelper
    class DataBaseHelper extends SQLiteOpenHelper {
        String dbName, tableName;

        public DataBaseHelper(@Nullable Context context, @Nullable String dbName, @Nullable SQLiteDatabase.CursorFactory factory, int version, String tableName) {
            super(context, dbName, factory, version);
            this.tableName = tableName;

        }

        public void onOpen(SQLiteDatabase dbName){
            println("DataBaseHelper onOpen 호출됨.");
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            println("DataBaseHelper onCreate 호출됨.");
            try {
                String sql = "create table if not exists "
                        + tableName + "(_id integer PRIMARY KEY autoincrement, " +
                        "image int, name text, phone text)";
                sqLiteDatabase.execSQL(sql);
                println("DataBase table 생성됨." + tableName);
            } catch (Exception e){e.printStackTrace();}
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
            println("DataBaseHelper onUpgrade 호출됨.");
            println("DataBase old Version :" + oldVer);
            println("DataBase new Version :" + newVer);
            if (newVer > 1){
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
                println("old table 삭제됨");
                String sql = "CREATE TABLE IF NOT EXISTS " + tableName +
                        "(_id integer PRIMARY KEY autoincrement, " +
                        "image int, name text, phone text)";
                sqLiteDatabase.execSQL(sql);
                println("new table 생성됨");
            }
        }
    }

    //TODO Adpater for recyclerView
    class recyclerAdpter extends RecyclerView.Adapter<recyclerAdpter.ViewHolder>{
        ArrayList<TelItem> items = new ArrayList<TelItem>();

        public recyclerAdpter(ArrayList<TelItem> items) {
            this.items = items;
        }

        public recyclerAdpter() {
            ;
        }

        public void addItem(TelItem item){
            items.add(item);
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            EditText edName, edPhone;
            ImageView imageView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                edName = (EditText) itemView.findViewById(R.id.edName);
                edPhone = (EditText) itemView.findViewById(R.id.edPhone);
                imageView = (ImageView) itemView.findViewById(R.id.imageView);

            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = (View) inflater.inflate(R.layout.tellist, parent,false);
            recyclerAdpter.ViewHolder vh = new recyclerAdpter.ViewHolder(view);

            return vh; //test
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            TelItem item = items.get(position);
            holder.imageView.setImageResource(item.getResId());
            holder.edName.setText(item.getName());
            holder.edPhone.setText(item.getPhone());
        }

        @Override
        public int getItemCount() {
            return items.size();
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
