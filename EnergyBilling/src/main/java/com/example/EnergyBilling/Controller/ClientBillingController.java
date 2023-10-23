package com.example.EnergyBilling.Controller;

import com.example.EnergyBilling.Helper.EnergyBillingHelper;
import com.example.EnergyBilling.Model.Client;
import com.example.EnergyBilling.Repository.LoadResources;
import com.example.EnergyBilling.Service.IServiceClientManager;
import com.example.EnergyBilling.Service.ServiceClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> getBillingByReferenceAndDate(@PathVariable String reference, @PathVariable String month, @PathVariable String year) throws IOException {
        String response;
        if(EnergyBillingHelper.isClientReferenceValid(reference))
        {
            Client client = loadResource.findClientByReference(reference);
            assert client!= null;
            IServiceClientManager serviceClientManager = serviceClientFactory.getClientType(client.getClientType());
            double total = serviceClientManager.getClientBill(reference, month, year);
            response = "the consummation of client " + reference + "is" + total;
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The client reference is not valid !");
        }
    }
}