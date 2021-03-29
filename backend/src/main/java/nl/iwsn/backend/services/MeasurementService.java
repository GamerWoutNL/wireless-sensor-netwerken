package nl.iwsn.backend.services;

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

    public MeasurementService(DatabaseService databaseService) {
        this.databaseService = databaseService;
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
        return measurement;
    }

    public double calculateHumidityTrend() {
        List<DhtData> dhtData = databaseService.getAllDhtData()
                .stream()
                .filter(dhtData1 -> dhtData1.getTimestamp().until(LocalDateTime.now(), ChronoUnit.MINUTES) <= 60)
                .collect(Collectors.toList());
        return ((double)dhtData.get(0).getHumidity() - dhtData.get(dhtData.size() - 1).getHumidity()) / (0 - (double)dhtData.indexOf(dhtData.get(dhtData.size() - 1)));
    }

    public double calculateTemperatureTrend() {
        List<DhtData> dhtData = databaseService.getAllDhtData()
                .stream()
                .filter(dhtData1 -> dhtData1.getTimestamp().until(LocalDateTime.now(), ChronoUnit.MINUTES) <= 60)
                .collect(Collectors.toList());
        return ((double)dhtData.get(0).getTemperature() - dhtData.get(dhtData.size() - 1).getTemperature()) / (0 - (double)dhtData.indexOf(dhtData.get(dhtData.size() - 1)));
    }

}
