package com.matriot.cbin.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.matriot.cbin.R;
import com.matriot.cbin.db.AppDatabase;
import com.matriot.cbin.domain.PushNotification;
import com.matriot.cbin.dto.RegisterPushNotificationDTO;
import com.matriot.cbin.dto.UserLoginDTO;
import com.matriot.cbin.dto.LoginDTO;
import com.matriot.cbin.domain.LoginInput;
import com.matriot.cbin.dto.UserProfileDTO;
import com.matriot.cbin.service.MontecitoClient;
import com.matriot.cbin.service.SessionInfo;



import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {


    EditText loginID,password;
    TextView forgetPassword,montecitoName,newUser,subHead;
    Button loginButton;
    Context context;
    View focusView = null;
    public static final String FILE_NAME = "MontecitoLogin.txt";
    public static final String INPUT_FILE_NAME = "MontecitoLoginDetails.txt";
    private boolean loggedIn=false;
    private  AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        db=AppDatabase.getAppDatabase(context);
        UserLoginDTO userLogin = (UserLoginDTO) loginRetrive("MontecitoLogin.txt");
        if (userLogin != null) {
            SessionInfo.getInstance().setUserLogin(userLogin);
            getUserProfile();
            Intent intent=new Intent(LoginScreen.this,Home.class);
            startActivity(intent);
        }
        else {
            LoginInput loginInput =(LoginInput) loginRetrive("MontecitoLoginDetails.txt");
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
            subHead = (TextView) findViewById(R.id.montecitoTopName);
            //SpannableStringBuilder cs = new SpannableStringBuilder("cBinTM");
            //cs.setSpan(new SuperscriptSpan(), 4, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            montecitoName.setText(Html.fromHtml("<sub><big>cBin</big></sub><sup><small></small></sup>\t"));
//            subHead.setText(Html.fromHtml("<sub><big></big></sub><sup><small>TM</small></sup>\t"));
            loggedIn=true;


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
                    loginToFile(userLogin,"MontecitoLogin.txt");
                    loginToFile(loginInput,"MontecitoLoginDetails.txt");


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

    public void loginToFile(Object details,String fileName) {
        File file = new File(getFilesDir(), fileName);
        file.delete();

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(fileName, LoginScreen.this.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(details);
            outputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object loginRetrive(String fileName) {
        try {
            ObjectInputStream ois = new ObjectInputStream(openFileInput(fileName));
            Object r = (Object) ois.readObject();
            return r;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

  /*  public void storeInput(LoginInput userInput) {
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
    }*/
    public void getUserProfile(){
        String token=SessionInfo.getInstance().getUserLogin().getToken();
        if(isNetworkAvailable()) {
            final Call<UserProfileDTO> userProfileDTOCall = new MontecitoClient().getClient().getUserProfile(token);
            userProfileDTOCall.enqueue(new Callback<UserProfileDTO>() {
                @Override
                public void onResponse(Call<UserProfileDTO> call, Response<UserProfileDTO> response) {
                    if (response.code()==200) {
                        UserProfileDTO  userProfile = response.body();
                        userProfile.setHide(false);
                        SessionInfo.getInstance().setUserProfile(userProfile);
                        addLocalUserProfile(db,userProfile);
                        if(loggedIn){
                            PushNotification pushNotificationToken=(PushNotification)loginRetrive("DeviceRegisterTokenFile.txt");
                            if(pushNotificationToken!=null)
                                sendRegistrationToServer(pushNotificationToken);
                        }
                       // addLocalUserProfile(db,userProfile);
                       // userId = userProfile.getId();
                       // System.out.print(userId);

                    }
                    else if (response.code() == 401 || response.code() == 403) {
                        Intent intent = new Intent(LoginScreen.this, LoginScreen.class);
                        startActivity(intent);

                    }


                }

                @Override
                public void onFailure(Call<UserProfileDTO> call, Throwable t) {

                }
            });
        }
        else{

            UserProfileDTO  userProfile = getLocalUserProfile(db);
            userProfile.setHide(true);
            SessionInfo.getInstance().setUserProfile(userProfile);
        }
    }


    private void sendRegistrationToServer(PushNotification pushNotification) {

        System.out.println("Token"+SessionInfo.getInstance().getUserLogin().getToken());
        System.out.println("userId"+SessionInfo.getInstance().getUserProfile().getId());
        if(isNetworkAvailable()) {
            Call<RegisterPushNotificationDTO> registerDeviceCall = new MontecitoClient().getClient().registerDevice(SessionInfo.getInstance().getUserProfile().getId(), pushNotification, SessionInfo.getInstance().getUserLogin().getToken());
            registerDeviceCall.enqueue(new Callback<RegisterPushNotificationDTO>() {
                @Override
                public void onResponse(Call<RegisterPushNotificationDTO> call, Response<RegisterPushNotificationDTO> response) {
                    if (response.code() == 200) {
                        RegisterPushNotificationDTO registerPushNotificationDTO = response.body();
                        Toast.makeText(context,"Registration Status : "+registerPushNotificationDTO.getStatus(),Toast.LENGTH_LONG).show();

                    } else if (response.code() == 401 || response.code() == 403) {



                    } else {

                    }

                }

                @Override
                public void onFailure(Call<RegisterPushNotificationDTO> call, Throwable t) {

                }
            });
        }

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    private static void addLocalUserProfile(final AppDatabase db, UserProfileDTO userProfileDTO) {
        db.userProfileDAO().deleteAll();
        db.userProfileDAO().insertAll(userProfileDTO);

    }
    private static UserProfileDTO  getLocalUserProfile(final AppDatabase db)
    {
        return db.userProfileDAO().getAll();

    }


}
