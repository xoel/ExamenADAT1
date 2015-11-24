package examenficheros1xoelvazquez;

import java.io.*;
import java.util.*;

/**
 * @author María José Galán López
 */
public class MenuSecuencial {

    static String fichEquipo = "Equipos.dat", fichCiclista = "Ciclistas.dat", fichTexto = "AumentoPuntos.txt";
    static Operaciones operaciones = new Operaciones(fichEquipo, fichCiclista, fichTexto);
  

    public static void main(String[] args) throws IOException {
        int opc = 1;
        int opcsub;
        while (opc != 3) {
            opc = Menu();
            switch (opc) {
                case 1:
                    opcsub = 1;
                    while (opcsub != 3) {
                        opcsub = SubMenuEquipo();
                        operacionesEquipo(opcsub);

                    }
                    break;
                case 2:
                    opcsub = 1;
                    while (opcsub != 4) {
                        opcsub = SubMenuCiclista();
                        operacionesEmpleado(opcsub);

                    }
                    break;

               
            }
        }
    }

    public static int Menu() {
        int opc = 1;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Menu de opciones");
        System.out.println("----------------");
        System.out.println(" 1.-Gestión de Equipos");
        System.out.println(" 2.-Gestion de Ciclistas ");   
        System.out.println(" 3.-Salir");
        boolean sw = true;
        while (sw) {
            System.out.print("Elige una opcion <1-3>");
            opc = entrada.nextInt();
            if (opc >= 1 && opc <= 3) {
                sw = false;
            }
        }
        return opc;
    }

    public static int SubMenuEquipo() {
        int opc = 1;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Menu de Equipo");
        System.out.println("----------------");
        System.out.println(" 1.-Alta Equipos");
        System.out.println(" 2.-Consulta todos los Equipos ");
        System.out.println(" 3.-Salir");
        boolean sw = true;
        while (sw) {
            System.out.print("Elige una opcion <1-3>");
            opc = entrada.nextInt();
            if (opc >= 1 && opc <= 3) {
                sw = false;
            }
        }
        return opc;
    }

    public static int SubMenuCiclista() {
        int opc = 1;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Menu de Ciclista");
        System.out.println("----------------");
        System.out.println(" 1.-Alta Ciclista");
        System.out.println(" 2.-Consulta todos los ciclistas");
        System.out.println(" 3.-Añadir puntos ciclistas de un equipo");
        System.out.println(" 4.-Salir");
        boolean sw = true;
        while (sw) {
            System.out.print("Elige una opcion <1-4>");
            opc = entrada.nextInt();
            if (opc >= 1 && opc <= 4) {
                sw = false;
            }
        }
        return opc;
    }

    public static void operacionesEquipo(int opc) throws IOException {
        switch (opc) {
            case 1:
                operaciones.AltasEquipo();
                break;
            case 2:
                operaciones.LeerTodosEquipos();
        }
    }

    public static void operacionesEmpleado(int opc) throws IOException {
        switch (opc) {
            case 1:
                operaciones.AltasCiclistas();
                break;
            case 2:
                operaciones.LeerTodosCiclistas();
                break;

            case 3:
                operaciones.subirPuntosEquipo();
        }
    }
}
