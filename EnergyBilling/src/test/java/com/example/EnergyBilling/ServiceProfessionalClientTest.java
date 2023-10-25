package com.example.EnergyBilling;

import com.example.EnergyBilling.Configuration.ConfigHandler;
import com.example.EnergyBilling.Model.Config;
import com.example.EnergyBilling.Model.Consumption;
import com.example.EnergyBilling.Model.ProClient;
import com.example.EnergyBilling.Repository.LoadResources;
import com.example.EnergyBilling.Service.ServiceProfessionalClient;
import org.junit.jupiter.api.Assertions;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ServiceProfessionalClientTest {
    @Mock
    private ConfigHandler mockConfigHandler;
    @Mock
    private LoadResources mockLoadResource;

    @Autowired
    private ServiceProfessionalClient serviceProfessionalClient;
    static Config config;
    static ProClient proClientSup;
    static ProClient proClientInf;
    static Consumption consumption1;
    static Consumption consumption2;

    static List<Consumption> consumptions1 = new ArrayList<>();
    static List<Consumption> consumptions2 = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        serviceProfessionalClient = new ServiceProfessionalClient(mockLoadResource, mockConfigHandler);
        config = new Config("Particular","Professional",1000000);
        consumption1 = new Consumption(100,200,"10","2023");
        consumption2 = new Consumption(1000,2000,"10","2023");

        consumptions1.add(consumption1);
        consumptions2.add(consumption2);

        proClientInf = new ProClient("EKW12347411","Professional", "362 521 879 00034","Test1", 597853,consumptions1);
        proClientSup = new ProClient("EKW12345678","Professional", "385 800 125 07891","Test2", 1500123,consumptions2);
    }
    @ParameterizedTest
    @CsvSource({"EKW12345678,10,2023,336"})
    public void getProClientBillTest_Sup_OK(String reference,String month, String year,double expected) throws IOException {
        Mockito.when(mockLoadResource.findConsumptionByDate(any(),anyString(),anyString())).thenReturn(consumption2);
        Mockito.when(mockLoadResource.findProClientByReference(anyString())).thenReturn(proClientSup);
        Mockito.when(mockConfigHandler.loadConfig()).thenReturn(config);
        Double total = serviceProfessionalClient.getClientBill(reference, month, year);
        Assertions.assertEquals(expected, total);
    }


    @ParameterizedTest
    @CsvSource({"EKW12347411,10,2023,34.4"})
    public void getProClientBillTest_Inf_OK(String reference,String month,String year,double expected) throws IOException {
        Mockito.when(mockLoadResource.findConsumptionByDate(any(),anyString(),anyString())).thenReturn(consumption1);
        Mockito.when(mockLoadResource.findProClientByReference(anyString())).thenReturn(proClientInf);
        Mockito.when(mockConfigHandler.loadConfig()).thenReturn(config);
        Double total = serviceProfessionalClient.getClientBill(reference, month, year);
        Assertions.assertEquals(expected, total);
    }
}
