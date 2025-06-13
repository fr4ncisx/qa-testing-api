![Static Badge](https://img.shields.io/badge/Java%2021-%23f89820?style=for-the-badge&logoColor=red)
![Static Badge](https://img.shields.io/badge/Spring%20Boot%203.5.0-%236DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Static Badge](https://img.shields.io/badge/mysql-%234479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Static Badge](https://img.shields.io/badge/docker-%232496ED?style=for-the-badge&logo=docker&logoColor=white)
## QA TESTING API
QA TESTING API es una API REST desarrollada en Java 21 con el framework Spring Boot 3.

Utiliza MySQL como sistema de almacenamiento de datos y está preparado para levantar el contenedor del proyecto, **no incluye MySQL**

La documentación interactiva de la API está disponible a través de Swagger UI.

Está RESTful API está principalmente enfocada para QA que se deseen iniciar en el testing funcional de desarrollo en backend.

## Características Principales

- Implementación de API REST usando Spring Boot.
- Persistencia de datos con MySQL.
- Exposición de endpoints documentados mediante Swagger.
- Arquitectura desacoplada ideal para entornos de pruebas (QA).
- Creador de imagen y contenedor de docker, mas eliminación de contenedor.

## Requisitos Técnicos

#### - [JDK 21](https://www.oracle.com/ar/java/technologies/downloads/#jdk21-windows)
#### - [MySQL 8+](https://dev.mysql.com/downloads/installer/)
#### - [Docker (opcional)](https://docs.docker.com/desktop/setup/install/windows-install/)
#### - Maven (No es requerido ya que lo trae embebido, pero se puede usar también)

## Cómo Ejecutar

### 1. Clona el repositorio

```
git clone https://github.com/fr4ncisx/qa-testing-api.git
```

### 2. Configura las variables de entorno del proyecto
Variables de entorno:
```
MYSQL_URL=jdbc:mysql://localhost:3306/api_test?createDatabaseIfNotExist=true
MYSQL_USER=El usuario mysql (generalmente root)
MYSQL_PASSWORD=Contraseña del usuario
SECRET=Clave secreta para verificar el token de acceso (Puede contener letras y numeros)
EXPIRATION_MINUTES=Tiempo de expiración en minutos sin puntos ni decimales (Ej: 5)
```

### 3. Construye y levanta la aplicación
> [!NOTE]
> Recuerda que necesitas anteriormente crear las variables de entorno en tu Sistema Operativo, sino les lanzará un error en ejecución.

```
./mvnw spring-boot:run
```

### 4. Accede a Swagger UI
Una vez iniciada la aplicación, puedes acceder a la documentación Swagger en:

[Swagger UI Link (Localhost)](http://localhost:8080/swagger-ui/index.html)

En el que vas a encontrar todos los endpoints disponibles

## Alternativa Docker

### 1. Crear imagen

```
docker build -t simple-api .
```

### 2. Crear un archivo con extensión .env

> [!NOTE]
> Debes crear un archivo *.env que debes cambiar los valores a los que sean de tu necesidad y usuario y contraseña de tu MySQL
> Usa como url 'host.docker.internal' ya que el contenedor de `docker` se va a conectar hacia la base de datos de tu computadora (De cierta manera estan en lugares distintos)

`Plantilla archivo .env`
```
MYSQL_URL=jdbc:mysql://host.docker.internal:3306/api_test?createDatabaseIfNotExist=true
MYSQL_USER=root
MYSQL_PASSWORD=tu_contraseña
SECRET=miClaveSecreta123
EXPIRATION_MINUTES=5
```

> [!CAUTION]
> Debés guardar este archivo en la raíz de la carpeta

### 3. Levantar contenedor de Docker

> [!CAUTION]
> Debes reemplazar `nombre-archivo.env` por el nombre de tu archivo, desde ahí va a consumir docker las variables de entorno para levantar el contenedor

##### Inicio contenedor
```
docker run --name qa-testing --env-file nombre-archivo.env -p 8080:8080 simple-api
                                        ^^^^^^^^^^^^^^^^^^
```

### 4. Eliminar contenedor
Una vez realizada todas las pruebas hay dos alternativas
1. Borrar el contenedor desde Docker Desktop en la sección `Containers`
2. Eliminar contenedor por consola
   1. Mostrar todos los contenedores de docker ``docker ps -a``
   2. Buscar el atributo `NAMES` del contenedor que querés eliminar (debería ser qa-testing)
   3. Eliminar el contenedor por id usando `docker rm qa-testing` y listo ya elimina el contenedor

> [!CAUTION]
> Recuerda que cada nuevos cambios que traigas del repositorio remoto o hagas cambios en el código es necesario borrar el contenedor ya que te va a dar error porque el contenedor con ese nombre ya existe, si solo necesitás levantar el contenedor en otro momento sigue leyendo el siguiente item.

### 5. Reiniciar contenedor existente
Usar el comando:

``docker start qa-testing``

Eso va a volver a levantar un contenedor existente con el nombre qa-testing que es el nombre que le asignamos en [Levantar contenedor de Docker](#3-levantar-contenedor-de-docker)
precisamente en el atributo `--name` y eso sería todo!

# Happy Testing ✅