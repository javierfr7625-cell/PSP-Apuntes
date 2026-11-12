package ejercicio2;

import java.util.concurrent.Semaphore;

public class Hilo extends Thread {
    // Semáforo static para que sea compartido por todos los 10 hilos
    public static Semaphore semaphore = new Semaphore(1);
    public static int numeros = 0;

    private String frase;

    public Hilo(String digito, String frase) {
        super(digito);
        this.frase = frase;
        start();
    }

    @Override
    public void run() {
        int contadorLocal = 0;
        char[] caracteres = frase.toCharArray();

        for (char c : caracteres) {
            if (String.valueOf(c).equals(getName())) {
                try {
                    semaphore.acquire();
                    numeros++; // Sección crítica: modificar acumulador global
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    semaphore.release();
                }
                contadorLocal++; // Variable local del hilo
            }
        }
        System.out.println("El número " + getName() + " ha aparecido " + contadorLocal + " veces");
    }
}
