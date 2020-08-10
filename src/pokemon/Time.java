/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author CarlosXavier
 */
public class Time extends TimerTask implements Runnable {

    private volatile boolean stopWork;
    Label lblTiempo;
    Timer timer = new Timer();
    public int segundos=0;

    public Time(Label lblTiempo) {
        this.lblTiempo = lblTiempo;
    }
    @Override
    public void run() {
        int i =0;
        /**TODO: Cambiar por constante de Tiempo de Juego.
         */        
        while(i<10 && Principal.cerrar==false){
            segundos++;
            Platform.runLater(() -> {
                //lblTiempo.setText(new Date().toString());
                
                lblTiempo.setText(Integer.toString(0));
                lblTiempo.setText(Integer.toString(segundos));
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.err.println("InterruptedException in TIME");
//                segundos =0;
//                Logger.getLogger(Time.class.getName()).log(Level.SEVERE, null, ex);

            }

            i++;
        }

    }
    
}
