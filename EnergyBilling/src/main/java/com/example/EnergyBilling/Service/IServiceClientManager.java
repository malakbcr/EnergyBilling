package com.example.EnergyBilling.Service;

import java.io.IOException;

public interface IServiceClientManager {
    public double getClientBill(String reference, String month, String year) throws IOException;
}

