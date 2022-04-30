package com.varsitycollege.opsc7311_collectionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.common.base.Verify;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{
    private Button btn_register;
    private Button btn_login;
    private TextView btn_forgotPassword;

    private EditText email;
    private EditText password;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (TextInputEditText)findViewById(R.id.email_input);
        password = (TextInputEditText)findViewById(R.id.password_input);

        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);
        btn_forgotPassword = findViewById(R.id.btn_forgotPassword);

        mAuth = FirebaseAuth.getInstance();

        /**
         *  SUMMERY: A listener for when the user clicks the Register button, then moves to register
         *           page
         */
        btn_register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"Register Button is clicked", Toast.LENGTH_LONG).show();
                //Intent i = new Intent(MainActivity.this, registerPage.class);
                //startActivity(i);
                Intent moveToRegisterPage = new Intent(MainActivity.this, registerPage.class);
                startActivity(moveToRegisterPage);
            }
        });

        /**
         *  SUMMERY: Once clicked the user will go through the LogUserIn() authentication process
         */
        btn_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                LogUserIn();
            }
        });

        /**
         *  SUMMERY: When the forgot password button is clicked the user will be asked
         *           to input their email for a reset link.
         */
        btn_forgotPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final EditText resetMail = new EditText(view.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset Password?");
                passwordResetDialog.setMessage("Enter Your Email To Receive Reset Link");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        String mail = resetMail.getText().toString();

                        //TODO: Add input verification for mail variable above to avoid a crash

                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>()
                        {
                            @Override
                            public void onSuccess(Void aVoid)
                            {
                                Toast.makeText(MainActivity.this,"Reset Link Has Been Sent To Your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                Toast.makeText(MainActivity.this,"Error! Reset Link Not Sent To Your Email" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {

                    }
                });
                passwordResetDialog.create().show();
            }
        });
    }

    /**     SUMMARY: if the user account is verified and the account details are correct
     *               the user will be logged in
     */
    void LogUserIn()
    {
        //progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (!user.isEmailVerified())
                    {
                        Toast.makeText(MainActivity.this, "Unable to login, Please verify your email", Toast.LENGTH_SHORT).show();
                        //Verify.setVisibility(View.VISIBLE);
                        // progressBar.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Logged in successfully.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), dashboard.class));
                        finish();
                    }
                }
                else
                {
                    //progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}