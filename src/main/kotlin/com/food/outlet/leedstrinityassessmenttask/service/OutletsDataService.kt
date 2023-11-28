package com.food.outlet.leedstrinityassessmenttask.service

import com.food.outlet.leedstrinityassessmenttask.data.OutletsDataDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import com.google.gson.Gson
import java.nio.charset.Charset


@Service
class OutletsDataService(
    var resourceLoader: ResourceLoader
) {

    fun getAllOutletsData(): Array<OutletsDataDTO>? {
            val gson = Gson()
            val res = resourceLoader.getResource("classpath:outlets.json")
            var jsonString = res.getContentAsString(Charset.defaultCharset())
            return gson.fromJson(jsonString, Array<OutletsDataDTO>::class.java)

    }
}