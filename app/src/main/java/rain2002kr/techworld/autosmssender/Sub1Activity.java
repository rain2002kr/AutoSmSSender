package rain2002kr.techworld.autosmssender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import rain2002kr.techworld.autosmssender.Tel_item.TelItem;

public class Sub1Activity extends AppCompatActivity {
    Button btEnter;
    EditText edName, edPhone;
    RecyclerView recyclerView;
    recyclerAdpter adpter;
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
        setContentView( R.layout.activity_sub1 );
        UserFindViewById();
        UserFunction();
    }

    //TODO UserFindViewById
    public void UserFindViewById(){
        btEnter = (Button) findViewById(R.id.btEnter);
        edName = (EditText) findViewById(R.id.edName);
        edPhone = (EditText) findViewById(R.id.edPhone);
        recyclerView = (RecyclerView) findViewById(R.id.recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adpter = new recyclerAdpter();
        recyclerView.setAdapter(adpter);

    }
    //TODO UserFunction
    public void UserFunction(){

        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString();
                String phone = edPhone.getText().toString();
                adpter.addItem(new TelItem(R.drawable.star, name, phone));
                adpter.notifyDataSetChanged();
            }
        });
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
}
