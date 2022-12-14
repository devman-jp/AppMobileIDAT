package idat.dami.prueba.retrofit.response

data class ResponsePaquete(

    var idPaquete: Int,
    var remitente: ResponseRemitente,
    var consignado: ResponseConsignado

)
