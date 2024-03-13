package x47x72x65x67x6Fx72.PVModbus.service.huawei;

/**
 * Registers given by Solar_Inverter_Modbus_Interface_Definitions__V3.0_.pdf
 */
public enum Registers {
    //
    // READ ONLY Registers
    //
    MODEL(30000, 15, RegisterTypes.STR),
    SN(30015, 10, RegisterTypes.STR),
    PN(30025, 10, RegisterTypes.STR),


    MODEL_ID(30070, 1, RegisterTypes.U16),
    NUMBER_OF_PV_STRINGS(30071, 1, RegisterTypes.U16),
    NUMBER_OF_MPP_TRACKERS(30072, 1, RegisterTypes.U16),
    RATED_POWER(30073,2, RegisterTypes.U32),
    MAXIMUM_ACTIVE_POWER(30075, 2, RegisterTypes.U32),
    MAXIMUM_APPARENT_POWER(30077, 2, RegisterTypes.U32),
    MAXIMUM_REACTIVE_POWER_FED_TO_THE_POWER_GRID(30079, 2, RegisterTypes.I32),
    MAXIMUM_REACTIVE_POWER_ABSORBED_FROM_THE_POWER_GRID(30081, 2, RegisterTypes.I32),


    STATE1(32000, 1, RegisterTypes.BIT16),
    STATE2(32002, 1, RegisterTypes.BIT16),
    STATE3(32003, 2, RegisterTypes.BIT32),


    ALARM1(32008, 1, RegisterTypes.BIT16),
    ALARM2(32009, 1, RegisterTypes.BIT16),
    ALARM3(32010, 1, RegisterTypes.BIT16),


    PV1_VOLTAGE(32016, 1, RegisterTypes.I16),
    PV1_CURRENT(32017, 1, RegisterTypes.I16),
    PV2_VOLTAGE(32018, 1, RegisterTypes.I16),
    PV2_CURRENT(32019, 1, RegisterTypes.I16),
    PV3_VOLTAGE(32020, 1, RegisterTypes.I16),
    PV3_CURRENT(32021, 1, RegisterTypes.I16),
    PV4_VOLTAGE(32022, 1, RegisterTypes.I16),
    PV4_CURRENT(32023, 1, RegisterTypes.I16),


    INPUT_POWER(32064, 2, RegisterTypes.I32),
    LINE_VOLTAGE_BETWEEN_PHASES_A_AND_B(32066, 1, RegisterTypes.U16),
    LINE_VOLTAGE_BETWEEN_PHASES_B_AND_C(32067, 1, RegisterTypes.U16),
    LINE_VOLTAGE_BETWEEN_PHASES_C_AND_A(32068, 1, RegisterTypes.U16),
    PHASE_A_VOLTAGE(32069, 1, RegisterTypes.U16),
    PHASE_B_VOLTAGE(32070, 1, RegisterTypes.U16),
    PHASE_C_VOLTAGE(32071, 1, RegisterTypes.U16),
    PHASE_A_CURRENT(32072, 2, RegisterTypes.I32),
    PHASE_B_CURRENT(32074, 2, RegisterTypes.I32),
    PHASE_C_CURRENT(32076, 2, RegisterTypes.I32),


    PEAK_ACTIVE_POWER_OF_CURRENT_DAY(32078, 2, RegisterTypes.I32),
    ACTIVE_POWER(32080, 2, RegisterTypes.I32),
    REACTIVE_POWER(32082, 2, RegisterTypes.I32),
    POWER_FACTOR(32084, 1, RegisterTypes.U16),
    GRID_FREQUENCY(32085, 1, RegisterTypes.U16),
    EFFICIENCY(32086, 1, RegisterTypes.U16),
    INTERNAL_TEMPERATURE(32087, 1, RegisterTypes.U16),
    INSULATION_RESISTANCE(32088, 1, RegisterTypes.U16),


    DEVICE_STATUS(32089, 1, RegisterTypes.U16),
    FAULT_CODE(32090, 1, RegisterTypes.U16),
    STARTUP_TIME(32091, 2, RegisterTypes.U32),
    SHUTDOWN_TIME(32093, 2, RegisterTypes.U32),


    ACCUMULATED_ENERGY_YIELD(32106, 2, RegisterTypes.U32),
    DAILY_ENERGY_YIELD(32114, 2, RegisterTypes.U32),


    // ADJUSTMENT_DATA NEEDS TO BE READ ONCE AT A TIME!
    // CHUNK 1: 35300 - 35303
    // CHUNK 2: 35304 - 35306
    ACTIVE_ADJUSTMENT_MODE(35300, 1, RegisterTypes.U16),
    ACTIVE_ADJUSTMENT_VALUE(35301, 1, RegisterTypes.U32),
    ACTIVE_ADJUSTMENT_COMMAND(35303, 1, RegisterTypes.U16),
    REACTIVE_ADJUSTMENT_MODE(35304, 1, RegisterTypes.U16),
    REACTIVE_ADJUSTMENT_VALUE(35305, 1, RegisterTypes.U32),
    REACTIVE_ADJUSTMENT_COMMAND(35307, 1, RegisterTypes.U16),


    FIRST_ENERGY_STORAGE_UNIT_RUNNING_STATUS(37000, 1, RegisterTypes.U16),
    FIRST_ENERGY_STORAGE_UNIT_CHARGE_AND_DISCHARGE_POWER(37001, 2, RegisterTypes.I32),
    FIRST_ENERGY_STORAGE_UNIT_CURRENT_DAY_CHARGE_CAPACITY(37015, 2, RegisterTypes.U32),
    FIRST_ENERGY_STORAGE_UNIT_CURRENT_DAY_DISCHARGE_CAPACITY(37017, 2, RegisterTypes.U32),


    POWERMETER_COLLECTION_ACTIVE_POWER(37113, 2, RegisterTypes.I32),


    OPTIMIZER_TOTAL_NUMBER_OF_OPTIMIZERS(37200, 1, RegisterTypes.U16),
    OPMIMIZER_NUMBER_OF_ONLINE_OPTIMIZERS(37201, 1, RegisterTypes.U16),
    OPTIMIZER_FEATURE_DATA(37202,1, RegisterTypes.U16);


    //
    // READ WRITE Registers
    //
    //
    // NOT IMPLEMENTED - NOT USED!

    /**
     * Holds constants used in context of Registers
     */
    public static class Constants {
        public static final int BYTES_PER_REGISTER = 2; // std 2 bytes : 16 bit on sun2000
    }

    // stores address for modbus request
    public final short address;

    // stores the number of units for modbus request
    public final byte quantity;

    // references the type and according handling of type at register
    public final RegisterTypes type;

    /**
     * Standard Constructor
     *
     * @param address where Register start
     * @param quantity number of Register-Entries
     * @param type info how to parse/ interpret bytes
     */
    Registers(int address, int quantity, RegisterTypes type) {
        this.address = (short) (address & 0xFFFF);
        this.quantity = (byte) (quantity & 0xFF);
        this.type = type;
    }


}
