# Arquitectura WEB, Trabajos practicos entregables.

### Integrantes
Braian Ricci (braianricci@gmail.com), Esteban Villanueva (evillanueva@alumnos.exa.unicen.edu.ar).

## Trabajo Practico Especial Primera Entrega

### Tecnologias y arquitectura
- **Microservicios** implementados en **Spring Boot**
- **Maven** para la gestión de dependencias y el ciclo de vida del proyecto
- **Docker** para la contenedorización de servicios
- **Eureka** para el registro de microservicios
- **API Gateway** para enrutar peticiones
- **Feign Clients** para la comunicación entre microservicios

## Cómo Ejecutar el Proyecto

1. Clona el repositorio:
    ```bash
    git clone https://github.com/braianricci/arq-tpe
    cd tpespecial
    ```

2. Compila el proyecto con **Maven**:
    ```bash
    mvn clean install
    ```

3. Construye y levanta los contenedores Docker:
    ```bash
    docker-compose up --build
    ```

4. Accede a la aplicación en `http://localhost:8080`


## Endpoints especiales para los servicios/reportes del punto 3
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
