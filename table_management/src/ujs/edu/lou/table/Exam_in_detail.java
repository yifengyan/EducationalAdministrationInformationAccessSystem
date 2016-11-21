package ujs.edu.lou.table;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Exam_in_detail  extends Activity{
	private TextView examView;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_in_detail);
        examView=(TextView)findViewById(R.id.my_exam_info);
        Intent get = getIntent();
        String re=get.getBundleExtra("session").getSerializable("sended").toString();
        examView.setText(re);
        examView.setTextSize(20);
        examView.setTextColor(Color.rgb(0, 0, 0));
    }
}
