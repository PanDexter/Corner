package szeptunm.corner.domain.footballers

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.Footballer
import szeptunm.corner.dataaccess.repository.FootballerRepositoryImpl
import javax.inject.Inject

class GetAllFootballers {

    @Inject
    lateinit var repository:FootballerRepositoryImpl

    fun execute():Single<List<Footballer>>{
        return repository.getAllFootballers()
    }
}