package szeptunm.corner.domain.footballers

import io.reactivex.Single
import szeptunm.corner.dataaccess.database.entity.FootballerEntity
import szeptunm.corner.dataaccess.repository.implementations.FootballerRepositoryImpl
import javax.inject.Inject

class GetAllFootballers @Inject constructor() {

    @Inject
    lateinit var repository: FootballerRepositoryImpl

    fun execute():Single<List<FootballerEntity>>{
        return repository.getAllFootballers()
    }
}