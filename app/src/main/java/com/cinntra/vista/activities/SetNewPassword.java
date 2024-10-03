package com.cinntra.vista.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cinntra.vista.R;
import com.cinntra.vista.databinding.ActivitySetNewPasswordBinding;
import com.cinntra.vista.model.ForgotPasswordModel;
import com.cinntra.vista.model.VerifyResetPassOtpModel;
import com.cinntra.vista.superadmin.ApiClientSuperAdmin;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetNewPassword extends AppCompatActivity {

    String email;
    TextView tvOtpSentToEmail;

    ActivitySetNewPasswordBinding binding;
    String newPassword;
    String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetNewPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tvOtpSentToEmail = binding.tvOtpSentToEmail;

        // Set Fullscreen mode
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        email = getIntent().getStringExtra("email");


        tvOtpSentToEmail.setText("Otp send to " + email);

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the text from EditText
                otp = binding.etOtp.getText().toString().trim();
                String newPassword = binding.etNewPassword.getText().toString().trim();

                if (binding.etOtp.getText().toString().isEmpty()) {
                    Toast.makeText(SetNewPassword.this, "Enter Otp", Toast.LENGTH_SHORT).show();
                } else if (binding.etNewPassword.getText().toString().isEmpty()) {
                    Toast.makeText(SetNewPassword.this, "Enter New Password", Toast.LENGTH_SHORT).show();
                } else {

//                    Toast.makeText(SetNewPassword.this, "OTP: " + otp + "\nNew Password: " + newPassword, Toast.LENGTH_LONG).show();
                    callSetNewPasswordApi(email, otp, newPassword);
                }
            }
        });
    }

    private void callSetNewPasswordApi(String email, String otp, String newPassword) {
        // App id for vista_saas is 2
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", newPassword);
        jsonObject.addProperty("otp", otp);


        Call<VerifyResetPassOtpModel> call = ApiClientSuperAdmin.getInstance().getApiService(this).VerifyResetPassword(jsonObject);

        call.enqueue(new Callback<VerifyResetPassOtpModel>() {
            @Override
            public void onResponse(Call<VerifyResetPassOtpModel> call, Response<VerifyResetPassOtpModel> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus() == 200) {

                        Intent intent = new Intent(SetNewPassword.this, Login.class);
                        intent.putExtra("email", email);
                        startActivity(intent);

                        Toast.makeText(SetNewPassword.this, "Update Password Successfully", Toast.LENGTH_LONG).show();
                        finish();

                    } else {
                        Toast.makeText(SetNewPassword.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(SetNewPassword.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<VerifyResetPassOtpModel> call, Throwable t) {
                Toast.makeText(SetNewPassword.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}