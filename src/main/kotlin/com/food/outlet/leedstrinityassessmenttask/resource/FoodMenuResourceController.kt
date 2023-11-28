package com.food.outlet.leedstrinityassessmenttask.resource

import com.food.outlet.leedstrinityassessmenttask.data.FoodsDataDTO
import com.food.outlet.leedstrinityassessmenttask.service.FoodsDataService
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@Validated
@RequestMapping("/foods", produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.IMAGE_JPEG_VALUE])
class FoodMenuResourceController(
  private val foodsDataService: FoodsDataService,
) {


  @GetMapping("/v1")
  fun getAllOutlets(
  ): Array<FoodsDataDTO>? {
    return foodsDataService.getAllFoodsMenuData()
  }
}
