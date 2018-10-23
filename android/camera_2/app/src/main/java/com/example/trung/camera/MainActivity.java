package com.example.trung.camera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    public static Mat mRgba;
    public static int i = 0;
    JavaCameraView javaCameraView;
    Button btn_start;
    ThreadUdpClient threadUdpClient;
    boolean is_connect_server = false;

    BaseLoaderCallback mloaderCallBack = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            super.onManagerConnected(status);

            switch (status) {
                case BaseLoaderCallback.SUCCESS: {
                    javaCameraView.enableView();
                    break;
                }
                default: {
                    super.onManagerConnected(status);
                    break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        set_up_camera();

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(! is_connect_server) {
                    if(ThreadUdpClient.ip != "none" && ThreadUdpClient.port != -1) {
                        threadUdpClient.start();
                        is_connect_server = !is_connect_server;
                    }
                }
                Intent intent = new Intent(MainActivity.this, SetUpServer.class);
                startActivity(intent);
            }
        });

    }

    private void set_up_camera() {
        threadUdpClient = new ThreadUdpClient();
        javaCameraView = (JavaCameraView)findViewById(R.id.viewCamera);
        btn_start = (Button)findViewById(R.id.btnConnect);
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.gray();
        return mRgba;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(OpenCVLoader.initDebug()) {
            Toast.makeText(MainActivity.this, "OpenCv loaded successfully !", Toast.LENGTH_SHORT).show();
            mloaderCallBack.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        } else {
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, mloaderCallBack);
            Toast.makeText(MainActivity.this, "OpenCv loaded fail !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(javaCameraView != null) {
            javaCameraView.disableView();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(javaCameraView != null) {
            javaCameraView.disableView();
        }
    }
}
