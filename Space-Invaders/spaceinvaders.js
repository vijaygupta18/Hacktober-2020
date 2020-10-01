import { Marciano, Nave, Disparo, Juego } from './sapce_invaders_models.js';

var juego;
var puntuacion = 0;
var disparos = [];
var vidas = 3;
var ganada;
var cont;

function movimiento(evento) {

    if (evento.keyCode == 32) {
        let x = juego.nave.x + (50 / 2);
        let y = juego.nave.y;
        disparos.push(new Disparo(x, y, juego.marcianos));
    }
    if (evento.keyCode == 37) {
        juego.nave.mover("-");
    }
    if (evento.keyCode == 39) {
        juego.nave.mover("+");
    }
}

window.onload = () => {
    crearJuego();
}


function ganador(){
    for (let marciano of juego.marcianos) {
        if(marciano==null){
            cont++;
        }

    }
    if (cont==16) {
        ganada=true;
    }else{
        ganada=false;
    }

    cont=0;
    return ganada;
}

function crearJuego() {

    juego = new Juego();
    juego.dibujar();
    let div = document.getElementById("puntuacion");
    let h1 = document.createElement("h1");
    h1.textContent = puntuacion;
    h1.style.marginLeft = "250px";
    let h2 = document.createElement("h2");
    h2.textContent = "Vidas restantes: " + vidas;
    div.appendChild(h1);
    div.appendChild(h2);

    document.body.addEventListener("keyup", movimiento);
    
    document.getElementById("si").addEventListener("click", crearJuego);
    document.getElementById("si2").addEventListener("click", crearJuego);

    var intervalo = setInterval(() => {
        juego.moverMarcianos();

        if (juego.perdida) {
            vidas--;
            h2.textContent = "Vidas restantes: " + vidas;
            if (vidas > 0) {
                let div = document.getElementById("juego");
                div.removeChild(document.getElementById("svg1"));
                juego = new Juego();
                juego.dibujar();

            } else {
                let div = document.getElementById("juego");
                div.removeChild(document.getElementById("svg1"));
                let div2 = document.getElementById("puntuacion");
                div2.removeChild(h1);
                div2.removeChild(h2);
                vidas = 3;
                puntuacion = 0;
                clearInterval(intervalo);
                $('#modal').modal('show');
                //location.href ="perdida";
                console.log("Has perdido");
            }

        }
            for (let i = 0; i < disparos.length; i++) {
                let disparo = disparos[i];
                if (disparo != null) { 
                if (disparo.impacto()) {
                    puntuacion++;
                    h1.textContent = puntuacion;
                    let svg = document.getElementById("svg1");
                    svg.removeChild(disparo.shot);
                    disparos[i] = null;

                    if(ganador()){
                        let div = document.getElementById("juego");
                        div.removeChild(document.getElementById("svg1"));
                        let div2 = document.getElementById("puntuacion");
                        div2.removeChild(h1);
                        div2.removeChild(h2);
                        vidas = 3;
                        puntuacion = 0;
                        clearInterval(intervalo);
                        $('#modal2').modal('show');
                        
                    }
                    
                }
                disparo.dibujar();
            }

    }
    }, 100);
}
//