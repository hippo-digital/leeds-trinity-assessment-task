package com.food.outlet.leedstrinityassessmenttask.data

import java.time.LocalDateTime

data class FoodsDataDTO(

    var id: String,

    var outletId: Int,

    val name: String,

    val startTime: String,

    val endTime : String,

    var inStock: Int,

    val active : Boolean
)
