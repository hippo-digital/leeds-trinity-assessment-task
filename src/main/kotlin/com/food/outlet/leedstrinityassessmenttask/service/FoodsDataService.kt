package com.food.outlet.leedstrinityassessmenttask.service

import com.food.outlet.leedstrinityassessmenttask.data.FoodsDataDTO
import com.food.outlet.leedstrinityassessmenttask.data.OutletsDataDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import com.google.gson.Gson
import java.nio.charset.Charset


@Service
class FoodsDataService(
    var resourceLoader: ResourceLoader
) {

    fun getAllFoodsMenuData(): Array<FoodsDataDTO>? {
            val gson = Gson()
            val res = resourceLoader.getResource("classpath:foods.json")
            var jsonString = res.getContentAsString(Charset.defaultCharset())
            return gson.fromJson(jsonString, Array<FoodsDataDTO>::class.java)

    }
}