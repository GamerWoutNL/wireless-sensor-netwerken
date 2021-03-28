package nl.iwsn.backend.services;

import com.mongodb.client.MongoClients;
import nl.iwsn.backend.model.smartmeter.Measurement;
import nl.iwsn.backend.repositories.DhtRepository;
import nl.iwsn.backend.repositories.SmartMeterRepository;
import nl.iwsn.backend.model.dht.DhtData;
import nl.iwsn.backend.model.smartmeter.SmartMeterData;
import org.apache.tomcat.jni.Local;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatabaseService {

    private final NextSequenceService nextSequenceService;
    private final DhtRepository dhtRepository;
    private final SmartMeterRepository smartMeterRepository;

    public DatabaseService(NextSequenceService nextSequenceService, DhtRepository dhtRepository, SmartMeterRepository smartMeterRepository) {
        this.nextSequenceService = nextSequenceService;
        this.dhtRepository = dhtRepository;
        this.smartMeterRepository = smartMeterRepository;
    }

    public void saveDhtData(DhtData data) {
        data.setUid(this.nextSequenceService.getNextSequence("dhtSequence"));
        this.dhtRepository.save(data);
    }

    public List<DhtData> getAllDhtData() {
        return this.dhtRepository.findAll();
    }

    public void saveSmartMeterData(SmartMeterData data) {
        data.setUid(this.nextSequenceService.getNextSequence("smartMeterSequence"));
        this.smartMeterRepository.save(data);
    }

    public List<SmartMeterData> getAllSmartMeterData() {
        return this.smartMeterRepository.findAll();
    }

    public double getCurrentPower() {
        return getAllSmartMeterData().get(getAllSmartMeterData().size() -1).getMeasurement().getInstantaneousPowerUsed();
    }

    public List<Double> getPowerLastXHours(int hours) {
        return smartMeterRepository.findPacketArrivedBetween(LocalDateTime.now().minus(hours, ChronoUnit.HOURS), LocalDateTime.now())
                .stream()
                .map(SmartMeterData::getMeasurement)
                .map(Measurement::getInstantaneousPowerUsed)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public double getTotalCost() {
        List<SmartMeterData> smartMeterData = smartMeterRepository.findAll();
        Collections.sort(smartMeterData);
        SmartMeterData lastSmartMeterData = smartMeterData.get(smartMeterData.size() - 1);
        // 0.22 is the average tarrif in the Netherlands
        return (lastSmartMeterData.getMeasurement().getEnergyUsedTariffOne() + lastSmartMeterData.getMeasurement().getEnergyUsedTariffTwo()) * 0.22;
    }

    public int getTemperature() {
        DhtData data = dhtRepository.findFirstByOrderByTimestampDesc();
        return data.getTemperature();
    }

    public int getHumidity() {
        DhtData data = dhtRepository.findFirstByOrderByTimestampDesc();
        return data.getHumidity();
    }
}
