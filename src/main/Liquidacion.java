package main;

import javax.swing.*;
import java.util.*;
import static files.manageFiles.*;

public class Liquidacion {

    //Definimos los atributos de la clase Liquidacion
    private String codigoLiquidacion;
    private String nombreLiquidacion;
    //private static float monto;
    static ArrayList< Liquidacion > listaDeLiquidaciones = new ArrayList();


    //Metodo constructor de la clase Liquidacion.
    public Liquidacion(String code, String name){

    	this.codigoLiquidacion = (code == "") ? "" : code;
        this.nombreLiquidacion = (name == "") ? "" : name;

    }

    // METODO PARA ESTABLECER LAS LIQUIDACIONES BASE.
    public static void liquidacionesBase(){

        Liquidacion ingresosBrutos = new Liquidacion("1","IIBB");
        Liquidacion cargasSociales = new Liquidacion("2","Cargas Sociales");
        Liquidacion iva = new Liquidacion("3","IVA");


        //Añadimos tres liquidaciones base:

        listaDeLiquidaciones.add(ingresosBrutos);
        listaDeLiquidaciones.add(cargasSociales);
        listaDeLiquidaciones.add(iva);

    }

    //============METODOS==============================

    //DESPLEGAMOS MENU DE OPCIONES EN LIQUIDACION.

    public static void opcionesLiquidacion(){

        //Con la clase escanner recibimos lo que el usuario escribe por teclado en sn.
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir)
        {

            System.out.println("====================================");
            System.out.println("MENU DE OPCIONES DE LIQUIDACIONES");
            System.out.println("");
            System.out.println("1. Crear nueva Liquidacion");
            System.out.println("2. Mostrar Liquidaciones");
            System.out.println("3. Buscar Liquidacion por código");
            System.out.println("4. Modificar liquidacion");
            System.out.println("5. Eliminar liquidacion");
            System.out.println("6. Mostrar log");
            System.out.println("7. Salir");
            System.out.println("");
            System.out.println("====================================");

            try {

                System.out.println("Escribe una de las opciones: \n");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        Liquidacion.añadirLiquidacion();
                        break;
                    case 2:
                        Liquidacion.mostrarLiquidaciones();
                        break;
                    case 3:
                        Liquidacion.buscarLiquidaciones();
                        break;
                    case 4:
                        Liquidacion.modificarLiquidacion();
                        break;
                    case 5:
                        Liquidacion.eliminarLiquidacion();
                        break;
                    case 6:
                        leerArchivo("src\\files\\log\\log_liquidaciones.txt");
                        break;
                    case 7:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }// fin del catch
        }// fin del ciclo while
    }// fin del metodo opcionesLiquidacion


    // Al igual que hicimos en el metodo agregarCliente(), también programamos un metodo agregaliquidacion para instanciar la clase Liquidacion.
    public static void añadirLiquidacion(){
        try{
            // Creamos el objeto Liquidacion.
            Liquidacion liquidacion = new Liquidacion("","");

            // Tomamos los valores ingresados por el usuario con JOptionPane y el metodo showInputDialog.
            String codigo_Liquidacion = JOptionPane.showInputDialog("Ingrese el código de la liquidación: ");
            String nombre_Liquidacion = JOptionPane.showInputDialog("Ingrese el nombre de la liquidación: ");

            // Con los metodos set, establecemos los valores de los atributos luego de que el usuario ingresa los datos.
            liquidacion.setCodigoLiquidacion(codigo_Liquidacion);
            liquidacion.setNombreLiquidacion(nombre_Liquidacion);

            // Añadimos la nueva liquidacion a la array de lista De Liquidaciones.
            listaDeLiquidaciones.add(liquidacion);

            //Generamos una entrada en el log de liquidaciones:
            escribirArchivo("src\\files\\log\\log_liquidaciones.txt"," Una nuevo nueva de código: " + codigo_Liquidacion + ", ha sido agregada");

            //Mostramos un mensaje al usuario
            JOptionPane.showMessageDialog(null,"La liquidacion ha sido agregada exitosamente!");
        }catch (IllegalArgumentException e){
            System.out.println("No se pudo agregar la liquidacion");
        }// fin del catch
    }// fin del metodo añadirLiquidacion

    //===================METODOS===========================

    //MOSTRAR LIQUIDACIONES
    public static void mostrarLiquidaciones(){
        try{
            System.out.println("Lista de liquidaciones: ");
            // EN caso de que no se hayan agregado liquidaciones, este condicional mostrara un mensaje al usuario.
            if(listaDeLiquidaciones.size() == 0){
                System.out.println("No hay liquidaciones agregadas actualmente");
            }

            // Con un ciclo for recorremos el arraylist listaDeLiquidaciones y mostramos los datos de cada liquidacion agregada.
            for(int i = 0;i< listaDeLiquidaciones.size();i++){
                System.out.println("================================");
                //Agregar còdigo liquidacion antes del nombre
                System.out.println("Liquidacion numero: " + (i + 1) + "\nCodigo liquidacion: "+ listaDeLiquidaciones.get(i).getCodigoLiquidacion() + "\nNombre: " + listaDeLiquidaciones.get(i).getNombreLiquidacion() + "\n" );
            }
        }catch(IllegalAccessError error){
            System.out.println("Ha ocurrido un error");
        }// fin del catch
    }// fin del metodo mostrarLiquidaciones

    //BUSCAR LIQUIDACIONES
    public static void buscarLiquidaciones(){

        try{
            String codigo = JOptionPane.showInputDialog("Ingrese el código de la liquidación que desea buscar: ");
            int position = -1;

            for(int i = 0; i < listaDeLiquidaciones.size();i++){
                if(listaDeLiquidaciones.get(i).getCodigoLiquidacion().equals(codigo)){
                    System.out.println("================================");
                    System.out.println("Resultado de la busqueda: ");
                    System.out.println("Liquidacion numero: " + (i + 1) + "\nCodigo liquidacion: "+ listaDeLiquidaciones.get(i).getCodigoLiquidacion() + "\nNombre: " + listaDeLiquidaciones.get(i).getNombreLiquidacion() + "\n" );
                    position = i;
                }
            }

            //Manejamos el error en el caso de que hayamos ingresado un codigo incorrecto.
            if(position != -1){
                System.out.println("");
            }else{
                throw new IllegalArgumentException(
                        "No se ha encontrado una liquidacion que coincida con el codigo ingresado"
                );
            }
        }catch(IllegalArgumentException e){
            System.out.printf("Exception: %s\n\n", e.getMessage());
        }// fin del catch
    }// fin del metodo buscarLiquidaciones

    //BUSCAR LIQUIDACIONES PARA CODIGO
    public static String buscarLiquidaciones_Cod(String codigo){

            //String codigo = JOptionPane.showInputDialog("Ingrese el código de la liquidación que desea buscar: ");
            String codigoDevuelto = "--1";

            for(int i = 0; i < listaDeLiquidaciones.size();i++){
                if(listaDeLiquidaciones.get(i).getCodigoLiquidacion().equals(codigo)){
                    codigoDevuelto = listaDeLiquidaciones.get(i).getCodigoLiquidacion();
                }
            }
            return codigoDevuelto;
    }

    // BUSCAR LIQUIDACIONES PARA NOMBRE
    public static String buscarLiquidaciones_Nombre(String codigo){

        //String codigo = JOptionPane.showInputDialog("Ingrese el código de la liquidación que desea buscar: ");
        String nombreDevuelto = "--1";

        for(int i = 0; i < listaDeLiquidaciones.size();i++){
            if(listaDeLiquidaciones.get(i).getCodigoLiquidacion().equals(codigo)){
                //System.out.println("================================");
                //System.out.println("Resultado de la busqueda: ");
                //System.out.println("main.Liquidacion numero: " + (i + 1) + "\nCodigo liquidacion: "+ listaDeLiquidaciones.get(i).getCodigoLiquidacion() + "\nNombre: " + listaDeLiquidaciones.get(i).getNombreLiquidacion() + "\n" );
               nombreDevuelto = listaDeLiquidaciones.get(i).getNombreLiquidacion();
            }
        }

        return nombreDevuelto;
    }// fin del metodo buscarLiquidaciones_Nombre

    //MODIFICAR UNA LIQUIDACION
    public static void modificarLiquidacion(){
        Liquidacion.mostrarLiquidaciones();
        try{
            String codigo = JOptionPane.showInputDialog("Ingrese el código de la liquidación que desea modificar: ");
            int position = -1;

            for(int i = 0; i < listaDeLiquidaciones.size();i++){
                if(listaDeLiquidaciones.get(i).getCodigoLiquidacion().equals(codigo)){
                    position = i;
                    String name = JOptionPane.showInputDialog("Ingrese el nuevo nombre: ");
                    listaDeLiquidaciones.get(i).setNombreLiquidacion(name);
                    //Mostramos un mensaje al usuario
                    JOptionPane.showMessageDialog(null,"La liquidacion ha sido modificada exitosamente!");
                    //Generamos una entrada en el log de liquidaciones:
                    escribirArchivo("src\\files\\log\\log_liquidaciones.txt"," Una liquidacion de código: " + listaDeLiquidaciones.get(i).getCodigoLiquidacion() + ", ha sido modificada");

                }
            }

            //Manejamos el error en el caso de que hayamos ingresado un codigo incorrecto.
            if(position != -1){
                System.out.println("");
            }else{
                throw new IllegalArgumentException(
                        "No se ha encontrado una liquidacion que coincida con el codigo ingresado"
                );
            }
        }catch(IllegalArgumentException e){
            System.out.printf("Exception: %s\n\n", e.getMessage());
        }

    }

    //ELIMINAR UNA LIQUIDACION

    public static void eliminarLiquidacion(){
        Liquidacion.mostrarLiquidaciones();
        try{
            String codigo = JOptionPane.showInputDialog("Ingrese el código de la liquidación que desea eliminar: ");
            int position = -1;

            for(int i = 0; i < listaDeLiquidaciones.size();i++){
                if(listaDeLiquidaciones.get(i).getCodigoLiquidacion().equals(codigo)){
                    //Generamos una entrada en el log de liquidaciones:
                    escribirArchivo("src\\files\\log\\log_liquidaciones.txt"," Una liquidacion de código: " + listaDeLiquidaciones.get(i).getCodigoLiquidacion() + ", ha sido eliminada");
                    position = i;
                    listaDeLiquidaciones.remove(position);
                    JOptionPane.showMessageDialog(null,"La liquidacion ha sido eliminada exitosamente!");
                }
            }


            //Manejamos el error en el caso de que hayamos ingresado un codigo incorrecto.
            if(position != -1){
                System.out.println("");
            }else{
                throw new IllegalArgumentException(
                        "No se ha encontrado una liquidacion que coincida con el codigo ingresado"
                );
            }
        }catch(IllegalArgumentException e){
            System.out.printf("Exception: %s\n\n", e.getMessage());
        }// fin del catch
    }// fin del metodo eliminarLiquidacion


    //Metodos settler: Para establecer los valores a traves de metodos y parametros

    public void setCodigoLiquidacion(String codeLiquidacion){
        this.codigoLiquidacion = codeLiquidacion;
    }

    public void setNombreLiquidacion(String nameLiquidacion){
        this.nombreLiquidacion = nameLiquidacion;
    }

    //Metodos getter:

    public String getCodigoLiquidacion(){
    return codigoLiquidacion;
}
        public String getNombreLiquidacion(){
        return nombreLiquidacion;
    }
   
    

}
