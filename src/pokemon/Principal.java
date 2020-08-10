/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author acarrera
 */
public class Principal extends Application {
    public static boolean cerrar= false;
    ThreadGroup group = new ThreadGroup("Threads collection");
    
    public static ArrayList<Entrenador> entrenadores= new ArrayList<>();
    public static ArrayList<Pokemon> pokemonsLibres = new ArrayList<>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        llenarDatosEntrenador();
        lecturaArchivoPokemon("pokemonslibres.txt");
        launch();
    }
    public static void llenarDatosEntrenador(){
        entrenadores.add(new Entrenador("Ash"));
        entrenadores.add(new Entrenador("Misti"));
        entrenadores.add(new Entrenador("Brooke"));
        
    }

    public static void lecturaArchivoPokemon(String inFilename) {
        Charset charset = Charset.forName("ISO-8859-1");
        try{
            ArrayList<String> linea = (ArrayList<String>) Files.readAllLines(Paths.get(inFilename),charset);
            int c=0;
            for(String l:linea){
                String[] datos = l.split(",");
                c++;
                if(c!=1){
                    System.out.println(l);
                    pokemonsLibres.add(new Pokemon(datos[0], datos[1], datos[2], datos[3], datos[4]));
                }  
            }
       } catch (IOException ex) {
            System.out.println("Error al trabajar con NIO " + ex);
        }
       
        
    }
    
//    public void cerrar(){
//        cerrar = true;
//        Map<Thread, StackTraceElement[]> p = Thread.getAllStackTraces();
//        for(Map.Entry)
//        System.out.println(p);
//    }

    @Override
    public void start(Stage primaryStage) {
           Scene sc = new Scene(new PanelPokemon().getRoot(),600,400);
           primaryStage.setScene(sc);
           primaryStage.setOnCloseRequest(e->cerrar = true);
           primaryStage.show();
           //sc.setCursor(new ImageCursor(new Image("/images/Pokeball_icon-icons.com_67448.png")));
    }
}
