package com.example.EnergyBilling.Repository;

import com.example.EnergyBilling.Model.Client;
import com.example.EnergyBilling.Model.Consumption;
import com.example.EnergyBilling.Model.ParticularClient;
import com.example.EnergyBilling.Model.ProClient;

import java.io.IOException;
import java.util.List;

public interface ILoadResources {
    Client FindClientByReference(String reference) throws IOException;

    ParticularClient FindParticularClientByReference(String reference) throws IOException;

    ProClient FindProClientByReference(String reference) throws IOException;

    Consumption FindConsumptionByDate(List<Consumption> consumptionList, String month, String year);
}