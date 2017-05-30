package com.mangaapp.nexusninja2.mangayeah;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignActivity extends AppCompatActivity
{
    private TextView txtLogin;
    private Button btnSignUp;
    private EditText etUsername, etPassword, etFirstname, etLastName;
    private RadioGroup rbGroup;
    private RadioButton rbSelected;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        txtLogin = (TextView)findViewById(R.id.txtLogin);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etFirstname = (EditText)findViewById(R.id.etFirstName);
        etLastName = (EditText)findViewById(R.id.etLastName);
      //  rbGroup = (RadioGroup)findViewById(R.id.rbGroup);
       btnSignUp = (Button)findViewById(R.id.btnSignUp);

       // rbSelected = (RadioButton)findViewById(rbGroup.getCheckedRadioButtonId());
       btnSignUp.setOnClickListener(onClick());
        txtLogin.setOnClickListener(onClick());
    }

    private void SignUpUser(String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                        {
                            Toast.makeText(SignActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            mAuth.signOut();
                        }
                        else if (task.isSuccessful())
                        {
                            Success(task.getResult().getUser());
                            mAuth.signOut();
                        }
                    }
                });
    }

    private void StoreDatabase()
    {
        rbGroup = (RadioGroup)findViewById(R.id.rbGroup);

        rbSelected = (RadioButton)findViewById(rbGroup.getCheckedRadioButtonId());

        if(TextUtils.isEmpty(etUsername.getText().toString()))
        {
            Toast.makeText(this, "Enter a username", Toast.LENGTH_LONG).show();
            etUsername.requestFocus();
        }
        else if (TextUtils.isEmpty(etPassword.getText().toString()))
        {
            Toast.makeText(this, "Enter a password", Toast.LENGTH_LONG).show();
            etPassword.requestFocus();
        }
        else if (TextUtils.isEmpty(etFirstname.getText().toString()))
        {
            Toast.makeText(this, "State your First Name", Toast.LENGTH_LONG).show();
            etFirstname.requestFocus();
        }
        else if (TextUtils.isEmpty(etLastName.getText().toString()))
        {
            Toast.makeText(this, "State your Last Name", Toast.LENGTH_LONG).show();
            etLastName.requestFocus();
        }
        else
        {
            SignUpUser(etUsername.getText().toString(), etPassword.getText().toString());
        }
    }

    private void Success(FirebaseUser mUser)
    {
        StoreData(etUsername.getText().toString(), etFirstname.getText().toString(), etLastName.getText().toString(), rbSelected.getText().toString(), mUser.getUid());
        GoLogin();
    }

    private void StoreData(String username, String firstName, String lastName, String gender, String userID)
    {
        String dbUserID = databaseReference.push().getKey();
        UserClass userClass = new UserClass(username, firstName, lastName, gender, userID);
        databaseReference.child("users").child(dbUserID).setValue(userClass);
    }

    private  void GoLogin()
    {
        Intent homeIntent = new Intent(SignActivity.this, MainActivity.class);
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
                if(buttonID == R.id.btnSignUp)
                {
                    StoreDatabase();
                }
                else if (buttonID == R.id.txtLogin)
                {
                    GoLogin();
                }
            }
        };
    }
}
