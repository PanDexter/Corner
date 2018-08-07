package szeptunm.corner.dataaccess.repository.implementations

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.dao.CompetitionDao
import szeptunm.corner.dataaccess.database.entity.CompetitionEntity
import javax.inject.Inject

class CompetitionRepository @Inject constructor(var competitionDao: CompetitionDao)  {

     fun insertCompetition(competitionEntity: CompetitionEntity) = competitionDao.insertCompetition(competitionEntity)

     fun insertAllCompetitions(competitionEntityList: List<CompetitionEntity>) = competitionDao.insertAllCompetitions(competitionEntityList)

     fun getAllCompetition(): Single<List<CompetitionEntity>> = competitionDao.getAllCompetitions()

     fun getCompetitionById(id: Int): Single<CompetitionEntity> = competitionDao.getCompetitionById(id)

     fun getCompetitionByName(name: String): Single<CompetitionEntity> = competitionDao.getCompetitionyName(name)
}