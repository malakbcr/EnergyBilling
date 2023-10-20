package com.example.EnergyBilling.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticularClient extends Client {
    public String referenceClient;
    public String clientType;
    public Civility civility;
    public String firstName;
    public String LastName;
    public List<Consumption> consumptions;
}