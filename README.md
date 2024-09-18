# Combina la potencia de Firebase con la seguridad de SQLite para ofrecerte una experiencia personalizada. Recibe notificaciones, guarda tu ubicación y accede a tu información en cualquier momento.

## Definición del proyecto: 
### Aplicación que utiliza los servicios de **Firebase** para recibir notificaciones push, las cuales se almacenaran en una pantalla de notificaciones usando **Room**. También se podrán enviar notificaciones de manera local para previsualizar su recepción. La aplicación tendrá que solicitar los **permisos de geolocalización** al ser instalada y en una de las pantallas **mostrar las coordenadas actuales** al momento de presionar el botón. Se usarán los **Navigation Components** para navegar entre los 3 **Fragments** usando la barra de navegación. 

![image](https://github.com/user-attachments/assets/cb4d98d3-0d84-4433-8933-634c0c7e5bdc)

## Tecnologías usadas:
- Lenguaje de programación **Kotlin**.
- **Firebase** para el servicio de notificaciones.
- **Room** para almacenar las notificaciones recibidas.
- Servicios de **Geolocalización**.
- Gestión de **permisos**
- Arquitectura **MVVM**.
- **LiveData**.
- **Corrutinas**.
- **Navigation Components**.
- **RecyclerViews** para la pantalla de notificaciones.
- **Fragments** para las pantallas.

# Recibe notificaciones desde Firebase 🔥
### La aplicación está diseñada para recibir notificacioens enviadas desde Firebase, si se requiern pruebas el token se puede encontrar en los **logs**
![image](https://github.com/user-attachments/assets/55215906-6b7e-4595-9673-233c04f9c433)

# Envíalas también de forma local!
### Se añadió una pantalla para mandar notificaciones de forma local y así previsualizar la recepción de las push.
![image](https://github.com/user-attachments/assets/d7edeb4d-7fcc-48ce-ab5f-26464a1eed68)

# Se guardan con Room para mostrarse en la pantalla de notificaciones
### Se usó **Room** y **RecyclerView** para la pantalla donde se mostrarán todas las notificaciones recibidas, tanto locales como de Firebase.
![image](https://github.com/user-attachments/assets/02e5158d-d970-47e7-9a20-6852b8e5539c)

# ¡Obten tus coordenadas actuales!
### Se usan los servicios de localización para obtener las coordenadas y pintarlas en pantalla.
![image](https://github.com/user-attachments/assets/d87da01f-ed26-46ab-9037-0777fd964ee0)

# Se te solicitaran permisos de ubicación
### Se gestionan los permisos necesarios para obtener la localización.
![image](https://github.com/user-attachments/assets/b7301562-bce3-4fd9-ba26-cb0a0b2b7de2)

