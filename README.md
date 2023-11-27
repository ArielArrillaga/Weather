##Contacto
Nombre: Ariel Arrillaga.
Email: arielarrillagagm@gmail.com

##Weather
El proyecto "Weather" es una aplicación Java basada en Spring Boot que se encarga de cargar datos desde una API externa y almacenar información de ciudades en una base de datos. Su principal funcionalidad consiste en proporcionar información sobre la temperatura actual de una ciudad específica, permitiendo a los usuarios obtener detalles meteorológicos del día actual.

#Funcionalidades Principales:
Carga de Datos desde API Externa: El proyecto realiza solicitudes a una API externa (AccuWeather) para obtener información relevante sobre ciudades, en este caso se almacena el nombre y la key de cada ciudad, lo que nos permite hacer futuras consultas a la api.

Almacenamiento de Datos: Los datos recopilados de la API se almacenan en una base de datos, permitiendo un acceso rápido y eficiente a la información de las ciudades.

Consulta de Temperatura Actual: Los usuarios pueden ingresar el nombre de una ciudad y obtener la temperatura actual para dicha ubicación.

## Requisitos del Sistema

- Java 1.8
- Spring Boot 2.1.5.RELEASE
- Maven
- Lombok 1.18.30
- SLF4J 1.7.21
- H2 Database 1.4.200
- JUnit Jupiter 5.3.2

##Instrucciones de Instalación
Paso 1: Clonar el Repositorio desde GitHub
Abre tu terminal o línea de comandos.
Ve hacia el directorio donde quieres almacenar el codigo:
- cd c:/algun/directorio/

Ejecuta el siguiente comando para clonar el repositorio desde GitHub:
- git clone https://github.com/ArielArrillaga/Weather.git

Paso 2: Importar el Proyecto en Eclipse
Abre Eclipse.
Selecciona "File" -> "Import" -> "Maven" -> "Existing Maven Projects".
Navega hasta la ubicación donde clonaste el repositorio y selecciona el directorio del proyecto "weather".
Haz clic en "Finish" para importar el proyecto a Eclipse.

Paso 3: Colocar Archivos Adicionales
Descarga y descomprime el archivo archivosAdicionales.rar que se encuentra en /weather/src/main/resources/archivosAdicionales.rar.
Coloca los archivos descomprimidos en la ruta c:/app/weather/.

Paso 4: Ejecutar el Proyecto
Para ejecutar el proyecto desde Eclipse:

Haz clic derecho en el proyecto -> "Run As" -> "Maven build...".
En la ventana de configuración, introduce clean install en el campo "Goals" y haz clic en "Run".

Opción alternativa para ejecutar el proyecto desde la línea de comandos:
Abre la terminal o línea de comandos.
Navega hasta el directorio del proyecto clonado.
Ejecuta los siguientes comandos:
- mvn clean install
- mvn spring-boot:run

Paso 5: Importar coleccion de Postman.
Abre postman.
Cliquea la opción import -> Upload files y busca el archivo weather.postman_collection.json que se encuentra en /weather/src/main/resources/weather.postman_collection.json

#Funcionamiento de la app.
Para conocer la funcionalidades de la app debera leerse la documentación de la misma, la cual se encuentra en /weather/src/main/resources/DocumentacionWeather.pdf







