var contador=0;

var contadorContratos = "oculto";
var contadorTrabajadores = "oculto";
var contadorCargos = "oculto";
var contadorPerfil = "oculto";
var contadorReportes = "oculto";

function mostrarMenu(id){
    if (contador == 0){
        mostrar(id);
        contador = 1;
    } else {
        ocultar(id);
        contador = 0;
    }
}

function mostrar(id){
    document.getElementById(id).style.visibility = "visible";
}

function ocultar(id){
    document.getElementById(id).style.visibility = "hidden";
}

function mostrarVista(idV){
    document.getElementById("vistaAdjuntar").style.visibility = "visible";
    document.getElementById(idV).style.visibility = "hidden";
    document.getElementById("idContratoV").innerHTML = `
                    <span id="codigoV">${idV}</span>
                    `;
    contador = 0;
}

function cerraVista(){
    document.getElementById("vistaAdjuntar").style.visibility = "hidden";
    document.getElementById("dragArea").style.display = "block";
    document.getElementById("preview").innerHTML = `
                    `;
    window.location.replace("/contrato/lista");
}

function desplegarSub(idMenu){
    switch(idMenu){
        case "contratos":
            if(contadorContratos == "oculto") {
                contadorContratos = "visibles";
                mostrarSubMenu(idMenu);
            } else {
                ocultarSubMenu(idMenu);
                contadorContratos = "oculto";
            }
            break;
        case "trabajadores":
            if(contadorTrabajadores == "oculto") {
                contadorTrabajadores = "visibles";
                mostrarSubMenu(idMenu);
            } else {
                ocultarSubMenu(idMenu);
                contadorTrabajadores = "oculto";
            }
            break;
        case "cargos":
            if(contadorCargos == "oculto") {
                contadorCargos = "visibles";
                mostrarSubMenu(idMenu);
            } else {
                ocultarSubMenu(idMenu);
                contadorCargos = "oculto";
            }
            break;
        case "perfil":
            if(contadorPerfil == "oculto") {
                contadorPerfil = "visibles";
                mostrarSubMenu(idMenu);
            } else {
                ocultarSubMenu(idMenu);
                contadorPerfil = "oculto";
            }
            break;
        case "reportes":
            if(contadorReportes == "oculto") {
                contadorReportes = "visibles";
                mostrarSubMenu(idMenu);
            } else {
                ocultarSubMenu(idMenu);
                contadorReportes = "oculto";
            }
            break;
        }
}

function mostrarSubMenu(idMenu){
    document.getElementById(idMenu).style.display = "block";
}

function ocultarSubMenu(idMenu){
    document.getElementById(idMenu).style.display = "none";
}





