# OsloBikeWebService

A simple REST Web Service using [OsloBikeClient](https://github.com/SquareSpheres/OsloBikeClient) to fetch information from https://oslobysykkel.no/api/v1. The webservice supplies the same information as [OsloBikeClient](https://github.com/SquareSpheres/OsloBikeClient) only as a web service instead of a local app.

Changes have been made to [OsloBikeClient](https://github.com/SquareSpheres/OsloBikeClient/blob/webservice/src/main/java/io/github/squarespheres/oslobikes/client/OsloBikeClient.java) to allow exceptions to propagate all the way through the application until caught by the default spring exception handler.

## Delivery

For the delivery we were asked to create an application that exposes a REST-based endpoint that displays data from [OsloBikeCLient](https://github.com/SquareSpheres/OsloBikeClient). We were also asked to write tests for [OsloBikeCLient](https://github.com/SquareSpheres/OsloBikeClient)


## Usage

1.

`java -jar .\OsloBikesWebService.jar --osloApiId="Client ID"`

where the client-id is for https://oslobysykkel.no/api/v1

alternatively you can package the code yourself using maven

`mvn package`

and run it the same way as described above.
Make sure you are in the same directory as the pom.xml

2.

Connect to http://localhost:8080/availability along with header "Client-Identifier". The Client-Identifier can be any value.

## Tests

To run the tests use

`mvn test -D clientId="clientId"`

where the client-id is for https://oslobysykkel.no/api/v1


## Results 
```
[
	{
		"stationName": "Nylandsveien",
		"numberOfBikes": 13,
		"numberOfLocks": 15
	},
	{
		"stationName": "Bentsebrugata",
		"numberOfBikes": 0,
		"numberOfLocks": 22
	},
	{
		"stationName": "Hans Nielsen Hauges plass",
		"numberOfBikes": 0,
		"numberOfLocks": 21
	},
	{
		"stationName": "Sjøsiden vest",
		"numberOfBikes": 23,
		"numberOfLocks": 26
	}

...

```
