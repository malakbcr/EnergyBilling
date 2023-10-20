package com.example.EnergyBilling.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consumption {
    float consumptionElectricity;
    float consumptionGaz;
    String month;
    String year;
}