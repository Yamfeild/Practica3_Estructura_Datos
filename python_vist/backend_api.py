from flask import Flask, jsonify, request
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy.exc import SQLAlchemyError
import os

app = Flask(__name__)
base_dir = os.path.abspath(os.path.dirname(__file__))
db_path = os.path.join(base_dir, 'data.db')
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///' + db_path
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)


class Tipo(db.Model):
    __tablename__ = 'tipo'
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(128), nullable=False)

    def to_dict(self):
        return {'id': self.id, 'nombre': self.nombre}


class Proyecto(db.Model):
    __tablename__ = 'proyecto'
    id = db.Column(db.Integer, primary_key=True)
    tipo = db.Column(db.Integer, db.ForeignKey('tipo.id'))
    nombreProyecto = db.Column(db.String(256))
    inversionTotal = db.Column(db.Float, default=0.0)
    TiempoVidaAnios = db.Column(db.Integer, default=0)

    def to_dict(self):
        return {
            'id': self.id,
            'codigoProyecto': self.id,
            'tipo': self.tipo,
            'nombreProyecto': self.nombreProyecto,
            'inversionTotal': self.inversionTotal,
            'TiempoVidaAnios': self.TiempoVidaAnios,
            'tiempoVidaAnios': self.TiempoVidaAnios
        }


class Inversionista(db.Model):
    __tablename__ = 'inversionista'
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(128))
    apellido = db.Column(db.String(128))
    identificacion = db.Column(db.String(64))
    telefono = db.Column(db.String(64))
    montoInvertido = db.Column(db.Float, default=0.0)

    def to_dict(self):
        return {
            'id': self.id,
            'nombre': self.nombre,
            'apellido': self.apellido,
            'identificacion': self.identificacion,
            'telefono': self.telefono,
            'montoInvertido': self.montoInvertido
        }


def init_db():
    db.create_all()
    # seed types if empty
    if Tipo.query.count() == 0:
        db.session.add_all([
            Tipo(nombre='Solar'),
            Tipo(nombre='Eólico')
        ])
        db.session.commit()


@app.route('/myapp/proyecto/listType', methods=['GET'])
def proyecto_list_type():
    tipos = [t.to_dict() for t in Tipo.query.all()]
    return jsonify({"msg": "OK", "data": tipos}), 200


@app.route('/myapp/proyecto/list', methods=['GET'])
def proyecto_list():
    proyectos = [p.to_dict() for p in Proyecto.query.all()]
    return jsonify({"msg": "OK", "data": proyectos}), 200


@app.route('/myapp/proyecto/get/<int:id>', methods=['GET'])
def proyecto_get(id):
    p = Proyecto.query.get(id)
    if p:
        return jsonify({"msg": "OK", "data": p.to_dict()}), 200
    return jsonify({"msg": "ERROR", "data": "Proyecto no encontrado"}), 404


@app.route('/myapp/proyecto/save', methods=['POST'])
def proyecto_save():
    try:
        data = request.get_json(force=True)
        p = Proyecto(
            tipo=int(data.get('tipo')) if data.get('tipo') is not None else None,
            nombreProyecto=data.get('nombreProyecto'),
            inversionTotal=float(data.get('inversionTotal', 0)),
            TiempoVidaAnios=int(data.get('TiempoVidaAnios', 0))
        )
        db.session.add(p)
        db.session.commit()
        return jsonify({"msg": "OK", "data": p.to_dict()}), 200
    except (ValueError, SQLAlchemyError) as e:
        db.session.rollback()
        return jsonify({"msg": "ERROR", "data": str(e)}), 400


@app.route('/myapp/proyecto/update', methods=['POST'])
def proyecto_update():
    try:
        data = request.get_json(force=True)
        p = Proyecto.query.get(data.get('id'))
        if not p:
            return jsonify({"msg": "ERROR", "data": "Proyecto no encontrado"}), 404
        p.tipo = int(data.get('tipo', p.tipo))
        p.nombreProyecto = data.get('nombreProyecto', p.nombreProyecto)
        p.inversionTotal = float(data.get('inversionTotal', p.inversionTotal))
        p.TiempoVidaAnios = int(data.get('TiempoVidaAnios', p.TiempoVidaAnios))
        db.session.commit()
        return jsonify({"msg": "OK", "data": p.to_dict()}), 200
    except (ValueError, SQLAlchemyError) as e:
        db.session.rollback()
        return jsonify({"msg": "ERROR", "data": str(e)}), 400


@app.route('/myapp/proyecto/order/<algorimo_sort>/<atributo_nom>/<tipo>', methods=['GET'])
def proyecto_order(algorimo_sort, atributo_nom, tipo):
    # tipo: 'asc' or 'desc'
    reverse = tipo.lower() != 'asc'
    try:
        # Validate attribute exists
        if not hasattr(Proyecto, atributo_nom):
            return jsonify({"msg": "ERROR", "data": "Atributo inválido"}), 400
        order_attr = getattr(Proyecto, atributo_nom)
        if reverse:
            ordered = Proyecto.query.order_by(order_attr.desc()).all()
        else:
            ordered = Proyecto.query.order_by(order_attr.asc()).all()
        return jsonify({"msg": "OK", "data": [p.to_dict() for p in ordered]}), 200
    except Exception as e:
        return jsonify({"msg": "ERROR", "data": str(e)}), 400


@app.route('/myapp/proyecto/search/<nombreProyecto>', methods=['GET'])
def proyecto_search(nombreProyecto):
    q = f"%{nombreProyecto}%"
    found = Proyecto.query.filter(Proyecto.nombreProyecto.ilike(q)).all()
    return jsonify({"msg": "OK", "data": [p.to_dict() for p in found]}), 200


@app.route('/myapp/proyecto/search/codigo/<int:codigoProyecto>', methods=['GET'])
def proyecto_search_codigo(codigoProyecto):
    p = Proyecto.query.get(codigoProyecto)
    if p:
        return jsonify({"msg": "OK", "data": p.to_dict()}), 200
    return jsonify({"msg": "ERROR", "data": "Proyecto no encontrado"}), 404


### Inversionista endpoints
@app.route('/myapp/inversionista/listType', methods=['GET'])
def inversionista_list_type():
    tipos = [t.to_dict() for t in Tipo.query.all()]
    return jsonify({"msg": "OK", "data": tipos}), 200


@app.route('/myapp/inversionista/list', methods=['GET'])
def inversionista_list():
    invs = [i.to_dict() for i in Inversionista.query.all()]
    return jsonify({"msg": "OK", "data": invs}), 200


@app.route('/myapp/inversionista/get/<int:id>', methods=['GET'])
def inversionista_get(id):
    i = Inversionista.query.get(id)
    if i:
        return jsonify({"msg": "OK", "data": i.to_dict()}), 200
    return jsonify({"msg": "ERROR", "data": "Inversionista no encontrado"}), 404


@app.route('/myapp/inversionista/save', methods=['POST'])
def inversionista_save():
    try:
        data = request.get_json(force=True)
        inv = Inversionista(
            nombre=data.get('nombre'),
            apellido=data.get('apellido'),
            identificacion=data.get('identificacion'),
            telefono=data.get('telefono'),
            montoInvertido=float(data.get('montoInvertido', 0))
        )
        db.session.add(inv)
        db.session.commit()
        return jsonify({"msg": "OK", "data": inv.to_dict()}), 200
    except (ValueError, SQLAlchemyError) as e:
        db.session.rollback()
        return jsonify({"msg": "ERROR", "data": str(e)}), 400


@app.route('/myapp/inversionista/update', methods=['POST'])
def inversionista_update():
    try:
        data = request.get_json(force=True)
        inv = Inversionista.query.get(data.get('id'))
        if not inv:
            return jsonify({"msg": "ERROR", "data": "Inversionista no encontrado"}), 404
        inv.nombre = data.get('nombre', inv.nombre)
        inv.apellido = data.get('apellido', inv.apellido)
        inv.identificacion = data.get('identificacion', inv.identificacion)
        inv.telefono = data.get('telefono', inv.telefono)
        inv.montoInvertido = float(data.get('montoInvertido', inv.montoInvertido))
        db.session.commit()
        return jsonify({"msg": "OK", "data": inv.to_dict()}), 200
    except (ValueError, SQLAlchemyError) as e:
        db.session.rollback()
        return jsonify({"msg": "ERROR", "data": str(e)}), 400


@app.route('/debug/tipos', methods=['GET'])
def debug_tipos():
    """Debug endpoint to check tipos in database"""
    tipos = Tipo.query.all()
    print(f"DEBUG: Total tipos en DB: {len(tipos)}")
    for t in tipos:
        print(f"  - ID: {t.id}, Nombre: {t.nombre}")
    data = [t.to_dict() for t in tipos]
    return jsonify({"total": len(data), "data": data, "debug": "OK"}), 200


if __name__ == '__main__':
    with app.app_context():
        init_db()
        print("=== DB INITIALIZED ===")
        print(f"Total tipos: {Tipo.query.count()}")
        for t in Tipo.query.all():
            print(f"  - {t.id}: {t.nombre}")
        # seed example data if empty
        if Proyecto.query.count() == 0:
            db.session.add_all([
                Proyecto(tipo=1, nombreProyecto='Proyecto Sol A', inversionTotal=100000.0, TiempoVidaAnios=20),
                Proyecto(tipo=2, nombreProyecto='Viento Norte', inversionTotal=250000.0, TiempoVidaAnios=25)
            ])
        if Inversionista.query.count() == 0:
            db.session.add(Inversionista(nombre='Juan', apellido='Perez', identificacion='1234', telefono='555-1234', montoInvertido=50000.0))
        db.session.commit()
    app.run(debug=True, port=8080)
