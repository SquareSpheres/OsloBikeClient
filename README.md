# OsloBikeClient

Simple client connecting to https://oslobysykkel.no/api/v1 and fetching station information and station availability.

## Delivery

For the delivery we were asked to show a list of stations, available locks, and bikes. The class ListAvailableBikesAndLocks only prints station name, available locks, and available bikes. The other properties are ignored for the purpose of the delivery. 

NOTE : The client is written so that it contains ALL the information supplied by the oslobysykkel API.

## Usage

`java -jar .\OsloBikes.jar "Client ID"`

alternatively you can package the code yourself using maven

`mvn package`

and run it the same way as described above.
Make sure you are in the same directory as the pom.xml

#### result 
```
-------Nylandsveien-------
Number of locks : 5
Number of bikes : 22

-------Bentsebrugata-------
Number of locks : 20
Number of bikes : 2

-------Hans Nielsen Hauges plass-------
Number of locks : 17
Number of bikes : 0

-------Sjøsiden vest-------
Number of locks : 21
Number of bikes : 25

-------St. Hanshaugen park vest-------
Number of locks : 17
Number of bikes : 0

...

```
