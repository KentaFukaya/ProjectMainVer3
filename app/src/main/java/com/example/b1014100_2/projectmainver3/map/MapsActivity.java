package com.example.b1014100_2.projectmainver3.map;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;


import com.example.b1014100_2.projectmainver3.DesiginPattern.Iterator;
import com.example.b1014100_2.projectmainver3.R;
import com.example.b1014100_2.projectmainver3.movie.MovieActivity;
import com.example.b1014100_2.projectmainver3.normalmovie.NormalMovieActivity;
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
import java.util.Map;
import java.util.StringTokenizer;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private AggregateMapLocation aggregateMapLocation = new AggregateMapLocation();
    private AggregateMapArea aggregateMapArea = new AggregateMapArea();
    private ArrayList<Marker> Markers = new ArrayList<>();

    private ListView mDrawerList;
    private MapListViewAdapter mAdapter;
    int c_Area = -1, c_Location = -1;
    Marker c_marker = null;

    final double firstXcor = 32.713744363054765, firstYcor = 135.45937590301037;
    final float firstZoom = 4;
    double o_xcor = 32.713744363054765, o_ycor = 135.45937590301037;
    float o_zoom = 4;

    ImageButton toZukanButton, InfoButton;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        /*---------------------------  read csv  ---------------------------*/
        ReadLocaitonCsv();
        ReadAreaCsv();
        /*--------------------------- slide menu ---------------------------*/
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.map_drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.map_navList);
        setMenu();
        /*---------------------------clicklistner---------------------------*/

        toZukanButton = (ImageButton) findViewById(R.id.map_tozukanbutton);
        toZukanButton.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //押したときの動作
                    toZukanButton.setImageResource(R.drawable.map_button_zukan_ontouch);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //離したときの動作
                    toZukanButton.setImageResource(R.drawable.map_button_zukan);
                }
                return false; //trueにすると他のリスナーが呼ばれない
            }
        });
        toZukanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ZukanListActivity.class); //ダイビングアクティビティに飛ぶ処理
                startActivity(intent);
            }
        });

        InfoButton = (ImageButton) findViewById(R.id.map_areabutton);
        InfoButton.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //押したときの動作
                    InfoButton.setImageResource(R.drawable.map_button_tiki_ontouch);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //離したときの動作
                    InfoButton.setImageResource(R.drawable.map_button_tiki);
                }
                return false; //trueにすると他のリスナーが呼ばれない
            }
        });
        InfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST Map", mMap.getCameraPosition().toString());
                drawer.openDrawer(Gravity.RIGHT);
            }
        });

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                drawer.closeDrawer(Gravity.RIGHT);
                ListView listView = (ListView) parent;
                // クリックされたアイテムを取得します
                MapData item = (MapData) listView.getItemAtPosition(position);
                //Toast.makeText(MapsActivity.this, item.getName(), Toast.LENGTH_LONG).show();
                /*-----------------------check clicked----------------------------*/
                if (item.getArea_id() == c_Area) {
                    if (item.getLocation_id() == -1) {//area double clicked
                        c_Area = -1;
                        c_Location = -1;
                    } else if (item.getLocation_id() == c_Location) {//location double clicked
                        c_Area = item.getArea_id();
                        c_Location = -1;
                    } else {//location single clicked
                        c_Area = item.getArea_id();
                        c_Location = item.getLocation_id();
                    }
                } else {
                    c_Area = item.getArea_id();
                    c_Location = item.getLocation_id();
                }
                setMenu();
                setCamera();
                /*----------------check infowindow--------------------------*/
                if (item.getLocation_id() != -1) {//choice infowindo
                    c_marker = Markers.get(aggregateMapLocation.getIdbyName(item.getName()));
                    c_marker.showInfoWindow();
                }
                if (c_marker != null) {//hide infowindow
                    if (c_Location == -1 && c_marker.isInfoWindowShown())
                        c_marker.hideInfoWindow();
                }
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

        setMarker();
        //remove polyline
        Polyline polyline = this.mMap.addPolyline(new PolylineOptions());
        polyline.remove();
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int id = aggregateMapLocation.getIdbyName(marker.getTitle());
                c_Area = aggregateMapLocation.getMapLocationAt(id).getArea_id();
                c_Location = getC_loaction(aggregateMapLocation.getMapLocationAt(id).getName());
                setMenu();
                return false;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent;
                int clicked_id = aggregateMapLocation.getIdbyName(marker.getTitle());
                int clicked_check360 = aggregateMapLocation.getMapLocationAt(clicked_id).getCheck360();
                Log.d("Map", "onMarkerClick:id =" + clicked_id + ", Name :" + marker.getTitle() +
                        ", Check360 : " + clicked_check360);
                if (clicked_check360 == 1) {//start 360movie activity
                    intent = new Intent(getApplication(), MovieActivity.class);
                } else {//start normal movie activity
                    intent = new Intent(getApplication(), NormalMovieActivity.class);
                }
                intent.putExtra("id", clicked_id);
                startActivity(intent);
            }
        });
    }

    public void ReadAreaCsv() {
        // AssetManagerの呼び出し
        AssetManager assetManager = getResources().getAssets();
        try {
            // CSVファイルの読み込み
            InputStream is = assetManager.open("area.csv");
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
                float zoom = Float.parseFloat(st.nextToken());
                aggregateMapArea.appendMapArea(new MapArea(id, name, xcor, ycor, zoom));
                //Log.d("ReadCsv", "read location" + id + "," + name + "," + xcor + "," + ycor);
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                int area_id = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                double xcor = Double.parseDouble(st.nextToken());
                double ycor = Double.parseDouble(st.nextToken());
                int check360 = Integer.parseInt(st.nextToken());
                aggregateMapLocation.appendMapLocation(new MapLocation(id, area_id, name, xcor, ycor, check360));
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
            LatLng lat = new LatLng(mapLocation.getXcor(), mapLocation.getYcor());
            //add_maerker
            if (mapLocation.getCheck360() == 1)//set red marker
                Markers.add(mMap.addMarker(new MarkerOptions().position(lat).title(mapLocation.getName())));
            else//set blue marker
                Markers.add(mMap.addMarker(new MarkerOptions().position(lat).title(mapLocation.getName()).
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
                    if (aggregateMapLocation.getMapLocationAt(clicked_id).getCheck360() == 1)
                        view = getLayoutInflater().inflate(R.layout.info_window3d, null);
                    else
                        view = getLayoutInflater().inflate(R.layout.info_window2d, null);

                    // タイトル設定
//                    TextView title = (TextView) view.findViewById(R.id.info_title);
//                    title.setText(marker.getTitle());
                    return view;
                }
            });
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(firstXcor, firstYcor), firstZoom));
    }

    public void setCamera() {
        double n_xcor, n_ycor;
        float n_zoom;
        if (c_Area == -1 && c_Location == -1) { //init
            n_xcor = firstXcor;
            n_ycor = firstYcor;
            n_zoom = firstZoom;
        } else if (c_Area != -1 && c_Location == -1) {//area clicked
            MapArea mapArea = aggregateMapArea.getMapAreaAt(c_Area);
            n_xcor = mapArea.getXcor();
            n_ycor = mapArea.getYcor();
            n_zoom = mapArea.getZoom();
        } else {
            MapLocation mapLocation = aggregateMapLocation.getMapLocation(c_Area, c_Location);
            n_xcor = mapLocation.getXcor();
            n_ycor = mapLocation.getYcor();
            n_zoom = o_zoom;
        }
        //Log.d("TEST", "setCamera: xcor =" + n_xcor + ", ycor =" + n_ycor + ", n_zoom=" + n_zoom);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(n_xcor, n_ycor), n_zoom), 1500, null);
        o_xcor = n_xcor;
        o_ycor = n_ycor;
        o_zoom = n_zoom;
    }

    public void setMenu() {
        int i = 0;
        ArrayList<MapData> MapDatas = new ArrayList<>();
        Iterator it_area = (Iterator) aggregateMapArea.Iterator();
        while (it_area.hasNext()) {
            //Log.d("TEST", "setMenu: i=" + i + ", c_area = " + c_Area + ", c_location=" + c_Location);
            MapDatas.add(new MapData(i++, (MapArea) it_area.next()));
            if (c_Area != -1 && c_Area == i - 1) {
                int n = 0;
                Iterator it_Location = (Iterator) aggregateMapLocation.Iterator();
                while (it_Location.hasNext()) {
                    MapLocation mapLocation = (MapLocation) it_Location.next();
                    if (mapLocation.getArea_id() == c_Area)
                        MapDatas.add(new MapData(i + n, n++, mapLocation));
                }
                i += n - 1;
            }
        }
        mAdapter = new MapListViewAdapter(this, MapDatas, c_Area, c_Location);
        mDrawerList.setAdapter(mAdapter);
    }

    public int getC_loaction(String name) {
        int n = 0;
        Iterator it_Location = aggregateMapLocation.Iterator();
        while (it_Location.hasNext()) {
            MapLocation mapLocation = (MapLocation) it_Location.next();
            if (mapLocation.getArea_id() == c_Area) {
                if (mapLocation.getName().equals(name))
                    return n;
                n++;
            }
        }
        return -1;
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
