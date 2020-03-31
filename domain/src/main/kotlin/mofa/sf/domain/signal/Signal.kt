package mofa.sf.domain.signal

import mofa.sf.domain.controller.ControllerId

interface Signal {
    fun id(): SignalId
    fun controllerId(): ControllerId
    fun timestamp(): Timestamp
    fun controlValue(): Control
}