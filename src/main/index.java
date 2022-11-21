package main;

import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import static files.manageFiles.*;


public class index {
    public static void main( String[] args) {

        //main.Cliente cliente = new main.Cliente();
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        JOptionPane.showMessageDialog(null,"Bienvenido al TP4 de Programación orientada a objetos con Java");

        //Creamos archivo
        //createFile("file\\hola.xlsx");
        //createFile("file\\hola.txt");

        //escribirArchivo("file\\hola.txt","Hola desde JAVA");
        //leerArchivo("file\\hola.txt");
        borrarArchivo("file\\hola.txt");

        //Iniciamos las tres liquidaciones base.
        Liquidacion.liquidacionesBase();

        while (!salir)
        {
            System.out.println("====================================");
            System.out.println("MENU DE OPCIONES");
            System.out.println("");
            System.out.println("1. Clientes");
            System.out.println("2. Liquidaciones");
            System.out.println("3. main.Vencimientos");
            System.out.println("4. Salir");
            System.out.println("");
            System.out.println("====================================");


            try {
                System.out.print("Escribe una de las opciones: ");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        Cliente.opcionesCliente();
                        break;
                    case 2:
                        Liquidacion.opcionesLiquidacion();
                        break;
                    case 3:
                        Vencimientos.opcionesVencimiento();
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }


    }



    }

