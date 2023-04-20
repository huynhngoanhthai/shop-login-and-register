package com.example.shoploginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class LoginActivity extends AppCompatActivity {
    ENV env = new ENV();
    private String APILogin = env.getAPI() + "/api/v1/users/login";
    private EditText inputEmail,inputPassword;
    private TextView textViewSignUp,textViewForgotPassword;
    private Button btnLogin;

    public void startLoading(int loadingTime){
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        }, loadingTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setEvent();
        SetController();

    }
    private void setEvent() {
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        textViewSignUp = findViewById(R.id.textViewSignUp);
//        textViewForgotPassword = findViewById(R.id.forgotPassword);
        btnLogin = findViewById(R.id.buttonLogin);
    }
    private void SetController() {
        clickSignUp();
        clickLogin();

    }
    private void clickSignUp() {
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void clickLogin(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAPILogin();
            }
        });
    }
    private void callAPILogin()  {
        try {

            // Make new json object and put params in it
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("email", inputEmail.getText().toString());
            jsonParams.put("password", inputPassword.getText().toString());

            // Building a request
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    APILogin,
                    jsonParams,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Handle the response
                            Toast.makeText(getApplicationContext(), "thành công", Toast.LENGTH_SHORT).show();

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


//
    }


}