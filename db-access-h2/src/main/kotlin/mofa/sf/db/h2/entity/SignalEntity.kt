package mofa.sf.db.h2.entity

import mofa.sf.db.DbRecord
import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.signal.Control
import mofa.sf.domain.signal.Signal
import mofa.sf.domain.signal.SignalId
import mofa.sf.domain.signal.Timestamp
import java.time.ZoneOffset

class SignalEntity(private val record: DbRecord) : Signal {
    override fun id(): SignalId {
        return SignalId.Default(this.record.getInt("id"))
    }

    override fun controllerId(): ControllerId {
        return ControllerId.Default(this.record.getInt("control_node"))
    }

    override fun timestamp(): Timestamp {
        return Timestamp.Default(this.record.getLocalDate("time_stamp").toInstant(ZoneOffset.UTC).toEpochMilli())
    }

    override fun controlValue(): Control {
        return Control.Default(this.record.getDouble("sig_value"))
    }
}