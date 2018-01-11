package com.dimitar.noli.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.TextView;

import com.dimitar.noli.R;
import com.dimitar.noli.feature.FeatureProvider;
import com.dimitar.noli.feature.signin.GoogleSignInFeature;
import com.dimitar.noli.ui.list.MainViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

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
    }

    private void updateUI(GoogleSignInAccount account) {
        if (account != null)
            ((TextView) findViewById(R.id.tvName)).setText(account.getDisplayName());
    }

}
