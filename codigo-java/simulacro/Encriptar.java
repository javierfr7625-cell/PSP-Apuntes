package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Encriptar {
    public static void main(String[] args){
       String password = recogerString();
       StringBuilder PasswordEncriptado = new StringBuilder();

        char[] letras = password.toCharArray();
       for (char letra: letras){
           PasswordEncriptado.append((char) (letra + 2));
       }


        System.out.println(PasswordEncriptado);
    }

    private static String recogerString() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String respuesta = "";

        try {
            respuesta = br.readLine();
        }catch (IOException e){
            System.out.println(e.getMessage());

        }
        return respuesta;
    }


}
