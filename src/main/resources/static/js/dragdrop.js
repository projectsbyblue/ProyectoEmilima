const dropArea = document.querySelector(".drag-area");
const dragText = dropArea.querySelector("h2");
const button = dropArea.querySelector("button");
const input = dropArea.querySelector("#file");


let files;

button.addEventListener('click', e =>{
    input.click();
});

input.addEventListener("change", (e) => {
    files = input.files;
    showFiles(files);
    
});

dropArea.addEventListener("dragover", (e) => {
    e.preventDefault();
    dragText.textContent = "Suelta para subir los archivos";
});
dropArea.addEventListener("dragleave", (e) => {
    e.preventDefault();
    dragText.textContent = "Arrasta y suelta un archivo";
});
dropArea.addEventListener("drop", (e) => {
    e.preventDefault();
    files = e.dataTransfer.files;
    showFiles(files);
    dragText.textContent = "Arrasta y suelta un archivo";
});

function showFiles(files){
    if(files.length == undefined){
        processFile(files);
    } else {
        for(const file of files){
            processFile(file);
        }
    }
}

function processFile(file){
    const docType = file.type;
    const validExtensions = ['application/pdf'];
    if(validExtensions.includes(docType)){
        const fileReader = new FileReader();
        const id = 1;
        fileReader.addEventListener('load',e => {
            const image = `
                <div id="${id}">
                    <span>nombre: ${file.name}</span><br>
                    <span class="status-text">Loading.........</span>
                </div>
            `;
        const html = document.querySelector("#preview").innerHTML;
        document.querySelector("#preview").innerHTML = image + html;
        });

    fileReader.readAsDataURL(file);
    uploadFile(file,id);
    } else{
        alert("Archivo no admitido");
    }
}

async function uploadFile(file, id){
    const formData = new FormData();
    formData.append("file",file);
    formData.append("nombre",file.name);
    var idContrato = document.getElementById("codigoV").textContent;
    formData.append("id",idContrato);
    try {
        const response = await fetch("actualizar", {
            method: "POST",
            body: formData,
        });

        document.getElementById("dragArea").style.display = "none";
        document.getElementById("preview").innerHTML = `
                    <span>nombre: ${file.name}</span><br>
                    <span class="status-text">Se subio el archivo correctamente</span>
                    `;
    } catch (error){
        document.getElementById("preview").innerHTML = `
                    <span>nombre: ${file.name}</span><br>
                    <span class="status-text">Error al subir el archivo</span>
                    `;
    }
}