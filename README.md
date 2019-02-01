# Corner

## General information
Corner is Android app for football fans created for Engineer Thesis and learning purpose.<br/>
App is working and could be used but it has limited API calls so it's not published on Play Store.

## Use cases

Main goal which I wanted to achive was to create simple and easy to use app for football fans.<br/>
Clubs which are included in app are:
* FC Barcelona
* Real Madrid
* Arsenal London
* Chelsea London
* Bayern Munich
* Liverpool FC
* Manchester City
* Manchester United
* Atletico Madrid
* Borussia Dortmund
* Juventus Turin
* Paris Saint Germain

All those clubs have personalized UI based on their crest and club colors.

Use cases covered:
* news (articles and movies)
* squad
* schedule
* standing
* team switching
* setting favourite team (it is shown on every app start)

**App is written in MVVM pattern using Android Clean Architecture pattern.**

## Tech stack

* Kotlin
* RxJava
* Dagger2
* Glide
* Room
* DataBinding
* CircleImageView
* JUnit
* Mockito

## App presentation

<p align="center">
  <b>Onboarding</b><br/>
<img src="https://user-images.githubusercontent.com/26793954/52140817-1866a800-2654-11e9-9bf6-e8d08372a045.jpg" width="200" height="400"><br/>
</p>
<p align="center">
  <b>News screen</b><br/>
<img src="https://user-images.githubusercontent.com/26793954/52140797-084ec880-2654-11e9-8cf1-61ab976e294b.jpg" width="200" height="400">
<img src="https://user-images.githubusercontent.com/26793954/52140836-26b4c400-2654-11e9-80ad-7122331a2f82.jpg" width="200" height="400">
 </p>
 <p align="center">
  <b>Squad screen</b><br/>
<img src="https://user-images.githubusercontent.com/26793954/52141208-41d40380-2655-11e9-8221-0b6e309b2892.jpg" width="200" height="400">
<img src="https://user-images.githubusercontent.com/26793954/52141225-50221f80-2655-11e9-9401-e663bf3e59cd.jpg" width="200" height="400">
 </p>
 <p align="center">
  <b>Schedule</b><br/>
<img src="https://user-images.githubusercontent.com/26793954/52141566-6bd9f580-2656-11e9-8fa7-38ebe7be2c2f.jpg" width="200" height="400"><br/>
</p>
<p align="center">
  <b>Standing</b><br/>
<img src="https://user-images.githubusercontent.com/26793954/52141579-772d2100-2656-11e9-9b2f-b4bc23571bf0.jpg" width="200" height="400"><br/>
</p>

## Running app

To run the app you have to download repository and build APK in Android Studio. However there is no keys for both API used in app.
If you want to get it just register on:
* [RSS2Json](https://rss2json.com/) - website to get information from RSS feed converted into API
* [Football-data](http://api.football-data.org/) - football API

And get API keys and then fill 
```
NEWS_KEY
```
and
```
MATCH_KEY
```
in **build.gradle**

## Authors

* **Marcin Szeptun** - [Linkedin](https://www.linkedin.com/in/marcin-szeptun-b3527212a/)

## License

This project is licensed under the GNU GPLv3 License - see the [LICENSE](LICENSE) file for details
