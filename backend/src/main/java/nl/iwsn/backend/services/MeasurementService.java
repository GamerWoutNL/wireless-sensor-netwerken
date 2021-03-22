package nl.iwsn.backend.services;

import nl.iwsn.backend.model.smartmeter.SmartMeterData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {

    private final DatabaseService databaseService;

    public MeasurementService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    void createMeasurement(int hours) {
        double currentPower = databaseService.getCurrentPower();

        List<Double> bruh = databaseService.getPowerLastXHours(hours);

        // trend

        // Gemeten grootheden van dht

        // Status


    }
}
