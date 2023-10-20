package com.example.EnergyBilling.Repository;

import com.example.EnergyBilling.Model.Client;
import com.example.EnergyBilling.Model.Consumption;
import com.example.EnergyBilling.Model.ParticularClient;
import com.example.EnergyBilling.Model.ProClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class LoadResources implements ILoadResources {

    public static final String clientsDataPath = "/clients.json";
    public static final String particularClientDataPath = "/particularClients.json";
    public static final String proClientDataPath = "/proClients.json";
    @Override
    public Client FindClientByReference(String reference) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TypeReference<List<Client>> typeReference = new TypeReference<>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream(clientsDataPath);
        List<Client> client = mapper.readValue(inputStream, typeReference);
        return client.stream().filter(c -> reference.equals(c.getReferenceClient()))
                .findAny()
                .orElse(null);
    }

    @Override
    public ParticularClient FindParticularClientByReference(String reference) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TypeReference<List<ParticularClient>> typeReference = new TypeReference<>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream(particularClientDataPath);
        List<ParticularClient> ParticularClientList = mapper.readValue(inputStream, typeReference);
        return ParticularClientList.stream().filter(c -> reference.equals(c.getReferenceClient()))
                .findAny()
                .orElse(null);
    }

    @Override
    public ProClient FindProClientByReference(String reference) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TypeReference<List<ProClient>> typeReference = new TypeReference<>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream(proClientDataPath);
        List<ProClient> proClientList = mapper.readValue(inputStream, typeReference);
        return proClientList.stream().filter(c -> reference.equals(c.getReferenceClient()))
                .findAny()
                .orElse(null);
    }
    public Consumption FindConsumptionByDate(List<Consumption> consumptionList, String month, String year) {
        return consumptionList.stream()
                .filter(c -> month.equals(c.getMonth()))
                .filter(c -> year.equals(c.getYear()))
                .findAny()
                .orElse(null);
    }
}
