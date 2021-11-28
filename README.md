## Description
Получение фильмв с интернета,отображение их в списке,просмотр деталей о фильме,а также фильтрация их по жанрам

## Main Stack : Retrofit,Coroutines,Mvp,Dagger2,View binding,Navigation component <br/>
Image loader: Picasso <br/>
SOLID,OOP,Clean architecture,JUnit tests<br/><br/>
Разбиение приложения на data, domain, presentation слои<br/>
Маппинг обЪектов через слои<br/><br/>
В качестве предоставления зависимостей(di) я использовал "Dagger2"<br/><br />
Для навигации приложения была использована библиотека "Navigation component"

### Tests
Необходимые классы покрыты юнит - тестами

#### package core
- [FilmMapperTest](https://github.com/KostyaGig/TzFilmsApp/blob/master/app/src/test/java/com/zinoview/tzfilmsapp/core/FilmMapperTest.kt)<br/>
- [FilmsMapperTest](https://github.com/KostyaGig/TzFilmsApp/blob/master/app/src/test/java/com/zinoview/tzfilmsapp/core/FilmsMapperTest.kt)<br/>
- [ResourceProviderTest](https://github.com/KostyaGig/TzFilmsApp/blob/master/app/src/test/java/com/zinoview/tzfilmsapp/core/ResourceProviderTest.kt)<br/>

#### package data
- [FilmsRepositoryTest](https://github.com/KostyaGig/TzFilmsApp/blob/master/app/src/test/java/com/zinoview/tzfilmsapp/data/FilmsRepositoryTest.kt)<br/>
- [ExceptionMapperTest](https://github.com/KostyaGig/TzFilmsApp/blob/master/app/src/test/java/com/zinoview/tzfilmsapp/data/ExceptionMapperTest.kt)<br/>

### Разбиение приложение на фичи

### Feature FA01 show films (данную фичу можно протестировать согласно тесткейсам)

[FA01TestCases](https://github.com/KostyaGig/TzFilmsApp/blob/master/app/src/main/java/com/zinoview/tzfilmsapp/testcases/FA01TestCases)<br/>

### Feature UA02 detail film (данную фичу можно протестировать согласно тесткейсам)

[FA02TestCases](https://github.com/KostyaGig/TzFilmsApp/blob/master/app/src/main/java/com/zinoview/tzfilmsapp/testcases/FA02TestCases)<br/>

### Feature UA03 filter films by genre (данную фичу можно протестировать согласно тесткейсам)

[FA03TestCases](https://github.com/KostyaGig/TzFilmsApp/blob/master/app/src/main/java/com/zinoview/tzfilmsapp/testcases/FA03TestCases)<br/>

### Rest API
 - [GET](https://s3-eu-west-1.amazonaws.com/sequeniatesttask/films.json)
