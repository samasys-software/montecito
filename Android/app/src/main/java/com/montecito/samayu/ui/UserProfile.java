package com.montecito.samayu.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.montecito.samayu.dto.UserProfileDTO;
import com.montecito.samayu.service.SessionInfo;
import com.prodcast.samayu.samayusoftcorp.R;


import java.util.List;

public class UserProfile extends MontecitoBaseActivity {
    ExpandableRelativeLayout changePasswordLayout;
    ImageButton changeButton;
    EditText oldPassword,newPassword,confirmPassword;
    Button savePassword,cancelPassword;
    boolean cancel=false;
    View focusview=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
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
        setUserProfile();
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
     }
     private void setUserProfile(){
         ImageView userImage=(ImageView)findViewById(R.id.userImage);
         TextView name=(TextView)findViewById(R.id.userName);
         TextView firstName=(TextView)findViewById(R.id.userFirstName);
         TextView lastName=(TextView)findViewById(R.id.userLastName);
         TextView dob=(TextView)findViewById(R.id.userDateOfBirth);
         TextView designation=(TextView)findViewById(R.id.userDesignation);
       /*  UserProfileDTO userProfile= SessionInfo.getInstance().getUserProfile();
         name.setText(userProfile.getName());
         firstName.setText(userProfile.getFirstName());
         lastName.setText(userProfile.getLastName());
         dob.setText(userProfile.getDob());

         designation.setText(userProfile.getDesignation());*/
         //Picasso.with(getBaseContext()).load(userProfile.getImageUrl()).into(userImage);
     }

}