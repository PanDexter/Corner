package szeptunm.corner.dataaccess.database;

import io.reactivex.Completable;
import javax.inject.Inject;

public class DatabaseTransaction {

    @Inject
    CornerDatabase database;

    @Inject
    DatabaseTransaction() {
    }

    public Completable runTransaction(Runnable runnable) {
        return Completable.fromAction(() -> database.runInTransaction(runnable));
    }
}
