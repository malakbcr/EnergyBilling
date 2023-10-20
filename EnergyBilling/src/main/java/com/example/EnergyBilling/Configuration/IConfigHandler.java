package com.example.EnergyBilling.Configuration;

import com.example.EnergyBilling.Model.Config;

import java.io.IOException;

public interface IConfigHandler {
    Config loadConfig() throws IOException;
}
