package szeptunm.corner.dataaccess.repository

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers
import szeptunm.corner.dataaccess.api.model.PlayerResponse
import szeptunm.corner.dataaccess.api.service.PlayerService
import szeptunm.corner.dataaccess.database.dao.PlayerDao
import szeptunm.corner.dataaccess.database.entity.PlayerEntity
import szeptunm.corner.entity.Player
import timber.log.Timber
import javax.inject.Inject

class PlayerRepository @Inject constructor(private var playerDao: PlayerDao,
        private var playerService: PlayerService) {

    private val teamTransformer: SingleTransformer<List<PlayerEntity>, List<Player>> =
            SingleTransformer { upstream ->
                upstream.flattenAsObservable { list -> list }
                        .map { Player(it) }
                        .toList()
            }

    fun getAllPlayers(): Observable<List<Player>> {
        return Observable.concatArray(
                getPlayersFromDb(), getPlayersFromApi()
        )
    }

    private fun getPlayersFromDb(): Observable<List<Player>> {
        return playerDao.getAllPlayers()
                .compose(teamTransformer)
                .filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} news from DB...")
                }
    }

    private fun getPlayersFromApi(): Observable<List<Player>> {
        return playerService.getAllPlayers(133739)
                .map {
                    mapResponseToEntity(it)
                }
                .doOnSuccess {
                    saveToDatabase(it)
                }
                .compose(teamTransformer)
                .toObservable()
    }

    private fun mapResponseToEntity(playerResponse: PlayerResponse): List<PlayerEntity> {
        val playerList: MutableList<PlayerEntity> = ArrayList()
        for (i in 0 until playerResponse.squad.size) {
            playerResponse.squad[i].let {
                playerList.add(
                        PlayerEntity(0, it.name, it.position, it.dateOfBirth, it.nationality, null,
                                it.description, it.thumbUrl, it.cutOutUrl))
            }
        }
        return playerList
    }

    @SuppressLint("CheckResult")
    private fun saveToDatabase(playerList: List<PlayerEntity>) {
        Observable.fromCallable { playerDao.insertAllPlayers(playerList) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Insert ${playerList.size} news to DB...")
                }
    }
}