package mofa.sf.data

import mofa.sf.domain.farm.Farm
import mofa.sf.domain.farm.FarmId

interface FarmDataSource {
    suspend fun findById(id: FarmId): Farm
    suspend fun list(): Collection<Farm>
}