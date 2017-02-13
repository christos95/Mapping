package com.example.a2papac92.mapping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by 2papac92 on 09/02/2017.
 */
public class SetLocationActivity extends Activity implements View.OnClickListener {


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);

        Button sbutton = (Button)findViewById(R.id.sbutton);
        sbutton.setOnClickListener(this);
    }


    public void onClick(View vie) {

        Intent intent = new Intent();
        Bundle bundle=new Bundle();

        EditText latitudeText = (EditText) findViewById(R.id.latitudeText);
        double latitude = Double.parseDouble(latitudeText.getText().toString());
        bundle.putDouble("com.example.latitude",latitude);
        EditText longitudeText = (EditText) findViewById(R.id.longitudeText);
        double longitude = Double.parseDouble(longitudeText.getText().toString());
        bundle.putDouble("com.example.longitude",longitude);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
}