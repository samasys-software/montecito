package com.montecito.samayu.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.montecito.samayu.dto.UserLoginDTO;
import com.montecito.samayu.dto.LoginDTO;
import com.montecito.samayu.domain.LoginInput;
import com.montecito.samayu.dto.UserProfileDTO;
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
        UserLoginDTO userLogin = loginRetrive();
        if (userLogin != null) {
            SessionInfo.getInstance().setUserLogin(userLogin);
            getUserProfile();
            Intent intent=new Intent(LoginScreen.this,Home.class);
            startActivity(intent);
        }
        else {
            LoginInput loginInput = inputRetrive();
            if (loginInput != null) {
                login(loginInput);
            }
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





    }


    public void attemptLogin(){
        String email = loginID.getText().toString();
        String pass = password.getText().toString();
        boolean validation=checkValid(email,pass);
        if (validation){
            focusView.requestFocus();
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
            loginID.setError("This field is Required");
            focusView = loginID;
            cancel = true;
            return cancel;
        }
        if(pass!=null){
            if(TextUtils.isEmpty(pass)){
                password.setError("This field is Required");
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
                if( response.code()==200 ) {
                    LoginDTO loginDTO = response.body();
                    UserLoginDTO userLogin=new UserLoginDTO();
                    userLogin.setFirstName(loginDTO.getFirstName());
                    userLogin.setLastName(loginDTO.getLastName());
                    userLogin.setToken("Bearer "+loginDTO.getToken());

                    SessionInfo.getInstance().setUserLogin( userLogin);
                    loginToFile(userLogin);
                    storeInput(loginInput);
                    getUserProfile();
                    Intent intent = new Intent(LoginScreen.this, Home.class);
                    startActivity(intent);

                }
                else if(response.code()==401 || response.code()==403) {

                    loginButton.setEnabled(true);
                    password.setError("The Email or Password is wrong");
                    focusView = password;
                    return;
                    //Show Error Message
                }
                else{

                }

            }

            @Override
            public void onFailure(Call<LoginDTO> call, Throwable t) {

            }
        });
    }

    public void loginToFile(UserLoginDTO customersLogin) {
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

    public UserLoginDTO loginRetrive() {
        try {
            ObjectInputStream ois = new ObjectInputStream(openFileInput(FILE_NAME));
            UserLoginDTO r = (UserLoginDTO) ois.readObject();
            return r;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void storeInput(LoginInput userInput) {
        File file = new File(getFilesDir(), INPUT_FILE_NAME);
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
    public void getUserProfile(){
        String token=SessionInfo.getInstance().getUserLogin().getToken();
        if(isNetworkAvailable()) {
            final Call<UserProfileDTO> userProfileDTOCall = new MontecitoClient().getClient().getUserProfile(token);
            userProfileDTOCall.enqueue(new Callback<UserProfileDTO>() {
                @Override
                public void onResponse(Call<UserProfileDTO> call, Response<UserProfileDTO> response) {
                    if (response.code()==200) {
                      UserProfileDTO  userProfile = response.body();
                        SessionInfo.getInstance().setUserProfile(userProfile);
                       // addLocalUserProfile(db,userProfile);
                       // userId = userProfile.getId();
                       // System.out.print(userId);

                    }
                    else {
                        if (response.code() == 401 || response.code() == 403) {
                            Toast.makeText(context," Error occured ",Toast.LENGTH_LONG).show();
                        }
                    }

                }

                @Override
                public void onFailure(Call<UserProfileDTO> call, Throwable t) {

                }
            });
        }
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


}
