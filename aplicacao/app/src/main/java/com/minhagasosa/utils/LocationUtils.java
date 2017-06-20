package com.minhagasosa.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import java.util.List;

/**
 * Created by GAEL on 19/06/2017.
 */

public class LocationUtils {


    //Location myLocation = getLastKnownLocation();


    public static boolean isNear(Context c, double targetLat, double targetLng) {
        try {
            float[] results = new float[1];
            Location location = getLastKnownLocation(c);
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            Location.distanceBetween(targetLat, targetLng, latitude, longitude, results);
            float distanceInMeters = results[0];
            return distanceInMeters < 200;
        }catch (Exception e){
            return false;
        }

    }

    private static Location getLastKnownLocation(Context c) {
        LocationManager mLocationManager;
        mLocationManager = (LocationManager) c.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }
}
