/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomemorama;

import javafx.scene.image.Image;

/**
 *
 * @author Luis Matuz
 */
public class Imagen {
    private int id;
    private Image imagen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    
    public Imagen(int id, String ruta){
        this.id = id;
        this.imagen = new Image(ruta);
    }
    
    
}
