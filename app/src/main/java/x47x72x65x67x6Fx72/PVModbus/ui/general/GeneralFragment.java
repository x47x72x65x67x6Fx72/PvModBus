package x47x72x65x67x6Fx72.PVModbus.ui.general;

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
import x47x72x65x67x6Fx72.PVModbus.databinding.FragmentGeneralBinding;
import x47x72x65x67x6Fx72.PVModbus.ui.DataFragment;
import x47x72x65x67x6Fx72.PVModbus.ui.dto.GeneralData;

public class GeneralFragment extends Fragment implements DataFragment {

    private FragmentGeneralBinding binding;

    private GeneralViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentGeneralBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = new ViewModelProvider(this).get(GeneralViewModel.class);

        // call updateUi() if data within Model changes
        this.viewModel.data.observe(getViewLifecycleOwner(), this::updateUi);

        // call pushMsg() if fault/notification within Model got detected
        this.viewModel.notify.observe(getViewLifecycleOwner(), this::pushMsg);
    }

    private void updateUi(GeneralData generalData) {
        this.binding.outTxtModelId.setText(generalData.model_id);

        this.binding.outTxtNumberOfPvStrings.setText(generalData.number_of_pv_strings);
        this.binding.outTxtNumberOfMppTrackers.setText(generalData.number_of_mpp_trackers);

        this.binding.outTxtRatedPower.setText(generalData.rated_power);
        this.binding.outTxtMaximumActivePower.setText(generalData.maximum_active_power);
        this.binding.outTxtMaximumApparentPower.setText(generalData.maximum_apparent_power);
        this.binding.outTxtMaximumReactivePowerFedToGrid.setText(generalData.maximum_reactive_power_fed_to_grid);
        this.binding.outTxtMaximumReactivePowerAbsorbedFromGrid.setText(generalData.maximum_reactive_power_absorbed_from_grid);

        this.binding.outTxtAccumulatedEnergyYield.setText(generalData.accumulated_energy_yield);
        this.binding.outTxtDailyEnergyYield.setText(generalData.daily_energy_yield);
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
