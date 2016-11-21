package ujs.edu.lou.table;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Mark_in_detail extends Activity{
	/** Called when the activity is first created. */
    private TextView MarkView;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_in_detail);
        MarkView=(TextView)findViewById(R.id.mymark);
        Intent get = getIntent();
        String re=get.getBundleExtra("session").getSerializable("sended").toString();
        MarkView.setText(re);
        MarkView.setTextSize(20);
        MarkView.setTextColor( Color.rgb(0, 0, 0));
       
    }
}
