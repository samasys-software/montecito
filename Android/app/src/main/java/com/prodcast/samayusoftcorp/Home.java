package com.prodcast.samayusoftcorp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.businessobjects.SessionInfo;
import com.prodcast.samayu.samayusoftcorp.R;

import org.w3c.dom.Text;

/**
 * Created by Preethiv on 1/13/2018.
 */

public class Home extends AppCompatActivity {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        text = (TextView) findViewById(R.id.text);
        text.setText("Welcome "+SessionInfo.instance().getFirstName()+" "+SessionInfo.instance().getLastName());
    }


}
