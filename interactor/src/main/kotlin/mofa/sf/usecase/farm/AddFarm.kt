package mofa.sf.usecase.farm

import mofa.sf.domain.farm.Farm

interface AddFarm {
    suspend fun add(farm: Farm)
}