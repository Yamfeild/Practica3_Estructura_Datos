from flask import Blueprint, abort, request, render_template, redirect, flash
import requests
import json
router = Blueprint('router', __name__)

"""@router.route('/')
def home():
    
    return render_template('/inicial/login.html')"""


@router.route('/home')
def home():
    return render_template('template.html')


#Registrar persona
@router.route('/home/proyecto/register')
def view_register_person():
    r = requests.get("http://localhost:8080/myapp/proyecto/listType")
    data = r.json()
    print(r.json())
    return render_template('proyecto/registro.html', lista = data["data"])


#Editar persona
@router.route('/home/proyecto/edit/<id>')
def view_edit_person(id):
    r = requests.get("http://localhost:8080/myapp/proyecto/listType")
    data = r.json()
    r1 = requests.get("http://localhost:8080/myapp/proyecto/get/"+id)
    data1 = r1.json()
    if(r1.status_code == 200):
        return render_template('proyecto/editar.html', lista = data["data"], person = data1["data"])
    else:
        flash(data1["data"], category='error')
        return redirect("/home/proyecto/list")
    
    
# lista de personas
@router.route('/home/proyecto/list')
def list_person():
    r = requests.get("http://localhost:8080/myapp/proyecto/list")
    rs = requests.get("http://localhost:8080/myapp/inversionista/list")
    #print(type(r.json()))
    #print(r.json())
    data = r.json()
    data1 = rs.json()
    return render_template('proyecto/lista.html', lista = data["data"],lista1 = data1["data"])


#Metodo de guardar
@router.route('/home/proyecto/save', methods=["POST"])
def save_person():
    headers = {'Content-type': 'application/json'}
    form = request.form
    
    dataF = {"tipo":form["tipo"], "nombreProyecto":form["nom"], "inversionTotal":form["inver"], "TiempoVidaAnios":form["tiem"]}
    r = requests.post("http://localhost:8080/myapp/proyecto/save", data = json.dumps(dataF), headers=headers)
    dat = r.json()
    if r.status_code==200:
        flash("Se ha guardado correctamente", category='info')
        return redirect("/home/proyecto/list")
    else:
        flash(str(dat["data"],category='error'))
        return redirect("/home/proyecto/list")
    
#Metodo de actualizar
@router.route('/home/proyecto/update', methods=["POST"])
def update_person():
    headers = {'Content-type': 'application/json'}
    form = request.form
    
    dataF = {"id":form["id"], "tipo":form["tipo"], "nombreProyecto":form["nom"], "inversionTotal":form["inver"], "TiempoVidaAnios":form["tiem"]}
    r = requests.post("http://localhost:8080/myapp/proyecto/update", data = json.dumps(dataF), headers=headers)
    dat = r.json()
    if r.status_code==200:
        flash("Se ha guardado correctamente", category='info')
        return redirect("/home/proyecto/list")
    else:
        flash(str(dat["data"],category='error'))
        return redirect("/home/proyecto/list")


##################### ROUTES INVERSIONISTA ########################


#Registrar INVERSIONISTA
@router.route('/home/inversionista/register')
def view_register_inversionista():
    r = requests.get("http://localhost:8080/myapp/inversionista/listType")
    data = r.json()
    print(r.json())
    return render_template('proyecto/registroInversionista.html', lista = data["data"])

#Metodo de guardar
@router.route('/home/inversionista/save', methods=["POST"])
def save_Inversionista():
    headers = {'Content-type': 'application/json'}
    form = request.form
    
    dataF = {"nombre":form["nom"], "apellido":form["apell"], "identificacion":form["iden"], "telefono":form["tel"], "montoInvertido":form["mont"]}
    r = requests.post("http://localhost:8080/myapp/inversionista/save", data = json.dumps(dataF), headers=headers)
    dat = r.json()
    if r.status_code==200:
        flash("Se ha guardado correctamente", category='info')
        return redirect("/home/inversionista/list")
    else:
        flash(str(dat["data"],category='error'))
        return redirect("/home/inversionista/list")
    


# lista de personas
@router.route('/home/inversionista/list')
def list_Inversionista():
    r = requests.get("http://localhost:8080/myapp/inversionista/list")
    #print(type(r.json()))
    #print(r.json())
    data = r.json()
    return render_template('proyecto/listaInver.html', lista = data["data"])

#Editar persona
@router.route('/home/inversionista/edit/<id>')
def view_edit_inversionista(id):
    r = requests.get("http://localhost:8080/myapp/inversionista/listType")
    data = r.json()
    r1 = requests.get("http://localhost:8080/myapp/inversionista/get/"+id)
    data1 = r1.json()
    if(r1.status_code == 200):
        return render_template('proyecto/editar.html', lista = data["data"], person = data1["data"])
    else:
        flash(data1["data"], category='error')
        return redirect("/home/inversionista/list")
    
    
#Metodo de actualizar
@router.route('/home/inversionista/update', methods=["POST"])
def update_inversionista():
    headers = {'Content-type': 'application/json'}
    form = request.form
    
    dataF = {"id":form["id"], "nombre":form["nom"], "apellido":form["apell"], "identificacion":form["iden"], "telefono":form["tel"], "montoInvertido":form["mont"]}
    r = requests.post("http://localhost:8080/myapp/inversionista/update", data = json.dumps(dataF), headers=headers)
    dat = r.json()
    if r.status_code==200:
        flash("Se ha guardado correctamente", category='info')
        return redirect("/home/inversionista/list")
    else:
        flash(str(dat["data"],category='error'))
        return redirect("/home/inversionista/list")
    


    #Metodos de ordenamiento 

@router.route('/home/proyecto/order/', methods=['GET'])
def view_order_person():
    # Obtener los valores desde la query string
    algorimo_sort = request.args.get('algorimo_sort')
    atributo_nom = request.args.get('atributo_nom')
    type = request.args.get('type')
    
    # Verificar que los parámetros existan
    if not algorimo_sort or not atributo_nom or not type:
        return "Parámetros faltantes.", 400

    # Construir la URL del servicio externo
    url = f"http://localhost:8080/myapp/proyecto/order/{algorimo_sort}/{atributo_nom}/{type}"
    try:
        # Realizar la solicitud al servicio externo
        response = requests.get(url)
        data = response.json()

        if response.status_code == 200 and data.get("msg") == "OK":
            return render_template('proyecto/lista.html', lista=data["data"])
        else:
            message = data.get("data", "Error desconocido en el servicio.")
            return render_template('proyecto/lista.html', lista=[], message=message)
    except requests.exceptions.RequestException as e:
        return render_template('proyecto/lista.html', lista=[], message=f"Error al conectar con el servicio: {e}")

#metodo de busqueda

# Buscar por nombre del proyecto
@router.route('/home/proyecto/search/', methods=['GET'])
def search_by_name():
    # Obtener el nombre del proyecto desde los parámetros de la URL
    nombreProyecto = request.args.get('nombreProyecto')

    if not nombreProyecto:
        flash("Debe proporcionar un nombre válido para buscar.", category='error')
        return redirect("/home/proyecto/list")

    try:
        # Construir la URL del servicio externo
        url = f"http://localhost:8080/myapp/proyecto/search/{nombreProyecto}"
        
        # Hacer la solicitud GET
        response = requests.get(url)
        data = response.json()
        
        # Verificar si hay resultados
        if response.status_code == 200 and data.get("msg") == "OK":
            resultados = data.get("data", [])
            if resultados:  # Si hay resultados
                return render_template('proyecto/lista.html', lista=resultados)
            else:
                flash("No se encontraron proyectos con el nombre proporcionado.", category='error')
                return redirect("/home/proyecto/list")
        else:
            flash(data.get("data", "No se encontró un proyecto con ese nombre."), category='error')
            return redirect("/home/proyecto/list")
    except requests.exceptions.RequestException as e:
        flash(f"Error al conectar con el servicio: {e}", category='error')
        return redirect("/home/proyecto/list")




# Buscar por código del proyecto
@router.route('/home/proyecto/search/codigo/', methods=['GET'])
def search_by_code():
    # Obtener el código desde los parámetros de la URL
    codigoProyecto = request.args.get('codigoProyecto', type=int)
    
    if codigoProyecto is None:
        flash("Debe proporcionar un código válido para buscar.", category='error')
        return redirect("/home/proyecto/list")

    try:
        # Construir la URL del servicio externo
        url = f"http://localhost:8080/myapp/proyecto/search/codigo/{codigoProyecto}"
        
        # Hacer la solicitud GET
        response = requests.get(url)
        data = response.json()
        
        # Verificar si hay resultados
        if response.status_code == 200 and data.get("msg") == "OK":
            return render_template('proyecto/lista.html', lista=[data["data"]])  # Renderizar con un solo resultado
        else:
            flash(data.get("data", "No se encontró un proyecto con ese código."), category='error')
            return redirect("/home/proyecto/list")
    except requests.exceptions.RequestException as e:
        flash(f"Error al conectar con el servicio: {e}", category='error')
        return redirect("/home/proyecto/list")

