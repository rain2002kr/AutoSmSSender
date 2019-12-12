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
    EditText editText,editText2;
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
        editText = (EditText) view.findViewById( R.id.editText );
        editText2 = (EditText) view.findViewById( R.id.editText2 );
        imageView = (ImageView) view.findViewById( R.id.imageView );

    }

    public void setName(String name) {
        editText.setText( name );
    }
    public void setPhone(String phone) {
        editText.setText( phone );
    }
    public void setImageView(int resId) {
        imageView.setImageResource( resId );
    }
}
