package nl.iwsn.backend.api;

import nl.iwsn.backend.model.dht.DhtData;
import nl.iwsn.backend.model.smartmeter.Measurement;
import nl.iwsn.backend.model.smartmeter.SmartMeterData;
import nl.iwsn.backend.services.DatabaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class TestController {

    private final DatabaseService databaseService;

    public TestController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("dht")
    public List<DhtData> getAllDhtData() {
        return this.databaseService.getAllDhtData();
    }

    @GetMapping("smartmeter")
    public List<SmartMeterData> getAllSmartMeterData() {
        return this.databaseService.getAllSmartMeterData();
    }

    @GetMapping("currentpower")
    public Double getWoutPower() {
        return this.databaseService.getCurrentPower();
    }

    @GetMapping("getlasthours/{hours}")
    public List<Double> getLastHours(@PathVariable int hours) {
        return this.databaseService.getPowerLastXHours(hours);
    }
}
