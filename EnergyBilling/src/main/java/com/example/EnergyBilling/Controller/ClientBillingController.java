package com.example.EnergyBilling.Controller;

import com.example.EnergyBilling.Model.Client;
import com.example.EnergyBilling.Repository.LoadResources;
import com.example.EnergyBilling.Service.IServiceClientManager;
import com.example.EnergyBilling.Service.ServiceClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ClientBillingController {
    @Autowired
    private LoadResources loadResource;
    @Autowired
    private ServiceClientFactory serviceClientFactory;
    @GetMapping("/API/{reference}/{month}/{year}")
    public ResponseEntity<Double> getInvoiceByReferenceAndDate(@PathVariable String reference, @PathVariable String month, @PathVariable String year) throws IOException {
        Client client = loadResource.FindClientByReference(reference);
        assert client!= null;
        IServiceClientManager serviceClientManager = serviceClientFactory.GetClientType(client.getClientType());
        double total = serviceClientManager.GetClientBill(reference, month, year);
        return ResponseEntity.ok(total);
    }
}