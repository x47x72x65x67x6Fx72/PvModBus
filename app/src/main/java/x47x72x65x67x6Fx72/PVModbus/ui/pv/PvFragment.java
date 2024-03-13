package x47x72x65x67x6Fx72.PVModbus.ui.pv;

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
import x47x72x65x67x6Fx72.PVModbus.databinding.FragmentPvBinding;
import x47x72x65x67x6Fx72.PVModbus.ui.DataFragment;
import x47x72x65x67x6Fx72.PVModbus.ui.dto.PvData;

public class PvFragment extends Fragment implements DataFragment {

    private FragmentPvBinding binding;
    private PvViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentPvBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = new ViewModelProvider(this).get(PvViewModel.class);

        // call updateUi() if data within Model changes
        this.viewModel.data.observe(getViewLifecycleOwner(), this::updateUi);

        // call pushMsg() if fault/notification within Model got detected
        this.viewModel.notify.observe(getViewLifecycleOwner(), this::pushMsg);
    }

    private void updateUi(PvData pvData) {
        this.binding.outTxtPv1Voltage.setText(pvData.pv1_voltage);
        this.binding.outTxtPv1Current.setText(pvData.pv1_current);

        this.binding.outTxtPv2Voltage.setText(pvData.pv2_voltage);
        this.binding.outTxtPv2Current.setText(pvData.pv2_current);

        this.binding.outTxtPv3Voltage.setText(pvData.pv3_voltage);
        this.binding.outTxtPv3Current.setText(pvData.pv3_current);

        this.binding.outTxtPv4Voltage.setText(pvData.pv4_voltage);
        this.binding.outTxtPv4Current.setText(pvData.pv4_current);
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