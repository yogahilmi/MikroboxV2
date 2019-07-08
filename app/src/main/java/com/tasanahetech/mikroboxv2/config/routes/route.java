package com.tasanahetech.mikroboxv2.config.routes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tasanahetech.mikroboxv2.MainActivity;
import com.tasanahetech.mikroboxv2.R;
import com.tasanahetech.mikroboxv2.api.ApiConnection;
import com.tasanahetech.mikroboxv2.api.MikrotikApiException;
import com.tasanahetech.mikroboxv2.config.Routes;

public class route extends AppCompatActivity {

    EditText dst, gateway, type, distance,chkGate;
    ApiConnection con = MainActivity.getCon();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_route);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dst = findViewById(R.id.editTextDstRoute);
        gateway = findViewById(R.id.editTextGatewayRt);
        type = findViewById(R.id.editTextTypeRt);
        distance = findViewById(R.id.editTextDistanceRt);
        chkGate = findViewById(R.id.editTextChkGate);

        final Button btnRoute = findViewById(R.id.btn_setRoute);
        btnRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dstAddress = dst.getText().toString();
                String gate = gateway.getText().toString();
                String typeRt = type.getText().toString();
                String distanceRt = distance.getText().toString();
                String check = chkGate.getText().toString();

                try{
                    con.execute("/ip/route/add dst-address= "+dstAddress+
                            " gateway= "+gate+" check-gateway= "+check+
                            " type= "+typeRt+" distance= "+distanceRt);
                    Intent intent = new Intent(route.this, Routes.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(), "Route successfully added", Toast.LENGTH_LONG).show();

                } catch (MikrotikApiException e) {
                    e.printStackTrace();
                }
            }
        });

        final Button btnCancelRoute = findViewById(R.id.btn_cancelRt);
        btnCancelRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                route.this
                     .finish();
            }
        });

    }
}
