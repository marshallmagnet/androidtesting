package com.mangaapp.nexusninja2.mangayeah;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{
    private TextView txtSignUp, txtForgotPass;
    private EditText etUsername, etPassword;
    private Button btnLogin, btnFacebook, btnGoogle;
    private FirebaseAuth.AuthStateListener mAuthlistener;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        mAuthlistener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();

                if (currentUser != null)
                {
                    GoHome();
                }
                else if (currentUser == null)
                {

                }
            }
        };

        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnFacebook = (Button)findViewById(R.id.btnFacebook);
        btnGoogle = (Button)findViewById(R.id.btnGoogle);
        txtSignUp = (TextView)findViewById(R.id.txtSignUp);
        txtForgotPass = (TextView)findViewById(R.id.txtForgotPass);

        txtSignUp.setOnClickListener(onClick());
        txtForgotPass.setOnClickListener(onClick());
        btnLogin.setOnClickListener(onClick());
        btnFacebook.setOnClickListener(onClick());
        btnGoogle.setOnClickListener(onClick());
    }

    @Override
    public void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthlistener);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //do something to update the user class
    }

    @Override
    public void onStop()
    {
        super.onStop();
        if (mAuthlistener != null)
        {
            mAuth.removeAuthStateListener(mAuthlistener);
        }
    }

    private void LoginUser(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                        else if (task.isSuccessful())
                        {
                            GoHome();
                        }
                    }
                });
    }

    private void GoHome()
    {
        Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(homeIntent);
    }

    private View.OnClickListener onClick()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int buttonID = v.getId();

                if(buttonID == R.id.btnLogin)
                {
                    LoginUser(etUsername.getText().toString(), etPassword.getText().toString());
                }
                else if (buttonID == R.id.btnFacebook)
                {

                }
                else if (buttonID == R.id.btnGoogle)
                {

                }
                else if (buttonID == R.id.txtSignUp)
                {
                    Intent homeIntent = new Intent(MainActivity.this, SignActivity.class);
                    startActivity(homeIntent);
                }
                else if (buttonID == R.id.txtForgotPass)
                {

                }
            }
        };
    }
}
