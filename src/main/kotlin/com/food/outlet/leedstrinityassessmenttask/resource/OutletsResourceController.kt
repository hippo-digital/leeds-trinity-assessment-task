package com.food.outlet.leedstrinityassessmenttask.resource

import com.food.outlet.leedstrinityassessmenttask.data.OutletsDataDTO
import com.food.outlet.leedstrinityassessmenttask.service.OutletsDataService
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@Validated
@RequestMapping("/outlets", produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.IMAGE_JPEG_VALUE])
class OutletsResourceController(
  private val outletsDataService: OutletsDataService,
) {


  @GetMapping("/v1")
  fun getAllOutlets(
  ): Array<OutletsDataDTO>? {
    return outletsDataService.getAllOutletsData()
  }
}
