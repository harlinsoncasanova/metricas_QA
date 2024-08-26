# metricas_QA
Instructivo para Configurar el Ambiente de Desarrollo en Eclipse 2020-03 
con SQL Server 2017 
1. Requisitos Previos 
Antes de comenzar, asegúrate de tener instalados los siguientes componentes en tu máquina 
de desarrollo: 
• Java Development Kit (JDK): Versión 17 o superior. Puedes descargarlo desde 
Oracle JDK o usar OpenJDK. 
• Eclipse IDE for Java Developers: Versión 2020-03. Puedes descargarla desde 
Eclipse Downloads. 
• Apache Maven: Es recomendable tener Maven instalado en el sistema para 
gestionar las dependencias y construir el proyecto. Descargar Maven. 
• Git: Para clonar el repositorio y manejar el control de versiones. Descargar Git. 
• SQL Server 2017: Instala SQL Server 2017 y asegúrate de que está ejecutándose. 
• Microsoft JDBC Driver para SQL Server: Asegúrate de tener el controlador 
JDBC de Microsoft para SQL Server. Puedes descargarlo desde Microsoft JDBC 
Driver for SQL Server. 
2. Clonación del Repositorio 
Clona el repositorio del proyecto desde tu repositorio remoto (por ejemplo, GitHub). Abre 
una terminal y ejecuta: 
bash 
Copiar código 
git clone https://github.com/tu-usuario/tu-repositorio.git 
cd tu-repositorio 
3. Importar el Proyecto en Eclipse 
1. Abrir Eclipse y Crear un Nuevo Espacio de Trabajo: 
o Inicia Eclipse y selecciona o crea un nuevo espacio de trabajo. 
2. Importar Proyecto Maven: 
o Ve a File > Import.... 
o Selecciona Maven > Existing Maven Projects y haz clic en Next. 
o Navega hasta la ubicación del repositorio clonado y selecciona la carpeta 
raíz del proyecto. Eclipse detectará automáticamente el archivo pom.xml. 
o Haz clic en Finish para completar la importación. 
4. Configuración del Proyecto para SQL Server 2017 
1. Agregar Dependencia de JDBC para SQL Server en pom.xml: 
Abre el archivo pom.xml y agrega la dependencia del controlador JDBC de 
Microsoft para SQL Server en la sección  
<dependency> 
<groupId>com.microsoft.sqlserver</groupId> 
<artifactId>mssql-jdbc</artifactId> 
<version>9.4.1.jre8</version> 
</dependency> 
2. Configurar el archivo application.properties: 
Configura el archivo src/main/resources/application.properties para 
conectarte a SQL Server 2017. Ejemplo de configuración: 
properties 
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=
 tu_base_de_datos 
spring.datasource.username=tu_usuario 
spring.datasource.password=tu_contraseña 
spring.datasource.driver-class
name=com.microsoft.sqlserver.jdbc.SQLServerDriver 
spring.jpa.hibernate.ddl-auto=update 
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLSe
 rver2012Dialect 
Asegúrate de reemplazar localhost, tu_base_de_datos, tu_usuario, y 
tu_contraseña con los valores adecuados para tu entorno. 
5. Actualizar Dependencias de Maven 
• Haz clic derecho en el proyecto en el explorador de proyectos y selecciona Maven > 
Update Project.... 
• Marca Force Update of Snapshots/Releases y haz clic en OK. 
6. Ejecución de la Aplicación 
1. Ejecutar la Aplicación: 
o Haz clic derecho en el proyecto y selecciona Run As > Spring Boot App. 
Esto iniciará el servidor Spring Boot y desplegará la aplicación. 
2. Verificar la Conexión a SQL Server: 
o Asegúrate de que tu SQL Server esté ejecutándose y de que los parámetros 
de conexión en application.properties estén correctamente 
configurados. 
o Accede a http://localhost:8080 en tu navegador para verificar que la 
aplicación se esté ejecutando correctamente. 
7. Ejecución de Pruebas 
1. Pruebas Unitarias: 
o Haz clic derecho en el proyecto y selecciona Run As > Maven test para 
ejecutar las pruebas unitarias. 
2. Pruebas de Integración: 
o Asegúrate de que las bases de datos y otros servicios necesarios estén en 
funcionamiento antes de ejecutar las pruebas de integración. 
8. Documentación Adicional 
Para más detalles sobre el uso de la aplicación, la estructura del código, y cómo extender o 
modificar funcionalidades, consulta los documentos proporcionados en el repositorio, como 
diagramas de clases, secuencia, componentes, casos de uso y objetos. 
9. Despliegue en Entorno de Producción 
Para desplegar la aplicación en un entorno de producción con SQL Server 2017, sigue los 
pasos adecuados para tu infraestructura. Asegúrate de configurar correctamente el entorno, 
incluidas las variables de entorno, la configuración del servidor de aplicaciones y las bases 
de datos.

