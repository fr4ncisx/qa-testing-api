![Static Badge](https://img.shields.io/badge/JDK%2021-black?style=flat&logo=openjdk&labelColor=black)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot%203.5.0-6DB33F?logo=springboot&logoColor=fff)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=fff)
![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff)
## QA TESTING API
QA TESTING API es una API REST desarrollada en Java 21 con el framework Spring Boot 3.5.0.

Utiliza MySQL como sistema de almacenamiento de datos y está preparada para correr en contenedores mediante Docker. **(PENDIENTE)**

La documentación interactiva de la API está disponible a través de Swagger UI (OpenAPI 3.1.0).

## Características Principales

- Implementación de API REST usando Spring Boot.
- Persistencia de datos con MySQL.
- Exposición de endpoints documentados mediante Swagger.
- Arquitectura desacoplada ideal para entornos de pruebas (QA).
- Preparación para contenedores Docker.



## Requisitos Técnicos

- Java 21
- MySQL 8+
- Docker (opcional)

## Cómo Ejecutar

### 1. Clona el repositorio

```bash
git clone https://github.com/tu-usuario/qa-testing-api.git
```

### 2. Configura la base de datos (MySQL) y otras variables de entorno
Variables de entorno:
```
MYSQL_URL=jdbc:mysql://localhost:3306/api_test?createDatabaseIfNotExist=true
MYSQL_USER=El usuario mysql (generalmente root)
MYSQL_PASSWORD=Contraseña del usuario
SECRET=Clave secreta para verificar el token de acceso (Puede contener letras y numeros)
EXPIRATION_MINUTES=Tiempo de expiración en minutos sin puntos ni comas (Ej: 5)
```

### 3. Construye y levanta la aplicación
```shell
./mvnw spring-boot:run
```

### 4. Accede a Swagger UI
Una vez iniciada la aplicación, puedes acceder a la documentación Swagger en:

[Swagger UI Link (Localhost)](http://localhost:8080/swagger-ui/index.html)

En el que vas a encontrar todos los endpoints disponibles

### La API estará disponible en el siguiente link

http://localhost:8080


