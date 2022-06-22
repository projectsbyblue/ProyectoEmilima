/* Ubigeo */
$(document).ready(function() {

//listar departamentos
var select01 = '';	
$.each(departamentos, function(i, obj) {
    select01 = '<option value="'+ departamentos[i].coddepartamento + '">' + departamentos[i].departamento + '</option>';
    $('#ubigeo_dep').append(select01);
});

//listar provincias por departamento
$('#ubigeo_dep').on('change', function() {
    
    //limpiar los selects	
    $('#ubigeo_pro').find('option').remove();
    $('#ubigeo_dis').find('option').remove();
    
    var iddep = this.value;
    var select02 = '';	
    
    var provincias_result = jQuery.grep(provincias, function (obj) {
        return obj[iddep];
    });
    
    //console.log( JSON.stringify(provincias_result, null, '\t') );
    //console.log( JSON.stringify(provincias_result[0][iddep], null, '\t') );
    
    var provincias_result_array = provincias_result[0][iddep];
    
    $.each(provincias_result_array, function(i, obj) {
        select02 = '<option value="'+ provincias_result_array[i].codprovincia + '">' + provincias_result_array[i].provincia + '</option>';
        $('#ubigeo_pro').append(select02);			
    });
    
    //cargar los distritos
    $('#ubigeo_pro').trigger('change');
    
});

//listar distritos por provincia
$('#ubigeo_pro').on('change', function() {
    
    //limpiar el select de distritos		
    $('#ubigeo_dis').find('option').remove();
    
    var idpro = this.value;
    var select03 = '';	
    
    var distritos_result = jQuery.grep(distritos, function (obj) {
        return obj[idpro];
    });
    
    //console.log( JSON.stringify(distritos_result, null, '\t') );
    //console.log( JSON.stringify(distritos_result[0][idpro], null, '\t') );
    
    var distritos_result_array = distritos_result[0][idpro];
    
    $.each(distritos_result_array, function(i, obj) {
        select03 = '<option value="'+ distritos_result_array[i].coddistrito + '">' + distritos_result_array[i].distrito + '</option>';
        $('#ubigeo_dis').append(select03);			
    });
    
});

//seleccionar por defecto LIMA
$('#ubigeo_dep option[value="15"]').attr("selected",true);
$('#ubigeo_dep').trigger('change');

//seleccionar por defecto LIMA
$('#ubigeo_pro option[value="1501"]').attr("selected",true);
$('#ubigeo_pro').trigger('change');

//seleccionar por defecto LA MOLINA
$('#ubigeo_dis option[value="150114"]').attr("selected",true);
});