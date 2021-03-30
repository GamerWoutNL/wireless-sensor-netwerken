package nl.iwsn.backend.services;

import com.google.gson.Gson;
import nl.iwsn.backend.model.GlobalMeasurement;
import nl.iwsn.backend.model.dht.DhtData;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementService {

    private final DatabaseService databaseService;
    private final Gson gson;

    public MeasurementService(DatabaseService databaseService, Gson gson) {
        this.databaseService = databaseService;
        this.gson = gson;
    }

    public GlobalMeasurement createMeasurement(int hours) {
        GlobalMeasurement measurement = new GlobalMeasurement();
        measurement.setCurrentInstantaneousPowerUsed(databaseService.getCurrentPower());
        measurement.setPowerOverHours(databaseService.getPowerLastXHours(hours));
        measurement.setTotalCost(databaseService.getTotalCost());
        measurement.setTemperature(databaseService.getTemperature());
        measurement.setHumidity(databaseService.getHumidity());
        measurement.setSmartMeterStatus(databaseService.getSmartMeterStatus());
        measurement.setDhtStatus(databaseService.getDhtStatus());
        measurement.setHumidityTrend(calculateHumidityTrend());
        measurement.setTemperatureTrend(calculateTemperatureTrend());
        measurement.setTimestamp(LocalDateTime.now());
        return measurement;
    }

    public String getSerializedMeasurement(int hours) {
        return this.gson.toJson(this.createMeasurement(hours));
    }

    public double calculateHumidityTrend() {
        List<DhtData> dhtData = this.databaseService.getAllDhtData()
                .stream()
                .filter(dhtData1 -> dhtData1.getTimestamp().until(LocalDateTime.now(), ChronoUnit.MINUTES) <= 60)
                .collect(Collectors.toList());

        if (dhtData.size() == 0) {
            return 0.0;
        }

        return ((double)dhtData.get(0).getHumidity() - dhtData.get(dhtData.size() - 1).getHumidity()) / (0 - (double)dhtData.indexOf(dhtData.get(dhtData.size() - 1)));
    }

    public double calculateTemperatureTrend() {
        List<DhtData> dhtData = this.databaseService.getAllDhtData()
                .stream()
                .filter(dhtData1 -> dhtData1.getTimestamp().until(LocalDateTime.now(), ChronoUnit.MINUTES) <= 60)
                .collect(Collectors.toList());

        if (dhtData.size() == 0) {
            return 0.0;
        }
        
        return ((double)dhtData.get(0).getTemperature() - dhtData.get(dhtData.size() - 1).getTemperature()) / (0 - (double)dhtData.indexOf(dhtData.get(dhtData.size() - 1)));
    }

}
