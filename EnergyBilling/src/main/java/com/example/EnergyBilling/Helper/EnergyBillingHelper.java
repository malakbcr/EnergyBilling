package com.example.EnergyBilling.Helper;

 public final class EnergyBillingHelper {
     public static Boolean isClientReferenceValid(String referenceClient) {
         if (referenceClient.indexOf("EKW") == 0 && referenceClient.length() == 11) {
             return Boolean.TRUE;
         }
         return false;
     }
}
