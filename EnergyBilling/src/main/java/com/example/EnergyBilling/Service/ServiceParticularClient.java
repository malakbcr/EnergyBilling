package com.example.EnergyBilling.Service;

import com.example.EnergyBilling.Model.Consumption;
import com.example.EnergyBilling.Model.ParticularClient;
import com.example.EnergyBilling.Model.Prices;
import com.example.EnergyBilling.Repository.ILoadResources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ServiceParticularClient implements IServiceClientManager{
    private final ILoadResources loadResource;
    @Autowired
    public ServiceParticularClient(ILoadResources loadResource) {
        this.loadResource = loadResource;
    }

    @Override
    public double GetClientBill(String reference, String month, String year) throws IOException {
        double total = 0;
        ParticularClient particularClient = loadResource.FindParticularClientByReference(reference);
        if (particularClient != null) {
            Consumption consumption = loadResource.FindConsumptionByDate(particularClient.getConsumptions(), month, year);
            if (consumption != null) {
                Prices prices = new Prices();
                total = consumption.getConsumptionGaz() * prices.ParGazPricing() +
                        consumption.getConsumptionElectricity() * prices.ParElectricityPricing();
            }
        }
        return total;
    }
}
