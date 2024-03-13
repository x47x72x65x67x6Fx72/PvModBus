package x47x72x65x67x6Fx72.PVModbus.ui.phase;

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
import x47x72x65x67x6Fx72.PVModbus.databinding.FragmentPhaseBinding;
import x47x72x65x67x6Fx72.PVModbus.ui.DataFragment;
import x47x72x65x67x6Fx72.PVModbus.ui.dto.PhaseData;

public class PhaseFragment extends Fragment implements DataFragment {

    private FragmentPhaseBinding binding;
    private PhaseViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentPhaseBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = new ViewModelProvider(this).get(PhaseViewModel.class);

        // call updateUi() if data within Model changes
        this.viewModel.data.observe(getViewLifecycleOwner(), this::updateUi);

        // call pushMsg() if fault/notification within Model got detected
        this.viewModel.notify.observe(getViewLifecycleOwner(), this::pushMsg);
    }

    private void updateUi(PhaseData phaseData) {
        this.binding.outTxtInputPower.setText(phaseData.input_power);

        this.binding.outTxtLineVoltageBetweenAAndB.setText(phaseData.line_voltage_between_a_and_b);
        this.binding.outTxtLineVoltageBetweenBAndC.setText(phaseData.line_voltage_between_b_and_c);
        this.binding.outTxtLineVoltageBetweenCAndA.setText(phaseData.line_voltage_beween_c_and_a);

        this.binding.outTxtAVoltage.setText(phaseData.a_voltage);
        this.binding.outTxtBVoltage.setText(phaseData.b_voltage);
        this.binding.outTxtCVoltage.setText(phaseData.c_voltage);

        this.binding.outTxtACurrent.setText(phaseData.a_current);
        this.binding.outTxtBCurrent.setText(phaseData.b_current);
        this.binding.outTxtCCurrent.setText(phaseData.c_current);
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