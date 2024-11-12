## Trabajo Practico Especial Primera Entrega

### Integrantes
Braian Ricci (braianricci@gmail.com)  
Esteban Villanueva (evillanueva@alumnos.exa.unicen.edu.ar).

### Tecnologias y arquitectura
- Aplicacion de Microservicios en Springboot
- Microservicios individuales desplegados con Docker

### Endpoints especiales para los servicios/reportes del punto 3
- A:  
    **_/monopatines/uso-por-kilometros_**  
    Acepta un request body con el siguiente formato:

    ```json
    {
        "incluirTiempo": true,
    }
    ```

- B:  
     **_/cuentas/{id}/cambiar-estado_**  
    Acepta un request body con el siguiente formato:

    ```json
    {
        "habilitada": false
    }
    ```

- C:  
     **_/viajes/monopatines-con-mas-viajes_**  
    Acepta un request body con el siguiente formato:

    ```json
    {
        "viajesMinimos": 20,
        "anio": 2023
    }
    ```

- D:  
     **_/viajes/total-facturado_**  
    Acepta un request body con el siguiente formato:

    ```json
    {
        "anio": 2023,
        "desdeMes": 4,
        "hastaMes": 10
    }
    ```

- E:  
    **_/monopatines/total-en-operacion-y-mantenimiento_**  
    No acepta parametros

- F:  
     **_/viajes/ajustar-precios_**  
    Acepta un request body con el siguiente formato:

    ```json
    {
        "nuevaTarifaNormal": 10.5,
        "nuevaTarifaPausaExtendida": 14.4,
        "fechaEfectiva": "2024-12-01"
    }
    ```

- G:  
    **_/cercanos/{latitud}/{longitud}/{radio}_**  
    No acepta parametros por body 