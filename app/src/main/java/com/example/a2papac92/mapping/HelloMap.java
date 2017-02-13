package com.example.a2papac92.mapping;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.content.Intent;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

public class HelloMap extends Activity {
    MapView mv;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));

        mv = (MapView) findViewById(R.id.map1);
        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(14);
        mv.getController().setCenter(new GeoPoint(40.1, 22.5));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hello_map, menu);
        return true;
    }




    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.choosemap) {
            Intent intent = new Intent(this, MapChooseActivity.class);
            startActivityForResult(intent, 0);

            return true;
        } else if (item.getItemId() == R.id.setlocation) {
            Intent intent = new Intent(this, SetLocationActivity.class);
            startActivityForResult(intent,1 );

            return true;
        }
        return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                Bundle extras = intent.getExtras();
                boolean cyclemap = extras.getBoolean("com.example.cyclemap");
                if (cyclemap == true) {
                    mv.setTileSource(TileSourceFactory.CYCLEMAP);
                } else {
                    mv.getTileProvider().setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }
        if (requestCode == 1) {

            if (resultCode == RESULT_OK) {
                Bundle extras = intent.getExtras();
                double latitude = extras.getDouble("com.example.latitude");
                double longitude = extras.getDouble("com.example.longitude");

                mv.getController().setCenter(new GeoPoint(latitude, longitude));

            }
        }

    }
}