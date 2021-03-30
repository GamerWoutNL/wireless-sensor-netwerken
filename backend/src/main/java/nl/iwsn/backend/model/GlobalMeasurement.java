package nl.iwsn.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalMeasurement {

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @JsonProperty("instantaneous_power_used")
    private double currentInstantaneousPowerUsed;

    @JsonProperty("power_over_hours")
    private List<Double> powerOverHours;

    @JsonProperty("total_cost")
    private double totalCost;

    @JsonProperty("temperature")
    private int temperature;

    @JsonProperty("humidity")
    private int humidity;

    @JsonProperty("smart_meter_status")
    private boolean smartMeterStatus;

    @JsonProperty("dht_status")
    private boolean dhtStatus;

    @JsonProperty("temperature_trend")
    private double temperatureTrend;

    @JsonProperty("humidity_trend")
    private double humidityTrend;

}
