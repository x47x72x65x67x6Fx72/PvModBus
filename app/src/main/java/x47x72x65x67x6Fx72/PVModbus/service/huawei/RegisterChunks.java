package x47x72x65x67x6Fx72.PVModbus.service.huawei;

import java.util.HashMap;

/**
 * Groups Registers in chunks, that might be fetched in one go.
 */
public enum RegisterChunks {

    NAMES_CHUNK(new Registers[] {
            Registers.MODEL,
            Registers.SN,
            Registers.PN}),

    GENERAL_CHUNK(new Registers[] {
            Registers.MODEL_ID,
            Registers.NUMBER_OF_PV_STRINGS,
            Registers.NUMBER_OF_MPP_TRACKERS,
            Registers.RATED_POWER,
            Registers.MAXIMUM_ACTIVE_POWER,
            Registers.MAXIMUM_APPARENT_POWER,
            Registers.MAXIMUM_REACTIVE_POWER_FED_TO_THE_POWER_GRID,
            Registers.MAXIMUM_REACTIVE_POWER_ABSORBED_FROM_THE_POWER_GRID
    }),

    STATUS_CHUNK(new Registers[] {
            Registers.STATE1,
            Registers.STATE2,
            Registers.STATE3
    }),

    ALARMS_CHUNK(new Registers[] {
            Registers.ALARM1,
            Registers.ALARM2,
            Registers.ALARM3
    }),

    PV_CHUNK(new Registers[] {
            Registers.PV1_VOLTAGE,
            Registers.PV1_CURRENT,
            Registers.PV2_VOLTAGE,
            Registers.PV2_CURRENT,
            Registers.PV3_VOLTAGE,
            Registers.PV3_CURRENT,
            Registers.PV4_VOLTAGE,
            Registers.PV4_CURRENT
    }),

    PHASE_CHUNK(new Registers[] {
            Registers.INPUT_POWER,
            Registers.LINE_VOLTAGE_BETWEEN_PHASES_A_AND_B,
            Registers.LINE_VOLTAGE_BETWEEN_PHASES_B_AND_C,
            Registers.LINE_VOLTAGE_BETWEEN_PHASES_C_AND_A,
            Registers.PHASE_A_VOLTAGE,
            Registers.PHASE_B_VOLTAGE,
            Registers.PHASE_C_VOLTAGE,
            Registers.PHASE_A_CURRENT,
            Registers.PHASE_B_CURRENT,
            Registers.PHASE_C_CURRENT
    }),

    POWER_CHUNK(new Registers[] {
            Registers.PEAK_ACTIVE_POWER_OF_CURRENT_DAY,
            Registers.ACTIVE_POWER,
            Registers.REACTIVE_POWER,
            Registers.POWER_FACTOR,
            Registers.GRID_FREQUENCY,
            Registers.EFFICIENCY,
            Registers.INTERNAL_TEMPERATURE,
            Registers.INSULATION_RESISTANCE
    }),

    ADDITIONAL_CHUNK(new Registers[] {
            Registers.DEVICE_STATUS,
            Registers.FAULT_CODE,
            Registers.STARTUP_TIME,
            Registers.SHUTDOWN_TIME
    }),

    ENERGY_CHUNK(new Registers[] {
            Registers.ACCUMULATED_ENERGY_YIELD,
            Registers.DAILY_ENERGY_YIELD
    }),

    ADJUSTMENT_CHUNK_1(new Registers[] {
            Registers.ACTIVE_ADJUSTMENT_MODE,
            Registers.ACTIVE_ADJUSTMENT_VALUE,
            Registers.ACTIVE_ADJUSTMENT_COMMAND
    }),

    ADJUSTMENT_CHUNK_2(new Registers[] {
            Registers.REACTIVE_ADJUSTMENT_MODE,
            Registers.REACTIVE_ADJUSTMENT_VALUE,
            Registers.REACTIVE_ADJUSTMENT_COMMAND
    }),

    ENERGY_STORAGE_CHUNK_1(new Registers[] {
            Registers.FIRST_ENERGY_STORAGE_UNIT_RUNNING_STATUS,
            Registers.FIRST_ENERGY_STORAGE_UNIT_CHARGE_AND_DISCHARGE_POWER
    }),

    ENERGY_STORAGE_CHUNK_2(new Registers[] {
            Registers.FIRST_ENERGY_STORAGE_UNIT_CURRENT_DAY_CHARGE_CAPACITY,
            Registers.FIRST_ENERGY_STORAGE_UNIT_CURRENT_DAY_DISCHARGE_CAPACITY
    }),

    POWERMETER_CHUNK(new Registers[] {
            Registers.POWERMETER_COLLECTION_ACTIVE_POWER
    }),

    OPTIMIZER_CHUNK(new Registers[] {
            Registers.OPTIMIZER_TOTAL_NUMBER_OF_OPTIMIZERS,
            Registers.OPMIMIZER_NUMBER_OF_ONLINE_OPTIMIZERS,
            Registers.OPTIMIZER_FEATURE_DATA
    });

    private final Registers[] registers;

    private final int chunkWidth;

    /**
     * Standard Constructor
     *
     * @param registers Array of Registers that lay together
     */
    RegisterChunks(final Registers[] registers) {
        this.registers = registers;
        int count = 0;
        for (Registers register: registers) {
            count += (register.quantity * Registers.Constants.BYTES_PER_REGISTER);
        }
        this.chunkWidth = count;
    }

    public Registers[] getRegisters() {
        return registers;
    }


    /**
     * Will split given byte-array in order of Registers contained in chunk.
     * Will map split byte-array to Registers.
     *
     * @param data byte-array that needs to be separated and mapped
     * @return mapping
     * @throws IllegalArgumentException data doesn't contain enough entries
     */
    public HashMap<Registers, byte[]> splitChunkData(final byte[] data) throws IllegalArgumentException {

        if (data.length < this.chunkWidth) throw new IllegalArgumentException("Data doesn't fit required length!");

        HashMap<Registers, byte[]> map = new HashMap<>();

        int dataIndex = 0;  // where to start reading within data-array
        int registerWidth;  // how many bytes to read for current register
        int readOffset;     // how many bytes got read for current register
        for(Registers register: this.registers) {
            // how many registers X how many bytes per register
            registerWidth = register.quantity * Registers.Constants.BYTES_PER_REGISTER;
            byte[] read = new byte[registerWidth];

            // read current register data
            for(readOffset = 0; readOffset < registerWidth; readOffset++) {
                read[readOffset] = data[dataIndex + readOffset];
            }

            // add to map
            map.put(register, read.clone());

            // increment Index
            dataIndex += registerWidth;
        }

        return map;
    }

}
