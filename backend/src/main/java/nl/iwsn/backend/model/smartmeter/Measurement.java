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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Measurement implements JsonDeserializer<Measurement> {

    @Expose
    @SerializedName("positive_active_energy_tariff_one_kwh")
    private double positiveActiveEnergyTariffOneKwh;

    @Expose
    @SerializedName("positive_active_energy_tariff_two_kwh")
    private double positiveActiveEnergyTariffTwoKwh;

    @Expose
    @SerializedName("negative_active_energy_tariff_one_kwh")
    private double negativeActiveEnergyTariffOneKwh;

    @Expose
    @SerializedName("negative_active_energy_tariff_two_kwh")
    private double negativeActiveEnergyTariffTwoKwh;

    @Expose
    @SerializedName("tariff_indicator")
    private int tariffIndicator;

    @Expose
    @SerializedName("instantaneous_positive_active_power_all_phases")
    private double instantaneousPositiveActivePowerAllPhases;

    @Expose
    @SerializedName("instantaneous_negative_active_power_all_phases")
    private double instantaneousNegativeActivePowerAllPhases;

    @Expose
    @SerializedName("power_failures_all_phases")
    private int powerFailuresAllPhases;

    @Expose
    @SerializedName("long_power_failures_all_phases")
    private int longPowerFailuresAllPhases;

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
    @SerializedName("instantaneous_positive_active_power_phase_one")
    private double instantaneousPositiveActivePowerPhaseOne;

    @Expose
    @SerializedName("instantaneous_positive_active_power_phase_two")
    private double instantaneousPositiveActivePowerPhaseTwo;

    @Expose
    @SerializedName("instantaneous_positive_active_power_phase_three")
    private double instantaneousPositiveActivePowerPhaseThree;

    @Expose
    @SerializedName("instantaneous_negative_active_power_phase_one")
    private double instantaneousNegativeActivePowerPhaseOne;

    @Expose
    @SerializedName("instantaneous_negative_active_power_phase_two")
    private double instantaneousNegativeActivePowerPhaseTwo;

    @Expose
    @SerializedName("instantaneous_negative_active_power_phase_three")
    private double instantaneousNegativeActivePowerPhaseThree;

    @Expose
    @SerializedName("timestamp")
    private LocalDateTime timestamp;

    @Override
    public Measurement deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        String payload = element.getAsString();

        // PARSING

        return Measurement.builder()
                .timestamp(LocalDateTime.now())
                .build();
    }

}
