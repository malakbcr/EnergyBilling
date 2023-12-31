package com.example.EnergyBilling.Service;

import com.example.EnergyBilling.Configuration.IConfigHandler;
import com.example.EnergyBilling.Model.Config;
import com.example.EnergyBilling.Repository.ILoadResources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class ServiceClientFactory {
    @Autowired
    private IConfigHandler configHandler;
    @Autowired
    private ILoadResources loadResource;
    public IServiceClientManager getClientType(String clientType) throws IOException {
        Config config = this.configHandler.loadConfig();
        IServiceClientManager ServiceClientManager = null;
        if(Objects.equals(clientType, config.getParticularType()))
        {
            ServiceClientManager = new ServiceParticularClient(loadResource);
            log.warn("Particular customer retrieved!");
            return ServiceClientManager;
        } else if (Objects.equals(clientType, config.getProfessionalType())) {
            ServiceClientManager = new ServiceProfessionalClient(loadResource,configHandler);
            log.warn("Professional customer retrieved!");
            return ServiceClientManager;
        }
        return ServiceClientManager;
    }
}

