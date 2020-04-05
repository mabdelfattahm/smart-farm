package mofa.sf.postgres.entity

import mofa.sf.db.DbRecord
import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.signal.Control
import mofa.sf.domain.signal.Signal
import mofa.sf.domain.signal.SignalId
import mofa.sf.domain.signal.Timestamp
import org.joda.time.DateTimeZone
import java.time.ZoneOffset

class SignalEntity(private val data: DbRecord) : Signal {
    override fun id(): SignalId {
        return SignalId.Default(this.data.getInt("id"))
    }

    override fun controllerId(): ControllerId {
        return ControllerId.Default(this.data.getInt("control_node"))
    }

    override fun timestamp(): Timestamp {
        return Timestamp.Default(this.data.getLocalDate("time_stamp").toInstant(ZoneOffset.UTC).toEpochMilli())
    }

    override fun controlValue(): Control {
        return Control.Default(this.data.getDouble("sig_value")!!)
    }
}