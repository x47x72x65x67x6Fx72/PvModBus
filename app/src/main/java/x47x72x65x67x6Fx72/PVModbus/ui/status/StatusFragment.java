package x47x72x65x67x6Fx72.PVModbus.ui.status;

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
import x47x72x65x67x6Fx72.PVModbus.databinding.FragmentStatusBinding;
import x47x72x65x67x6Fx72.PVModbus.ui.DataFragment;
import x47x72x65x67x6Fx72.PVModbus.ui.dto.StatusData;

public class StatusFragment extends Fragment implements DataFragment {
    private FragmentStatusBinding binding;
    private StatusViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentStatusBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = new ViewModelProvider(this).get(StatusViewModel.class);

        // call updateUi() if data within Model changes
        this.viewModel.data.observe(getViewLifecycleOwner(), this::updateUi);

        // call pushMsg() if fault/notification within Model got detected
        this.viewModel.notify.observe(getViewLifecycleOwner(), this::pushMsg);
    }

    private void updateUi(StatusData statusData) {
        this.binding.outTxtState1.setText(statusData.state_1);
        this.binding.outTxtState2.setText(statusData.state_2);
        this.binding.outTxtState3.setText(statusData.state_3);

        this.binding.outTxtAlarm1.setText(statusData.alarm_1);
        this.binding.outTxtAlarm2.setText(statusData.alarm_2);
        this.binding.outTxtAlarm3.setText(statusData.alarm_3);

        this.binding.outTxtDeviceStatus.setText(statusData.device_status);
        this.binding.outTxtFaultCode.setText(statusData.fault_code);
        this.binding.outTxtStartupTime.setText(statusData.startup_time);
        this.binding.outTxtShutdownTime.setText(statusData.shutdown_time);
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
