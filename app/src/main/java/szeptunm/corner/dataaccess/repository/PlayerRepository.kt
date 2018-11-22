package szeptunm.corner.dataaccess.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.SingleTransformer
import szeptunm.corner.commons.Preferences
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
        private var playerService: PlayerService, val preferences:Preferences) {

    private val teamTransformer: SingleTransformer<List<PlayerEntity>, List<Player>> =
            SingleTransformer { upstream ->
                upstream.flattenAsObservable { list -> list }
                        .map { Player(it) }
                        .toList()
            }

    fun getAllPlayers(club: ClubInfo): Observable<List<Player>> = getPlayersFromDb(club.matchTeamId)

    private fun getPlayersFromDb(club: Int): Observable<List<Player>> {
        return playerDao.getPlayerByClub(club)
                .compose(teamTransformer)
                .filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} news from DB...")
                }
    }

    fun getPlayersFromApi(clubInfo: ClubInfo): Completable {
        return playerService.getAllPlayers(clubInfo.teamApiId)
                .flatMapCompletable {
                    mapResponseToEntity(it, clubInfo.matchTeamId)
                }
    }

    private fun mapResponseToEntity(playerResponse: PlayerResponse, club: Int): Completable {
        val playerList: MutableList<PlayerEntity> = ArrayList()
        for (i in 0 until playerResponse.squad.size) {
            playerResponse.squad[i].let {
                playerList.add(
                        PlayerEntity(it.name, it.position, it.dateOfBirth, it.nationality, club,
                                it.description, it.thumbUrl, it.cutOutUrl, it.weight, it.height))
            }
        }
        return saveToDatabase(playerList)
    }

    private fun saveToDatabase(playerList: List<PlayerEntity>) =
            Completable.fromAction { playerDao.insertAllPlayers(playerList) }
}