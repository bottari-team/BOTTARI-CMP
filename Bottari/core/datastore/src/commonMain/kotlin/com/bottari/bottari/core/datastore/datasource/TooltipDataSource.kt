package com.bottari.bottari.core.datastore.datasource

import com.bottari.bottari.core.model.config.TooltipType
import kotlinx.coroutines.flow.Flow

interface TooltipDataSource {
    suspend fun isTooltipDismissed(tooltipType: TooltipType): Flow<Boolean>

    suspend fun setTooltipDismissed(tooltipType: TooltipType)
}
