package mofa.sf.h2.repository

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import mofa.sf.data.SignalDataSource
import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.reading.Temperature
import mofa.sf.domain.signal.Control
import mofa.sf.domain.signal.Signal
import mofa.sf.domain.signal.Timestamp
import mofa.sf.h2.config.DbConnectionPool
import mofa.sf.h2.config.DbStatement
import mofa.sf.h2.entity.SignalEntity
import java.time.LocalDateTime
import java.time.ZoneOffset

class ControlSignalRepo(private val pool: DbConnectionPool): SignalDataSource {
    override suspend fun add(signal: Signal) {
        DbStatement(this.pool)
            .execute(
                "INSERT INTO smart_farm.signals(time_stamp, sig_value, control_node) VALUES (?, ?, ?)",
                signal.timestamp(),
                signal.controlValue(),
                signal.controllerId()
            )
    }

    override suspend fun all(): Collection<Signal> {
        return DbStatement(this.pool)
            .execute("SELECT * FROM smart_farm.signals ORDER BY time_stamp")
            .map { row, metadata -> SignalEntity(row, metadata) }
            .asFlow()
            .toList()
    }

    override suspend fun between(from: Timestamp, to: Timestamp): Collection<Signal> {
        return DbStatement(this.pool)
            .execute(
                "SELECT * FROM smart_farm.signals" +
                    " WHERE time_stamp BETWEEN DATEADD('SECOND', ?, DATE '1970-01-01') AND DATEADD('SECOND', ?, DATE '1970-01-01') ORDER BY time_stamp",
                    from.asLong(),
                    to.asLong()
            )
            .map { row, metadata -> SignalEntity(row, metadata) }
            .asFlow()
            .toList()
    }

    override suspend fun findById(id: ControllerId): Collection<Signal> {
        return DbStatement(this.pool)
            .execute("SELECT * FROM smart_farm.signals WHERE control_node = ? ORDER BY time_stamp", id.asString())
            .map { row, metadata -> SignalEntity(row, metadata) }
            .asFlow()
            .toList()
    }

    override suspend fun findById(id: ControllerId, from: Timestamp, to: Timestamp): Collection<Signal> {
        return DbStatement(this.pool)
            .execute(
            "SELECT * FROM smart_farm.signals" +
                    " WHERE control_node = ?" +
                    " AND time_stamp BETWEEN DATEADD('SECOND', ?, DATE '1970-01-01') AND DATEADD('SECOND', ?, DATE '1970-01-01') ORDER BY time_stamp",
                id.asString(),
                from.asLong(),
                to.asLong()
        )
        .map { row, metadata ->  SignalEntity(row, metadata) }
        .asFlow()
        .toList()
    }

    override suspend fun signalWithTemperature(): Collection<Triple<Timestamp, Temperature, Control>> {
        return DbStatement(this.pool)
            .execute(
                "SELECT signals.time_stamp AS time_stamp, signals.sig_value AS sig_value, readings.temperature AS temperature" +
                " FROM smart_farm.signals, smart_farm.readings" +
                " WHERE signals.time_stamp = readings.time_stamp"
            )
            .map { row, metadata ->
                Triple(
                    Timestamp.Default(row.get("time_stamp", LocalDateTime::class.java)!!.toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Temperature.Default(row.get("temperature", metadata.getColumnMetadata("temperature").javaType) as Double),
                    Control.Default(row.get("sig_value", metadata.getColumnMetadata("sig_value").javaType) as Double)
                )
            }
            .asFlow()
            .toList()
    }

    override suspend fun signalWithTemperature(from: Timestamp, to: Timestamp): Collection<Triple<Timestamp, Temperature, Control>> {
        return DbStatement(this.pool)
            .execute(
                "SELECT signals.time_stamp AS time_stamp, signals.sig_value AS sig_value, readings.temperature AS temperature" +
                    " FROM smart_farm.signals, smart_farm.readings" +
                    " WHERE signals.time_stamp = readings.time_stamp" +
                    " AND signals.time_stamp BETWEEN DATEADD('SECOND', ?, DATE '1970-01-01') AND DATEADD('SECOND', ?, DATE '1970-01-01')",
                from.asLong(),
                to.asLong()
            )
            .map { row, metadata ->
                Triple(
                    Timestamp.Default(row.get("time_stamp", LocalDateTime::class.java)!!.toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Temperature.Default(row.get("temperature", metadata.getColumnMetadata("temperature").javaType) as Double),
                    Control.Default(row.get("sig_value", metadata.getColumnMetadata("sig_value").javaType) as Double)
                )
            }
            .asFlow()
            .toList()
    }

    override suspend fun signalWithTemperature(id: ControllerId, from: Timestamp, to: Timestamp): Collection<Triple<Timestamp, Temperature, Control>> {
        return DbStatement(this.pool)
            .execute(
                "SELECT signals.time_stamp AS time_stamp, signals.sig_value AS sig_value, readings.temperature AS temperature " +
                    " FROM smart_farm.signals, smart_farm.readings" +
                    " WHERE signals.time_stamp = readings.time_stamp" +
                    " AND signals.control_node = ?" +
                    " AND signals.time_stamp BETWEEN DATEADD('SECOND', ?, DATE '1970-01-01') AND DATEADD('SECOND', ?, DATE '1970-01-01')",
                    id.asString(),
                    from.asLong(),
                    to.asLong()
            )
            .map { row, metadata ->
                Triple(
                    Timestamp.Default(row.get("time_stamp", LocalDateTime::class.java)!!.toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Temperature.Default(row.get("temperature", metadata.getColumnMetadata("temperature").javaType) as Double),
                    Control.Default(row.get("sig_value", metadata.getColumnMetadata("sig_value").javaType) as Double)
                )
            }
            .asFlow()
            .toList()
    }
}