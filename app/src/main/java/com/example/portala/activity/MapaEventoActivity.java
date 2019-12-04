package com.example.portala.activity;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.example.portala.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapaEventoActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng enderecoEvento;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_evento);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void passarEnderecoEvento(String endereco){

        Geocoder geocoder = new Geocoder(MapaEventoActivity.this, Locale.getDefault());

        try{

            if(geocoder.isPresent()){

                if(!endereco.isEmpty()){

                    List<Address> addresses = geocoder.getFromLocationName(endereco,1);
                    double latitude = addresses.get(0).getLatitude();
                    double longitude = addresses.get(0).getLongitude();

                    enderecoEvento = new LatLng(latitude,longitude);
                }

            }else {


            }

        }catch(IOException e){


            Log.e("execaoMapa",e.getMessage());

        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();

        String nomeProvedor = locationManager.getBestProvider(criteria,true);

        if(ContextCompat.checkSelfPermission(MapaEventoActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        || ContextCompat.checkSelfPermission(MapaEventoActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            mMap.setMyLocationEnabled(true);

            Location atual = locationManager.getLastKnownLocation(nomeProvedor);

            if(atual != null){

                LatLng posicaoAtual = new LatLng(atual.getLatitude(),atual.getLongitude());
                mMap.addMarker(new MarkerOptions().position(posicaoAtual).title("Minha localização atual"));
                mMap.addPolyline(new PolylineOptions().add(posicaoAtual).add(enderecoEvento).width(3f).color(Color.BLUE));

            }

        }

        mMap.addMarker(new MarkerOptions().position(enderecoEvento).title("Endereço do evento"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(enderecoEvento));
    }
}
