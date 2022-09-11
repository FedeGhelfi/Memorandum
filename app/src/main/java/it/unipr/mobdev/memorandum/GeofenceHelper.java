package it.unipr.mobdev.memorandum;

import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.maps.model.LatLng;

public class GeofenceHelper extends ContextWrapper {

    private static final String TAG = "GeofenceHelper";
    PendingIntent pendingIntent;

    public GeofenceHelper(Context base) {
        super(base);
    }


    // request for monitoring the geofence
    public GeofencingRequest getGeofencingRequest(Geofence geofence) {
        Log.d(TAG, "Sono in geofencingRequest");

        return new GeofencingRequest.Builder()
                .addGeofence(geofence)
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .build();
    }


    // the builder class for creating Geofence objects
    public Geofence getGeofence(String ID, LatLng latLng, float radius, int transitionTypes) {
        return new Geofence.Builder()
                .setCircularRegion(latLng.latitude, latLng.longitude, radius)
                .setRequestId(ID)
                .setTransitionTypes(transitionTypes)
                .setLoiteringDelay(5000) // milliseconds for the transition
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .build();
    }

    public PendingIntent getPendingIntent() {
        Log.d(TAG, "sono in getPending Intent");

        if (pendingIntent != null) {
            return pendingIntent;
        }

        // define a pending intent that starts a broadcast receiver
        Intent intent = new Intent(this, GeofenceBroadcastReceiver.class);
        Log.d(TAG, "Ho startato BroadcastReceiver");
        pendingIntent = PendingIntent.getBroadcast(this, 2607, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    // in case of error
    public String getErrorString(Exception e) {

        if (e instanceof ApiException) {

            ApiException apiException = (ApiException) e;

            switch (apiException.getStatusCode()) {
                case GeofenceStatusCodes
                        .GEOFENCE_NOT_AVAILABLE:
                    return "GEOFENCE_NOT_AVAILABLE";

                case GeofenceStatusCodes
                        .GEOFENCE_TOO_MANY_GEOFENCES:
                    return "GEOFENCE_TOO_MANY_GEOFENCES";

                case GeofenceStatusCodes
                        .GEOFENCE_TOO_MANY_PENDING_INTENTS:
                    return "GEOFENCE_TOO_MANY_PENDING_INTENTS";
            }
        }

        return e.getLocalizedMessage();
    }
}
