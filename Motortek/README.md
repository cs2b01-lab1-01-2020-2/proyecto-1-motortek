# El aplicativo Motortek :iphone:
## Integrantes ✒️
* **Juan Pablo Miguel Lozada Velasco** - [IWeseI - juan.lozada@utec.edu.pe]
* **Claudio Alejandro Echarre Lopez** - [cecharre - claudio.echarre@utec.edu.pe]
* **Jorge Harlop Flores Berrio** - [HarlopFlores - jorge.flores.b@utec.edu.pe]
* **Jose Miguel Sanchez Atahualpa** - [josemiguel-AAA - jose.sanchez.a@utec.edu.pe]
## Definición del Problema :books:
La empresa Motortek desea ir un paso más en cuanto a su servicio de atención al cliente y se pregunta como podria brindar este servicio de una forma más práctica y cómoda. Es así, que nuestro equipo propone la creación de un aplicativo Android.

Este aplicativo deberá funcionar de tal manera que se puedan realizar las mismas operaciones que ofrece la página web y tener una vista agradabale y simple para una mejor experiencia de usuario. 

## Tecnologías Involucradas :wrench:

### Front-End:
- XML
- Android
### Back-End:
- Java
### Sistema Operativo:
- Android Marshmallow 6.0

## Aspectos Técnicos :wrench:
A continuación se presentan las caracteríticas implementadas dentro de nuestro proyecto.
## Layouts
### activity_main:
![activity](https://user-images.githubusercontent.com/71047903/102825970-d5c82900-43ad-11eb-8da6-96d5fc27002e.PNG)
### activity_log_in:
![log_in](https://user-images.githubusercontent.com/71047903/102826016-e2e51800-43ad-11eb-823c-9a06395404f8.PNG)

### activity_register:
![register](https://user-images.githubusercontent.com/71047903/102826038-ec6e8000-43ad-11eb-9095-131531e7293c.PNG)

### activity_insert_auto:
![insert auto](https://user-images.githubusercontent.com/71047903/102826055-f3958e00-43ad-11eb-9d31-794910103c22.PNG)

### activity_autos:
![autos2](https://user-images.githubusercontent.com/71047903/102826096-014b1380-43ae-11eb-8723-0f0471fd6c05.PNG)

### activity_insert_servicio:
![insert servicio](https://user-images.githubusercontent.com/71047903/102826122-0a3be500-43ae-11eb-9e63-f3520de6e03a.PNG)

### activity_servicios:
![servicios](https://user-images.githubusercontent.com/71047903/102826752-4cb1f180-43af-11eb-92bd-ed15abd68539.PNG)

### activity_detalles:
![detalles](https://user-images.githubusercontent.com/71047903/102826167-20e23c00-43ae-11eb-9ad0-4b76f4ebe9e7.PNG)

## Activities 

### MainActivity:
Activity principal que posee 4 botones que dirigen al usuario al log_in, register, insert_auto e insert_servicio.
### Autos:
Muestra los autos especificos del cliente que se encuentra utilizando el sistema. Permite redirigir cada auto con sus respectivos servicios asociados.
### InsertAuto:
Guarda en un fichero los datos de un auto específico.
### Servicios:
Muestra todos los servicios de un auto específico.
### InsertServicio:
Guarda en un fichero un servicio.
### Log_in:
Permite la autenticación de usuarios dentro del sistema. De este modo, tiliza un fichero para comprobar que el usuario que desea entrar al sistema se encuentre registrado.
### Register:
Registra al usuario que desea crearse una cuenta para usar el servicio. Es así, que la data es guardada en un fichero y al usuario se le permite iniciar sesión en el aplicativo.
