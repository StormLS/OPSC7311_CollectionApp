package com.varsitycollege.opsc7311_collectionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registerPage extends AppCompatActivity
{
    //Declaring any UI element for interaction
        private Button btn_register;
        private TextView btn_alreadyMember;
    //Declaring any UI element for interaction

    //Declaring EditText Fields on Register page
    //TODO: Add input verification for all variables
        EditText Email;
        EditText name;
        EditText surname;
        EditText password;
        EditText confirmPassword;
    //Declaring EditText Fields on Register page

    //Code for firebase database
        FirebaseAuth mAuth;
        FirebaseFirestore fstore;
        String userID;
        FirebaseUser fuser;
    //Code for firebase database

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        //All the findViewById methods are created
        Email = findViewById(R.id.email_input);
        name = findViewById(R.id.name_input);
        surname = findViewById(R.id.surname_input);
        password = findViewById(R.id.password_input);
        confirmPassword = findViewById(R.id.confirmPassword_input);

        btn_register = findViewById(R.id.btn_register);
        btn_alreadyMember = findViewById(R.id.btn_alreadyMember);
        //All the findViewById methods are created

        //Code for firebase database
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        fuser = mAuth.getCurrentUser();
        //Code for firebase database

        if(mAuth.getCurrentUser() != null) //Checks to see which user is signed in
        {
            if (!fuser.isEmailVerified())
            {
                Log.d("mytag", "email not verified");
            }
            else
            {
                //TODO: Figure out wtf this does XD
                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                //finish();
            }
        }

        /**
         *  SUMMERY: When the user clicks on the Register button, Proceed to WriteDate to Firebase
         */
        btn_register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (password.getText().toString().equals(confirmPassword.getText().toString()))
                {
                    registerUserWithFirebase();
                    // showDialog();
                }else
                {
                    Toast.makeText(registerPage.this,  "Your passwords dont match! Try Again!", Toast.LENGTH_LONG);
                }
            }
        });

        /**
         *  SUMMERY: When the user clicks on the Already a Member button, Return to Login
         */
        btn_alreadyMember.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(registerPage.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    /** SUMMARY: Saves the input data to Firebase. The user is then sent a verification email that
     *           needs to be completed before they can login
     */
    void registerUserWithFirebase()
    {
        //progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(Email .getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful()) //When the user is successfully registered
                {
                    FirebaseUser auth = FirebaseAuth.getInstance().getCurrentUser();
                    auth.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            Toast.makeText(registerPage.this,  "Verification Email Has Been Sent", Toast.LENGTH_LONG);
                        }
                    }).addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Log.d("onFailure_EMAIL", "onFailure: Email Not Sent" + e.getMessage());

                        }
                    });

                    Toast.makeText(registerPage.this, "Verification Email Has Been Sent.", Toast.LENGTH_LONG).show();

                    userID = mAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fstore.collection("users").document(userID);
                    Map<String, Object> user =  new HashMap<>();
                    user.put("Name",name.getText().toString());
                    user.put("Surname",surname.getText().toString());

                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            Log.d("onSuccess_TAG", "onSuccess: User profile and data saved" + userID);
                        }
                    }).addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Log.d("onFailure_USER", "onFailure: User Not Saved" + e.getMessage());
                        }
                    });
                    showThankYouDialog();
                }
                else //When the user isn't registered correctly
                {
                   // progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(registerPage.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showThankYouDialog()
    {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setCanceledOnTouchOutside(false); //-- To prevent a user from clicking away --
        dialog.setContentView(R.layout.dialog_account_created);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.login_container); //Adds greyness to the corners too

        Button btn_thanks = dialog.findViewById(R.id.btn_thanks);
        btn_thanks.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(), MainActivity.class)); // New way of doing intent :-)
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
/**-------------------------------------===< END OF FILE >===-------------------------------------*/