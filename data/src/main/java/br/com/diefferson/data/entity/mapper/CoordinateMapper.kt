package br.com.diefferson.data.entity.mapper

import br.com.diefferson.data.entity.Coordinate as CoordinateDTO
import br.com.diefferson.domain.model.Coordinate as CoordinateBO

fun CoordinateBO.toCoordinateDTO()= CoordinateDTO(
        latitude = this.latitude,
        longitude = this.longitude
)
fun CoordinateDTO.toCoordinateBO()= CoordinateBO(
        latitude = this.latitude,
        longitude = this.longitude
)

fun List<CoordinateBO>.toCoordinateDTO() = this.map { it.toCoordinateDTO() }

fun List<CoordinateDTO>.toCoordinateBO()= this.map { it.toCoordinateBO() }
