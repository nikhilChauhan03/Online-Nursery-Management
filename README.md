
<h1 align="center"> ⚡️ FLORA FORT ⚡️</h1>


![Logo](https://user-images.githubusercontent.com/105915351/201520600-c9177661-043b-4be2-8568-0049ecc489b6.png)


### Link: https://drive.google.com/file/d/1F8R8e6eU3rrtEPq5xuSFkN33GKvMDMb4/view?usp=sharing 


## Acknowledgements:

[FLORA FORT is a online garden store with vast collection of plants, seeds and garden accessories.A one-stop-shop for all gardening related requirements, FLORA FORT has more than 6000 products available online for delivery across India saving you numerous messy trips to various nurseries.We cater to all kinds of gardening needs ranging from plants, pots, tools, to curated plant-scaping solutions. Our ever-growing platform integrates nurseries and customers across India. ]()
## Functions:

### Base:
- FloraFortApplication

### Controller:
- AdminController
- CustomerController
- OrderController
- PlantController
- PlanterController
- SeedController

### Repository:
- AdminRepository
- CustomerRepository
- CurrentUserSessionRepository
- OrderDao
- PlanterRepo
- PlantRepo
- SeedRepository

### Service:
- AdminService
- AdminServiceImpl
- CustomerService
- CustomerServiceImpl
- OrderService
- OrderServiceImpl
- PlanterService
- PlanterServiceImpl
- PlantService
- PlantServiceImpl
- SeedService
- SeedServiceImpl

### Model:
- Admin
- Customer
- CurrentUserSession
- Order
- Plant 
- Planter 
- Seed

### DTO:
- LoginDTO
- LogoutDTO

### Exceptions:
- AdminException
- CustomerException
- GlobalErrorException
- MyErrorDetails
- OrderException
- PlanterException
- PlantException
- SeedException

## Teck Stacks:
- Java
- Hibernate
- SpringBoot
- REST API
- Lombok
- SQL
- Swagger
- Postman

## Class method design:
![image](https://user-images.githubusercontent.com/104348363/201666308-6e5b0b4a-2193-4eac-943f-f6cda668431f.png)

## Class module design:
![image](https://user-images.githubusercontent.com/104348363/201664014-a1eb958f-0986-47e0-8c5d-16c760ba5113.png)

##  ER Diagram:
![alt text](https://user-images.githubusercontent.com/105915351/201520484-d274a422-21c2-4de0-afb7-d9e192ea6378.jpg)

## Restrictions:
- Before performing any task the user should be confirmed whether the user is Admin or Customer and for that the user should have logged in.<br/>
- For each contoller we need to provide the login details for implementation of particular methods i.e, if the customer wants to purchase some planter then he/she should give his/her correct name during purchasing session and only customer can perform that action and for that One to Many relationship is established.
- If we want to add some plants or seeds then only admin can do that so here we have provided the validation i.e during adding a particular item  user should give the correct name of the admin otherwise it will throw an exception and that exception is properly handled.


## Team members & contributions:
[click to see GitHub profile]




1. [Nikhil Chauhan](https://github.com/NLucifer03)(Teamlead)
- AdminController.java
- CustomerController.java
- AdminRepository.java
- CustomerRepository.java
- CurrentUserSessionRepository.java
- LoginDTO.java
- LogoutDTO.java

2. [Subojit Mukherjee](https://github.com/subo8083)
- PlantController.java
- PlantRepo.java
- PlantService
- PlantServiceImpl
- Plant.java
- PlantException

3. [Atabur Rahaman Mollah](https://github.com/Ataburjee)
- SeedController.java
- SeedRepository.java
- SeedService.java
- SeedServiceImpl.java
- Seed.java
- SeedException.java

4. [Sudarshan Shinde](https://github.com/sudarshan1309)
- OrderController.java
- OrderDao.java
- OrderService.java
- OrderServiceImpl.java
- Order.java
- OrderException.java

5. [Pankaj Sharma](https://github.com/Pankajsharma8221)
- PlanterController.java
- PlanterRepo.java
- PlanterService.java
- PlanterServiceImpl.java
- Planter.java
- PlanterException.java
