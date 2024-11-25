# Arquitectura WEB, Trabajos practicos entregables.

### Integrantes
Braian Ricci (braianricci@gmail.com), Esteban Villanueva (evillanueva@alumnos.exa.unicen.edu.ar).

## Trabajo Practico Especial Primera Entrega

### Tecnologias y arquitectura
- **Arquitectura de Microservicios** usando **Spring Boot**
- **Maven** para la gestión de dependencias
- **Bases de datos**: **MySQL** (para datos relacionales) y **MongoDB** (para datos NoSQL)
- **Docker** para la virtualizacion
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
   
5. Para ver un ejemplo de Swagger acceder a 'http://localhost:8081/swagger-ui/index.html' (Usuario-Service)

## Endpoints Especiales

A continuación, se describen los endpoints clave del sistema, organizados por funcionalidad:

### A. Monopatines - Uso por Kilómetros
- **Endpoint:** `/monopatines/uso-por-kilometros`
- **Método:** `GET`
- **Descripción:** Genera un reporte del uso de monopatines según los kilómetros recorridos, con opción para incluir el tiempo de uso en el reporte.
- **Body de Ejemplo:**
  
  ```json
  {
      "incluirTiempo": true
  }
  ```

### B. Cuentas - Cambiar Estado
- **Endpoint:** `/cuentas/{id}/cambiar-estado`
- **Método:** `PATCH`
- **Descripcion:** Cambia el estado de una cuenta (habilitada o deshabilitada) basado en el ID del usuario.
- **Body de ejemplo:**
    
  ```json
  {
      "habilitada": false
  }
  ```
  
### C. Viajes - Monopatines con Más Viajes
- **Endpoint:** `/viajes/monopatines-con-mas-viajes`
- **Método:** `GET`
- **Descripcion:** Devuelve una lista de monopatines que han realizado más viajes durante un año específico y que superan un número mínimo de viajes.
- **Body de Ejemplo:**
    
    ```json
    {
        "viajesMinimos": 20,
        "anio": 2023
    }
    ```
    
### D. Viajes - Total Facturado
- **Endpoint:** `/viajes/total-facturado`
- **Método:** `GET`
- **Descripción:** Calcula el total facturado por todos los viajes en un rango de meses dentro de un año determinado.
-**Body de Ejemplo:**
     
     ```json
     {
        "anio": 2023,
        "desdeMes": 4,
        "hastaMes": 10
     }
     ```
  
### E. Monopatines - Total en Operación y Mantenimiento
- **Endpoint:** `/monopatines/total-en-operacion-y-mantenimiento`
- **Método:** `GET`
- **Descripción:** Retorna el total de monopatines que están actualmente en operación o en mantenimiento.
- **Body:** No requiere parámetros.
 
### F. Viajes - Ajustar Precios
- **Endpoint:** `/viajes/ajustar-precios`
- **Método:** `PUT`
- **Descripción:** Permite ajustar las tarifas normales y de pausa extendida para los viajes, a partir de una fecha efectiva.
- **Body de Ejemplo:**
      
     ```json    
     {
        "nuevaTarifaNormal": 10.5,
        "nuevaTarifaPausaExtendida": 14.4,
        "fechaEfectiva": "2024-12-01"
     }
     ```

### G. Monopatines - Buscar Cercanos
- **Endpoint:** `/monopatines/cercanos/{latitud}/{longitud}/{radio}`
- **Método:** `GET`
- **Descripción:** Devuelve una lista de monopatines disponibles dentro de un radio específico desde una ubicación dada (latitud y longitud).
- **Body:** No requiere parámetros por body, los parámetros se envían en la URL. 
