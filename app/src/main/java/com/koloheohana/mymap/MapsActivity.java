package com.koloheohana.mymap;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.data_base.OrmaShopData_Selector;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.dialog.CBookmarkDialog;
import com.koloheohana.mymap.dialog.MemoCustomDialog;
import com.koloheohana.mymap.dialog.ShopDialog;
import com.koloheohana.mymap.map.ShopDataIntent;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.map.ShopList;
import com.koloheohana.mymap.map.ShopSearch;
import com.koloheohana.mymap.me.MyUser;
import com.koloheohana.mymap.server.ServerOperator;
import com.koloheohana.mymap.sns.MainTork;
import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.util.BitmapReader;
import com.koloheohana.mymap.util.Clocks;
import com.koloheohana.mymap.util.MyConfig;
import com.koloheohana.mymap.util.Scene;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private GoogleMap map;
    private GoogleApiClient glocationClient;
    private Marker click_marker;
    public static MapsActivity MAP_ME = new MapsActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MAP_ME = this;
        Scene.set(MAP_ME);
        /*CsvReader.parse(this);*/

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        ShopSearch.setFirst();
        ShopSearch.setAdapter();
        mapFragment.getMapAsync(this);
        if (isLocationEnabled()) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "-----onConnected----");
            }
            connectGooglePlayServices();
        }
        // 選択されたアイテムを取得します
        ArrayList<ShopDate> list = ShopSearch.getSearchRange(2);
        MapsActivity.MAP_ME.setMarker(list, true);
    }
    public static final String TAG = "LocationService";

    //protected LocationClient mLocationClient;
    protected GoogleApiClient mGoogleApiClient;

    protected LocationRequest mLocationRequest;

    // 緯度
    protected double mLatitude;
    // 経度
    protected double mLongitude;
    // 標高
    protected double mAltitude;


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        disConnectGooglePlayServices();
    }

    /**
     * 現在地情報が取得可能な場合はtrue, 取得できない場合はfalse
     *
     * @return
     */
    protected boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * GooglePlayServicesに接続する
     */
    protected void connectGooglePlayServices() {
        mLocationRequest = LocationRequest.create();
        // 10秒おきに位置情報を取得する
        mLocationRequest.setInterval(10000);
        // 精度優先
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
/*
        // 最短で5秒おきに位置情報を取得する
        mLocationRequest.setFastestInterval(5000);
*/

//        mLocationClient = new LocationClient(getActivity().getApplicationContext(), connectionCallbacks, onConnectionFailedListener);
//        mLocationClient.connect();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        if (mGoogleApiClient != null) {
            // GoogleApiClient start
            mGoogleApiClient.connect();
        }

    }

    /**
     * GooglePlayServicesを切断する
     */
    protected void disConnectGooglePlayServices() {
        if(mGoogleApiClient == null){
            System.out.println("mGoogleApi:null");
        }
        if (mGoogleApiClient.isConnected()) {
            // 位置情報の取得を停止
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            //mGoogleApiClient.removeLocationUpdates(locationListener);
            mGoogleApiClient.disconnect();
        }

    }

/*    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }*/
    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        double altitude = location.getAltitude();

        // latitude changed
        if (mLatitude != latitude) {
            mLatitude = latitude;
            now_lati = latitude;
        }

        // longitude changed
        if (mLongitude != longitude) {
            mLongitude = longitude;
            now_long = longitude;
        }

        // altitude changed
        if (mAltitude != altitude) {
            mAltitude = altitude;
        }

        if (BuildConfig.DEBUG) {
/*
            Log.d(TAG, "mLatitude:" + mLatitude + ", mLongitude: " + mLongitude + ", mAltitude: " + mAltitude);
*/
        }
        LatLng sydney = new LatLng(mLatitude, mLongitude);
        if (home_marker != null) {
            home_marker.remove();
        }
        home_marker = mMap.addMarker(new MarkerOptions().position(sydney).title("現在地"));
        if (first) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            zoomMap(mLatitude, mLongitude);
            first = false;
        }
    }
    public LatLng getLatLngNow(){
        return new LatLng(now_lati,now_long);
    }
    public double now_lati = 0;
    public double now_long = 0;

    public Marker home_marker = null;
    public boolean first = true;

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }
    private void zoomMap(ShopDate sd){
        zoomMap(sd.getLATLNG().latitude,sd.getLATLNG().longitude);
    }
    private void zoomMap(double lat, double lon) {
        double south = lat * (1 - 0.00005);
        double west = lon * (1 - 0.00005);
        double north = lat * (1 + 0.00005);
        double east = lon * (1 + 0.00005);

        // LatLngBounds (LatLng southwest, LatLng northeast)
        LatLngBounds bounds = LatLngBounds.builder()
                .include(new LatLng(south, west))
                .include(new LatLng(north, east))
                .build();

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        // static CameraUpdate.newLatLngBounds(LatLngBounds bounds, int width, int height, int padding)
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, 0));

    }

    /**
     * GPSが使えるかチェック
     */
    @Override
    protected void onStart() {
        super.onStart();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (gpsEnabled) {

        }
    }

    private void enableGpsSetting() {
        Intent set_intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(set_intent);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    public void setMarker(double x,double y){
        LatLng sydney = new LatLng(x, y);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Shop"));
/*
        System.out.println(x+"と"+y+"にマーカーをセットしました");
*/
    }

    ArrayList<Marker> list = new ArrayList<Marker>();

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
/*        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (click_marker != null) {
                    click_marker.remove();
                }
                click_marker = mMap.addMarker(new MarkerOptions().position(latLng).title("クリック"));
                
            }
        });*/
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(35, 136);

        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

/*        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener(){
            @Override
            public void onMyLocationChange(Location loc) {
                LatLng curr = new LatLng(loc.getLatitude(), loc.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLng(curr));
            }
        });*/
/*
        zoomMap();
*/

    }
    private void setMarker(ArrayList<ShopDate> list){
        int count = 0;
        int max_value = MyConfig.map_marker_max;
        for(ShopDate sd:list){
            setMarker(sd,false);
            count++;
            if(count >= max_value){
                Toast to = Toast.makeText(this,"表示件数が"+max_value+"を超えた為、それ以上の表示が出来ませんでした",Toast.LENGTH_LONG);
                to.setGravity(Gravity.CENTER|Gravity.BOTTOM,0,1);
                to.show();
                break;
            }
        }
    }
    public void setMarker(ShopDate sd,boolean zoom,boolean clear){
        if(clear){
            clearMarker();
        }
        setMarker(sd,zoom);
    }
    public void setMarker(final ShopDate sd,boolean zoom){
        LatLng sydney = new LatLng(sd.getX(), sd.getY());
        Marker mk = mMap.addMarker(new MarkerOptions().position(sydney).icon
                (BitmapDescriptorFactory.defaultMarker(sd.testMarker())).title(sd.getShopName()).snippet(sd.getCategoryNames()));
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                LatLng ll = marker.getPosition();
                OrmaShopData_Selector osd = OrmaOperator.getShopDataSelector().coordinate_xEq(ll.latitude).coordinate_yEq(ll.longitude);
                ShopDate S_D = new ShopDate(osd.get(0));
                if(S_D ==null){
                    return;
                }
                ShopDialog sd = new ShopDialog(S_D,MAP_ME);
                sd.show(getSupportFragmentManager(),S_D.getShopName());
            }
        });
        list.add(mk);
        if(zoom){
            zoomMap(sd);
        }
    }


    public void getSnapShot(final ShopDate SD, final User user, final Context context){
        // TODO Auto-generated method stub
        mMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
            @Override
            public void onSnapshotReady(Bitmap bitmap) {
                // TODO Auto-generated method stub
                // スナップショット画像の縮小
                System.out.println(bitmap.getHeight()+":"+bitmap.getWidth());

                Intent intent = new Intent(MapsActivity.MAP_ME,MainTork.class);
                Uri uri = SaveDateController.saveBitmapFile(MapsActivity.MAP_ME,bitmap,"map"+new Clocks(MAP_ME).getStringAllTime());
                ServerOperator.imageUploadAndSendPush(context,user.getId(),SD.ID,"id"+ MyUser.ME.getId()+"id"+user.getId()+"time"+ new Clocks(context).getStringAllTime()+".png", BitmapReader.getBitmap(context,uri,true));
                intent.putExtra("ShopData", new ShopDataIntent(SD.getShopName(),SD.getADDRRES(),(int)user.getUserId(),uri.getPath()));
                intent.setAction(Intent.ACTION_VIEW);

                MapsActivity.MAP_ME.startActivity(intent);
            }
        });
    }
    public void clearMarker(){
        for(int i = 0; i < list.size();i++){
            list.get(i).remove();
        }
    }
    public void setMarker(ArrayList<ShopDate> _list, boolean clear){
        if(clear){
            clearMarker();
        }
        setMarker(_list);
    }

    public void bookmarkButton(View view){
        CBookmarkDialog cbd = new CBookmarkDialog(this);
        cbd.show();

    }
    public void memoButton(View view){
        MemoCustomDialog mcd = new MemoCustomDialog(this);
        mcd.show();
    }
    public void categoryButton(View view){
        ShopSearch.setCategory();

    }
    public void rangeButton(View view){
        ShopSearch.setRange();
    }
    public void myTomo(View view) {
        zoomMap(now_lati, now_long);

    }
    public void mapSearchButton(View view){
        ShopSearch.searchShop(this,view);
    }
}
