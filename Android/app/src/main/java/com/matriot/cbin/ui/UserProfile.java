package com.matriot.cbin.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.matriot.cbin.R;
import com.matriot.cbin.db.AppDatabase;
import com.matriot.cbin.domain.ChangePassword;
import com.matriot.cbin.dto.UserProfileDTO;
import com.matriot.cbin.service.MontecitoClient;
import com.matriot.cbin.service.SessionInfo;



import java.io.File;

import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfile extends MontecitoBaseActivity {
    ExpandableRelativeLayout changePasswordLayout;
    ImageButton changeButton;
    EditText oldPassword,newPassword,confirmPassword;
    Button savePassword,cancelPassword;
    boolean cancel=false;
    View focusview=null;
    Context context;
    String userId;
    private  AppDatabase db;
    UserProfileDTO userProfile=SessionInfo.getInstance().getUserProfile();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        context=this;

        oldPassword=(EditText)findViewById(R.id.oldPassword);
        newPassword=(EditText)findViewById(R.id.newPassword);
        confirmPassword=(EditText)findViewById(R.id.confirmPassword);
        savePassword=(Button)findViewById(R.id.save);
        cancelPassword=(Button)findViewById(R.id.cancel);
        changeButton=findViewById(R.id.changeButton);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });
        savePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptChangePassword();
            }
        });
        cancelPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
        userId = userProfile.getId();
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.ChangePasswordLayout);

        setUserProfile();
        if(isNetworkAvailable()){
            linearLayout.setVisibility(View.VISIBLE);
        }
        else{
            linearLayout.setVisibility(View.GONE);
        }




    }
    public void changePassword() {
        changePasswordLayout = (ExpandableRelativeLayout) findViewById(R.id.ChangePasswordDetailsLayout);
        changePasswordLayout.toggle(); // toggle expand and collapse

        if(changePasswordLayout.isExpanded())
        {
            changeButton.setImageResource(R.drawable.downarrow);
        }
        else
        {
            changeButton.setImageResource(R.drawable.uparrow);
        }
    }

    public boolean checkValue(String oldPwd,String newPwd,String confirmPwd){
        cancel=false;
        oldPassword.setError(null);
        newPassword.setError(null);
        confirmPassword.setError(null);
        if(TextUtils.isEmpty(oldPwd)){
            oldPassword.setError("This field is Required");
            focusview=oldPassword;
            cancel=true;
            return cancel;
        }
        if(TextUtils.isEmpty(newPwd)){
            newPassword.setError("This field is Required");
            focusview=newPassword;
            cancel=true;
            return cancel;
        }
        if(TextUtils.isEmpty(confirmPwd)){
            confirmPassword.setError("This field is Required");
            focusview=confirmPassword;
            cancel=true;
            return cancel;
        }
        if(oldPwd.equals(newPwd)){
            newPassword.setError("Your New Password Is Same As The OldPassword");
            focusview=newPassword;
            cancel=true;
            return cancel;
        }
        if(!confirmPwd.equals(newPwd)){
            confirmPassword.setError("Your Confirm Password Is Not Valid");
            focusview=confirmPassword;
            cancel=true;
            return cancel;
        }
        return cancel;
    }
    public void clear(){
        oldPassword.setText("");
        newPassword.setText("");
        confirmPassword.setText("");
    }

     private void attemptChangePassword(){
            String oldPwd=oldPassword.getText().toString();
            String newPwd=newPassword.getText().toString();
            String confirmPwd=confirmPassword.getText().toString();
            boolean validation=checkValue(oldPwd,newPwd,confirmPwd);
            if(validation){
                focusview.requestFocus();
                return;
            }
            else{
                ChangePassword changePassword = new ChangePassword();
                changePassword.setOldPassword(oldPwd);
                changePassword.setNewPassword(newPwd);
                if(isNetworkAvailable()) {
                    Call<ResponseBody> changePasswordDTOCall = new MontecitoClient().getClient().changePassword(userId, changePassword, SessionInfo.getInstance().getUserLogin().getToken());
                    changePasswordDTOCall.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if(response.code()==200) {
                                    Toast.makeText(context, "Your Password Changed Successfully", Toast.LENGTH_LONG).show();
                                    File dir = getFilesDir();
                                    File file = new File(dir, "MontecitoLoginDetails.txt");

                                    boolean deleted = file.delete();
                                }
                                else if (response.code() == 401 || response.code() == 403) {
                                        Intent intent = new Intent(UserProfile.this, LoginScreen.class);
                                        startActivity(intent);
                                }
                                else {
                                }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
            }
     }

     private void setUserProfile(){

         ImageView userImage=(ImageView)findViewById(R.id.userImage);
         TextView name=(TextView)findViewById(R.id.userName);
         TextView firstName=(TextView)findViewById(R.id.userFirstName);
       //  TextView lastName=(TextView)findViewById(R.id.userLastName);
       TextView email=(TextView)findViewById(R.id.userEmail);
         TextView designation=(TextView)findViewById(R.id.userDesignation);
         if(userProfile!=null) {
            // name.setText(userProfile.getName());
             firstName.setText(userProfile.getName());
             email.setText(userProfile.getEmail());
           //  lastName.setText(userProfile.getName());
            // dob.setText("null");

             designation.setText(userProfile.getRole());

             //Picasso.with(getBaseContext()).load(userProfile.getImageUrl()).into(userImage);
         }
     }



}
