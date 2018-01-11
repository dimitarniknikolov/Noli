package com.dimitar.noli.feature;

import android.support.v7.app.AppCompatActivity;

import com.dimitar.noli.feature.signin.GoogleSignInFeature;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by dimitar on 09.01.18.
 */

@Singleton
public class FeatureProvider {

    @Inject
    public FeatureProvider() {
    }

    public GoogleSignInFeature addSignInFeature(AppCompatActivity activity, boolean autoRequestLocation) {
        GoogleSignInFeature googleSignInFeature = GoogleSignInFeature.newInstance(autoRequestLocation);
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(googleSignInFeature, GoogleSignInFeature.TAG)
                .commit();
        return googleSignInFeature;
    }
}
