package com.example.EnergyBilling.Configuration;

import com.example.EnergyBilling.Model.Config;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ConfigHandler implements IConfigHandler {
    public static final String configPath = "/config.json";

    @Override
    public Config loadConfig() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TypeReference<Config> typeReference = new TypeReference<>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream(configPath);
        return mapper.readValue(inputStream, typeReference);
    }
}
