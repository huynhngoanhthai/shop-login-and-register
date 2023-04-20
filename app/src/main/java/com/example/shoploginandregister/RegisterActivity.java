package com.example.shoploginandregister;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    ENV env = new ENV();
    private String APIRegister = env.getAPI() + "/api/v1/users/signup";
    private EditText inputName,inputEmail,inputPassword,inputPasswordConfirm;
    private FrameLayout suggestPassword;
    private Button btnRegister;
    private Boolean email,name,password,passwrodConfirm;
    private TextView textViewHaveAccount,check_1,check_2,check_3,check_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setEvent();
        setController();
    }
    private void setEvent(){
        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputPasswordConfirm = findViewById(R.id.inputPasswordConfirm);
        btnRegister = findViewById(R.id.buttonResignter);
        suggestPassword = findViewById(R.id.instructPassword);
        textViewHaveAccount = findViewById(R.id.textViewHaveAccount);
        check_1 = findViewById(R.id.check_1);
        check_2 = findViewById(R.id.check_2);
        check_3 = findViewById(R.id.check_3);
        check_4 = findViewById(R.id.check_4);
        name = false;
        email = false;
        password = false;
        passwrodConfirm = false;
    }
    private void setController(){
        checkEmail();
        showSuggest();
        checkedPasswordConfirm();
        clickRegister();
        clickLogin();
    }
    private void clickRegister(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFormRegister();

//                nextSendOTPActivity();
            }
        });
    }
    private void checkFormRegister(){
        if(inputName.getText().toString().isEmpty()){
            Toast.makeText(this, "name khong duoc de trong", Toast.LENGTH_SHORT).show();
            return;
        } else if (!email) {
            Toast.makeText(this, "email khong hop le", Toast.LENGTH_SHORT).show();
            return;

        } else if (!password) {
            Toast.makeText(this, "password khong an toan", Toast.LENGTH_SHORT).show();
            return;

        } else if (!passwrodConfirm) {
            Toast.makeText(this, "password confirm khong giong password", Toast.LENGTH_SHORT).show();
            return;
        }

        callAPIRegister();
    }
    private void callAPIRegister(){
        try {

            // Make new json object and put params in it
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("name", inputName.getText().toString());
            jsonParams.put("email", inputEmail.getText().toString());
            jsonParams.put("password", inputPassword.getText().toString());
//            jsonParams.put("passwordCofirm", inputPasswordConfirm.getText().toString());

            // Building a request
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    APIRegister,
                    jsonParams,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if (response.optString("success").equals("true")){
                                nextSendOTPActivity();
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
    private void nextSendOTPActivity(){
        Intent intent = new Intent(RegisterActivity.this,SendOTPActivity.class);
        intent.putExtra("name",inputName.getText().toString());
        intent.putExtra("email",inputEmail.getText().toString());
        intent.putExtra("password",inputPassword.getText().toString());
        intent.putExtra("passwrodConfirm",inputPasswordConfirm.getText().toString());
        startActivity(intent);
    }
    private void checkEmail(){
        inputEmail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (Patterns.EMAIL_ADDRESS.matcher( s.toString().trim()).matches()) {
                    inputEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_email_true, 0);
                    email = true;
                } else if (inputEmail.getText().toString().isEmpty()) {
                    inputEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_email, 0);
                    email = false;
                }else {
                    inputEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_email_false, 0);
                    email = false;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void showSuggest(){
        inputPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    suggestPassword.setVisibility(View.VISIBLE);
                } else {
                    suggestPassword.setVisibility(View.GONE);
                }
            }
        });
        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                boolean hasSpecialChar = charSequence.toString().matches(".*[^a-zA-Z0-9].*");
                boolean hasUpperCase = charSequence.toString().matches(".*[A-Z].*");
                boolean hasLowerCaseAndNumber = charSequence.toString().matches(".*[a-z].*") && charSequence.toString().matches(".*[0-9].*");
                boolean isBetween8and30Chars = charSequence.length() > 8 && charSequence.length() < 30;

                check_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, hasSpecialChar ? R.drawable.ic_check_circle_true : R.drawable.ic_check_circle_false, 0);
                check_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, hasUpperCase ? R.drawable.ic_check_circle_true : R.drawable.ic_check_circle_false, 0);
                check_3.setCompoundDrawablesWithIntrinsicBounds(0, 0, hasLowerCaseAndNumber ? R.drawable.ic_check_circle_true : R.drawable.ic_check_circle_false, 0);
                check_4.setCompoundDrawablesWithIntrinsicBounds(0, 0, isBetween8and30Chars ? R.drawable.ic_check_circle_true : R.drawable.ic_check_circle_false, 0);
                if (hasSpecialChar && hasUpperCase && hasLowerCaseAndNumber && isBetween8and30Chars) {
                    inputPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_lock_true, 0);
                    password = true;
                }else{
                    inputPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_lock_loading, 0);
                    password = false;
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(inputPassword.getText().toString().isEmpty()){
                    inputPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_lock, 0);
                    password= false;
                }
            }
        });
    }

    private void clickLogin(){
        textViewHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startLoading(5000);
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }

    public void checkedPasswordConfirm(){
        inputPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if( inputPassword.getText().toString().equals(charSequence.toString())){
                    inputPasswordConfirm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_true, 0);
                    passwrodConfirm = true;
                }else{
                    inputPasswordConfirm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_false, 0);
                    passwrodConfirm = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}