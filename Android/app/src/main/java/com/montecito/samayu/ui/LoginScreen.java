package com.montecito.samayu.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.montecito.samayu.domain.UserLogin;
import com.montecito.samayu.dto.LoginDTO;
import com.montecito.samayu.dto.LoginInput;
import com.montecito.samayu.service.MontecitoClient;
import com.montecito.samayu.service.SessionInfo;
import com.prodcast.samayu.samayusoftcorp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {


    EditText loginID,password;
    TextView forgetPassword,montecitoName,newUser;
    Button loginButton;
    Context context;
    View focusView = null;
    public static final String FILE_NAME = "MontecitoLogin.txt";
    public static final String INPUT_FILE_NAME = "MontecitoLoginDetails.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserLogin userLogin = loginRetrive();
        if (userLogin != null) {
           SessionInfo.getInstance().setUserLogin(userLogin);
           Intent intent=new Intent(LoginScreen.this,Home.class);
           startActivity(intent);
        }
        else {
            LoginInput loginInput = inputRetrive();
            if (loginInput != null) {
               login(loginInput);
            }
            else{
                setContentView(R.layout.activity_login_screen);
                context = this;

                loginID = (EditText) findViewById(R.id.loginID);
                montecitoName = (TextView) findViewById(R.id.montecitoName);
                password = (EditText) findViewById(R.id.password);
                loginButton = (Button) findViewById(R.id.loginButton);
                forgetPassword = (TextView) findViewById(R.id.forgotPassword);
                //SpannableStringBuilder cs = new SpannableStringBuilder("cBinTM");
                //cs.setSpan(new SuperscriptSpan(), 4, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                montecitoName.setText(Html.fromHtml("<sub><big>cBin</big></sub><sup><small>TM</small></sup>\t"));

                //montecitoName.setText(cs);
            }

        }
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



    }


    public void attemptLogin(){
        String email = loginID.getText().toString();
        String pass = password.getText().toString();

        if (checkValid(email,pass)){
            return ;

        }
        loginButton.setEnabled(false);
        final LoginInput loginInput = new LoginInput();
        loginInput.setEmail(email);
        loginInput.setPassword(pass);
        login(loginInput);

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

    public void login(final LoginInput loginInput)
    {
        Call<LoginDTO> loginDTOCall = new MontecitoClient().getClient().authenticate( loginInput );
        loginDTOCall.enqueue(new Callback<LoginDTO>() {
            @Override
            public void onResponse(Call<LoginDTO> call, Response<LoginDTO> response) {
                if( response.isSuccessful() ) {
                    LoginDTO loginDTO = response.body();
                    UserLogin userLogin=new UserLogin();
                    userLogin.setFirstName(loginDTO.getFirstName());
                    userLogin.setLastName(loginDTO.getLastName());
                    userLogin.setToken("Bearer "+loginDTO.getToken());

                    SessionInfo.getInstance().setUserLogin( userLogin);
                    loginToFile(userLogin);
                    storeInput(loginInput);
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

    public void loginToFile(UserLogin customersLogin) {
        File file = new File(getFilesDir(), FILE_NAME);
        file.delete();

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(FILE_NAME, LoginScreen.this.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(customersLogin);
            outputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserLogin loginRetrive() {
        try {
            ObjectInputStream ois = new ObjectInputStream(openFileInput(FILE_NAME));
            UserLogin r = (UserLogin) ois.readObject();
            return r;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void storeInput(LoginInput userInput) {
        File file = new File(getFilesDir(), FILE_NAME);
        file.delete();

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(INPUT_FILE_NAME, LoginScreen.this.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(userInput);
            outputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LoginInput inputRetrive() {
        try {
            ObjectInputStream ois = new ObjectInputStream(openFileInput(INPUT_FILE_NAME));
            LoginInput r = (LoginInput) ois.readObject();
            return r;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
