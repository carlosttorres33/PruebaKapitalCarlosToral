Esta es una aplicación de prueba para la posición de Android Developer en Kapital Bank


![Captura de pantalla 2025-03-17 a la(s) 1 54 13 p m](https://github.com/user-attachments/assets/27adaf21-6d75-470c-bfc1-8623d19666b4)


Para el desarrollo de la aplicación utilicé la Arquitectura MVVM y los principios del Clean Architecture para separar el proyecto por capas y tener cada funcionalidad separada en su lugar correspondiente.

El consumo del servicio se hizo a mediante Retrofit y Glide para pintar las imagenes en la interfaz

Los datos se almacenan en ROOM después de ser consumidos de la Api y estos datos descargados (o actualizados) en ROOM son los mostrados por el usuario cada vez, para respetar el principio de Single Source Of Truth.

Todo este manejo de información se hace en un hilo secundario para no afectar el hilo principal de la applicación mediante el uso de corrutinas y Flows, mostrando siempre un indicador cuando se esté ejecutando un proceso en segundo plano.

Se implementó DaggerHilt para la inyección de dependencias en todo el proyecto

La UI fué diseñada con XML, usando los diferentes Layouts que este nos provee para garantizar un diseño adaptable a los diferentes dispositivos, ademas del uso de RecyclerViews para el manejo de listas de manera optimizada.


En ella se hace consumo de la api gratuita de Yu-Gi-Oh! (https://db.ygoprodeck.com/api/v7/cardinfo.php) mediante la librería de Retrofit para poder leer la información de las Cartas de Yu-Gi-Oh! que nos devuelve en forma de JSON.

Se manejan los errores en caso de que exista alguno y ofrece la oportunidad de reintentar la petición

![Captura de pantalla 2025-03-17 a la(s) 1 55 13 p m](https://github.com/user-attachments/assets/7e424296-099e-4b62-adb7-f825d5a5af76)





Se muestra un pequeño CircularProgressIndicator mientras esperamos la respuesta del servicio (En la imagen no se aprecia del todo)


![Captura de pantalla 2025-03-17 a la(s) 1 55 52 p m](https://github.com/user-attachments/assets/bb38e258-4387-4d74-b8c9-cf555c11bb5c)

Posteriormente se almacena la información de las Cartas en una base de datos local con ROOM para mostrarlas al usuario en una lista horizontal.

Puedes deslizar de izquierda a derecha para ver todas las cartas obtenidas.

Debajo se encuentra la lista de cartas favoritas, la cual, en caso de no tener ninguna carta agregada estará vacía y mostrará un mensaje.

Al hacer click en alguna de las tarjetas mostradas podremos acceder a otra pantalla para ver sus detalles.


![Captura de pantalla 2025-03-17 a la(s) 1 56 17 p m](https://github.com/user-attachments/assets/3238b95b-e6a3-44c8-9a2a-98ce03cb9cef)


Gracias al uso de ROOM la información es accesible aunque no se cuente con conexión a internet, por lo que la aplicación funciona de manera online y offline.
La carga de las imagenes se hace a través de la libreria de Glide. En caso de no contar con internet se mostrará un placeholder en vez de la imagen de internet.

Al navegar a los detalles de la carta veremos información como su nomre, tipo, su descripción y un botón para agregar dicha carta a Favoritas.
El botón muestra el estado de favorito de la carta para ver si la tenemos agregada a favoritos o no.


![Captura de pantalla 2025-03-17 a la(s) 1 56 29 p m](https://github.com/user-attachments/assets/f4581ab2-e200-4eaf-bcd5-8bd0be282965)
![Captura de pantalla 2025-03-17 a la(s) 1 56 42 p m](https://github.com/user-attachments/assets/17fedfa6-3712-45ef-81db-b9ea099be1ed)

Al hacer click en el icono para agregar a Favoritos dicha carta será guardada en esa lista y al volver a la pantalla principal podremos ver la lista de Favoritas que anteriormente se encontraba vacía con su nuevo contenido.


![Captura de pantalla 2025-03-17 a la(s) 2 26 46 p m](https://github.com/user-attachments/assets/1d6d3419-beca-4208-b6cd-10c64cf34050)


Puedes agregar tantas cartas a favoritos como quieras y consultarlas todo el tiempo.

Link del APK de la aplicación final: https://github.com/carlosttorres33/PruebaKapitalCarlosToral/releases/download/AppWorking/pruebaKapital1.0.apk 
(También encontrarán el APK en los releases del repositorio)
