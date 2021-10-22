# Base de datos

## Instancia MySQL

Para utilizar la base de datos MySQL que se usó en el desarrollo de este software, es necesario tener una instancia de MySQL corriendo en el equipo donde se correrá la aplicación. Los parámetros de conexión con la base de datos pueden ser modificados en el archivo `.env` ubicado en la raíz del proyecto.

## Docker compose

En el desarrollo de la aplicación se utilizó Docker, con un archivo `docker-compose.yml` en donde se genera la instancia de MySQL necesaria de forma automática, tomando el archivo `dump.sql` y, por lo tanto, generando la base de datos de forma automática. 

Para utilizar esta opción, es necesario [instalar docker](https://docs.docker.com/get-started/#download-and-install-docker). Una vez instalado en el equipo, se debe ejecutar el siguiente comando por consola (estando en el directorio raíz de este proyecto):

```shell
docker-compose  --env-file ./.env -f ./database/docker-compose.yml up -d
```

Y listo, la instancia MySQL debería estar corriendo exitosamente con los parámetros especificados en el archivo `.env`.