package com.cinntra.vista.superadmin;

import com.cinntra.vista.model.BodyForRegisterSaas;
import com.cinntra.vista.model.ForgotPasswordModel;
import com.cinntra.vista.model.ResponseIndustrySaas;
import com.cinntra.vista.model.TokenResponseModel;
import com.cinntra.vista.model.VerifyResetPassOtpModel;
import com.cinntra.vista.superadmin.response.ResponseSuperAdmin;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SuperAdminServices {

    @POST("api/user/login")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<ResponseSuperAdmin> loginToken(@Body JsonObject jsonObject);

    @POST("api/user/forgot_password")
    Call<ForgotPasswordModel> ForgotPassword(@Body JsonObject jsonObject);

    @POST("api/user/verify_otp")
    Call<VerifyResetPassOtpModel> VerifyResetPassword(@Body JsonObject jsonObject);

    @GET("api/industries/all")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<ResponseIndustrySaas> getIndustrySaas();

    @POST("api/customer/register")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<ResponseIndustrySaas> registerCustomer(@Body BodyForRegisterSaas bodyForRegisterSaas);

    @POST("api/customer/verify_otp")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<ResponseIndustrySaas> verifyOtp(@Body JsonObject bodyForRegisterSaas);

}
