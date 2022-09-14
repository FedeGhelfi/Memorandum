package it.unipr.mobdev.memorandum;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "GeofenceBroadcastReceiv";
    /*
     a BroadcastReceiver is a good way to handle a geofence transition.
     A BroadcastReceiver gets updates when an event occurs,
      such as a transition into or out of a geofence, and can start long-running background work.
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "Sono nel broadcast, nella onReceive");
        // This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "Geofence triggered..", Toast.LENGTH_SHORT).show();
        // dbg: Toast.makeText(context, "ONRECEIVE..", Toast.LENGTH_SHORT).show();

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        // dbg: Toast.makeText(context, "EVENT..", Toast.LENGTH_SHORT).show();


        if (geofencingEvent.hasError()) {
            Log.d(TAG, "onReceive, Error receiving geofence event...");
            Toast.makeText(context, "HAS ERROR..", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get the transition type.
        int transitionType = geofencingEvent.getGeofenceTransition();
        if (transitionType == Geofence.GEOFENCE_TRANSITION_ENTER) {
            Log.d(TAG, "ENTRATO NEL FENCE");
        }

        // Get the geofences that were triggered. A single event can trigger
        // multiple geofences.
        List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();

        Toast.makeText(context,"Sei entrato nel raggio di un promemoria attivo!", Toast.LENGTH_LONG)
                .show();
    }
}