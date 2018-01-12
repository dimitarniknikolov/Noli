package com.dimitar.noli.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.TextView;

import com.dimitar.noli.R;
import com.dimitar.noli.feature.FeatureProvider;
import com.dimitar.noli.feature.signin.GoogleSignInFeature;
import com.dimitar.noli.ui.list.MainViewModel;
import com.dimitar.noli.vo.Note;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Inject ViewModelProvider.Factory viewModelFactory;
    private MainViewModel viewModel;
    @Inject FeatureProvider featureProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(MainActivity.this, viewModelFactory).get(
                MainViewModel.class);
        viewModel.print();

        GoogleSignInFeature googleSignInFeature = featureProvider.addSignInFeature(this, true);
        googleSignInFeature.getGoogleSignInAccountLiveData().observe(this, this::updateUI);

        findViewById(R.id.tvName).setOnClickListener(view -> googleSignInFeature.signOut());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("notes");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Note note = dataSnapshot.getValue(Note.class);
                ((TextView) findViewById(R.id.tvName)).setText(note.toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Note note = dataSnapshot.getValue(Note.class);
                ((TextView) findViewById(R.id.tvName)).setText(note.toString());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void updateUI(GoogleSignInAccount account) {
//        if (account != null)
        //  ((TextView) findViewById(R.id.tvName)).setText(account.getDisplayName());
    }

}
