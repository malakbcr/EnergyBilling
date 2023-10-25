package com.example.EnergyBilling;

import com.example.EnergyBilling.Model.Civility;
import com.example.EnergyBilling.Model.Client;
import com.example.EnergyBilling.Model.Consumption;
import com.example.EnergyBilling.Model.ParticularClient;
import com.example.EnergyBilling.Repository.LoadResources;
import com.example.EnergyBilling.Service.ServiceParticularClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ServiceParticularClientTest {
    @Mock
    private LoadResources mockLoadResource;
    @Autowired
    private ServiceParticularClient particularClientService;
    static Client client;
    static ParticularClient particularClient;
    static Consumption consumption;
    static List<Consumption> consumptions = new ArrayList<>();
    @BeforeEach
    public void setUp() {
        particularClientService = new ServiceParticularClient(mockLoadResource);
        consumption = new Consumption(1000,2000,"10","2023");
        consumptions.add(consumption);
        particularClient = new ParticularClient("EKW12358251","Particular", Civility.Mister,"Malak","Chemingui",consumptions);
    }
    @ParameterizedTest
    @CsvSource({"EKW12358251,10,2023,351"})
    public void getParticularClientBillTest_OK(String reference, String month, String year, Double expected) throws IOException {
        Mockito.when(mockLoadResource.findConsumptionByDate(any(),anyString(),anyString())).thenReturn(consumption);
        Mockito.when(mockLoadResource.findParticularClientByReference(anyString())).thenReturn(particularClient);
        Double total = particularClientService.getClientBill(reference, month, year);
        assertEquals(expected, total);
    }
}
