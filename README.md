# FICOHSA Challenge

## Enunciado del Ejercicio
Se quiere reclutar la mayor cantidad de mutantes. Por lo cual te han contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.
En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.
## Tecnologías usadas
Para la solución del ejercicio se utilizaron las siguientes herramientas tecnologías:

**BD:** *PostgreSQL*

**DATOS DE CONEXIÓN:**

*URL=JDBC:POSTGRESQL://EC2-34-236-94-53.COMPUTE-1.AMAZONAWS.COM:5432/D1HIP89J25FHVL*

*USERNAME=XNNBMKRUDNSFAR*

*PASSWORD=7AEB34B7600979341093223F5DDEAFA01C570D6DA5989B35C33B44DA75EC23B2*

Heroku: Cloud Application Platform: Para el despliegue de la API y de la base de datos se hizo uso de los servicios cloud gratuitos ofrecidos por el proveedor Heroku.
Heroku URL: https://www.heroku.com/home
La implementación del desarrollo se realizó en java, con los framework Spring boot, JPA.


> Postman Collection
https://www.getpostman.com/collections/bef922936b912aa7c7ab

## Test Coverage


## Indicaciones para ejecutar el servicio

La API expone los siguientes servicios

1) mutant: Servicio que recibe como parámetros el adn y determina si corresponde a Mutante o Humano. <br>
Endpoint: https://ficohsa-challenge.herokuapp.com:443/adn/challenge/v1/mutant<br>
Tipo: POST<br>
Body: Json<br>
Request Example:<br>
`
{
    "dna": [
        "ATTCGA",
        "CCACTT",
        "TTATGG",
        "AGATGA",
        "CCGCAG",
        "TCATTG"
    ]
}
`
   <br>
2)	stats: Servicio que permite recuperar las estadísticas almacenadas. Devuelve el número total de mutantes, el numero total de Humanos y la relación entre los valores.<br>

Endpoint: https://ficohsa-challenge.herokuapp.com:443/adn/challenge/v1/stats<br>
Tipo: GET <br>
Body: N/A<br>
Request Example: N/A<br>
Response Example:<br>

`
{
    "ratio": 0.3333333333333333,
    "count_mutant_dna": 2,
    "count_human_dna": 6
}`

<br>

3) getstats: servicio que retorna todos los registros almacenados en la bd stats.<br>
Endpoint: https://ficohsa-challenge.herokuapp.com:443/adn/challenge/v1/getstats
Tipo: GET<br>
Body: N/A<br>
Request Example: N/A<br>
Response Example:<br>

`
[
    {
        "id": 18,
        "adnMutantRequest": "dna={[ATTCGA, CCACTT, TTATGG, AGATGA, CCGCAG, TCATTG]}",
        "mutant": 0
    },
    {
        "id": 10,
        "adnMutantRequest": "dna={[ATGCGA, CCTCTT, TTATGG, AGAAGA, CCCTAG, TCACTG]}",
        "mutant": 0
    },
    {
        "id": 11,
        "adnMutantRequest": "dna={[ATGCGA, CCTCTT, TTATGG, AGATGA, CCCTAG, TCACTG]}",
        "mutant": 0
    }
]
`
