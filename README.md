## API Rest Documents

Autor: **Cristian Fernandez**

Version Java 17

El proyecto utiliza los algoritmos "SHA-256" y "SHA-512"

Se guarda en base de datos con el algoritmo pasado a **hexadecimal**

Base de datos: PostgreSql

Configuraciones iniciales:
- En el archivo "application.properties" se tiene que modificar para el uso de base de dato, user y password propias. Se puede mediante las variables de entorno: DB_NAME, DB_USER, DB_PASS o directamente ponerlas en el archivo application.properties


## Uso de la API

- Las peticiones se hacen mediante form-data
- Las peticiones para lista de documentos y documento por su hash comparten la misma url pero va a responder dependiendo si se le envia los parametros correctos por form-data o ninguno
- Al intentar subir el mismo archivo, se le actualizar√° la fecha mostrandos√© en la respuesta y en caso que tenga otro nombre tambien se cambiar√° (Si es la primera vez que se sube el archivo entonces no se va a mostrar la fecha en la respuesta)

## Endpoints
- POST - api/documents/hash - Subida de archivos
- GET - api/documents - Lista de archivos        
- GET - api/documents - Mostrar un archivo por su hash (params = {"hashType", "hash"})

## Http Status de la API
En las respuestas correctas se puede visualizar el mensaje en el header y tambien retorna el numero de http status con su significado en status.

Excepciones:
- 400 - Si se hace la peticion sin ingresar un archivo o el archivo esta vacio o si el hashType no es correcto (Algoritmos que se utilizan en el proyecto).
- 404 - Si el hash del archivo no se encuentra en la base de datos.
