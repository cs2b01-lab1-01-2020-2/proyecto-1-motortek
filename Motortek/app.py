from flask import *
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from sqlalchemy import Table, Column, Integer, ForeignKey
from sqlalchemy.orm import relationship
from sqlalchemy.ext.declarative import declarative_base
import psycopg2

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://postgres:password@localhost:5432/motortek'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)
migrate = Migrate(app, db)
#connection = psycopg2.connect('dbname=motortek user=postgres password=Quierochocolate9 host=localhost')
#cursor = connection.cursor()

class Usuario_cliente(db.Model):
    __tablename__='usuario_cliente'
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(), nullable=False)
    apellido = db.Column(db.String(), nullable=False)
    sexo = db.Column(db.String(), nullable=False)
    contacto = db.Column(db.Integer, nullable=False)
    email = db.Column(db.String(), nullable=False, unique=True)
    password = db.Column(db.String(), nullable=False)

class Usuario_administrador(db.Model):
    __tablename__='usuario_administracion'
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(), nullable=False)
    apellido = db.Column(db.String(), nullable=False)
    sexo = db.Column(db.String(), nullable=False)
    contacto = db.Column(db.Integer, nullable=False)
    email = db.Column(db.String(), nullable=False, unique=True)
    password = db.Column(db.String(), nullable=False)

class Auto(db.Model):
    __tablename__='auto'
    email_usuario = db.Column(db.String(), ForeignKey("usuario_cliente.email"), nullable=False)
    numplaca = db.Column(db.Integer, primary_key=True)
    modelo = db.Column(db.String(), nullable=False)
    foto = db.Column(db.LargeBinary, nullable=True)

class Servicio(db.Model):
    __tablename__='servicio'
    id = db.Column(db.Integer, primary_key=True)
    tipo = db.Column(db.String(), nullable=False)
    costo = db.Column(db.Integer, nullable=False)
    descripcion = db.Column(db.String(300), nullable=False)
    fecha_ingreso = db.Column(db.Date, nullable=False)
    fecha_entrega = db.Column(db.Date, nullable=False)
    mecanico = db.Column(db.Integer, ForeignKey("mecanico.dni"), nullable=False)
    numplaca = db.Column(db.Integer, ForeignKey("auto.numplaca"), nullable=False)

class Mecanico(db.Model):
    __tablename__='mecanico'
    dni = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(), nullable=False)
    apellido = db.Column(db.String(), nullable=False)
    sexo = db.Column(db.String(), nullable=False)
    contacto = db.Column(db.Integer, nullable=False)
    foto = db.Column(db.String(), nullable=True)



#db.create_all()

@app.route('/')
def index():
    return render_template("index.html")

@app.route('/login', methods=['GET'])
def login():
    return render_template("login.html")
    
@app.route('/login_admin', methods=['GET'])
def login_admin():
    return render_template("login_admin.html")

@app.route('/register', methods=['POST'])
def register():
    if(request.form['button1'] == 'Registrarse'):
        return render_template("register.html")

@app.route('/register_admin', methods=['POST'])
def register_admin():
    if(request.form['button1'] == 'Registrarse'):
        return render_template("register_admin.html")

@app.route('/proceso_register', methods=['GET'])
def proceso_register():
    nombre = request.args.get("nombre")
    apellido = request.args.get("apellido")
    sexo = request.args.get("sexo")
    contacto = request.args.get("contacto")
    correo = request.args.get("email")
    password = request.args.get("password")

    u = Usuario_cliente(nombre = nombre, apellido= apellido, sexo=sexo, contacto=contacto, email=correo, password=password)
    db.session.add(u)
    db.session.commit()
    return redirect(url_for('login'))


@app.route('/proceso_register_admin', methods=['GET'])
def proceso_register_admin():
    nombre = request.args.get("nombre")
    apellido = request.args.get("apellido")
    sexo = request.args.get("sexo")
    contacto = request.args.get("contacto")
    correo = request.args.get("email")
    password = request.args.get("password")

    u = Usuario_administrador(nombre=nombre, apellido= apellido, sexo=sexo, contacto=contacto, email=correo, password=password)
    db.session.add(u)
    db.session.commit()
    return redirect(url_for('login_admin'))

@app.route('/client', methods=['POST'])
def client_sign():
    correo = request.form.get('username')
    contrasena = request.form.get('password')
    data = Usuario_cliente.query.filter(Usuario_cliente.email==correo, Usuario_cliente.password==contrasena).first()

    if data:
        return redirect(url_for('plataform'))
    else: 
        return render_template('login.html', data=True)
    

@app.route('/admin', methods=['POST'])
def proceso_login_admin():
    correo = request.form.get('username')
    contrasena = request.form.get('password')
    data = Usuario_administrador.query.filter(Usuario_administrador.email==correo, Usuario_administrador.password==contrasena).first()

    if data:
        return redirect(url_for('plataform'))
    else: 
        return render_template('login.html', data=True)

@app.route('/register_auto', methods=['POST'])
def register_auto():
    placa = request.form['placa']
    modelo = request.form['modelo']
    correo = request.form['correo']
    foto = request.form['imagen']

    a = Auto(email_usuario=correo, placa=placa, modelo=modelo, foto=foto)
    db.session.add(a)
    db.session.commit()
    return render_template("admin.html")

@app.route('/register_servicio', methods=['POST'])
def register_servicio():


    return 

@app.route('/register_mecanico', methods=['POST'])
def register_mecanico():
    return 

@app.route('/plataform', methods=['GET'])
def plataform():
    return render_template("plataform.html")


if __name__ == '__main__':
    app.run(debug=True, port=5000)