# Desarrollo Basado en Plataformas Sección 1.01 - Proyecto 1
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
### Front-end: 
HTML, CSS
#### index.html:
Interfaz Inicial donde te permite a escoger entre las opciones de usuario de administrador.

#### login.html
Iniciar sesion como cliente dentro del sistema 

#### login_admin.html
Iniciar sesion como administrador dentro del sistema

#### register.html
Permite el registro de nuevos usuarios dentro del sistema.

#### client.html
Interfaz donde el usuario interactua con la información dentro de la base de datos. En otras palabras, permite al usuario ver los datos que necesita.

#### admin.html
Interfaz donde los administradores pueden ingresar datos para cada cliente. 

### Back-end: 
flask, SQLAlchemy, flask db migration, render_template, request, redirect, url_for, jsonify, Jinja
#### app.py:
Implementación del back-end utilizando el framework de flask y otras tecnologías que permiten que la página web funcione correctamente con una base de datos.

### Database: 
Se utiliza Postgresql para la base de datos de este proyecto.
## Script para crear la base de Datos:
`python script.py`

## Información sobre los API. Request
Se utilizan varios @app.route que reciben y retornan información.
## Hosts
Tanto el front-end y back-end del proyecto son soportados en un Host local.

## Forma de Autenticación
Se necesita velar por la autenticidad de los usuarios que ingresan al sistema:
### Usuarios
Mediante este código se controla la entrada al sistema ya que se limita a los usuarios que han sido registrados.

```python 
@app.route('/client', methods=['POST'])
def client_sign():
    correo = request.form.get('username')
    contrasena = request.form.get('password')
    data = Usuario_cliente.query.filter(Usuario_cliente.email==correo, Usuario_cliente.password==contrasena).first()

    if data:
        return redirect(url_for('plataform'))
    else: 
        return render_template('login.html', data=True)          
```
### Administradores
Mediante este código se controla la entrada al sistema como administrador.

```python 
@app.route('/admin', methods=['POST'])
def proceso_login_admin():
    correo = request.form.get('username')
    contrasena = request.form.get('password')
    data = Usuario_administrador.query.filter(Usuario_administrador.email==correo, Usuario_administrador.password==contrasena).first()

    if data:
        return redirect(url_for('plataform'))
    else: 
        return render_template('login.html', data=True)         
```
## Manejo de Errores:
Los errores son manejados dentro del proyecto de acuerdo a los inputs del usuario. Por ejemplo, para iniciar sesión en el sistema es necesario ingresar un usuario y contraseña válido (almacenado en la base de datos) 
### Error en Registro de Usuarios:
Se produce el mensaje de error cuando se intenta registrar un usuario que ya está registrado en el sistema.
Código:

```python 
@app.route('/proceso_register', methods=['GET'])
def proceso_register():
    
    try:
        nombre = request.args.get("nombre")
        apellido = request.args.get("apellido")
        sexo = request.args.get("sexo")
        contacto = request.args.get("contacto")
        correo = request.args.get("email")
        password = request.args.get("password")
        error=False
        u = Usuario_cliente(nombre = nombre, apellido= apellido, sexo=sexo, contacto=contacto, email=correo, password=password)
        db.session.add(u)
        db.session.commit()
        return redirect(url_for('login'))
    except:
        db.session.rollback()
        error=True
        return render_template('register.html', error=error)         
```
### Error en Registro de Administradores:
Se produce el mensaje de error cuando se intenta registrar un administrador que ya está registrado en el sistema.
Código:

```python 
@app.route('/proceso_register_admin', methods=['GET'])
def proceso_register_admin():
    try:
        nombre = request.args.get("nombre")
        apellido = request.args.get("apellido")
        sexo = request.args.get("sexo")
        contacto = request.args.get("contacto")
        correo = request.args.get("email")
        password = request.args.get("password")
        error=False
        u = Usuario_administrador(nombre=nombre, apellido= apellido, sexo=sexo, contacto=contacto, email=correo, password=password)
        db.session.add(u)
        db.session.commit()
        return redirect(url_for('login_admin'))
    except:
        db.session.rollback()
        error = True
        return render_template('register_admin.html', error=error)         
```
### Error en Registro de Información de Autos:
Se produce cuando el administrador provoca un error dentro de la base de datos al intentar ingresar los datos de un Auto.
Código:

```python 
@app.route('/register_auto', methods=['GET'])
def register_auto():
    
    try:
        placa = request.args.get("placa")
        modelo = request.args.get("modelo")
        correo = request.args.get("correo")
        placa = int(placa)
        error=False
        a = Auto(email_usuario=correo, numplaca=placa, modelo=modelo)
        db.session.add(a)
        db.session.commit()
    except:
        db.session.rollback()
        error = True
    finally:
        return render_template("admin.html", error=error)        
```
### Error en Registro de Información del Servicio:
Se produce cuando el administrador provoca un error dentro de la base de datos al intentar ingresar los datos de un Servicio.
Código:

```python 
@app.route('/register_servicio', methods=['GET'])
def register_servicio():
    
    try:
        tipo = request.args.get("servicio")
        costo = request.args.get("costo")
        descripcion = request.args.get("descripcion")
        fecha_in= request.args.get("fecha_i")
        fecha_out=request.args.get("fecha_e")
        dni=request.args.get("dni")
        placa=request.args.get("placa")
        costo=int(costo)
        dni= int(dni)
        placa = int(placa)
        error=False
        s = Servicio(tipo=tipo, costo=costo, descripcion=descripcion, fecha_ingreso=fecha_in, fecha_entrega=fecha_out, numplaca=placa, mecanico=dni)
        db.session.add(s)
        db.session.commit()
    except:
        db.session.rollback()
        error = True
    finally:
        return render_template("admin.html", error=error)       
```
### Error en Registro de Datos del Mecánico:
Se produce cuando el administrador provoca un error dentro de la base de datos al intentar ingresar los datos de un Mecánico.
Código:

```python 
@app.route('/register_mecanico', methods=['GET'])
def register_mecanico():
    try:
        dni = request.args.get("dni")
        nombre = request.args.get("nombre")
        apellido = request.args.get("apellido")
        sexo = request.args.get("sexo")
        telefono = request.args.get("contacto")
        error=False
        m = Mecanico(dni=dni, nombre=nombre, apellido=apellido, sexo=sexo, contacto=telefono)
        db.session.add(m)
        db.session.commit()
    except:
        db.session.rollback()
        error = True
    finally:
        return render_template("admin.html", error=error)       
```
## Cómo ejecutar el sistema:
En la primera vez que se ejecuta el sistema es necesario ejecutar el script para la creación de la base de datos: `python script.py`
Luego, para iniciar el sistema: `python app.py`
