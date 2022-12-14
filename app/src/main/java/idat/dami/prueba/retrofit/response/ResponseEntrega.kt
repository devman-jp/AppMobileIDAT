package idat.dami.prueba.retrofit.response

data class ResponseEntrega(

    var idEntrega: Int,
    var estado: String,
    var fecha: String,
    var repartidor: ResponseRepartidor,
    var paquete: ResponsePaquete

)
