package ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class principal {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Hilo> hilos = new ArrayList<>();
        String frase = "";

        System.out.println("Dime una frase con números:");
        try {
            frase = br.readLine();
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
            return;
        }

        // Crear 10 hilos, uno por cada dígito (0 al 9)
        for (int i = 0; i < 10; i++) {
            hilos.add(new Hilo(String.valueOf(i), frase));
        }

        // Esperar a que terminen todos los hilos
        for (Hilo h : hilos) {
            try {
                h.join();
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido: " + e.getMessage());
            }
        }

        System.out.println("El número total de números en la frase es: " + Hilo.numeros);
    }
}
