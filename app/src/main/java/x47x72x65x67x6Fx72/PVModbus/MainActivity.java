package x47x72x65x67x6Fx72.PVModbus;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import x47x72x65x67x6Fx72.PVModbus.databinding.ActivityMainBinding;
import x47x72x65x67x6Fx72.PVModbus.ui.DataModel;
import x47x72x65x67x6Fx72.PVModbus.ui.DataFragment;
import x47x72x65x67x6Fx72.PVModbus.ui.dto.HeaderData;
import x47x72x65x67x6Fx72.PVModbus.ui.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // SET TOOLBAR
        setSupportActionBar(binding.appBarMain.toolbar);

        // SET LAYOUT
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_general, R.id.nav_phase, R.id.nav_power,
                R.id.nav_pv, R.id.nav_settings, R.id.nav_status, R.id.nav_storage)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // viewModel
        this.viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // call updateUi() if data within Model changes
        this.viewModel.data.observe(this, this::updateUi);

        // UPDATE-BUTTON
        binding.appBarMain.inputBtnUpdate.setOnClickListener(view -> {
            Snackbar.make(view, "Values will be updated!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .setAnchorView(R.id.input_btn_update).show();

            // call fetchData on ViewModel if Fragment is DataFragment and View is ApiData
            Fragment targetFragment = Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main)).getChildFragmentManager().getFragments().get(0);
            if (targetFragment != null) {

                if (targetFragment instanceof DataFragment) {
                    ViewModel targetViewModel = ((DataFragment) targetFragment).getViewModel();
                    if (targetViewModel instanceof DataModel) {
                        ((DataModel) targetViewModel).fetchData();
                    }

                } else if (targetFragment instanceof SettingsFragment) {
                    ((SettingsFragment) targetFragment).updateSettings();

                    this.viewModel.fetchData();
                }
            }
        });
    }

    private void updateUi(HeaderData headerData) {
        ((TextView)findViewById(R.id.out_txt_sn)).setText(headerData.sn);
        ((TextView)findViewById(R.id.out_txt_pn)).setText(headerData.pn);
        ((TextView)findViewById(R.id.out_txt_model)).setText(headerData.model);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}