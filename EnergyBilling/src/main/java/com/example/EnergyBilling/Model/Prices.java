package com.example.EnergyBilling.Model;

public record Prices(double ProInfGazPricing , double ProInfElectricityPricing, double ProSupGazPricing, double ProSupElectricity, double ParGazPricing, double ParElectricityPricing){
    public Prices() {
        this(0.113, 0.118, 0.111, 0.114, 0.115, 0.121);
    }
}

