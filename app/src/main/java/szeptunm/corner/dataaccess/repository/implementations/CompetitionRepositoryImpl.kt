package szeptunm.corner.dataaccess.repository.implementations

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.dao.CompetitionDao
import szeptunm.corner.dataaccess.database.entity.Competition
import szeptunm.corner.dataaccess.repository.interfaces.CompetitionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompetitionRepositoryImpl @Inject constructor(var competitionDao: CompetitionDao) : CompetitionRepository {

    override fun insertCompetition(competition: Competition) = competitionDao.insertCompetition(competition)

    override fun insertAllCompetitions(competitionList: List<Competition>) = competitionDao.insertAllCompetitions(competitionList)

    override fun getAllCompetition(): Single<List<Competition>> = competitionDao.getAllCompetitions()

    override fun getCompetitionById(id: Int): Single<Competition> = competitionDao.getCompetitionById(id)

    override fun getCompetitionByName(name: String): Single<Competition> = competitionDao.getCompetitionyName(name)
}