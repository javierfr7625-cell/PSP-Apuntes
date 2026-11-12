package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Desencriptar {
    public static void main(String[] args){
        String passwordEncriptada = recogerString();
        StringBuilder PasswordDesencriptada = new StringBuilder();

        char[] letras = passwordEncriptada.toCharArray();
        for (char letra: letras){
            PasswordDesencriptada.append((char) (letra - 2));
        }


        System.out.println(PasswordDesencriptada);
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
