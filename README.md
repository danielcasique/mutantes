# Mutantes

## Arquitectura
* Capa Aplicacion
Contiene una interfaz llamada DnaHandler que define los comportamiento posibles (save / stats). Actualmente solo tiene una implementacion local. Pero esta implementacion puede cambiar agregando la funcionalidad de utilizar una BD.
* Capa Dominio
Se encuentra el DTO(input DnaDto), entity (DnaSecuence), output (Stats) y la clase HumanDna que contiene la implementacion para determinar si una secuencia de ADN contiene alguna muestra de mutante
* Capa servicios
Define los servicios posibles

## Como compilar
Clonar el projecto y ejecutar "mvn package"

## Como ejecutar
Luego de construir el jar, en la carpeta target ser crea el .jar, ejecutar "java -jar mutantes-0.0.1-SNAPSHOT.jar"; la aplicacion esta disponible en el puerto 8080
