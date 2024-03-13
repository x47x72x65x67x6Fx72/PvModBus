package x47x72x65x67x6Fx72.PVModbus.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import x47x72x65x67x6Fx72.PVModbus.GlobalSettings;
import x47x72x65x67x6Fx72.PVModbus.R;
import x47x72x65x67x6Fx72.PVModbus.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GlobalSettings config = GlobalSettings.getInstance();

        String[] splitIp = config.getIpAddress().split("\\.");
        this.binding.inputNumberIp0.setText(splitIp[0]);
        this.binding.inputNumberIp1.setText(splitIp[1]);
        this.binding.inputNumberIp2.setText(splitIp[2]);
        this.binding.inputNumberIp3.setText(splitIp[3]);

        this.binding.inputNumberPort.setText(String.valueOf(config.getPort()));
        this.binding.inputNumberUnitId.setText(String.valueOf(config.getUnitId()));
        this.binding.inputNumberTimeout.setText(String.valueOf(config.getTimeoutBeforeRequest()));
    }

    public void updateSettings() {
        GlobalSettings config = GlobalSettings.getInstance();

        try {
            config.setIpAddress(String.valueOf(this.binding.inputNumberIp0.getText()) + "."
                    + String.valueOf(this.binding.inputNumberIp1.getText()) + "."
                    + String.valueOf(this.binding.inputNumberIp2.getText()) + "."
                    + String.valueOf(this.binding.inputNumberIp3.getText()));

            config.setPort(Integer.parseInt(String.valueOf(this.binding.inputNumberPort.getText())));
            config.setUnitId((byte) Short.parseShort(String.valueOf(this.binding.inputNumberUnitId.getText())));
            config.setTimeoutBeforeRequest(Integer.parseInt(String.valueOf(this.binding.inputNumberTimeout.getText())));

        } catch (Exception e) {
            pushMsg("Something went wrong!");
        }
    }

    private void pushMsg(String msg) {
        Snackbar.make(requireView(), msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.input_btn_update).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }
}
