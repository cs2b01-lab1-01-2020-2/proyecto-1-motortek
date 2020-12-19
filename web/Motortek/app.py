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
    descripcion = db.Column(db.String(), nullable=False)
    fecha_ingreso = db.Column(db.String(), nullable=False)
    fecha_entrega = db.Column(db.String(), nullable=False)
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

@app.route('/admin_client', methods=['POST'])
def admin_client():
    if(request.form['button1'] == 'Soy Cliente'):
        return redirect(url_for('login'))
    else:
        return redirect(url_for('login_admin'))

@app.route('/login', methods=['GET'])
def login():
    return render_template("login.html")
    
@app.route('/login_admin', methods=['GET'])
def login_admin():
    return render_template("login_admin.html")

@app.route('/register', methods=['GET'])
def register():
    return render_template("register.html")

@app.route('/register_admin', methods=['POST'])
def register_admin():
    if(request.form['button1'] == 'Registrarse'):
        return render_template("register_admin.html")

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
    

@app.route('/client', methods=['POST'])
def proceso_login():
    correo = request.form['email']
    password = request.form['password']

    usuario = Usuario_cliente.query.filter_by(email=correo).first()
    
    if(usuario is None):
        return redirect(url_for('login'))
    else:
        if(usuario.password == password):
            autos = Auto.query.with_entities(Auto.numplaca).filter_by(email_usuario=correo).all()
            servicios = Servicio.query.filter(Servicio.numplaca.in_(autos)).all()
            autos = Auto.query.filter_by(email_usuario=correo).all()
            return render_template("client.html", client=usuario, autos=autos, servicios=servicios)
        else:
            return redirect(url_for('login'))
    

@app.route('/admin', methods=['POST'])
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

if __name__ == '__main__':
    app.run(debug=True, port=5000)