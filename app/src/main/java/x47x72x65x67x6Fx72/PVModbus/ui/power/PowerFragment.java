package x47x72x65x67x6Fx72.PVModbus.ui.power;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import x47x72x65x67x6Fx72.PVModbus.R;
import x47x72x65x67x6Fx72.PVModbus.databinding.FragmentPowerBinding;
import x47x72x65x67x6Fx72.PVModbus.ui.DataFragment;
import x47x72x65x67x6Fx72.PVModbus.ui.dto.PowerData;

public class PowerFragment extends Fragment implements DataFragment {

    private FragmentPowerBinding binding;

    private PowerViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentPowerBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = new ViewModelProvider(this).get(PowerViewModel.class);

        // call updateUi() if data within Model changes
        this.viewModel.data.observe(getViewLifecycleOwner(), this::updateUi);

        // call pushMsg() if fault/notification within Model got detected
        this.viewModel.notify.observe(getViewLifecycleOwner(), this::pushMsg);
    }
    private void updateUi(PowerData powerData) {
        this.binding.outTxtPeakActivePower.setText(powerData.peak_active_power);
        this.binding.outTxtActivePower.setText(powerData.active_power);
        this.binding.outTxtReactivePower.setText(powerData.reactive_power);

        this.binding.outTxtPowerFactor.setText(powerData.power_factor);
        this.binding.outTxtGridFrequency.setText(powerData.grid_frequency);
        this.binding.outTxtEfficiency.setText(powerData.efficiency);
        this.binding.outTxtInternalTemperature.setText(powerData.internal_temperature);
        this.binding.outTxtInsulationResistance.setText(powerData.insulation_resistance);

        this.binding.outTxtPowermeterCollectionActivePower.setText(powerData.powermeter_collection_active_power);
    }

    private void pushMsg(String msg) {
        Snackbar.make(requireView(), msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.input_btn_update).show();
    }

    public ViewModel getViewModel() {
        return this.viewModel;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
        this.viewModel = null;
    }
}