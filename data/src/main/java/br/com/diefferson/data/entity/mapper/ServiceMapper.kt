package br.com.diefferson.data.entity.mapper

import br.com.diefferson.data.entity.Service as ServiceDTO
import br.com.diefferson.domain.model.Service as ServiceBO

fun ServiceBO.toServiceDTO()= ServiceDTO(
        codeService = this.codeService,
        name = this.name
)
fun ServiceDTO.toServiceBO()= ServiceBO(
        codeService = this.codeService,
        name = this.name
)

fun List<ServiceBO>.toServiceDTO() = this.map { it.toServiceDTO() }

fun List<ServiceDTO>.toServiceBO()= this.map { it.toServiceBO() }
