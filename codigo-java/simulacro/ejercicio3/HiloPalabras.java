package ejercicio3;

import java.util.concurrent.Semaphore;

public class HiloPalabras extends Thread {
    // Variables globales requeridas por el enunciado:
    // En Java un identificador no puede comenzar con dígito, por lo que usamos _5omas (o cincoOMas)
    public static int menos5 = 0;
    public static int _5omas = 0;

    // Semáforos independientes (uno para menos5 y otro para 5omas)
    public static Semaphore semMenos5 = new Semaphore(1);
    public static Semaphore sem5omas = new Semaphore(1);

    private String frase;

    public HiloPalabras(String nombre, String frase) {
        super(nombre);
        this.frase = frase;
        start();
    }

    @Override
    public void run() {
        // Separamos la frase en palabras (ignorando espacios consecutivos y puntuación básica)
        String[] palabras = frase.trim().split("\\s+");

        for (String p : palabras) {
            // Limpiamos signos de puntuación pegados a la palabra para contar solo letras
            String palabraLimpia = p.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]", "");
            int longitud = palabraLimpia.length();
            if (longitud == 0) continue;

            boolean procesar = false;

            switch (getName()) {
                case "2" -> {
                    // El hilo "2" contará las palabras de 2 o menos letras
                    if (longitud <= 2) {
                        procesar = true;
                        try {
                            semMenos5.acquire();
                            menos5++;
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                            semMenos5.release();
                        }
                    }
                }
                case "4" -> {
                    // El hilo "4" contará las palabras de 3 y 4 letras
                    if (longitud >= 3 && longitud <= 4) {
                        procesar = true;
                        try {
                            semMenos5.acquire();
                            menos5++;
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                            semMenos5.release();
                        }
                    }
                }
                case "6" -> {
                    // El hilo "6" contará las palabras de 5 y 6 letras
                    if (longitud >= 5 && longitud <= 6) {
                        procesar = true;
                        try {
                            sem5omas.acquire();
                            _5omas++;
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                            sem5omas.release();
                        }
                    }
                }
                case "8" -> {
                    // El hilo "8" contará las palabras de más de 6 letras
                    if (longitud > 6) {
                        procesar = true;
                        try {
                            sem5omas.acquire();
                            _5omas++;
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                            sem5omas.release();
                        }
                    }
                }
            }

            // Cada hilo deberá parar 5ms después de contar cada palabra
            if (procesar) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
