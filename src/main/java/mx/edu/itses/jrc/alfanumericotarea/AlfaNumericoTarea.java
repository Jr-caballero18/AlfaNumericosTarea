/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mx.edu.itses.jrc.alfanumericotarea;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import mx.edu.itses.jrc.alfanumericotarea.Tipo;
/**
 *
 * @author juras
 */


public class AlfaNumericoTarea implements Runnable {

    private Tipo tipo;

    public AlfaNumericoTarea(Tipo tipo) {
        this.tipo = tipo;
    }

  

    public static void main(String[] args) {
        // Tarea 1 Thread clásico usando Runnable 
        Thread hilo1 = new Thread(() -> 
        new AlfaNumericoTarea(Tipo.NUMERO).run(), "Thread-Normal");
        hilo1.start();

        //  Tarea 2 ExecutorService 
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> new AlfaNumericoTarea(Tipo.LETRA).run());
        executor.shutdown();

        // Tarea 3 Runnable
        Runnable tarea3 = () -> new AlfaNumericoTarea(Tipo.NUMERO).run();
        new Thread(tarea3, "Thread-Lambda").start();

        // 4.Tarea 4 TimerTask
        Timer timer = new Timer("Timer-Hilo");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new AlfaNumericoTarea(Tipo.LETRA).run();
            }
        }, 2000);

        System.out.println("Hilos lanzados desde el main");
    }
    
      @Override
    public void run() {
        if (tipo == Tipo.NUMERO) {
            for (int i = 1; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " Número: " + i);
            }
        } else if (tipo == Tipo.LETRA) {
            for (char c = 'A'; c <= 'Z'; c++) {
                System.out.println(Thread.currentThread().getName() + " Letra: " + c);
            }
        }
    }
}