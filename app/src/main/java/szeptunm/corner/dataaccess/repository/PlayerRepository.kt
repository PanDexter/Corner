package szeptunm.corner.dataaccess.repository

import io.reactivex.Observable
import io.reactivex.SingleTransformer
import szeptunm.corner.dataaccess.api.model.PlayerResponse
import szeptunm.corner.dataaccess.api.service.PlayerService
import szeptunm.corner.dataaccess.database.DatabaseTransaction
import szeptunm.corner.dataaccess.database.dao.PlayerDao
import szeptunm.corner.dataaccess.database.entity.PlayerEntity
import szeptunm.corner.entity.ClubInfo
import szeptunm.corner.entity.Player
import timber.log.Timber
import javax.inject.Inject

class PlayerRepository @Inject constructor(private var playerDao: PlayerDao,
        private var playerService: PlayerService, private val databaseTransaction: DatabaseTransaction) {

    private val teamTransformer: SingleTransformer<List<PlayerEntity>, List<Player>> =
            SingleTransformer { upstream ->
                upstream.flattenAsObservable { list -> list }
                        .map { Player(it) }
                        .toList()
            }

    fun getAllPlayers(club: ClubInfo): Observable<List<Player>> {
        return Observable.concatArray(
                getPlayersFromDb(club.teamApiId), getPlayersFromApi(club.teamApiId)
        )
    }

    private fun getPlayersFromDb(club: Int): Observable<List<Player>> {
        return playerDao.getPlayerByClub(club)
                .compose(teamTransformer)
                .filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} news from DB...")
                }
    }

    private fun getPlayersFromApi(club: Int): Observable<List<Player>> {
        return playerService.getAllPlayers(club)
                .map {
                    mapResponseToEntity(it, club)
                }
                .doOnSuccess {
                    saveToDatabase(it)
                }
                .compose(teamTransformer)
                .toObservable()
    }

    private fun mapResponseToEntity(playerResponse: PlayerResponse, club: Int): List<PlayerEntity> {
        val playerList: MutableList<PlayerEntity> = ArrayList()
        for (i in 0 until playerResponse.squad.size) {
            playerResponse.squad[i].let {
                playerList.add(
                        PlayerEntity(0, it.name, it.position, it.dateOfBirth, it.nationality, club,
                                it.description, it.thumbUrl, it.cutOutUrl, it.weight, it.height))
            }
        }
        return playerList
    }

    private fun saveToDatabase(playerList: List<PlayerEntity>) {
        databaseTransaction.runTransaction { playerDao.insertAllPlayers(playerList) }
    }
}