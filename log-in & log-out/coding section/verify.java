package com.example.authentication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class verify extends Activity {
    private Button otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);
       otp=findViewById(R.id.ggg);
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intenet = new Intent(verify.this, signuactivity.class);
                startActivity(intenet);
                finish();
            }
        });
    }



}
