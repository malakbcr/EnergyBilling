package com.example.EnergyBilling.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Config {
    String particularType;
    String professionalType;
    int ca;
}