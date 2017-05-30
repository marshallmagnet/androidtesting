package com.mangaapp.nexusninja2.mangayeah;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
{
    private static final String TAG = "ViewDatabase";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthlistener;
    private Button btnLogout;
    private TextView txtName, txtGender;
    private DatabaseReference databaseReference, specificReference;
    private String userID;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        specificReference = FirebaseDatabase.getInstance().getReference("users");

        listView = (ListView)findViewById(R.id.displayer);
        btnLogout = (Button)findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(onClick());

        FirebaseUser currentUser = mAuth.getCurrentUser();

        userID = currentUser.getUid();

        mAuthlistener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();

                if (currentUser != null)
                {
                    Toast.makeText(HomeActivity.this, "Successfully Logged In.", Toast.LENGTH_SHORT).show();
                }
                else if (currentUser == null)
                {
                    GoLogin();
                }
            }
        };

//        Query userQuery = specificReference.orderByChild("mUserID").equalTo(userID);
//
//        userQuery.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot zoneSnapshot: dataSnapshot.getChildren())
//                {
//                    UserClass userClass = new UserClass();
//                    userClass.setmFirstName(zoneSnapshot.child("mFirstName").getValue(UserClass.class).getmFirstName());
//                    userClass.setmLastName(zoneSnapshot.child("mLastName").getValue(UserClass.class).getmLastName());
//                    userClass.setmGender(zoneSnapshot.child("mGender").getValue(UserClass.class).getmGender());
//                    userClass.setmUsername(zoneSnapshot.child("mUsername").getValue(UserClass.class).getmUsername());
//
//                    Log.i(TAG, userClass.getmUsername());
//
//                    ArrayList<String> array = new ArrayList<>();
//                    array.add(userClass.getmFirstName());
//                    array.add(userClass.getmLastName());
//                    array.add(userClass.getmGender());
//                    array.add(userClass.getmUsername());
//                    //ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
//                    //listView.setAdapter(arrayAdapter);
//                    //Log.i(TAG, zoneSnapshot.child("mUsername").getValue(String.class));
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        //Posting();
        //Gwapo si Jan!!!!
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

    private void Posting()
    {
        txtName = (TextView) findViewById(R.id.txtName);
        txtGender = (TextView)findViewById(R.id.txtGender);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    //Log.i(TAG, ds.child("mUserID").getValue(Profile.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    private void Logout()
    {
        mAuth.signOut();
        GoLogin();
    }

    private void GoLogin()
    {
        Intent homeIntent = new Intent(HomeActivity.this, MainActivity.class);
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
                if(buttonID == R.id.btnLogout)
                {
                    Logout();
                }
            }
        };
    }
}
