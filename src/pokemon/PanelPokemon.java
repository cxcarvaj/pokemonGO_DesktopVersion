/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import java.util.Random;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author Carlos Carvajal
 */
public class PanelPokemon {
    private final BorderPane root = new BorderPane();
    private final Pane paneJuego = new Pane();
    private final ComboBox<Entrenador> cboEntrenadores = new ComboBox();
    private final HBox panelTop= new HBox(new Label("Entrenador: "),cboEntrenadores);
    private final HBox panelInferior= new HBox(new Label("EN ESTA SECCION IRAN LOS DATOS DEL POKEMON QUE SE ATRAPARA"));
    private final FlowPane panelDerecho = new FlowPane(Orientation.VERTICAL);
    private PathTransition transition;
    private final Label label = new Label();


    public PanelPokemon() {
        //LLena ComboBox
        label.setTextFill(Color.web("BLACK"));
        label.setFont(new Font("Arial", 20));
        panelTop.getChildren().add(label);
        panelTop.setSpacing(20);
        cboEntrenadores.getItems().addAll(Principal.entrenadores);
        cboEntrenadores.setValue(Principal.entrenadores.get(0));
        root.setTop(panelTop);
        root.setBottom(panelInferior);        
        paneJuego.setStyle("-fx-background-image: url('"+getClass().getResource("fondo.jpg").toExternalForm()+"');"
                + "-fx-background-repeat: stretch;"             
                + "-fx-background-position: top center;");
        
        cboEntrenadores.setOnAction(e->llenarPanelD());
        root.setCenter(paneJuego);
         
        root.setRight(panelDerecho);
        animation();
        Thread t1 = new Thread(new pokemonThread());
        t1.start();

      
    }
    public void AgregarInfo(Pokemon p){
        String lbInfo = p.getNombre().toUpperCase()+": "+p.getPoder()+" "+p.getTipo().toUpperCase()+": "+p.getVelocidad();
        Label info = new Label(lbInfo);
        panelInferior.getChildren().clear();
        panelInferior.getChildren().addAll(info);
    }
    
    public void llenarPanelD(){
        Entrenador trainerPokemon = cboEntrenadores.getValue();
        for(Entrenador t: Principal.entrenadores){
            if(t.equals(trainerPokemon)){
                try{
                    panelDerecho.getChildren().clear();
                    for(Pokemon p: t.getSetPokemonsTipo()){
                        ImageView img= new ImageView();  
                        img.setImage(new Image(p.getRuta()));
                        img.setFitWidth(75);
                        img.setFitHeight(74);
                        VBox infoD = new VBox();
                        infoD.setAlignment(Pos.BASELINE_CENTER);
                        infoD.getChildren().addAll(new Label(p.getTipo().toUpperCase()),img, new Label("Nombre: "+p.getNombre()),new Label("Poder: "+p.getPoder()),new Label("|||||||||||||||||||"));
                        panelDerecho.getChildren().add(infoD);
                    }
                }catch(NullPointerException ex){
                    System.out.println(ex);
                }
            }
        }
    }
    
    public static int indice;
    public ImageView agregarPokemon(){
         Thread t2 = new Thread(new Time(label));
         t2.start();

        System.out.println("Ingreso");
        
        String ruta = Principal.pokemonsLibres.get(indice).getRuta();
        ImageView pokemon= new ImageView(ruta);
        paneJuego.getChildren().clear();
        paneJuego.getChildren().add(pokemon);
        AgregarInfo(Principal.pokemonsLibres.get(indice));
        pokemon.setOnMouseClicked(e->clickPokemon(Principal.pokemonsLibres.get(indice)));
        return pokemon;
    }
    public void clickPokemon(Pokemon p){

        
        Entrenador trainer = cboEntrenadores.getValue();
        System.out.println(trainer);
        trainer.registroPokemonTrainer(p);
        Principal.pokemonsLibres.remove(p);
        System.out.println(Principal.pokemonsLibres);
        paneJuego.getChildren().clear();
        llenarPanelD();
    
    
    }
    void animation(){
        /*
        * Cree un objeto de tipo Path, con el objetivo de crear un cubic Curve
        */
        try{
            Random r = new Random();
            int num1 = r.nextInt((int)paneJuego.getWidth());
            int num2 = r.nextInt((int)paneJuego.getHeight());
            Rectangle trazo= new Rectangle(num1, num2);
            trazo.setLayoutX(200);
            trazo.setLayoutY(200);

        
                     
        //TODO: Inicializo mi objeto PathTransition         
         transition = new PathTransition();
         //TODO: Anado un node a mi path con el metodo setNode(), aqui debo anadir lo que deseo q se mueva
         transition.setNode(agregarPokemon());
         /* TODO:Seteo la duracion de la animacion con el metodo setDuration
          * este metodo me permite darle una velocidad a la animacion si pongo
          */
         transition.setDuration(Duration.millis(10000-(Integer.parseInt(Principal.pokemonsLibres.get(indice).getVelocidad())*1000)));
//         System.out.println(10000-(Integer.parseInt(Principal.pokemonsLibres.get(indice).getVelocidad())*1000));
         /**
          * TODO: Seteo los ciclos de animacion con el metodo setCycleCount
          * me indica cuantas veces deseo q se repita la animacion si quiero indefinido pongo Timeline.INDEFINITE
          */
         transition.setCycleCount(Timeline.INDEFINITE);
         //TODO: Seteo el path de como se va a mover mi figura, puede escoger cualquier forma
         transition.setPath(trazo);
         
         //Setteo autoreverse para que regrese a la posicion original si se desea, comentar sino se desea
         transition.setAutoReverse(true);
         //TODO: Poner play a la transicion sino no correra usar el metodo play. 
         transition.play();
         }catch(IllegalArgumentException e){
            System.out.println("Dimensiones negativas o nulas");
        }
     }
    class pokemonThread implements Runnable{
        private volatile boolean stopWork0;
            @Override
            public void run(){
                while(!Principal.pokemonsLibres.isEmpty() && Principal.cerrar==false){
                    try {
                        Random r = new Random();
                        indice = r.nextInt(Principal.pokemonsLibres.size());
                        Platform.runLater(()->animation());
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        System.err.println("Interrupted Thread POKEMON");
//                        System.out.println("Error: "+ex);
                    }
                }
            }
            public void stop() {
            stopWork0 = true;
        }
    }

    public BorderPane getRoot() {
        return root;
    }

}
