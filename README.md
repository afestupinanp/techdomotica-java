![Logo Tech Domótica](https://raw.githubusercontent.com/xman40100/techdomotica-java/master/src/resources/media/L1.png)

# Tech Domótica Java
Este es el repositorio de la versión de Java presentada el 6 de junio de 2019 a los instructores del SENA. Esta versión fue desarrollada entre el 18 de febrero hasta el 5 de junio del mismo año.

## Descripción
Tech Domótica es un software simulador de ambientes domóticos, capaz de gestionar múltiples dispositivos (virtuales, al ser un simulador), en el cual se puede administrar ciertos aspectos de ellos, como el estado del dispositivo, temperatura, encendido/apagado, entre otras propiedades.

Tech Domótica cuenta con sincronización a los cambios que se realicen desde la versión de Android. Si un dispositivo cambia sus condiciones desde la app, se verán reflejadas en la versión de escritorio.

Para ejecutar la aplicación, se debe de contar con la base de datos montada. La base de datos fue hecha para MariaDB, por lo que es recomendado usar un gestor de base de datos como phpMyAdmin para realizar los cambios necesarios.

## Documentos
* SRS del proyecto: [Disponible en Google Drive.](https://docs.google.com/document/d/1s-fPnOE5YMZHdId5FKCmrs7rSYZ_qTbNhT2ADDz6-E4/edit?usp=sharing)
* Plan de calidad del proyecto: [Disponible en Google Drive.](https://docs.google.com/document/d/1AiHI5LY3GIgdPPMwh6N3BHj-9fUpDo1dB0uSSSvrx8Y/edit?usp=sharing)

## Enlaces
* Versionamiento: Ver en la parte de [releases.](https://github.com/xman40100/techdomotica-java/releases)
* Documentación de código: Cierta parte del código está documentado, puesto que el proyecto en general se dividió entre múltiples miembros. [Click aquí para ir a la Wiki.](https://github.com/xman40100/techdomotica-java/wiki)

## Compilación
Para compilar Tech Domótica, se debe de tener el JDK 8 o superior instalado, y se debe de usar el IDE NetBeans 8.0 o superior, y asegurarte de que el JDK usado durante el momento a compilar sea el apropiado, debido a que el JDK usado para el desarrollo fue ajustado a OpenJDK 8. Si es necesario, es importante cambiar entonces al JDK de Oracle.

El build target de NetBeans debe ser cambiado en caso de que no se posea OpenJDK 8.

## Errores conocidos
* El programa en la parte de sensores no está correctamente terminado, y tiene algunos fallos que provocan que el programa deje de funcionar como se espera.
