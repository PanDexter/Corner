//package szeptunm.corner.dataaccess.repository.implementations
//
//import android.annotation.SuppressLint
//import szeptunm.corner.dataaccess.api.service.PlayerService
//import szeptunm.corner.dataaccess.database.entity.TeamEntity
//import javax.inject.Inject
//import io.reactivex.SingleTransformer
//import szeptunm.corner.entity.Team
//import io.reactivex.Observable
//import io.reactivex.schedulers.Schedulers
//import szeptunm.corner.commons.Utils
//import szeptunm.corner.dataaccess.api.model.PlayerResponse
//import szeptunm.corner.dataaccess.database.dao.PlayerDao
//import szeptunm.corner.dataaccess.database.entity.PlayerEntity
//import szeptunm.corner.dataaccess.database.entity.NewsEntity
//import timber.log.Timber
//
//class TeamRepository @Inject constructor(var playerDao: PlayerDao, var playerService: PlayerService, var utils: Utils) {
//
//    val hasConnection = utils.isConnectedToInternet()
//
//
//
//    private val teamTransformer: SingleTransformer<List<PlayerEntity>, List<Footballer>> =
//            SingleTransformer { upstream ->
//                upstream.flattenAsObservable { list -> list }
//                        .map { Team(it) }
//                        .toList()
//            }
//
//    fun getAllTeams(): Observable<List<Team>> {
//        return Observable.concatArray(
//                getTeamFromDb(), getTeamFromApi()
//        )
//    }
//
//    private fun getTeamFromDb(): Observable<List<Team>> {
//        return playerDao.getAllFootballers()
//                .compose(teamTransformer)
//                .filter { it.isNotEmpty()}
//                .toObservable()
//                .doOnNext {
//                    Timber.d("Dispatching ${it.size} news from DB...")
//                }
//    }
//
//    private fun getTeamFromApi(): Observable<List<Team>> {
//        return playerService.getAllTeams(133739)
//                .map {
//                    mapResponseToEntity(it)
//                }
//                .doOnSuccess {
//                    saveToDatabase(it)
//                }
//                .compose(teamTransformer)
//                .toObservable()
//
//    }
//
//
//    private fun mapResponseToEntity(playerResponse: PlayerResponse): List<TeamEntity> {
//        val newsList: MutableList<NewsEntity> = ArrayList()
//        for (i in 0 until playerResponse) {
//            newsResponse.items[i].let {
//                newsList.add(
//                        NewsEntity(0,it.title, it.description, it.pubDate, it.enclosure.imageURL, it.link, null))
//            }
//        }
//        return newsList
//    }
//
//    @SuppressLint("CheckResult")
//    private fun saveToDatabase(newsList: List<NewsEntity>) {
//        Observable.fromCallable { newsDao.insertAllNews(newsList) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe {
//                    Timber.d("Insert ${newsList.size} news to DB...")
//                }
//    }
//
//    private val teamEntityTransformer = teamTransformer()
//
////
////    fun getTeamById(id: Int): Single<List<Team>> = teamDao.getTeamById(id).compose(teamEntityTransformer)
////
////    fun getAllTeams(): Single<List<Team>> = teamDao.getAllTeams().compose(teamEntityTransformer)
////
////    fun updateTeamDb(){
////        return playerService.getAllTeams().map()
////    }
////
////    fun refreshTeam(){
////        if(teamDao.getAllTeams() ==null){
////            playerService.getAllTeams().compose(teamEntityTransformer)
////        }
////    }
//////
////    fun getTeamsFromApi():Single<List<TeamEntity>>{
////        return playerService.getAllTeams()
////                .doOnSuccess {
////                    it.map { it ->
////                        TeamEntity(it)
////                    }
////                }
////            teamDao.insertAllTeams(it)
////        }
////    }
////
////    fun getAllTeams():Single<List<Team>> {
////        if(hasConnection){
////            Single.just(1).subscribe{
////                it -> playerService.getAllTeams()
////            }
////        }
////    }
////
////
////    private fun saveToDatabase(team:List<TeamEntity>): Completable {
////       return Completable.create {
////           cornerDatabase.runInTransaction {teamDao.insertAllTeams(team)}
////       }
////    }
////
////
////
////    fun insertTeam(team: TeamEntity) = teamDao.insertTeam(team)
////
////    fun insertAllTeams(teamList: List<TeamEntity>) = teamDao.insertAllTeams(teamList)
////
////    fun getTeamByName(club: String): Single<TeamEntity> = teamDao.getTeamByName(club)
//}
//
//
