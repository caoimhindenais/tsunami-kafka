# tsunami-kafka

This project demonstrates the use of Kafka's Broker, Consumer's Producers and Streams using data from NOAA
(http://wcatwc.arh.noaa.gov)


**Install KAFKA** 
Download Confluent Open Source 4.1 https://www.confluent.io/download/
- Unzip : tar -zxvf confluent-oss-4.1.0-2.11.tar.g 
- Start : _bin/confluent start_


**Microservices**
The project has a number of micoservices which can be each built and running using
mvn clean install  spring-boot:run

- **Tsunami Service** - Producing tsunami alerts (based on the NOAA tsunami database below) 
- **Tsunami UI** - Consuming tsunami alerts and tsnumai stream results and exposing them via REST UI
- **Tsunami Stream** - Kafka Streams analysing the Tsunami wave systems

**GUI**  Tomcat (Springboot) serving on localhost:8080

**Data Source**

https://resourcewatch.org/data/explore/dis009nrt-Tsunamis

The Global Historical Tsunami Database released by NOAA (National Oceanic and Atmospheric 
Administration ) provides information on over 2,400 tsunamis from 2100 BC to the present 
in the Atlantic, Indian, and Pacific Oceans; and the Mediterranean and 
Caribbean Seas.