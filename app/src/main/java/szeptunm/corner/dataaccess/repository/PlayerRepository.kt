package szeptunm.corner.dataaccess.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers
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

    fun getAllPlayers(club: ClubInfo): Observable<List<Player>> = getPlayersFromDb(club.teamApiId)

    private fun getPlayersFromDb(club: Int): Observable<List<Player>> {
        return playerDao.getPlayerByClub(club)
                .compose(teamTransformer)
                .filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} news from DB...")
                }
    }

    fun getPlayersFromApi(club: Int): Completable {
        return playerService.getAllPlayers(club)
                .flatMapCompletable {
                    mapResponseToEntity(it, club)
                }
    }

    private fun mapResponseToEntity(playerResponse: PlayerResponse, club: Int): Completable {
        val playerList: MutableList<PlayerEntity> = ArrayList()
        for (i in 0 until playerResponse.squad.size) {
            playerResponse.squad[i].let {
                playerList.add(
                        PlayerEntity(0, it.name, it.position, it.dateOfBirth, it.nationality, club,
                                it.description, it.thumbUrl, it.cutOutUrl, it.weight, it.height))
            }
        }
        return saveToDatabase(playerList)
    }

    private fun saveToDatabase(playerList: List<PlayerEntity>) =
            Completable.fromAction { playerDao.insertAllPlayers(playerList) }
}