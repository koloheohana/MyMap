package com.koloheohana.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by User on 2017/06/02.
 */
public class MapStreetView extends FragmentActivity implements OnStreetViewPanoramaReadyCallback{
    public static LatLng latLng;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.street_view);
        StreetViewPanoramaFragment streetViewPanoramaFragment = (StreetViewPanoramaFragment)getFragmentManager().findFragmentById(R.id.streetviewpanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
        Intent intent = getIntent();
        Double latitude = (Double) intent.getSerializableExtra("latitude");
        Double longitude = (Double) intent.getSerializableExtra("longitude");
        latLng = new LatLng(latitude,longitude);
    }

    //ストリートビューの取得
    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        streetViewPanorama.setPosition(latLng);
    }
}
