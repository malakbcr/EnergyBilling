# EnergyBilling
## Model
The model package contains the business entities of the application:

*Client: object representing a client.  
*ProClient: object representing the details of a professional client herited from client class.  
*ParticularClient: object representing the details of a particular client herited from client class.  
*Consumption: object representing the details of a client's consumption.  
*Civility: civility of client (Miss or Mister).  
*Prices: a record class that stores the prices of gaz and electricity for particular and professional client.  
*Config: object representing the resources that the project need for retrieving values of ca and Type of client.

## Service
The services layer of the application contains model's uses case.

## Repository
The repository package contains the persistence layer for retrieving clients details for testing and uses Cruds endpoints.
### Remark : I used jsons files for retrieving resource, we can replaces that with a NoSql database.

## Helper
Helper package contains EnergyBillingHelper that helping for testing the customer reference.  

## Configuration
Configuration package contains ConfigHandler for retrieving the resources from JSONS files (Customer Types and Ca Value).


