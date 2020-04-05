package mofa.sf.h2.entity

import io.r2dbc.spi.Row
import io.r2dbc.spi.RowMetadata
import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.signal.Control
import mofa.sf.domain.signal.Signal
import mofa.sf.domain.signal.SignalId
import mofa.sf.domain.signal.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset

class SignalEntity(private val data: Row, private val metadata: RowMetadata) : Signal {
    override fun id(): SignalId {
        return SignalId.Default(this.data.get("id", metadata.getColumnMetadata("id").javaType) as Int)
    }

    override fun controllerId(): ControllerId {
        return ControllerId.Default(this.data.get("control_node", this.metadata.getColumnMetadata("control_node").javaType) as Int)
    }

    override fun timestamp(): Timestamp {
        return Timestamp.Default(this.data.get("time_stamp", LocalDateTime::class.java)!!.toInstant(ZoneOffset.UTC).toEpochMilli())
    }

    override fun controlValue(): Control {
        return Control.Default(this.data.get("sig_value", this.metadata.getColumnMetadata("sig_value").javaType) as Double)
    }
}