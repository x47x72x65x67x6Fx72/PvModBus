package x47x72x65x67x6Fx72.PVModbus.service.huawei;

import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * Bitfield-structures given by Solar_Inverter_Modbus_Interface_Definitions__V3.0_.pdf
 * Holds several structures to parse Bitfields.
 */
public class HuaweiBitfields {
    public static class Alarm1 {
        public final boolean high_string_input_voltage;
        public final boolean dc_arc_fault;
        public final boolean string_reverse_connection;
        public final boolean string_current_backfeed;
        public final boolean abnormal_string_power;
        public final boolean afci_self_check_fail;
        public final boolean phase_wire_short_circuited_to_pe;
        public final boolean grid_loss;
        public final boolean grid_undervoltage;
        public final boolean grid_overvoltage;
        public final boolean grid_voltage_imbalance;
        public final boolean grid_overfrequency;
        public final boolean grid_underfrequency;
        public final boolean unstable_grid_frequency;
        public final boolean output_overcome;
        public final boolean output_dc_component_overhigh;

        public Alarm1(final short bitfield) {
            this.high_string_input_voltage = (bitfield & (1)) != 0;
            this.dc_arc_fault = (bitfield & (1 << 1)) != 0;
            this.string_reverse_connection = (bitfield & (1 << 2)) != 0;
            this.string_current_backfeed = (bitfield & (1 << 3)) != 0;
            this.abnormal_string_power = (bitfield & (1 << 4)) != 0;
            this.afci_self_check_fail = (bitfield & (1 << 5)) != 0;
            this.phase_wire_short_circuited_to_pe = (bitfield & (1 << 6)) != 0;
            this.grid_loss = (bitfield & (1 << 7)) != 0;
            this.grid_undervoltage = (bitfield & (1 << 8)) != 0;
            this.grid_overvoltage = (bitfield & (1 << 9)) != 0;
            this.grid_voltage_imbalance = (bitfield & (1 << 10)) != 0;
            this.grid_overfrequency = (bitfield & (1 << 11)) != 0;
            this.grid_underfrequency = (bitfield & (1 << 12)) != 0;
            this.unstable_grid_frequency = (bitfield & (1 << 13)) != 0;
            this.output_overcome = (bitfield & (1 << 14)) != 0;
            this.output_dc_component_overhigh = (bitfield & (1 << 15)) != 0;
        }

        @NonNull
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (high_string_input_voltage) builder.append("high_string_input_voltage\n");
            if (dc_arc_fault) builder.append("dc_arc_fault\n");
            if (string_reverse_connection) builder.append("string_reverse_connection\n");
            if (string_current_backfeed) builder.append("string_current_backfeed\n");
            if (abnormal_string_power) builder.append("abnormal_string_power\n");
            if (afci_self_check_fail) builder.append("afci_self_check_fail\n");
            if (phase_wire_short_circuited_to_pe) builder.append("phase_wire_short_circuited_to_pe\n");
            if (grid_loss) builder.append("grid_loss\n");
            if (grid_undervoltage) builder.append("grid_undervoltage\n");
            if (grid_overvoltage) builder.append("grid_overvoltage\n");
            if (grid_voltage_imbalance) builder.append("grid_voltage_imbalance\n");
            if (grid_overfrequency) builder.append("grid_overfrequency\n");
            if (grid_underfrequency) builder.append("grid_underfrequency\n");
            if (unstable_grid_frequency) builder.append("unstable_grid_frequency\n");
            if (output_overcome) builder.append("output_overcome\n");
            if (output_dc_component_overhigh) builder.append("output_dc_component_overhigh\n");

            if (builder.length() == 0) builder.append("none");
            return builder.toString().trim();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Alarm1 alarm1 = (Alarm1) o;
            return high_string_input_voltage == alarm1.high_string_input_voltage && dc_arc_fault == alarm1.dc_arc_fault && string_reverse_connection == alarm1.string_reverse_connection && string_current_backfeed == alarm1.string_current_backfeed && abnormal_string_power == alarm1.abnormal_string_power && afci_self_check_fail == alarm1.afci_self_check_fail && phase_wire_short_circuited_to_pe == alarm1.phase_wire_short_circuited_to_pe && grid_loss == alarm1.grid_loss && grid_undervoltage == alarm1.grid_undervoltage && grid_overvoltage == alarm1.grid_overvoltage && grid_voltage_imbalance == alarm1.grid_voltage_imbalance && grid_overfrequency == alarm1.grid_overfrequency && grid_underfrequency == alarm1.grid_underfrequency && unstable_grid_frequency == alarm1.unstable_grid_frequency && output_overcome == alarm1.output_overcome && output_dc_component_overhigh == alarm1.output_dc_component_overhigh;
        }

        @Override
        public int hashCode() {
            return Objects.hash(high_string_input_voltage, dc_arc_fault, string_reverse_connection, string_current_backfeed, abnormal_string_power, afci_self_check_fail, phase_wire_short_circuited_to_pe, grid_loss, grid_undervoltage, grid_overvoltage, grid_voltage_imbalance, grid_overfrequency, grid_underfrequency, unstable_grid_frequency, output_overcome, output_dc_component_overhigh);
        }
    }

    public static class Alarm2 {
        public final boolean abnormal_residual_current;
        public final boolean abnormal_grounding;
        public final boolean low_insulation_resistance;
        public final boolean overtemperature;
        public final boolean device_fault;
        public final boolean upgrade_failed_or_version_mismatch;
        public final boolean license_expired;
        public final boolean faulty_monitoring_unit;
        public final boolean faulty_power_collector;
        public final boolean battery_abnormal;
        public final boolean active_islanding;
        public final boolean passive_islanding;
        public final boolean transient_ac_overvoltage;
        public final boolean peripheral_port_short_circuit;
        public final boolean churn_output_overload;
        public final boolean abnormal_pv_module_configuration;

        public Alarm2(final short bitfield) {
            this.abnormal_residual_current = (bitfield & (1)) != 0;
            this.abnormal_grounding = (bitfield & (1 << 1)) != 0;
            this.low_insulation_resistance = (bitfield & (1 << 2)) != 0;
            this.overtemperature = (bitfield & (1 << 3)) != 0;
            this.device_fault = (bitfield & (1 << 4)) != 0;
            this.upgrade_failed_or_version_mismatch = (bitfield & (1 << 5)) != 0;
            this.license_expired = (bitfield & (1 << 6)) != 0;
            this.faulty_monitoring_unit = (bitfield & (1 << 7)) != 0;
            this.faulty_power_collector = (bitfield & (1 << 8)) != 0;
            this.battery_abnormal = (bitfield & (1 << 9)) != 0;
            this.active_islanding = (bitfield & (1 << 10)) != 0;
            this.passive_islanding = (bitfield & (1 << 11)) != 0;
            this.transient_ac_overvoltage = (bitfield & (1 << 12)) != 0;
            this.peripheral_port_short_circuit = (bitfield & (1 << 13)) != 0;
            this.churn_output_overload = (bitfield & (1 << 14)) != 0;
            this.abnormal_pv_module_configuration = (bitfield & (1 << 15)) != 0;
        }

        @NonNull
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (abnormal_residual_current) builder.append("abnormal_residual_current\n");
            if (abnormal_grounding) builder.append("abnormal_grounding\n");
            if (low_insulation_resistance) builder.append("low_insulation_resistance\n");
            if (overtemperature) builder.append("overtemperature\n");
            if (device_fault) builder.append("device_fault\n");
            if (upgrade_failed_or_version_mismatch) builder.append("upgrade_failed_or_version_mismatch\n");
            if (license_expired) builder.append("license_expired\n");
            if (faulty_monitoring_unit) builder.append("faulty_monitoring_unit\n");
            if (faulty_power_collector) builder.append("faulty_power_collector\n");
            if (battery_abnormal) builder.append("battery_abnormal\n");
            if (active_islanding) builder.append("active_islanding\n");
            if (passive_islanding) builder.append("passive_islanding\n");
            if (transient_ac_overvoltage) builder.append("transient_ac_overvoltage\n");
            if (peripheral_port_short_circuit) builder.append("peripheral_port_short_circuit\n");
            if (churn_output_overload) builder.append("churn_output_overload\n");
            if (abnormal_pv_module_configuration) builder.append("abnormal_pv_module_configuration\n");

            if (builder.length() == 0) builder.append("none");
            return builder.toString().trim();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Alarm2 alarm2 = (Alarm2) o;
            return abnormal_residual_current == alarm2.abnormal_residual_current && abnormal_grounding == alarm2.abnormal_grounding && low_insulation_resistance == alarm2.low_insulation_resistance && overtemperature == alarm2.overtemperature && device_fault == alarm2.device_fault && upgrade_failed_or_version_mismatch == alarm2.upgrade_failed_or_version_mismatch && license_expired == alarm2.license_expired && faulty_monitoring_unit == alarm2.faulty_monitoring_unit && faulty_power_collector == alarm2.faulty_power_collector && battery_abnormal == alarm2.battery_abnormal && active_islanding == alarm2.active_islanding && passive_islanding == alarm2.passive_islanding && transient_ac_overvoltage == alarm2.transient_ac_overvoltage && peripheral_port_short_circuit == alarm2.peripheral_port_short_circuit && churn_output_overload == alarm2.churn_output_overload && abnormal_pv_module_configuration == alarm2.abnormal_pv_module_configuration;
        }

        @Override
        public int hashCode() {
            return Objects.hash(abnormal_residual_current, abnormal_grounding, low_insulation_resistance, overtemperature, device_fault, upgrade_failed_or_version_mismatch, license_expired, faulty_monitoring_unit, faulty_power_collector, battery_abnormal, active_islanding, passive_islanding, transient_ac_overvoltage, peripheral_port_short_circuit, churn_output_overload, abnormal_pv_module_configuration);
        }
    }

    public static class Alarm3 {
        public final boolean optimizer_fault;
        public final boolean build_in_pid_operation_abnormal;
        public final boolean high_input_string_voltage_to_ground;
        public final boolean external_fan_abnormal;
        public final boolean battery_reverse_connection;
        public final boolean on_grid_off_grid_controller_abnormal;
        public final boolean pv_string_loss;
        public final boolean interenal_fan_abnormal;
        public final boolean dc_protection_unit_abnormal;

        public Alarm3(final short bitfield) {
            this.optimizer_fault = (bitfield & (1)) != 0;
            this.build_in_pid_operation_abnormal = (bitfield & (1 << 1)) != 0;
            this.high_input_string_voltage_to_ground = (bitfield & (1 << 2)) != 0;
            this.external_fan_abnormal = (bitfield & (1 << 3)) != 0;
            this.battery_reverse_connection = (bitfield & (1 << 4)) != 0;
            this.on_grid_off_grid_controller_abnormal = (bitfield & (1 << 5)) != 0;
            this.pv_string_loss = (bitfield & (1 << 6)) != 0;
            this.interenal_fan_abnormal = (bitfield & (1 << 7)) != 0;
            this.dc_protection_unit_abnormal = (bitfield & (1 << 8)) != 0;
        }

        @NonNull
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (optimizer_fault) builder.append("optimizer_fault\n");
            if (build_in_pid_operation_abnormal) builder.append("build_in_pid_operation_abnormal\n");
            if (high_input_string_voltage_to_ground) builder.append("high_input_string_voltage_to_ground\n");
            if (external_fan_abnormal) builder.append("external_fan_abnormal\n");
            if (battery_reverse_connection) builder.append("battery_reverse_connection\n");
            if (on_grid_off_grid_controller_abnormal) builder.append("on_grid_off_grid_controller_abnormal\n");
            if (pv_string_loss) builder.append("pv_string_loss\n");
            if (interenal_fan_abnormal) builder.append("interenal_fan_abnormal\n");
            if (dc_protection_unit_abnormal) builder.append("dc_protection_unit_abnormal\n");

            if (builder.length() == 0) builder.append("none");
            return builder.toString().trim();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Alarm3 alarm3 = (Alarm3) o;
            return optimizer_fault == alarm3.optimizer_fault && build_in_pid_operation_abnormal == alarm3.build_in_pid_operation_abnormal && high_input_string_voltage_to_ground == alarm3.high_input_string_voltage_to_ground && external_fan_abnormal == alarm3.external_fan_abnormal && battery_reverse_connection == alarm3.battery_reverse_connection && on_grid_off_grid_controller_abnormal == alarm3.on_grid_off_grid_controller_abnormal && pv_string_loss == alarm3.pv_string_loss && interenal_fan_abnormal == alarm3.interenal_fan_abnormal && dc_protection_unit_abnormal == alarm3.dc_protection_unit_abnormal;
        }

        @Override
        public int hashCode() {
            return Objects.hash(optimizer_fault, build_in_pid_operation_abnormal, high_input_string_voltage_to_ground, external_fan_abnormal, battery_reverse_connection, on_grid_off_grid_controller_abnormal, pv_string_loss, interenal_fan_abnormal, dc_protection_unit_abnormal);
        }
    }

    public static class State1 {
        public final boolean standby;
        public final boolean grid_connected;
        public final boolean grid_connected_normally;
        public final boolean grid_connection_with_derating_due_to_power_rationing;
        public final boolean grid_connection_with_derating_due_to_internal_causes_of_the_solar_inverter;
        public final boolean normal_stop;
        public final boolean stop_due_to_faults;
        public final boolean stop_due_to_power_rationing;
        public final boolean shutdown;
        public final boolean spot_check;

        public State1(final short bitfield) {
            this.standby = (bitfield & (1)) != 0;
            this.grid_connected = (bitfield & (1 << 1)) != 0;
            this.grid_connected_normally = (bitfield & (1 << 2)) != 0;
            this.grid_connection_with_derating_due_to_power_rationing = (bitfield & (1 << 3)) != 0;
            this.grid_connection_with_derating_due_to_internal_causes_of_the_solar_inverter = (bitfield & (1 << 4)) != 0;
            this.normal_stop = (bitfield & (1 << 5)) != 0;
            this.stop_due_to_faults = (bitfield & (1 << 6)) != 0;
            this.stop_due_to_power_rationing = (bitfield & (1 << 7)) != 0;
            this.shutdown = (bitfield & (1 << 8)) != 0;
            this.spot_check = (bitfield & (1 << 9)) != 0;
        }

        @NonNull
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (standby) builder.append("standby\n");
            if (grid_connected) builder.append("grid_connected\n");
            if (grid_connected_normally) builder.append("grid_connected_normally\n");
            if (grid_connection_with_derating_due_to_power_rationing) builder.append("grid_connection_with_derating_due_to_power_rationing\n");
            if (grid_connection_with_derating_due_to_internal_causes_of_the_solar_inverter) builder.append("grid_connection_with_derating_due_to_internal_causes_of_the_solar_inverter\n");
            if (normal_stop) builder.append("normal_stop\n");
            if (stop_due_to_faults) builder.append("stop_due_to_faults\n");
            if (stop_due_to_power_rationing) builder.append("stop_due_to_power_rationing\n");
            if (shutdown) builder.append("shutdown\n");
            if (spot_check) builder.append("spot_check\n");

            if (builder.length() == 0) builder.append("none");
            return builder.toString().trim();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State1 state1 = (State1) o;
            return standby == state1.standby && grid_connected == state1.grid_connected && grid_connected_normally == state1.grid_connected_normally && grid_connection_with_derating_due_to_power_rationing == state1.grid_connection_with_derating_due_to_power_rationing && grid_connection_with_derating_due_to_internal_causes_of_the_solar_inverter == state1.grid_connection_with_derating_due_to_internal_causes_of_the_solar_inverter && normal_stop == state1.normal_stop && stop_due_to_faults == state1.stop_due_to_faults && stop_due_to_power_rationing == state1.stop_due_to_power_rationing && shutdown == state1.shutdown && spot_check == state1.spot_check;
        }

        @Override
        public int hashCode() {
            return Objects.hash(standby, grid_connected, grid_connected_normally, grid_connection_with_derating_due_to_power_rationing, grid_connection_with_derating_due_to_internal_causes_of_the_solar_inverter, normal_stop, stop_due_to_faults, stop_due_to_power_rationing, shutdown, spot_check);
        }
    }

    public static class State2 {
        public final boolean locking_status_unlocked;
        public final boolean pv_connection_connected;
        public final boolean dsp_data_collection_yes;

        public State2(final short bitfield) {
            this.locking_status_unlocked = (bitfield & (1)) != 0;
            this.pv_connection_connected = (bitfield & (1 << 1)) != 0;
            this.dsp_data_collection_yes = (bitfield & (1 << 2)) != 0;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("locking_status_unlocked: ").append(locking_status_unlocked).append("\n");
            builder.append("pv_connection_connected: ").append(pv_connection_connected).append("\n");
            builder.append("dsp_data_collection_yes: ").append(dsp_data_collection_yes);

            return builder.toString().trim();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State2 state2 = (State2) o;
            return locking_status_unlocked == state2.locking_status_unlocked && pv_connection_connected == state2.pv_connection_connected && dsp_data_collection_yes == state2.dsp_data_collection_yes;
        }

        @Override
        public int hashCode() {
            return Objects.hash(locking_status_unlocked, pv_connection_connected, dsp_data_collection_yes);
        }
    }

    public static class State3 {
        public final boolean off_grid;
        public final boolean off_grid_switch_enabled;

        /**
         * @param bitfield documentation says 32bitfield for this!
         */
        public State3(final int bitfield) {
            this.off_grid = (bitfield & (1)) != 0;
            this.off_grid_switch_enabled = (bitfield & (1 << 1)) != 0;
        }

        @NonNull
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (off_grid) { builder.append("off_grid\n"); } else { builder.append("on_grid\n"); }
            if (off_grid_switch_enabled) { builder.append("off_grid_switch_enabled\n"); } else { builder.append("off_grid_switch_disenabled\n");}

            if (builder.length() == 0) builder.append("none");
            return builder.toString().trim();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State3 state3 = (State3) o;
            return off_grid == state3.off_grid && off_grid_switch_enabled == state3.off_grid_switch_enabled;
        }

        @Override
        public int hashCode() {
            return Objects.hash(off_grid, off_grid_switch_enabled);
        }
    }
}
