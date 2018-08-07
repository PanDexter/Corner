package szeptunm.corner.dataaccess.database

import io.reactivex.Completable
import javax.inject.Inject

class DatabaseTransaction @Inject constructor(var database: CornerDatabase) {

    fun runTransaction(runnable: Runnable): Completable {
        return Completable.fromAction { database.runInTransaction(runnable) }
    }
}