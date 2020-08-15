package mofa.sf.db.h2.repository

import mofa.sf.data.PredictionDataSource
import mofa.sf.db.DbConnection
import mofa.sf.domain.ml.Prediction

class PredictionRepo(private val connection: DbConnection): PredictionDataSource {
    override suspend fun add(prediction: Prediction): Int {
        return this.connection
            .execute(
                "INSERT INTO machine_learning.accuracy(rmse, rrmse, me, rme, class) VALUES(?, ?, ?, ?, ?)",
                prediction.rmse,
                prediction.rrmse,
                prediction.me,
                prediction.rme,
                prediction.cls.toString()
            )
            .records()
            .map { record ->  record.getInt("id") }
            .first()
    }
}