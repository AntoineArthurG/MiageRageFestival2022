package com.example.miageragefestival2022;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.miageragefestival2022.databinding.ActivityNumUrgenceBinding;
import com.google.android.material.navigation.NavigationView;

public class NumUrgenceActivity extends DrawerBaseActivity {

    ActivityNumUrgenceBinding activityNumUrgenceBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNumUrgenceBinding = ActivityNumUrgenceBinding.inflate(getLayoutInflater());
        setContentView(activityNumUrgenceBinding.getRoot());
        allocateActivityTitle("Numéros d'urgence");

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);

        LinearLayout appelEuro = findViewById(R.id.num_appel_euro);
        appelEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv_num = findViewById(R.id.num_euro);
                String num = tv_num.getText().toString();
                Intent appel = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", num, null));
                startActivity(appel);
            }
        });
    }
}