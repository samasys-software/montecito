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
import com.businessobjects.domain.UserLogin;
import com.dto.LoginDTO;
import com.montecito.samayu.service.MontecitoClient;
import com.prodcast.samayu.samayusoftcorp.R;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {


    EditText loginID,password;
    TextView forgetPassword,newUser;
    Button loginButton;
    Context context;
    View focusView = null;
    public static final String FILE_NAME = "MontecitoLogin.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        context = this;
        loginID= (EditText)findViewById(R.id.loginID);
        password= (EditText)findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);
        forgetPassword = (TextView)findViewById(R.id.forgotPassword);

        UserLogin userLogin = loginRetrive();
        if (userLogin != null) {
            SessionInfo.getInstance().setUserLogin(userLogin);
            Intent intent = new Intent(LoginScreen.this, Home.class);
            startActivity(intent);
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
        Call<LoginDTO> loginDTOCall = new MontecitoClient().getClient().authenticate(email, pass );
       loginDTOCall.enqueue(new Callback<LoginDTO>() {
           @Override
           public void onResponse(retrofit2.Call<LoginDTO> call, Response<LoginDTO> response) {
               if( response.isSuccessful() ) {
                   LoginDTO loginDTO = response.body();
                   UserLogin userLogin=new UserLogin();
                   userLogin.setFirstName(loginDTO.getFirstName());
                   userLogin.setLastName(loginDTO.getLastName());
                   userLogin.setToken(loginDTO.getToken());

                   SessionInfo.getInstance().setUserLogin( userLogin);
                  loginToFile(userLogin);
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

    public void loginToFile(UserLogin customersLogin) {
        File file = new File(getFilesDir(), FILE_NAME);

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




}
