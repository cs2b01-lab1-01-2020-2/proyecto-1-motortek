from flask import *
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
import psycopg2

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://postgres:Quierochocolate9@localhost:5432/motortek'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)
migrate = Migrate(app, db)

class Usuario_cliente(db.Model):
    __tablename__='usuario_cliente'
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(), nullable=False)
    apellido = db.Column(db.String(), nullable=False)
    sexo = db.Column(db.String(), nullable=False)
    contacto = db.Column(db.Integer, nullable=False)
    email = db.Column(db.String(), nullable=False)
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
    numplaca = db.Column(db.Integer, primary_key=True)
    modelo = db.Column(db.String(), nullable=False)
    foto = db.Column(db.LargeBinary, nullable=True)

class Servicio(db.Model):
    __tablename__='servicio'
    id = db.Column(db.Integer, primary_key=True)
    tipo = db.Column(db.String(), nullable=False)
    costo = db.Column(db.Integer, nullable=False)
    descripcion = db.Column(db.Integer, nullable=False)
    fecha_ingreso = db.Column(db.Date, nullable=False)
    fecha_entrega = db.Column(db.Date, nullable=False)
    numplaca = db.Column(db.Integer, nullable=False)

class Mecanico(db.Model):
    __tablename__='mecanico'
    dni = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(), nullable=False)
    apellido = db.Column(db.String(), nullable=False)
    sexo = db.Column(db.String(), nullable=False)
    contacto = db.Column(db.Integer, nullable=False)
    foto = db.Column(db.LargeBinary(), nullable=True)

class Descripcion(db.Model):
    __tablename__='descripcion'
    numero_descripcion = db.Column(db.Integer, primary_key=True)
    descripcion = db.Column(db.String(), nullable= False)
    progreso = db.Column(db.Integer, nullable=False)


#db.create_all()

@app.route('/')
def index():
    return render_template("index.html")

@app.route('/login', methods=['GET'])
def login():
    return
    
@app.route('/login_admin', methods=['GET'])
def login_admin():
    return

@app.route('/register', methods=['GET'])
def register():
    return

@app.route('/register_admin', methods=['GET'])
def register_admin():
    return


if __name__ == '__main__':
    app.run(debug=True, port=5000)
