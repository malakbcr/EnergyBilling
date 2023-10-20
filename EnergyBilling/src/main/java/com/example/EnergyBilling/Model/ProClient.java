package com.example.EnergyBilling.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProClient extends Client {
    public String clientType = "ProPricing";
    public String referenceClient;
    public String socialReason;
    public String siretNumber;
    public float ca;
    public List<Consumption> consumptions;

    public ProClient(String reference, String number, String test, int i, Consumption consumption1) {
    }
}