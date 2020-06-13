package mofa.sf.domain.controller

import mofa.sf.domain.farm.FarmId
import mofa.sf.domain.geofeature.GeoLocation

/**
 * Controller node abstraction.
 * @author m_afatah
 * @since 1.0
 */
interface Controller {
    /**
     * Controller node ID.
     * @author m_afatah
     * @since 1.0
     */
    fun id(): ControllerId

    /**
     * Controller node name.
     * @author m_afatah
     * @since 1.0
     */
    fun name(): ControllerName

    /**
     * Controller node location.
     * @author m_afatah
     * @since 1.0
     */
    fun location(): GeoLocation

    /**
     * Farm Id in which this controller is installed.
     * @author m_afatah
     * @since 1.0
     */
    fun farmId(): FarmId

    /**
     * Status of the controller node.
     * @author m_afatah
     * @since 1.0
     */
    fun status(): ControllerStatus
}