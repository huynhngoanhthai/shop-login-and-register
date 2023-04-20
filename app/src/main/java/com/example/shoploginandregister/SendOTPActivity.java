package com.example.shoploginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SendOTPActivity extends AppCompatActivity {
    ENV env = new ENV();

    private String APISendOTP = env.getAPI() + "/api/v1/users/sendmail";
    private String APIAction = env.getAPI() + "/api/v1/users/action/";
    private TextView textViewSendMail;
    private Button btnTryAgain,btnSubmit;
    private EditText inputCode;
    private String OTP,name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otpactivity);
        setEvent();
        setController();
    }

    private void setEvent(){
        Intent intent = getIntent();
        textViewSendMail = findViewById(R.id.textViewSendMail);
        btnSubmit = findViewById(R.id.buttonSubmit);
        btnTryAgain = findViewById(R.id.buttonTryAgain);
        inputCode = findViewById(R.id.inputCode);
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
    }
    private void setController(){
        setupTextViewSendMail();
        sendOTP();
        clickTryAgain();
        clickSubmit();

    }
    private void clickSubmit(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("OTP:",OTP);
                if(inputCode.getText().toString().equals(OTP)){
                    callAPIAction();
                    Toast.makeText(getApplicationContext(), "xac nhan thanh cong", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private void nextLoginActivity(){
        Intent intent = new Intent(SendOTPActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    private void callAPIAction(){

        String URL = APIAction + email;

        // Building a request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Xử lý dữ liệu trả về ở đây
                        if(response.optString("success").equals("true")){
                            nextLoginActivity();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String message = "";
                        String statusCode = "";
                        if (error.networkResponse != null && error.networkResponse.data != null) {
                            try {
                                JSONObject errorJson = new JSONObject(new String(error.networkResponse.data));
                                message = errorJson.getString("message");
                                statusCode = errorJson.getString("statusCode");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // Hiển thị thông báo lỗi
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                });

        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }
    private void clickTryAgain(){
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOTP();
            }
        });
    }
    private void setupTextViewSendMail(){
        textViewSendMail.setText(textViewSendMail.getText().toString() + email);
    }
    private void sendOTP(){
        try {

            // Make new json object and put params in it
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("email", email);
//            jsonParams.put("password", inputPassword.getText().toString());

            // Building a request
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    APISendOTP,
                    jsonParams,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if (response.optString("success").equals("true")){
                                OTP = response.optString("OTP");
                                Toast.makeText(getApplicationContext(), "da gui cho ban 1 email", Toast.LENGTH_SHORT).show();
                            }



                        }
                    },

                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String message = "";
                            String statusCode = "";
                            if (error.networkResponse != null && error.networkResponse.data != null) {
                                try {
                                    JSONObject errorJson = new JSONObject(new String(error.networkResponse.data));
                                    message = errorJson.getString("message");
                                    statusCode = errorJson.getString("statusCode");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            // Hiển thị thông báo lỗi
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                        }
                    });


            Volley.newRequestQueue(this).add(request);

        } catch(JSONException ex){
            // Catch if something went wrong with the params
            Toast.makeText(getApplicationContext(), "lỗi", Toast.LENGTH_SHORT).show();
        }
    }
}