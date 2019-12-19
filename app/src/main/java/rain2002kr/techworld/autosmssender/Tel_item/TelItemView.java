package rain2002kr.techworld.autosmssender.Tel_item;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import rain2002kr.techworld.autosmssender.R;

public class TelItemView extends LinearLayout {
    EditText edName,edPhone;
    ImageView imageView;

    public TelItemView(Context context) {
        super( context );
        init( context );
    }

    public TelItemView(Context context, @Nullable AttributeSet attrs) {
        super( context, attrs );
        init( context );
    }
    private void init (Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view =(View) inflater.inflate( R.layout.tellist, this, true );
        edName = (EditText) view.findViewById( R.id.edName );
        edPhone = (EditText) view.findViewById( R.id.edPhone );
        imageView = (ImageView) view.findViewById( R.id.imageView );

    }

    public void setName(String name) {
        edName.setText( name );
    }
    public void setPhone(String phone) {
        edPhone.setText( phone );
    }
    public void setImageView(int resId) {
        imageView.setImageResource( resId );
    }
}
