package com.dimitar.noli.feature.signin;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

/**
 * Created by dimitar on 08.01.18.
 */

public class GoogleSignInFeature extends Fragment {

    public static final String TAG = "GoogleSignInFeature";

    public static final String AUTO_SIGN_IN = "auto_sign_in";
    public static final int RC_SIGN_IN = 100;

    private GoogleSignInClient googleSignInClient;
    private MutableLiveData<GoogleSignInAccount> googleSignInAccountMutableLiveData = new MutableLiveData<>();

    // ---------------------------------------------------------------------------------------------
    // Lifecycle
    // ---------------------------------------------------------------------------------------------
    @Override
    public void onStart() {
        super.onStart();
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(getContext(), gso);


        if (shouldAutoSingIn()) {
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
            googleSignInAccountMutableLiveData.setValue(account);
            if (account == null) signIn();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }


    // ---------------------------------------------------------------------------------------------
    // Public
    // ---------------------------------------------------------------------------------------------
    public void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signOut() {
        googleSignInClient.signOut().addOnCompleteListener(getActivity(), task -> googleSignInAccountMutableLiveData.setValue(null));
    }

    public LiveData<GoogleSignInAccount> getGoogleSignInAccountLiveData() {
        return googleSignInAccountMutableLiveData;
    }


    // ---------------------------------------------------------------------------------------------
    // Private
    // ---------------------------------------------------------------------------------------------
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            googleSignInAccountMutableLiveData.setValue(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            googleSignInAccountMutableLiveData.setValue(null);
        }
    }

    private boolean shouldAutoSingIn() {
        return getArguments().getBoolean(AUTO_SIGN_IN);
    }


    // ---------------------------------------------------------------------------------------------
    // New instance
    // ---------------------------------------------------------------------------------------------
    public static GoogleSignInFeature newInstance(boolean autoSignIn) {
        GoogleSignInFeature googleSignIn = new GoogleSignInFeature();
        Bundle args = new Bundle();
        args.putBoolean(AUTO_SIGN_IN, autoSignIn);
        googleSignIn.setArguments(args);
        return googleSignIn;
    }

}
