# Desarrollo Basado en Plataformas Sección 2.02 - Proyecto 1
## Nombre del Proyecto: Motortek

## Integrantes: 
- Juan Pablo Miguel Lozada Velasco
- Claudio Alejandro Echarre Lopez
- Jorge Harlop Flores Berrio
- Jose Miguel Sanchez Atahualpa

## Descripción del proyecto
En este primer proyecto del curso, se buscó recrear una típica problemática de un negocio que busca digitalizarse y así brindar un mejor servicio a sus clientes. Es así, que tomamos el caso de la empresa **Motortek** que busca pasar de su sistema tradicional hacia uno más moderno y cómodo tanto para sus clientes y personal.
### Sobre Motortek:
Motortek es una empresa peruana que brinda los servicios de un taller de autos y lleva más de 6 años dentro del mercado. Este taller recibe a diario muchísimas llamadas y mensajes de sus clientes que solicitan conocer el estado de su vehiculo, provocando que algunas veces esto sea un trabajo agobiante o que reciban quejas de su servicio al cliente. Por lo tanto, el administrador de Motortek se siente que necesita brindar este servicio haciendo uso de una página web.
### La solución propuesta:
"Motortek" es la página web del taller de autos del mismo nombre, en este se podrá visualizar los horarios de atención, los servicios disponibles y la información del personal (mecánicos) a cargo de un auto. Del mismo modo, el personal del taller tendrá los privilegios para registrar usuarios (clientes) al sistema para agendar un servicio para su auto. 
Se escoge esta solución ya que permitirá al taller poder brindar información a muchísimos de sus clientes a la vez. Además, le permitirá obtener una presencia en la web y así hacerse más conocido entre los demás talleres locales.

## Objetivos Principales / Misión / Visión
### Misión:
- Trabajar en equipo para que el producto final sea el resultado de combinar diferentes buenas ideas.
### Visión:
- Lograr que la página web sea útil para la empresa y que sus clientes se sientan más satisfechos con su servicio.
### Objetivos Principales:
- Implementar un módulo de registro y login para usuarios del sistema.
- Poder hacer CRUD con los datos de los vehículos de los clientes.
- Diseñar e implementar dentro del proyecto una base de datos relacional.
- Añadir una interfaz para facilitar la interacción con el sistema.

## Tecnologías Involucradas 


## Script para crear la base de Datos:
`python dbcreate.py`

## Información sobre los API. Request

## Hosts
Tanto el front-end y back-end del proyecto son soportados en un Host local.

## Forma de Autenticación
Se necesita velar por la autenticidad de los usuarios que ingresan al sistema:
## Usuarios
Mediante este código se controla la entrada al sistema ya que se limita a los usuarios que han sido registrados.

```python @app.route('/client', methods=['POST'])
def proceso_login():
    correo = request.form['email']
    password = request.form['password']
    usuario = Usuario_cliente.query.filter_by(email=correo).first() 
    if(usuario is None):
        return redirect(url_for('login'))
    else:
        if(usuario.password == password):
            return render_template("client.html")
        else:
            return redirect(url_for('login'))          
```
## Administradores
Mediante este código se controla la entrada al sistema como administrador. Los usuarios de tipo administrador solo pueden ser ingresados al sistema por otros administradores.

```python @app.route('/admin', methods=['POST'])
def proceso_login_admin():
    correo = request.form['email']
    password = request.form['password']

    usuario = Usuario_administrador.query.filter_by(email=correo).first()
    
    if(usuario is None):
        return redirect(url_for('login'))
    else:
        if(usuario.password == password):
            return render_template("admin.html")
        else:
            return redirect(url_for('login_admin'))          
```











Información acerca de las tecnologías utilizadas en Front-end, Back-end y Base de datos:
Front-end: HTML
Back-end: flask, SQLAlchemy, flask db migration, render_template, request, redirect, url_for, jsonify
Base de datos: Postgresql, 

El nombre del script a ejecutar para iniciar la base de datos con datos:

Información acerca de los API. Requests y respuestas de cada endpoint utilizado en el sistema:

Hosts:
localhost

Forma de Autenticación:

Manejo de errores HTTP: 500, 400, 300, 200, 100, etc:

Cómo ejecutar el sistema (Deployment scripts):


