package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ejercicio1Main {
    private static String usuario = null;
    private static String passwordEnc = null;

    public static void main(String[] args) {
        String opcion = "3";
        do {
            mostrarOpciones();
            opcion = recogerString();
            realizarOpcion(opcion);
        } while (!opcion.equals("3"));
    }

    private static void realizarOpcion(String opcion) {
        switch (opcion) {
            case "1" -> registrarse();
            case "2" -> acreditarse();
            case "3" -> System.out.println("Saliendo...");
            default -> System.out.println("Opción incorrecta");
        }
    }

    private static void registrarse() {
        System.out.println("Dime el usuario");
        usuario = recogerString();

        System.out.println("Dime el password");
        String password = recogerString();

        Process procesoEncriptar = generarProcesoEncriptar();
        if (procesoEncriptar != null) {
            escribirEnProceso(password, procesoEncriptar);
            passwordEnc = leerDesdeProceso(procesoEncriptar);
            try {
                procesoEncriptar.waitFor();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Registrado correctamente");
        } else {
            System.out.println("Error al ejecutar el subproceso de encriptación.");
        }
    }

    private static void acreditarse() {
        if (usuario == null || passwordEnc == null) {
            System.out.println("Primero hay que registrarse");
            return;
        }

        String nuevoUsuario = "";
        do {
            System.out.println("Dime el usuario");
            nuevoUsuario = recogerString();
            if (!usuario.equals(nuevoUsuario)) {
                System.out.println("Usuario incorrecto");
            }
        } while (!usuario.equals(nuevoUsuario));

        String password = "";
        String passwordDesenc = "";
        do {
            System.out.println("Dime el password");
            password = recogerString();
            passwordDesenc = desenCriptarPassword();
            if (!password.equals(passwordDesenc)) {
                System.out.println("Password incorrecto");
            }
        } while (!password.equals(passwordDesenc));

        System.out.println("Bienvenido " + usuario + "!!!");
    }

    private static String desenCriptarPassword() {
        String[] comandoDesencriptar = {"java", "-jar", "out/artifacts/Desencriptar/Desencriptar.jar"};
        Process proceso = crearProceso(comandoDesencriptar);
        if (proceso != null) {
            escribirEnProceso(passwordEnc, proceso);
            String resultado = leerDesdeProceso(proceso);
            try {
                proceso.waitFor();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return resultado;
        }
        return "";
    }

    private static Process generarProcesoEncriptar() {
        String[] comandoEncriptar = {"java", "-jar", "out/artifacts/Encriptar/Encriptar.jar"};
        return crearProceso(comandoEncriptar);
    }

    private static Process crearProceso(String[] comando) {
        ProcessBuilder pb = new ProcessBuilder(comando);
        Process proceso = null;
        try {
            proceso = pb.start();
        } catch (IOException e) {
            System.out.println("Error al crear el proceso: " + e.getMessage());
        }
        return proceso;
    }

    private static void escribirEnProceso(String texto, Process proceso) {
        PrintStream ps = new PrintStream(proceso.getOutputStream());
        ps.println(texto);
        ps.flush();
        ps.close();
    }

    private static String leerDesdeProceso(Process proceso) {
        BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String respuesta = "";
        try {
            respuesta = br.readLine();
        } catch (IOException e) {
            System.out.println("Error al leer desde subproceso: " + e.getMessage());
        }
        return respuesta;
    }

    private static void mostrarOpciones() {
        System.out.println("Selecciona una de las opciones\n" +
                "1. Registrarse.\n" +
                "2. Acreditarse.\n" +
                "3. Salir.");
    }

    private static String recogerString() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String respuesta = "";
        try {
            respuesta = br.readLine();
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
        return respuesta;
    }
}
