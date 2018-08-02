package szeptunm.corner.dataaccess.repository.interfaces

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.Competition

interface CompetitionRepository {

    fun insertCompetition(competition: Competition)
    fun insertAllCompetitions(competitionList:List<Competition>)
    fun getAllCompetition(): Single<List<Competition>>
    fun getCompetitionById(id:Int):Single<Competition>
    fun getCompetitionByName(name:String):Single<Competition>
}