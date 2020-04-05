package mofa.sf.db.h2.repository

import mofa.sf.data.SignalDataSource
import mofa.sf.db.DbConnection
import mofa.sf.db.h2.entity.SignalEntity
import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.reading.Temperature
import mofa.sf.domain.signal.Control
import mofa.sf.domain.signal.Signal
import mofa.sf.domain.signal.Timestamp
import java.time.ZoneOffset

class ControlSignalRepo(private val connection: DbConnection): SignalDataSource {
    override suspend fun add(signal: Signal) {
        this.connection
            .execute(
                "INSERT INTO smart_farm.signals(time_stamp, sig_value, control_node) VALUES (?, ?, ?)",
                signal.timestamp(),
                signal.controlValue(),
                signal.controllerId()
            )
    }

    override suspend fun all(): Collection<Signal> {
        return this.connection
            .execute("SELECT * FROM smart_farm.signals ORDER BY time_stamp")
            .records()
            .map { SignalEntity(it) }
    }

    override suspend fun between(from: Timestamp, to: Timestamp): Collection<Signal> {
        return this.connection
            .execute(
                "SELECT * FROM smart_farm.signals" +
                    " WHERE time_stamp BETWEEN DATEADD('SECOND', ?, DATE '1970-01-01') AND DATEADD('SECOND', ?, DATE '1970-01-01') ORDER BY time_stamp",
                    from.asLong(),
                    to.asLong()
            )
            .records()
            .map { SignalEntity(it) }
    }

    override suspend fun findById(id: ControllerId): Collection<Signal> {
        return this.connection
            .execute("SELECT * FROM smart_farm.signals WHERE control_node = ? ORDER BY time_stamp", id.asString())
            .records()
            .map { SignalEntity(it) }
    }

    override suspend fun findById(id: ControllerId, from: Timestamp, to: Timestamp): Collection<Signal> {
        return this.connection
            .execute(
            "SELECT * FROM smart_farm.signals" +
                    " WHERE control_node = ?" +
                    " AND time_stamp BETWEEN DATEADD('SECOND', ?, DATE '1970-01-01') AND DATEADD('SECOND', ?, DATE '1970-01-01') ORDER BY time_stamp",
                id.asString(),
                from.asLong(),
                to.asLong()
            )
            .records()
            .map { SignalEntity(it) }
    }

    override suspend fun signalWithTemperature(): Collection<Triple<Timestamp, Temperature, Control>> {
        return this.connection
            .execute(
                "SELECT signals.time_stamp AS time_stamp, signals.sig_value AS sig_value, readings.temperature AS temperature" +
                " FROM smart_farm.signals, smart_farm.readings" +
                " WHERE signals.time_stamp = readings.time_stamp"
            )
            .records()
            .map {
                Triple(
                    Timestamp.Default(it.getLocalDate("time_stamp").toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Temperature.Default(it.getDouble("temperature")),
                    Control.Default(it.getDouble("sig_value"))
                )
            }
    }

    override suspend fun signalWithTemperature(from: Timestamp, to: Timestamp): Collection<Triple<Timestamp, Temperature, Control>> {
        return this.connection
            .execute(
                "SELECT signals.time_stamp AS time_stamp, signals.sig_value AS sig_value, readings.temperature AS temperature" +
                    " FROM smart_farm.signals, smart_farm.readings" +
                    " WHERE signals.time_stamp = readings.time_stamp" +
                    " AND signals.time_stamp BETWEEN DATEADD('SECOND', ?, DATE '1970-01-01') AND DATEADD('SECOND', ?, DATE '1970-01-01')",
                from.asLong(),
                to.asLong()
            )
            .records()
            .map {
                Triple(
                    Timestamp.Default(it.getLocalDate("time_stamp").toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Temperature.Default(it.getDouble("temperature")),
                    Control.Default(it.getDouble("sig_value"))
                )
            }
    }

    override suspend fun signalWithTemperature(id: ControllerId, from: Timestamp, to: Timestamp): Collection<Triple<Timestamp, Temperature, Control>> {
        return this.connection
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
            .records()
            .map {
                Triple(
                    Timestamp.Default(it.getLocalDate("time_stamp").toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Temperature.Default(it.getDouble("temperature")),
                    Control.Default(it.getDouble("sig_value"))
                )
            }
    }
}