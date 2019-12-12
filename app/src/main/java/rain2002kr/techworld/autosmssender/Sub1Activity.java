package rain2002kr.techworld.autosmssender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class Sub1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sub1 );
        //container = (FrameLayout) findViewById( R.id.container );

        //LayoutInflater inflater = (LayoutInflater) getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        //inflater.inflate( R.layout.tellist, container, true );

    }
}
