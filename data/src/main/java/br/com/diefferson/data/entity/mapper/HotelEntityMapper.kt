package br.com.diefferson.data.entity.mapper

import br.com.diefferson.data.entity.Hotel as HotelDTO
import br.com.diefferson.domain.model.Hotel as HotelBO

fun HotelBO.toHotelDTO() = HotelDTO(
        code = this.code,
        name = this.name,
        email = this.email,
        address = this.address,
        price = this.price,
        contactEmail = this.contactEmail,
        contactPhone = this.contactPhone,
        coordenates = this.coordenates?.toCoordinateDTO(),
        cep = this.cep,
        city = this.city,
        uf = this.uf,
        status = this.status,
        description = this.description,
        coverImage = this.coverImage,
        rating = this.rating,
        ratingsNumber = this.ratingsNumber,
        commentsNumber = this.commentsNumber,
        images = this.images,
        services = this.services?.toServiceDTO()
)


fun HotelDTO.toHotelBO()= HotelBO(
        code = this.code,
        name = this.name,
        email = this.email,
        address = this.address,
        price = this.price,
        contactEmail = this.contactEmail,
        contactPhone = this.contactPhone,
        coordenates = this.coordenates?.toCoordinateBO(),
        cep = this.cep,
        city = this.city,
        uf = this.uf,
        status = this.status,
        description = this.description,
        coverImage = this.coverImage,
        rating = this.rating,
        ratingsNumber = this.ratingsNumber,
        commentsNumber = this.commentsNumber,
        images = this.images,
        services = this.services?.toServiceBO()
)

fun List<HotelBO>.toHotelDTO() = this.map { it.toHotelDTO() }

fun List<HotelDTO>.toHotelBO()= this.map { it.toHotelBO() }

