package com.example.EnergyBilling.Service;

import com.example.EnergyBilling.Configuration.IConfigHandler;
import com.example.EnergyBilling.Model.Consumption;
import com.example.EnergyBilling.Model.Prices;
import com.example.EnergyBilling.Model.ProClient;
import com.example.EnergyBilling.Repository.ILoadResources;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ServiceProfessionalClient implements IServiceClientManager {

    private final ILoadResources loadResource;
    private  final IConfigHandler configHandler;
    public ServiceProfessionalClient(ILoadResources loadResource, IConfigHandler configHandler)
    {
        this.loadResource = loadResource;
        this.configHandler = configHandler;
    }
    @Override
    public double getClientBill(String reference, String month, String year) throws IOException {
        int caValue = configHandler.loadConfig().getCa();
        double total = 0;
        ProClient proClient = loadResource.findProClientByReference(reference);
        if(proClient != null) {
            Consumption consumption = loadResource.findConsumptionByDate(proClient.getConsumptions(), month, year);
            if(consumption != null) {
                Prices prices = new Prices();
                if (proClient.getCa() < caValue) {
                    total = consumption.getConsumptionGaz() * prices.ProInfGazPricing() +
                            consumption.getConsumptionElectricity() * prices.ProInfElectricityPricing();
                } else if (proClient.getCa() > caValue) {
                    total = consumption.getConsumptionGaz() * prices.ProSupGazPricing() +
                            consumption.getConsumptionElectricity() * prices.ProSupElectricity();
                }
            }
        }
        return total;
    }
}