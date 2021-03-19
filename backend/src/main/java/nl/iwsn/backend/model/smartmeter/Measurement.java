package nl.iwsn.backend.model.smartmeter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Measurement implements JsonDeserializer<Measurement> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmmss");

    @Expose
    @SerializedName("identification")
    private String identification;

    @Expose
    @SerializedName("dsmr_version")
    private String dsmrVersion;

    @Expose
    @SerializedName("timestamp")
    private ZonedDateTime timestamp;

    @Expose
    @SerializedName("equipment_identifier")
    private String equipmentIdentifier;

    @Expose
    @SerializedName("energy_used_tariff_one")
    private double energyUsedTariffOne;

    @Expose
    @SerializedName("energy_used_tariff_two")
    private double energyUsedTariffTwo;

    @Expose
    @SerializedName("energy_produced_tariff_one")
    private double energyProducedTariffOne;

    @Expose
    @SerializedName("energy_produced_tariff_two")
    private double energyProducedTariffTwo;

    @Expose
    @SerializedName("tariff_indicator")
    private int tariffIndicator;

    @Expose
    @SerializedName("instantaneous_power_used")
    private double instantaneousPowerUsed;

    @Expose
    @SerializedName("instantaneous_power_produced")
    private double instantaneousPowerProduced;

    @Expose
    @SerializedName("power_failures")
    private int powerFailures;

    @Expose
    @SerializedName("long_power_failures")
    private int longPowerFailures;

    @Expose
    @SerializedName("voltage_sags_phase_one")
    private int voltageSagsPhaseOne;

    @Expose
    @SerializedName("voltage_sags_phase_two")
    private int voltageSagsPhaseTwo;

    @Expose
    @SerializedName("voltage_sags_phase_three")
    private int voltageSagsPhaseThree;

    @Expose
    @SerializedName("voltage_swells_phase_one")
    private int voltageSwellsPhaseOne;

    @Expose
    @SerializedName("voltage_swells_phase_two")
    private int voltageSwellsPhaseTwo;

    @Expose
    @SerializedName("voltage_swells_phase_three")
    private int voltageSwellsPhaseThree;

    @Expose
    @SerializedName("instantaneous_voltage_phase_one")
    private double instantaneousVoltagePhaseOne;

    @Expose
    @SerializedName("instantaneous_voltage_phase_two")
    private double instantaneousVoltagePhaseTwo;

    @Expose
    @SerializedName("instantaneous_voltage_phase_three")
    private double instantaneousVoltagePhaseThree;

    @Expose
    @SerializedName("instantaneous_current_phase_one")
    private double instantaneousCurrentPhaseOne;

    @Expose
    @SerializedName("instantaneous_current_phase_two")
    private double instantaneousCurrentPhaseTwo;

    @Expose
    @SerializedName("instantaneous_current_phase_three")
    private double instantaneousCurrentPhaseThree;

    @Expose
    @SerializedName("instantaneous_power_used_phase_one")
    private double instantaneousPowerUsedPhaseOne;

    @Expose
    @SerializedName("instantaneous_power_used_phase_two")
    private double instantaneousPowerUsedPhaseTwo;

    @Expose
    @SerializedName("instantaneous_power_used_phase_three")
    private double instantaneousPowerUsedPhaseThree;

    @Expose
    @SerializedName("instantaneous_power_produced_phase_one")
    private double instantaneousPowerProducedPhaseOne;

    @Expose
    @SerializedName("instantaneous_power_produced_phase_two")
    private double instantaneousPowerProducedPhaseTwo;

    @Expose
    @SerializedName("instantaneous_power_produced_phase_three")
    private double instantaneousPowerProducedPhaseThree;

    @Expose
    @SerializedName("device_type")
    private String deviceType;

    @Expose
    @SerializedName("gas_equipment_identifier")
    private String gasEquipmentIdentifier;

    @Expose
    @SerializedName("gas_timestamp")
    private ZonedDateTime gasTimestamp;

    @Expose
    @SerializedName("gas_usage")
    private double gasUsage;

    @Override
    public Measurement deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        Measurement measurement = new Measurement();

        Scanner scanner = new Scanner(element.getAsString());
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int length = line.length();

            if (line.length() == 0) {
                continue;
            }

            if (line.startsWith("/")) {
                measurement.setIdentification(line.substring(1));

            } else if (line.startsWith("1-3:0.2.8")) {
                measurement.setDsmrVersion(line.substring(10, length - 1));

            } else if (line.startsWith("0-0:1.0.0")) {
                measurement.setTimestamp(readTimestamp(line.substring(10, Math.min(22, length - 1)), line.charAt(22)));

            } else if (line.startsWith("0-0:96.1.1")) {
                measurement.setEquipmentIdentifier(line.substring(11, length - 1));

            } else if (line.startsWith("1-0:1.8.1")) {
                measurement.setEnergyUsedTariffOne(Double.parseDouble(line.substring(10, length - 5)));

            } else if (line.startsWith("1-0:1.8.2")) {
                measurement.setEnergyUsedTariffTwo(Double.parseDouble(line.substring(10, length - 5)));

            } else if (line.startsWith("1-0:2.8.1")) {
                measurement.setEnergyProducedTariffOne(Double.parseDouble(line.substring(10, length - 5)));

            } else if (line.startsWith("1-0:2.8.2")) {
                measurement.setEnergyProducedTariffTwo(Double.parseDouble(line.substring(10, length - 5)));

            } else if (line.startsWith("0-0:96.14.0")) {
                measurement.setTariffIndicator(Integer.parseInt(line.substring(12, length - 1)));

            } else if (line.startsWith("1-0:1.7.0")) {
                measurement.setInstantaneousPowerUsed(Double.parseDouble(line.substring(10, length - 4)));

            } else if (line.startsWith("1-0:2.7.0")) {
                measurement.setInstantaneousPowerProduced(Double.parseDouble(line.substring(10, length - 4)));

            } else if (line.startsWith("0-0:96.7.21")) {
                measurement.setPowerFailures(Integer.parseInt(line.substring(12, length - 1)));

            } else if (line.startsWith("0-0:96.7.9")) {
                measurement.setLongPowerFailures(Integer.parseInt(line.substring(11, length - 1)));

            } else if (line.startsWith("1-0:32.32.0")) {
                measurement.setVoltageSagsPhaseOne(Integer.parseInt(line.substring(12, length - 1)));

            } else if (line.startsWith("1-0:52.32.0")) {
                measurement.setVoltageSagsPhaseTwo(Integer.parseInt(line.substring(12, length - 1)));

            } else if (line.startsWith("1-0:72.32.0")) {
                measurement.setVoltageSagsPhaseThree(Integer.parseInt(line.substring(12, length - 1)));

            } else if (line.startsWith("1-0:32.36.0")) {
                measurement.setVoltageSwellsPhaseOne(Integer.parseInt(line.substring(12, length - 1)));

            } else if (line.startsWith("1-0:52.36.0")) {
                measurement.setVoltageSwellsPhaseTwo(Integer.parseInt(line.substring(12, length - 1)));

            } else if (line.startsWith("1-0:72.36.0")) {
                measurement.setVoltageSwellsPhaseThree(Integer.parseInt(line.substring(12, length - 1)));

            } else if (line.startsWith("1-0:32.7.0")) {
                measurement.setInstantaneousVoltagePhaseOne(Double.parseDouble(line.substring(11, length - 3)));

            } else if (line.startsWith("1-0:52.7.0")) {
                measurement.setInstantaneousVoltagePhaseTwo(Double.parseDouble(line.substring(11, length - 3)));

            } else if (line.startsWith("1-0:72.7.0")) {
                measurement.setInstantaneousVoltagePhaseThree(Double.parseDouble(line.substring(11, length - 3)));

            } else if (line.startsWith("1-0:31.7.0")) {
                measurement.setInstantaneousCurrentPhaseOne(Double.parseDouble(line.substring(11, length - 3)));

            } else if (line.startsWith("1-0:51.7.0")) {
                measurement.setInstantaneousCurrentPhaseTwo(Double.parseDouble(line.substring(11, length - 3)));

            } else if (line.startsWith("1-0:71.7.0")) {
                measurement.setInstantaneousCurrentPhaseThree(Double.parseDouble(line.substring(11, length - 3)));

            } else if (line.startsWith("1-0:21.7.0")) {
                measurement.setInstantaneousPowerUsedPhaseOne(Double.parseDouble(line.substring(11, length - 4)));

            } else if (line.startsWith("1-0:41.7.0")) {
                measurement.setInstantaneousPowerUsedPhaseTwo(Double.parseDouble(line.substring(11, length - 4)));

            } else if (line.startsWith("1-0:61.7.0")) {
                measurement.setInstantaneousPowerUsedPhaseThree(Double.parseDouble(line.substring(11, length - 4)));

            } else if (line.startsWith("1-0:22.7.0")) {
                measurement.setInstantaneousPowerProducedPhaseOne(Double.parseDouble(line.substring(11, length - 4)));

            } else if (line.startsWith("1-0:42.7.0")) {
                measurement.setInstantaneousPowerProducedPhaseTwo(Double.parseDouble(line.substring(11, length - 4)));

            } else if (line.startsWith("1-0:62.7.0")) {
                measurement.setInstantaneousPowerProducedPhaseThree(Double.parseDouble(line.substring(11, length - 4)));

            } else if (line.startsWith("0-1:24.1.0")) {
                measurement.setDeviceType(line.substring(11, length - 1));

            } else if (line.startsWith("0-1:96.1.0")) {
                measurement.setGasEquipmentIdentifier(line.substring(11, length - 1));

            } else if (line.startsWith("0-1:24.2.1")) {
                measurement.setGasTimestamp(readTimestamp(line.substring(11, 23), line.charAt(23)));
                measurement.setGasUsage(Double.parseDouble(line.substring(26, length - 4)));
            }

        }
        scanner.close();

        return measurement;
    }

    private ZonedDateTime readTimestamp(String timestampString, char daylightSaving) {
        try {
            LocalDateTime timestamp = LocalDateTime.parse(timestampString, DATE_TIME_FORMATTER);
            String zonedTimestamp = timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            if (daylightSaving == 'W') {
                zonedTimestamp += "+01:00";
            } else if (daylightSaving == 'S') {
                zonedTimestamp += "+02:00";
            } else {
                zonedTimestamp += "+00:00";
            }
            return ZonedDateTime.parse(zonedTimestamp, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
