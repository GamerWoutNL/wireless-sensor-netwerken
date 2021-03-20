package nl.iwsn.backend.services;

import nl.iwsn.backend.repositories.DhtRepository;
import nl.iwsn.backend.repositories.SmartMeterRepository;
import nl.iwsn.backend.model.dht.DhtData;
import nl.iwsn.backend.model.smartmeter.SmartMeterData;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
