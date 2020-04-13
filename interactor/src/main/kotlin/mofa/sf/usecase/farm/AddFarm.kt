package mofa.sf.usecase.farm

import mofa.sf.domain.farm.Farm
import mofa.sf.domain.farm.FarmId

interface AddFarm {
    suspend fun add(farm: Farm): FarmId
}