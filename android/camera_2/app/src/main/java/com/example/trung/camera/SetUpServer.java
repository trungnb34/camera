package com.example.trung.camera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetUpServer extends AppCompatActivity {

    EditText address;
    EditText port;
    Button btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_server);

        address = (EditText)findViewById(R.id.addressServer);
        port = (EditText)findViewById(R.id.portServer);
        btnAccept = (Button)findViewById(R.id.btnConnect);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThreadUdpClient.port = Integer.parseInt(port.getText().toString());
                ThreadUdpClient.ip = address.getText().toString();

                Intent intent = new Intent(SetUpServer.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
