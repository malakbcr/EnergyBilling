package com.example.EnergyBilling.Repository;

import com.example.EnergyBilling.Model.Client;
import com.example.EnergyBilling.Model.Consumption;
import com.example.EnergyBilling.Model.ParticularClient;
import com.example.EnergyBilling.Model.ProClient;

import java.io.IOException;
import java.util.List;

public interface ILoadResources {
    Client findClientByReference(String reference) throws IOException;

    ParticularClient findParticularClientByReference(String reference) throws IOException;

    ProClient findProClientByReference(String reference) throws IOException;

    Consumption findConsumptionByDate(List<Consumption> consumptionList, String month, String year);
}