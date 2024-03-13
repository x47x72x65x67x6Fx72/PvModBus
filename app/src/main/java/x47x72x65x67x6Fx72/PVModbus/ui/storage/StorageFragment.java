package x47x72x65x67x6Fx72.PVModbus.ui.storage;

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
import x47x72x65x67x6Fx72.PVModbus.databinding.FragmentStorageBinding;
import x47x72x65x67x6Fx72.PVModbus.ui.DataFragment;
import x47x72x65x67x6Fx72.PVModbus.ui.dto.StorageData;

public class StorageFragment extends Fragment implements DataFragment {
    private FragmentStorageBinding binding;
    private StorageViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentStorageBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = new ViewModelProvider(this).get(StorageViewModel.class);

        // call updateUi() if data within Model changes
        this.viewModel.data.observe(getViewLifecycleOwner(), this::updateUi);

        // call pushMsg() if fault/notification within Model got detected
        this.viewModel.notify.observe(getViewLifecycleOwner(), this::pushMsg);
    }

    private void updateUi(StorageData storageData) {
        this.binding.outTxtRunningStatus.setText(storageData.running_status);
        this.binding.outTxtChargeAndDischargePower.setText(storageData.charge_and_discharge_power);
        this.binding.outTxtCurrentDayChargeCapacity.setText(storageData.current_day_charge_capacity);
        this.binding.outTxtCurrentDayDischargeCapacity.setText(storageData.current_day_discharge_capacity);
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
