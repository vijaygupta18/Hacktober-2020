/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomemorama;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.FontWeight;
import javafx.stage.StageStyle;
import javafx.util.Duration;
/**
 *
 * @author Luis Matuz
 */
public class ProyectoMemorama extends Application {
    
    private String eleccionComboFotos;
    private String eleccionComboSize;
    private String fotos[];
    private Button jugar;
    private Cuadro seleccionado = null;
    private Stage auxPrimaryStage;
    private int contador =0;
    private int contadorVictoria;
    private int contadorIntentos = 0;
    private int numeroPartidas=0;
    private int partidasGanadas=0;
    private Text partidasG;
    private Text intentos;
    private Text textoJuegos;
    //Toda la estructura de los componentes
    @Override
    public void start(Stage primaryStage) {
        
        
        auxPrimaryStage = primaryStage;
        contadorVictoria=0;
        final ComboBox lista;
        GridPane grid;
        Scene scene;
        textoJuegos = new Text("Partidas jugadas: " + numeroPartidas);
        textoJuegos.setFill(Color.WHITE);
        textoJuegos.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        partidasG = new Text("Partidas ganadas: " + partidasGanadas);
        partidasG.setFill(Color.WHITE);
        partidasG.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        
        fotos = new String[] {"a"};
        
        grid = new GridPane();
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        grid.add(textoJuegos,1,2);
        grid.add(partidasG,1,3);
        scene = new Scene(grid,300,200);
        jugar = new Button();
        jugar.setText("Jugar");
        
        jugar.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(eleccionComboFotos!="null"){
                   numeroPartidas++;
                switch(eleccionComboFotos){

                    case "Animales":
                        fotos = new String[] {"images/Animals/fish.jpg","images/Animals/fox.jpg","images/Animals/eagle.jpg","images/Animals/snake.jpg","images/Animals/frog.jpg","images/Animals/panda.jpg","images/Animals/peacock.jpg","images/Animals/shark.jpg"};
                    break;
                    
                    case  "Superhéroes":
                        fotos = new String[]{"images/Superheroes/batman.jpg","images/Superheroes/captain_america.jpg","images/Superheroes/deadpool.jpg","images/Superheroes/green_lantern.jpg","images/Superheroes/ironman.jpg","images/Superheroes/nightwing.jpg","images/Superheroes/spiderman.jpg","images/Superheroes/superman.jpg"};
                    break;
                    
                    case "Personajes de Anime":
                        fotos= new String[]{"images/Anime/genos.jpg","images/Anime/goku.jpg","images/Anime/kaiba.jpg","images/Anime/naruto.jpg","images/Anime/rock_lee.jpg","images/Anime/saitama.jpg","images/Anime/vegeta.jpg","images/Anime/yugi.jpg",};
                    break;
                    
                    case "Frutas":
                        fotos = new String[]{"images/Fruits/apple.jpg","images/Fruits/banana.jpg","images/Fruits/coconut.jpg","images/Fruits/mango.jpg","images/Fruits/pineapple.jpg","images/Fruits/strawberry.jpg","images/Fruits/tangerine.jpg","images/Fruits/watermelon.jpg",};
                    break;
                }
                crearTabla(java.lang.Integer.parseInt(""+eleccionComboSize.split(" ")[0]),eleccionComboFotos);
                
                }   
            }
        });
        
        
        Image menu = new Image("images/Backgrounds/menu.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(menu, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        grid.setBackground(new Background(backgroundImage));
        
        
        lista = crearComboBox();
        ComboBox size = new ComboBox();
        size.getItems().addAll(
        "Seleccionar...",
        "4 fotos",
        "6 fotos",
        "8 fotos",
        "10 fotos",
        "12 fotos",
        "14 fotos",
        "16 fotos"
        );
        size.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String valorAnterior, String valorNuevo) { 
                if(valorNuevo.equals("Seleccionar...")){
                    eleccionComboSize="null";
                }
                else {
                    eleccionComboSize = valorNuevo;
                }
                verificarComboBox(eleccionComboFotos);
            }    
        });
        size.setValue("Seleccionar...");
        grid.add(jugar,0,2);
        grid.add(lista,0,0);
        grid.add(size,0,1);
        
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setTitle("Memorama");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public ComboBox crearComboBox(){
        ComboBox listaImagenes = new ComboBox();
        listaImagenes.getItems().addAll(
            "Seleccionar...",
            "Animales",
            "Superhéroes",
            "Personajes de Anime",
            "Frutas"  
        );
        listaImagenes.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String valorAnterior, String valorNuevo) { 
                eleccionComboFotos = valorNuevo;
                verificarComboBox(valorNuevo);
            }    
        });
        listaImagenes.setValue("Seleccionar...");
        return listaImagenes;
    }

    public void verificarComboBox(String eleccion){
        
        if(eleccion.equals("Seleccionar...") || eleccionComboSize.equals("null")){
            if(eleccion.equals("Seleccionar...")){
                eleccionComboFotos="null";
            }
            else {
                eleccionComboSize = "null";
            }
            jugar.setDisable(true);
        }
        else {
            eleccionComboFotos= eleccion;
            jugar.setDisable(false);
        } 
    }
    
    private class Cuadro extends StackPane {
        private ImageView imagenView;
        private int numeroClicks = 2;
        private boolean isShown;

        public Cuadro(String ruta) {
            isShown=false;
            Imagen imagen = new Imagen(contador,ruta);
            Rectangle border = new Rectangle(150, 150);
            border.setStrokeWidth(3);
            border.setFill(null);
            border.setStroke(Color.WHITE);
            imagenView = new ImageView(imagen.getImagen());
            imagenView.setFitHeight(150);
            imagenView.setFitWidth(150);
            imagenView.setId(""+contador);
            
            setAlignment(Pos.CENTER);
            getChildren().addAll(border, imagenView);

            setOnMouseClicked(this::handleMouseClick);
            ocultar();
        }
        

        public void handleMouseClick(MouseEvent event) {
            
            if (isShown){
                return;
            }
                

            if (seleccionado == null) {
                seleccionado = this;
                mostrar(() -> {});
            }
            else {
                if(!isShown){
                    int auxIntentos = java.lang.Integer.parseInt(intentos.getText());
                    auxIntentos++;
                    intentos.setText(""+ auxIntentos);
                    if (sonIguales(seleccionado)) {
                        contadorVictoria++;
                    } 
                    mostrar(() -> {
                    if (!sonIguales(seleccionado)) {
                        seleccionado.ocultar();
                        this.ocultar();
                    } 
                    seleccionado = null;
                });
                }
                    
                
                
            }
            int num = java.lang.Integer.parseInt(""+eleccionComboSize.split(" ")[0])/2;
            victoria(num);
        }

        public void mostrar(Runnable accion) {
            isShown = true;
            FadeTransition ft = new FadeTransition(Duration.seconds(0.1), imagenView);
            ft.setToValue(1);
            ft.setOnFinished(e -> accion.run());
            ft.play();
        }

        public void ocultar() {
                isShown=false;
                FadeTransition ft = new FadeTransition(Duration.seconds(0.1),imagenView);
                ft.setToValue(0);
                ft.play();
            
        }

        public boolean sonIguales(Cuadro segundaImagen) {
            return imagenView!=segundaImagen.imagenView && imagenView.getId().equals(segundaImagen.imagenView.getId());
        }
        
    }
    
    
    public void crearTabla(int numFotos,String fondos){
        String ruta= "";
        switch(eleccionComboFotos){

                    case "Animales":
                        ruta= "images/Backgrounds/animals.jpg";
                    break;
                    
                    case  "Superhéroes":
                        ruta = "images/Backgrounds/superheroes.jpg";
                    break;
                    
                    case "Personajes de Anime":
                        ruta = "images/Backgrounds/anime.jpg";
                    break;
                    
                    case "Frutas":
                        ruta = "images/Backgrounds/fruits.jpg";
                    break;
                }
        
        Pane root = new Pane();
        Scene x = new Scene(root,830,630);
        Random random = new Random();
        Image background = new Image(ruta);
        BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        root.setBackground(new Background(backgroundImage));
        int aux;
        boolean flag=false;
        int[] numerosUsados = new int[numFotos];
        for(int i=0;i<numerosUsados.length;i++){
            numerosUsados[i]=-1;
        }
        root.setPrefSize(600, 600);
        ArrayList<Cuadro> cuadros = new ArrayList<>();
        for (int i = 0; i < numFotos/2; i++) {
            do{
                flag=false;
                aux=Math.abs(random.nextInt()%fotos.length);
                for(int j=0;j<numerosUsados.length;j++){
                    if(numerosUsados[j]==aux){
                        flag = true;
                    }
                }
            }while(flag);
            cuadros.add(new Cuadro(fotos[aux]));
            cuadros.add(new Cuadro(fotos[aux]));
            numerosUsados[i] = aux;
            contador++;
        }

        Collections.shuffle(cuadros);

        for (int i = 0; i < cuadros.size(); i++) {
            Cuadro cuadro = cuadros.get(i);
            cuadro.setTranslateX(150 * (i % 4));
            cuadro.setTranslateY(150 * (i / 4));
            root.getChildren().add(cuadro);
        }
        Text text = new Text("Movimientos: ");
         text.setFill(Color.WHITE);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        text.setX(610);
        text.setY(20);
        intentos = new Text();
        intentos.setText("0");
        intentos.setFill(Color.WHITE);
        intentos.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        intentos.setX(770);
        intentos.setY(20);
        root.getChildren().add(text);
        root.getChildren().add(intentos);
        
        Button reiniciar = new Button();
        reiniciar.setText("Reiniciar");
        reiniciar.setTranslateX(610);
        reiniciar.setTranslateY(50);
        reiniciar.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
            numeroPartidas++;
            contadorVictoria=0;
            crearTabla(java.lang.Integer.parseInt(""+eleccionComboSize.split(" ")[0]),eleccionComboFotos);
        }
    });
        root.getChildren().add(reiniciar);
        
        Button menu = new Button();
        menu.setText("Menú");
        menu.setTranslateX(680);
        menu.setTranslateY(50);
        menu.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
            start(auxPrimaryStage);
        }
        });
        root.getChildren().add(menu);
        
        
        
        auxPrimaryStage.setScene(x);
        auxPrimaryStage.show();
    }
    
    private void victoria(int numeroFotos){
        if(contadorVictoria==numeroFotos){
                Alert mensaje = new Alert(AlertType.INFORMATION);
                mensaje.setTitle("¡GANÓ LA PARTIDA!");
                mensaje.setContentText("Ha ganado la partida.");
                mensaje.initStyle(StageStyle.UTILITY);
                mensaje.setHeaderText(null);
                mensaje.showAndWait();
                partidasGanadas++;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
