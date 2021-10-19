package com.vishal.whiteboard;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.herewhite.sdk.RoomParams;
import com.herewhite.sdk.WhiteboardView;
import com.herewhite.sdk.WhiteSdk;
import com.herewhite.sdk.WhiteSdkConfiguration;
import com.herewhite.sdk.Room;
import com.herewhite.sdk.domain.Promise;

import com.herewhite.sdk.domain.Region;
import com.herewhite.sdk.domain.SDKError;
import com.herewhite.sdk.domain.MemberState;

public class MainActivity extends AppCompatActivity {

    //  Pass in the App Identifier
    final String appId = "DZMLEC_WEeyucw3ypGOF9Q/v!$h@laXoMmHUUueGgYw";
    // Pass in the Room UUID
    final String uuid = "1988966bf0300211ec8e16ef0d8e32fdd708";
    // Pass in the Room Token
    final String roomToken = "NETLESSROOM_YWs9VFRHZF9BNjV4WlVaNk9LTSZub25jZT1mdW5jdGlvbigpJTIwJTdCJTBBJTIwJTIwJTIwJTIwJTIwJTIwJTIwJTIwdmFyJTIwbm93JTIwJTNEJTIwTWF0aC5wb3coMTAlMkMlMjAyKSUyMColMjAlMkJuZXclMjBEYXRlKCklMEElMEElMjAlMjAlMjAlMjAlMjAlMjAlMjAlMjBpZiUyMChub3clMjAlM0QlM0QlMjBsYXN0KSUyMCU3QiUwQSUyMCUyMCUyMCUyMCUyMCUyMCUyMCUyMCUyMCUyMCUyMCUyMHJlcGVhdCUyQiUyQiUwQSUyMCUyMCUyMCUyMCUyMCUyMCUyMCUyMCU3RCUyMGVsc2UlMjAlN0IlMEElMjAlMjAlMjAlMjAlMjAlMjAlMjAlMjAlMjAlMjAlMjAlMjByZXBlYXQlMjAlM0QlMjAwJTBBJTIwJTIwJTIwJTIwJTIwJTIwJTIwJTIwJTIwJTIwJTIwJTIwbGFzdCUyMCUzRCUyMG5vdyUwQSUyMCUyMCUyMCUyMCUyMCUyMCUyMCUyMCU3RCUwQSUwQSUyMCUyMCUyMCUyMCUyMCUyMCUyMCUyMHZhciUyMHMlMjAlM0QlMjAobm93JTIwJTJCJTIwcmVwZWF0KS50b1N0cmluZygpJTBBJTIwJTIwJTIwJTIwJTIwJTIwJTIwJTIwcmV0dXJuJTIwJTJCcy5zdWJzdHIocy5sZW5ndGglMjAtJTIwbGVuZ3RoKSUwQSUyMCUyMCUyMCUyMCU3RCZyb2xlPTAmc2lnPTNiNGFkYzkwODAwNGRlNzEzODgxMzdjMGUwZTY2NjE5ZjY4YjkzODNiNThmZmMzY2FlYzE5NWVmNDJlNDkwODUmdXVpZD04ODk2NmJmMDMwMDIxMWVjOGUxNmVmMGQ4ZTMyZmRkv!$h@lNw";

    // Create a WhiteboardView object to implement the whiteboard view
    WhiteboardView whiteboardView;
    // Create a WhiteSdkConfiguration object to configure the App Identifier and log settings
    WhiteSdkConfiguration sdkConfiguration = new WhiteSdkConfiguration(appId, true);
    // Set the data center as Silicon Valley, US


    // Create a RoomParams object to set room parameters used in joining a room
    RoomParams roomParams = new RoomParams(uuid, roomToken);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Assign the whiteboard view in layout to the WhiteboardView object
        sdkConfiguration.setRegion(Region.gb_lon);
        whiteboardView = findViewById(R.id.white);
        // Create a WhiteSdk object to initialize the whiteboard SDK
        WhiteSdk whiteSdk = new WhiteSdk(whiteboardView, MainActivity.this, sdkConfiguration);
        // Join a room
        whiteSdk.joinRoom(roomParams, new Promise<Room>() {
            @Override
            public void then(Room wRoom) {
                MemberState memberState = new MemberState();
                // Set the tool to pencil
                memberState.setCurrentApplianceName("pencil");
                // Set the color tor red
                memberState.setStrokeColor(new int[]{255, 0, 0});
                // Assign the set-up tool to the current user
                wRoom.setMemberState(memberState);
            }

            @Override
            public void catchEx(SDKError t) {
                Object o = t.getMessage();
                Log.i("showToast", o.toString());
                Toast.makeText(MainActivity.this, o.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        whiteboardView.removeAllViews();
        whiteboardView.destroy();
    }
}