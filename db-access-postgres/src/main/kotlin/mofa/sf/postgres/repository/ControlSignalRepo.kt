package mofa.sf.postgres.repository

import kotlinx.coroutines.future.await
import mofa.sf.data.SignalDataSource
import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.reading.Temperature
import mofa.sf.domain.signal.Control
import mofa.sf.domain.signal.Signal
import mofa.sf.domain.signal.Timestamp
import mofa.sf.postgres.config.DbConnectionPool
import mofa.sf.postgres.entity.SignalEntity
import org.joda.time.DateTimeZone

class ControlSignalRepo(private val connection: DbConnectionPool): SignalDataSource {
    override suspend fun add(signal: Signal) {
        this.connection
            .get()
            .sendPreparedStatement(
            "INSERT INTO smart_farm.signals(time_stamp, sig_value, control_node) VALUES (?, ?, ?)",
                arrayListOf(
                    signal.timestamp(), signal.controlValue(), signal.controllerId()
                )
            )
            .await()
    }

    override suspend fun all(): Collection<Signal> {
        return this.connection
            .get()
            .sendQuery("SELECT * FROM smart_farm.signals ORDER BY time_stamp")
            .await()
            .rows
            .map { SignalEntity(it) }
    }

    override suspend fun between(from: Timestamp, to: Timestamp): Collection<Signal> {
        return this.connection
            .get()
            .sendPreparedStatement(
                "SELECT * FROM smart_farm.signals where time_stamp BETWEEN TO_TIMESTAMP(?) AND TO_TIMESTAMP(?) ORDER BY time_stamp",
                arrayListOf(from.asLong(), to.asLong())
            )
            .await()
            .rows
            .map { SignalEntity(it) }
    }

    override suspend fun findById(id: ControllerId): Collection<Signal> {
        return this.connection
            .get()
            .sendPreparedStatement(
                "SELECT * FROM smart_farm.signals where control_node = ? ORDER BY time_stamp",
                arrayListOf(id.asString())
            )
            .await()
            .rows
            .map { SignalEntity(it) }
    }

    override suspend fun findById(id: ControllerId, from: Timestamp, to: Timestamp): Collection<Signal> {
        return this.connection
            .get()
            .sendPreparedStatement(
                "SELECT * FROM smart_farm.signals where control_node = ? AND time_stamp BETWEEN TO_TIMESTAMP(?) AND TO_TIMESTAMP(?) ORDER BY time_stamp",
                arrayListOf(id.asString(), from.asLong(), to.asLong())
            )
            .await()
            .rows
            .map { SignalEntity(it) }
    }

    override suspend fun signalWithTemperature(): Collection<Triple<Timestamp, Temperature, Control>> {
        return this.connection
            .get()
            .sendQuery(
                arrayListOf(
                    "SELECT signals.time_stamp AS time_stamp, signals.sig_value AS sig_value, readings.temperature AS temperature",
                    "FROM smart_farm.signals, smart_farm.readings",
                    "WHERE signals.time_stamp = readings.time_stamp"
                ).joinToString(" ")
            )
            .await()
            .rows
            .map {
                Triple(
                    Timestamp.Default(it.getDate("time_stamp")!!.toDateTime(DateTimeZone.UTC).millis),
                    Temperature.Default(it.getDouble("temperature")!!),
                    Control.Default(it.getDouble("sig_value")!!)
                )
            }
    }

    override suspend fun signalWithTemperature(from: Timestamp, to: Timestamp): Collection<Triple<Timestamp, Temperature, Control>> {
        return this.connection
            .get()
            .sendPreparedStatement(
                arrayListOf(
                    "SELECT signals.time_stamp AS time_stamp, signals.sig_value AS sig_value, readings.temperature AS temperature",
                    "FROM smart_farm.signals, smart_farm.readings",
                    "WHERE signals.time_stamp = readings.time_stamp AND signals.time_stamp BETWEEN TO_TIMESTAMP(?) AND TO_TIMESTAMP(?)"
                ).joinToString(" "),
                arrayListOf(from.asLong(), to.asLong())
            )
            .await()
            .rows
            .map {
                Triple(
                    Timestamp.Default(it.getDate("time_stamp")!!.toDateTime(DateTimeZone.UTC).millis),
                    Temperature.Default(it.getDouble("temperature")!!),
                    Control.Default(it.getDouble("sig_value")!!)
                )
            }
    }

    override suspend fun signalWithTemperature(id: ControllerId, from: Timestamp, to: Timestamp): Collection<Triple<Timestamp, Temperature, Control>> {
        return this.connection
            .get()
                .sendPreparedStatement(
                    arrayListOf(
                        "SELECT signals.time_stamp AS time_stamp, signals.sig_value AS sig_value, readings.temperature AS temperature",
                        "FROM smart_farm.signals, smart_farm.readings",
                        "WHERE signals.time_stamp = readings.time_stamp AND signals.control_node = ? AND signals.time_stamp BETWEEN TO_TIMESTAMP(?) AND TO_TIMESTAMP(?)"
                    ).joinToString(" "),
                    arrayListOf(id.asString(), from.asLong(), to.asLong())
                )
            .await()
            .rows
            .map {
                Triple(
                    Timestamp.Default(it.getDate("time_stamp")!!.toDateTime(DateTimeZone.UTC).millis),
                    Temperature.Default(it.getDouble("temperature")!!),
                    Control.Default(it.getDouble("sig_value")!!)
                )
            }
    }
}