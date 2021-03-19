### Simple weather
- Simple weather app has 2 activities / pages
  - One that allows you to search any city and click on recent searches
  - One that displays important information about the searched city

#### Gradle version
- Gradle 4.1.2
- Compiled sdk version and target sdk version 30
- Min sdk version 16

#### Tools used
- Data binding
- Androidx
- Room, LiveData and View Model
- Retrofit and Gson to query weather api

#### TODO
- Better UI/UX
  - for error handling, example when gson return empty from non existing city
  - Loading screens when querying api
  - In general, define proper styles in xml
- Use RxJava for better data handling / LiveData ?

## Whats supported
- Running OpenWeatherMapAPI
- Simple Unit Test
- Search by city name
- Most recent search location loads automatically
- Recent Searches
- Delete Recent Searches (can be deleting by swiping items left or right)

## Not yet completed
- Search by zipcode
- Search by GPS
- Multi-market
