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



class Usuario(db.Model):
    __tablename__='usuario_cliente'
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(), nullable=False)
    apellido = db.Column(db.String(), nullable=False)
    sexo = db.Column(db.String(), nullable=False)
    contacto = db.Column(db.Integer, nullable=False)
    email = db.Column(db.String(), nullable=False, unique=True)
    password = db.Column(db.String(), nullable=False)
    tipo = db.Column(db.String(), nullable=True)

    def __repr__(self):
        return f' {self.nombre}'

class Auto(db.Model):
    __tablename__='auto'
    email_usuario = db.Column(db.String(), ForeignKey("usuario_cliente.email"), nullable=False)
    numplaca = db.Column(db.Integer, primary_key=True)
    modelo = db.Column(db.String(), nullable=False)
    foto = db.Column(db.LargeBinary, nullable=True)
    
    def __repr__(self):
        return f'Auto {self.modelo}, placa {self.numplaca}'

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
    completed = db.Column(db.String(2), nullable=True, default="f")
    def __repr__(self):
        return f'Servicio {self.id}: {self.descripcion}Placa: {self.numplaca}, Costo: {self.costo} Ingreso: {self.fecha_ingreso}, Entrega: {self.fecha_entrega}'

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
        tipo = "cliente"
        error=False
        u = Usuario(nombre = nombre, apellido= apellido, sexo=sexo, contacto=contacto, email=correo, password=password, tipo=tipo)
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
        tipo = "admin"
        error=False
        u = Usuario(nombre=nombre, apellido= apellido, sexo=sexo, contacto=contacto, email=correo, password=password, tipo = admin)
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
    tipo="cliente"
    usuario = Usuario.query.filter_by(email=correo, tipo=tipo).first()
    
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
    tipo="admin"
    usuario = Usuario.query.filter_by(email=correo, tipo=tipo).first()
    
    if(usuario is None):
        return redirect(url_for('login_admin'))
    else:
        if(usuario.password == password):
            all_servicios = Servicio.query.all()
            all_autos = Auto.query.all()
            all_mecanicos = Mecanico.query.all() 
            return render_template("admin.html", servicios=all_servicios, autos=all_autos, mecanicos=all_mecanicos)
        else:
            return redirect(url_for('login_admin'))

@app.route('/registro_auto', methods=['GET'])
def registro_auto():
    return render_template("register_auto.html")
@app.route('/registro_mecanico', methods=['GET'])
def registro_mecanico():
    return render_template("register_mecanico.html")
@app.route('/registro_servicio', methods=['GET'])
def registro_servicio():
    return render_template("register_servicio.html")

@app.route('/register_auto', methods=['POST'])
def register_auto():
    
    try:
        placa = request.get_json()['placa']
        modelo = request.get_json()['modelo']
        correo = request.get_json()['correo']
        placa = int(placa)
        a = Auto(email_usuario=correo, numplaca=placa, modelo=modelo)
        db.session.add(a)
        db.session.commit()
        return jsonify({'auto':a.numplaca})
    except Exception as e:
        db.session.rollback()

    finally:
        db.session.close()
    

@app.route('/register_servicio', methods=['POST'])
def register_servicio():
    
    try:
        tipo = request.get_json()["servicio"]
        costo = request.get_json()["costo"]
        descripcion = request.get_json()["descripcion"]
        fecha_in= request.get_json()["fecha_i"]
        fecha_out=request.get_json()["fecha_e"]
        dni=request.get_json()["dni"]
        placa=request.get_json()["placa"]
        costo=int(costo)
        dni= int(dni)
        placa = int(placa)
        s = Servicio(tipo=tipo, costo=costo, descripcion=descripcion, fecha_ingreso=fecha_in, fecha_entrega=fecha_out, numplaca=placa, mecanico=dni)
        db.session.add(s)
        db.session.commit()
        return jsonify({'tipo':s.tipo})
    except Exception as e:
        db.session.rollback()
    finally:
        db.session.close()
    

@app.route('/register_mecanico', methods=['POST'])
def register_mecanico():
    print("mecanico")
    try:
        dni = request.get_json()['dni']
        nombre = request.get_json()['nombre']
        apellido = request.get_json()['apellido']
        sexo = request.get_json()['sexo']
        telefono = request.get_json()['contacto']
        m = Mecanico(dni=dni, nombre=nombre, apellido=apellido, sexo=sexo, contacto=telefono)
        db.session.add(m)
        db.session.commit()
        return jsonify({'mecanico': m.nombre})
    except Exception as e:
        db.session.rollback()
    finally:
        db.session.close()

if __name__ == '__main__':
    app.run(debug=True, port=5000)