package com.prodcast.samayusoftcorp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.businessobjects.SessionInfo;
import com.dto.LoginDTO;
import com.montecito.samayu.service.MontecitoClient;
import com.prodcast.samayu.samayusoftcorp.R;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {


    EditText loginID,password;
    TextView forgetPassword,newUser;
    Button loginButton;
    Context context;
    View focusView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        context = this;
        loginID= (EditText)findViewById(R.id.loginID);
        password= (EditText)findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);
        forgetPassword = (TextView)findViewById(R.id.forgotPassword);
        newUser = (TextView)findViewById(R.id.newUser);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });


        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //registerEmployee();

            }
        });


    }


    public void attemptLogin(){
        String email = loginID.getText().toString();
        String pass = password.getText().toString();

        if (checkValid(email,pass)){
            return ;

        }
        loginButton.setEnabled(false);
        Call<LoginDTO> loginDTOCall = new MontecitoClient().getClient().authenticate(email, pass );
       loginDTOCall.enqueue(new Callback<LoginDTO>() {
           @Override
           public void onResponse(retrofit2.Call<LoginDTO> call, Response<LoginDTO> response) {
               if( response.isSuccessful() ) {
                   LoginDTO loginDTO = response.body();
                   SessionInfo.instance().setToken( loginDTO.getToken());
                   SessionInfo.instance().setFirstName(loginDTO.getFirstName());
                   SessionInfo.instance().setLastName(loginDTO.getLastName());
                   Intent intent = new Intent(LoginScreen.this, Home.class);
                   startActivity(intent);

               }
               else{
                   loginButton.setEnabled(true);
                   //Show Error Message
               }
           }

           @Override
           public void onFailure(Call<LoginDTO> call, Throwable t) {

           }
       });
    }

    public void registerEmployee(){
        Intent intent=new Intent(LoginScreen.this,Register.class);
       // startActivity(intent);
    }



    public boolean checkValid(String email,String pass){
        boolean cancel = false;
        loginID.setError(null);
        password.setError(null);

        if (TextUtils.isEmpty(email)){
            loginID.setError("Please enter the emailId");
            focusView = loginID;
            cancel = true;
        }
        if(pass!=null){

            if(TextUtils.isEmpty(pass)){
                password.setError("Please enter some value");
                focusView = password;
                cancel = true;
                return cancel;

            }


        }




        return cancel;
    }



}
