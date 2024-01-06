package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView flashlightIcon ;
    Button btnTorch ;
    Boolean isFlashOn = false;
    CameraManager cameraManager ;
    String cameraId ;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashlightIcon = findViewById(R.id.flashlight_icon) ;
        btnTorch = findViewById(R.id.btn_torch) ;

        try {
            cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            cameraId = cameraManager.getCameraIdList()[0];
        }catch(Exception e) {}

        btnTorch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( isFlashOn )
                {
                    isFlashOn = false ;
                    btnTorch.setText("TURN ON TORCH");
                }else
                {
                    isFlashOn = true ;
                    btnTorch.setText("TURN OFF TORCH");
                }
                switchFlash(isFlashOn) ;

            }
        });
    }
    public void switchFlash(Boolean status)
    {
        try {
            cameraManager.setTorchMode(cameraId , status);
        }catch(Exception e) {}
    }
}