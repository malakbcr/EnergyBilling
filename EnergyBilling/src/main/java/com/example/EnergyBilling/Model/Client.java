package com.example.EnergyBilling.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Client {
    public String referenceClient;
    public String clientType;
}

