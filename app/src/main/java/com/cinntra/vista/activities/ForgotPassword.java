package com.cinntra.vista.activities;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cinntra.vista.EasyPrefs.Prefs;
import com.cinntra.vista.R;
import com.cinntra.vista.activities.bpActivity.AddBPCustomer;
import com.cinntra.vista.globals.Globals;
import com.cinntra.vista.model.ForgotPasswordModel;
import com.cinntra.vista.model.ResponseBusinessPartnerDropDown;
import com.cinntra.vista.spinneradapter.BusinessPartnerSearchableSpinnerAdapter;
import com.cinntra.vista.superadmin.ApiClientSuperAdmin;
import com.cinntra.vista.webservices.NewApiClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    private EditText etEmail;
    private Button btnVerifyEmail;
    private TextView goBackToLogin;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password); // Make sure the layout file name is correct

        // Initialize views from the XML layout
        etEmail = findViewById(R.id.etEmail);
        btnVerifyEmail = findViewById(R.id.btnVerifyEmail);
        goBackToLogin = findViewById(R.id.go_back_to_login); // Initialized from XML

        // Set Fullscreen mode
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // Handle 'Go Back to Login' click
        goBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Handle 'Verify Email' button click
        btnVerifyEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString().trim();

                // Validate email field
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(ForgotPassword.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(ForgotPassword.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                } else {

                    callForgotPasswordApi(email);
                }
            }
        });
    }


    private void callForgotPasswordApi(String email) {
    // App id for vista_saas is 2
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("app_id",2);

        Call<ForgotPasswordModel> call = ApiClientSuperAdmin.getInstance().getApiService(this).ForgotPassword(jsonObject);


        call.enqueue(new Callback<ForgotPasswordModel>() {
            @Override
            public void onResponse(Call<ForgotPasswordModel> call, Response<ForgotPasswordModel> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus()==200) {



                        Toast.makeText(ForgotPassword.this, "Otp send to "+email, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ForgotPassword.this, SetNewPassword.class);
                        intent.putExtra("email",email);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(ForgotPassword.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    Toast.makeText(ForgotPassword.this, response.body().getMsg(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordModel> call, Throwable t) {
                Toast.makeText(ForgotPassword.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
