package ejercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ejercicio3Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introduce una frase para contar palabras según su longitud:");
        String frase = "";

        try {
            frase = br.readLine();
        } catch (IOException e) {
            System.out.println("Error al leer frase: " + e.getMessage());
            return;
        }

        // Crear 4 hilos llamados "2", "4", "6", "8"
        String[] nombresHilos = {"2", "4", "6", "8"};
        ArrayList<HiloPalabras> hilos = new ArrayList<>();

        for (String nombre : nombresHilos) {
            hilos.add(new HiloPalabras(nombre, frase));
        }

        // Esperar a que terminen todos los hilos
        for (HiloPalabras h : hilos) {
            try {
                h.join();
            } catch (InterruptedException e) {
                System.out.println("Error esperando al hilo: " + e.getMessage());
            }
        }

        // Al terminar todos los hilos, el programa principal sacará los resultados
        System.out.println("\n=== Resultados Finales ===");
        System.out.println("Palabras con menos de 5 letras (menos5): " + HiloPalabras.menos5);
        System.out.println("Palabras con 5 o más letras (_5omas): " + HiloPalabras._5omas);
    }
}
