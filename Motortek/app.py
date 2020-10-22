from flask import *
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
import psycopg2

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://postgres:password@localhost:5432/motortek'
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
    id_usuario = db.Column(db.Integer, db.ForeignKey('usuario_cliente.id'), nullable=False)
    numplaca = db.Column(db.Integer, primary_key=True)
    modelo = db.Column(db.String(), nullable=False)
    foto = db.Column(db.LargeBinary, nullable=True)

class Servicio(db.Model):
    __tablename__='servicio'
    id = db.Column(db.Integer, primary_key=True)
    tipo = db.Column(db.String(), nullable=False)
    costo = db.Column(db.Integer, nullable=False)
    descripcion = db.Column(db.String(300), nullable=False)
    descripcion = db.Column(db.Integer, nullable=False)
    fecha_ingreso = db.Column(db.Date, nullable=False)
    fecha_entrega = db.Column(db.Date, nullable=False)
    numplaca = db.Column(db.Integer, db.ForeignKey("auto.numplaca"), nullable=False)

class Mecanico(db.Model):
    __tablename__='mecanico'
    dni = db.Column(db.Integer, primary_key=True)
    id_servicio = db.Column(db.Integer, db.ForeignKey("servicio.id"), nullable=False)
    nombre = db.Column(db.String(), nullable=False)
    apellido = db.Column(db.String(), nullable=False)
    sexo = db.Column(db.String(), nullable=False)
    contacto = db.Column(db.Integer, nullable=False)
    foto = db.Column(db.String(), nullable=True)



db.create_all()

@app.route('/')
def index():
    return render_template("index.html")

@app.route('/login', methods=['GET'])
def login():
    return render_template("login.html")

@app.route('/login/signup', methods=['GET'])
def login_sign():
    return render_template("login.html")
    
@app.route('/login_admin', methods=['GET'])
def login_admin():
    return render_template("login_admin.html")





@app.route('/register', methods=['GET'])
def register():
    return render_template("register.html")

@app.route('/register_admin', methods=['GET'])
def register_admin():
    return render_template("register_admin.html")

@app.route('/client')
def client():
    return render_template("client.html")

@app.route('/admin')
def admin():
    return render_template("admin.html")

if __name__ == '__main__':
    app.run(debug=True, port=5000)