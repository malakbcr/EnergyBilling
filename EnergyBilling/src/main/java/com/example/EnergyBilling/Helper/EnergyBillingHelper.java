package com.example.EnergyBilling.Helper;

public final class EnergyBillingHelper {
    public static Boolean isClientReferenceValid(String referenceClient) {
        String numericPart = referenceClient.substring(3,11);
        if (referenceClient.indexOf("EKW") == 0 && Character.isDigit(Integer.parseInt(numericPart)) && referenceClient.length() == 11) {
            return Boolean.TRUE;
        }
        return false;
    }
}
