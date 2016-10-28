package com.example.b1014100_2.projectmainver3.map;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Movie;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.b1014100_2.projectmainver3.DesiginPattern.Iterator;
import com.example.b1014100_2.projectmainver3.HomeActivity;
import com.example.b1014100_2.projectmainver3.R;
import com.example.b1014100_2.projectmainver3.movie.MovieActivity;
import com.example.b1014100_2.projectmainver3.normalmovie.NormalMovieActivity;
import com.example.b1014100_2.projectmainver3.zukan.ZukanActivity;
import com.example.b1014100_2.projectmainver3.zukan.ZukanListActivity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private AggregateMapLocation aggregateMapLocation = new AggregateMapLocation();
    private ArrayList<LatLng> lats = new ArrayList<>();
    private ArrayList<Marker> Markers = new ArrayList<>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
       //viewの上書き
        View view = this.getLayoutInflater().inflate(R.layout.activity_maps_on, null);
        addContentView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        //clicklistner
        Button toQuizbutton = (Button) findViewById(R.id.map_to_quiz_button);
        toQuizbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(getApplication(), ZukanListActivity.class); //ダイビングアクティビティに飛ぶ処理
                startActivity(intent);
            }
        });
        /*-----------------------------------------------------------------*/
        //test
        Button InfoButton = (Button) findViewById(R.id.map_getinfo);
        InfoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Log.d("TEST Map", mMap.getCameraPosition().toString());
            }
        });
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(4);
        ReadLocaitonCsv();
        setMarker();
        //remove polyline
        Polyline polyline = this.mMap.addPolyline(new PolylineOptions());
        polyline.remove();

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent;
                int clicked_id = aggregateMapLocation.getIdbyName(marker.getTitle());
                int clicked_check360 = aggregateMapLocation.getMapLocationAt(clicked_id).getCheck360();
                Log.d("Map", "onMarkerClick:id =" + clicked_id + ", Name :" + marker.getTitle()+
                        ", Check360 : "+clicked_check360);
                if(clicked_check360 == 1) {//start 360movie activity
                   intent = new Intent(getApplication(), MovieActivity.class);
                }else{//start normal movie activity
                    intent = new Intent(getApplication(), NormalMovieActivity.class);
                }
                intent.putExtra("id", clicked_id);
                startActivity(intent);
            }
        });
    }

    public void ReadLocaitonCsv() {
        // AssetManagerの呼び出し
        AssetManager assetManager = getResources().getAssets();
        try {
            // CSVファイルの読み込み
            InputStream is = assetManager.open("location.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferReader.readLine()) != null) {
                // 各行が","で区切られていて4つの項目があるとす
                StringTokenizer st = new StringTokenizer(line, ",");
                int id = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                double xcor = Double.parseDouble(st.nextToken());
                double ycor = Double.parseDouble(st.nextToken());
                int check360 = Integer.parseInt(st.nextToken());
                aggregateMapLocation.appendMapLocation(new MapLocation(id, name, xcor, ycor,check360));
                // Log.d("ReadCsv", "read location"+id+","+name+","+xcor+","+ycor);
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMarker() {
        Iterator it = aggregateMapLocation.Iterator();
        while (it.hasNext()) {
            final MapLocation mapLocation = (MapLocation) it.next();
            //get lats
            lats.add(new LatLng(mapLocation.getXcor(), mapLocation.getYcor()));
            //add_maerker
            if(mapLocation.getCheck360() == 1)//set red marker
                Markers.add(mMap.addMarker(new MarkerOptions().position(lats.get(mapLocation.getId())).title(mapLocation.getName())));
            else//set blue marker
                Markers.add(mMap.addMarker(new MarkerOptions().position(lats.get(mapLocation.getId())).title(mapLocation.getName()).
                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))));//add color change
            mMap.setInfoWindowAdapter(new InfoWindowAdapter() {
                @Override
                public View getInfoContents(Marker marker) {
                 // TODO Auto-generated method stub
                   return null;
                    }
                @Override
                public View getInfoWindow(Marker marker) {
                    // TODO Auto-generated method stub
                    View view;
                    int clicked_id = aggregateMapLocation.getIdbyName(marker.getTitle());
                    if(aggregateMapLocation.getMapLocationAt(clicked_id).getCheck360() == 1)
                         view = getLayoutInflater().inflate(R.layout.info_window3d, null);
                    else
                         view = getLayoutInflater().inflate(R.layout.info_window2d, null);

                    // タイトル設定
                    TextView title = (TextView)view.findViewById(R.id.info_title);
                    title.setText(marker.getTitle());
                    return view;
                    }
                });
            //set zoom
            mMap.moveCamera(CameraUpdateFactory.zoomTo(12));
            //change view postion
            double x = aggregateMapLocation.getMapLocationAt(6).getXcor();
            double y = aggregateMapLocation.getMapLocationAt(6).getYcor();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(x,y))
            );
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Maps Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
