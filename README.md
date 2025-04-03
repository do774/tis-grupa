Tehnologije: 
-> Java: Verzija 17 
-> Spring Boot: Verzija 3.4.4 
-> Spring Web 
-> Spring Data JPA 
-> H2 database
-> Maven 
-> Docker & Docker Compose

Alati: 
-> Spring Tool Suite 4.4.29.1 
-> VS code (više kao terminal)
-> Postman

*************** Pokretanje aplikacije: 

-> dovući projekt: git clone https://github.com/do774/tis-grupa.git

1. Sa editorom:
-> Import as mavnen project 
-> Run as Java Application 

2. Sa mavenom:
-> ući u projekt: cd restapi
-> mvn clean install 
-> java -jar ./target/restapi-0.0.1-SNAPSHOT.jar

3. Sa dockerom:
-> ući u projekt: cd restapi
-> sudo docker compose up --build

*************** Korištenje aplikacije: 

1. Dohvaćanje svih proizvoda: 
-> http://localhost:8080/products

2. Dohvaćanje popularnih proizvoda:
-> http://localhost:8080/products/popular

3. Dohvaćanje svih proizvoda kao view(thymeleaf):
-> http://localhost:8080/view/products

4. Dohvaćanje popularnih proizvoda kao view(thymeleaf):
-> http://localhost:8080/view/popular

*************** Endpointi iz zadatka: 

1. Unos produkta
   Omogućiti unos šifre (CODE), imena (NAME), cijene u EUR (PRICE_EUR) i opisa
   (DESCRIPTION), a cijena u USD se treba računati pozivom HNB API-ja v3: http://api.hnb.hr/
-> Postman : POST : http://localhost:8080/products
   Body/JSON : 
   {
    "code": "PROD00000000006",
    "name": "New Product",
    "priceEur": 500.00,
    "description": "New product desc"
   }

2. Dohvat produkata – omogućiti dohvat liste produkata po neobaveznim filterima: šifra, ime
   (oba trebaju biti case insensitive i ne trebaju biti strogo jednaki nego se traži šifra/ime
   produkta koji sadrže upisani filter) ..primjer 
-> http://localhost:8080/products?code=prod&name=xiaom

3. Dohvat popularnih produkata – endpoint koji vraća tri najpopularnija produkta tako da iz
   tablice review izračuna prosječnu ocjenu svih produkata i vrati tri koja imaju najveću ocjenu.
   Response objekt treba izgledati ovako (rating zaokružiti na jednu decimalu):
-> http://localhost:8080/products/popular


