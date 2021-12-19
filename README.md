# Prueba de ingreso al equipo de desarrollo de SAIMYR
Sí estás aquí es porque estás siendo considerado para pertenecer al equipo de desarrolladores web de SAIMYR. A continuación,
te presentamos un pequeño reto que nos ayudará a conocer adecuadamente tus skills técnicos. 

¡Éxitos! 

### Tabla de Contenido

* [Prerrequisitos](#prerequisites)
* [Para empezar](#quickstart)
    - [Utilizando IntelliJ](#intellij) 
    - [Utilizando Eclipse](#eclipse)
* [Reto](#reto)
  - [Actividad 1](#actividad-1)
  - [Actividad 2](#actividad-2)
  - [Actividad 3](#actividad-3)
  - [Actividad 4](#actividad-4)
  - [Actividad 5](#actividad-5)

<a name="prerequisites"></a>
## Prerrequisitos
* [Java JDK 11 o superior](https://adoptopenjdk.net/)
* [IntelliJ](https://www.jetbrains.com/es-es/idea/download/) ó [Eclipse](https://www.eclipse.org/downloads/)
* [Git](https://git-scm.com/downloads)

<a name="quickstart"></a>
## Para empezar
Lo primero que debes hacer es clonar el proyecto para poder empezar a trabajar sobre él. Desde la consola de git en la
carpeta de tu preferencia debes ingresar los siguientes comandos: 
```
git init
git clone https://git.saimyr.com/desarrollo-web/prueba
```
<a name="intellij"></a>
### Utilizando IntelliJ
Abrir el IDE y en la sección de bienvenida, al lado derecho, seleccionar la opción "Import Project".

En el menú contextual que se abre, buscar el archivo pom.xml del proyecto que acabas de descargar de git. IntelliJ 
descargará todas las dependencias necesarias para que empieces a trabajar en el proyecto.    

<a name="eclipse"></a>
### Utilizando Eclipse
Abrir el IDE y crear un workspace. Una vez allí, en el menú de **File** debes seleccionar la opción _import_. 

En el menú contextual que aparece debes buscar y seleccionar el folder que dice "Maven" y elegir la opción _Existing Maven Projects_.

En la ventana que se abre debes seleccionar a través del botón _Browse..._ el directorio raíz del proyecto y ver que en 
la lista de "Projects" aparezca el pom.xml del proyecto. Por último, dar clic en el botón Finish, con lo que el IDE descargará todas 
las dependencias necesarias para que empieces a trabajar en el proyecto.  

<a name="reto"></a>
## Reto
Al importar el proyecto en tu IDE te encontrarás con un proyecto en donde en el folder src/main/resources están los siguientes
archivos:

- **application.properties**: Es el archivo de configuración de Spring Boot. 
- **schema.sql**: En este archivo se encuentra la creación de la tabla BOOKS sobre la cual se trabaja en este reto.
- **data.sql**: En este archivo se encuentran los registros iniciales con los que cuenta la tabla BOOKS para realizar las 
operaciones solicitadas.

Así mismo, el folder src/main/java cuenta con la clase principal _BookstoreApplication_ (que es la que se ejecuta) y 
tres paquetes llamados _co.saimyr.bookstore.domain_, _co.saimyr.bookstore.persistence_ y _co.saimyr.bookstore.web_

- **co.saimyr.bookstore.domain**:
    - _co.saimyr.bookstore.domain.repository_: 
        - _BookRepository_: Interface que contiene TODAS las operaciones que debe implementar posteriormente el repositorio.
    - _co.saimyr.bookstore.domain.service_:
        - _BookService_: Es la clase de servicio de la aplicación. Es a donde el controlador REST debe realizar las peticiones.
- **co.saimyr.bookstore.persistence**
  - _BookRepositoryImpl **(ESTA CLASE INICIALMENTE CONTIENE ERRORES)**_: Es la implementación de la interface _BookRepository_,
    internamente inyecta la interface _CrudBookRepository_ para comunicarse con la base de datos. Para corregir los errores
    con lo que se crea esta clase debes implementar los métodos que no han sido implementados.
  - _co.saimyr.bookstore.persistence.crud_:
    - _CrudBookRepository_: Es la interface que hereda de CrudRepository y se encarga de realizar sentencias en la
  base de datos, ya sea mediante Query methods o @Query.
  - _co.saimyr.bookstore.persistence.entity_:
    - _BookEntity_: Es la entidad sobre el cual se realizarán todas las operaciones. También hace las veces de 
  representación en Java (Entity bean) de la tabla en la base de datos.
- **co.saimyr.bookstore.web**
    - _BookController_: Es el controlador REST de la aplicación.
 
El reto consta de 5 actividades:  

<a name="actividad-1"></a>
### Actividad 1 (Hacer refactor al proyecto para orientar al dominio)
El proyecto actualmente está trabajando en todas sus capas con las entidades (exponiendo el BookEntity) mediante el controller.
Cómo bien sabes esto representa una mala práctica ya que no es bueno exponer nuestros entities al exterior. 

La actividad consiste en modificar lo que sea necesario para que la aplicación quede orientada al dominio (Debes añadir
clases de dominio y/o DTO's). Para esto puedes ayudarte de herramientas como MapStruct o Dozer.

<a name="actividad-2"></a>
### Actividad 2 (Controlar las excepciones del proyecto)
El proyecto no posee control de excepciones y cualquier error que ocurra provocará un error 500 que queremos evitar.

La actividad consiste en agregar el control de excepciones con @RestControllerAdvice para las excepciones (que también debes crear)
que se lanzan desde el dominio.

<a name="actividad-3"></a>
### Actividad 3 (Recuperar alfabéticamente)
En la clase BookRepositoryImpl existe un método que recupera todos los libros de la base de datos (Esto está expuesto en 
la API mediante _/api/books/all_).

La actividad consiste en modificar lo que sea necesario para que se recuperen los libros ordenados alfabéticamente. 

<a name="actividad-4"></a>
### Actividad 4 (Recuperar por Editorial)
Crear un método GET en el controlador REST (y todo lo que haga falta) para consultar los libros dado un nombre de editorial
(publisher).
 
<a name="actividad-5"></a>
### Actividad 5 (Eliminar un Libro)
Crear un método DELETE o GET en el controlador REST (y todo lo que haga falta) para eliminar un libro dado su ISBN.

Tip: Deberías crear un método en la interface _BookRepository_ que recupere un libro por su ISBN.
