package main;

import javax.swing.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Vencimientos {
//private String key;
private String fechaVencimiento;
private String codigoLiquidacion;
private String nombreLiquidacion;
static ArrayList< Vencimientos > listaDeVencimientos = new ArrayList();

//METODO CONSTRUCTOR
public Vencimientos(String nombre, String codigo, String vencimiento){
    //this.key = generarKey();//Eliminar
    this.fechaVencimiento =  vencimiento;
    this.codigoLiquidacion = codigo;
    this.nombreLiquidacion = nombre;

}



    //DESPLEGAMOS MENU DE OPCIONES EN VENCIMIENTOS.

    public static void opcionesVencimiento(){

        //Con la clase scanner recibimos lo que el usuario escribe por teclado en sn.
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir)
        {

            System.out.println("====================================");
            System.out.println("MENU DE OPCIONES DE Vencimiento");
            System.out.println("");
            System.out.println("1. Agregar vencimiento");
            System.out.println("2. Mostrar vencimientos");
            System.out.println("3. Buscar vencimiento por codigo");
            System.out.println("4. Modificar Vencimiento");
            System.out.println("5. Quitar vencimiento"); // pronto sera agregado
            System.out.println("6. Salir");
            System.out.println("");
            System.out.println("====================================");

            try {

                System.out.println("Escribe una de las opciones: \n");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        Vencimientos.agregarvencimiento();
                        break;
                    case 2:
                        Vencimientos.mostrarVencimientos();
                        break;
                    case 3:
                        Vencimientos.buscarVencimientos();
                        break;
                    case 4:
                        Vencimientos.modificarVencimiento();
                        break;
                    case 5:
                        Vencimientos.eliminarVencimiento();
                        break;
                    case 6:
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

    //AGREGAR VENCIMIENTO
    public static void agregarvencimiento() {
        try{
            //Instanciamos la clase Vencimientos con parametros en blanco.
            Vencimientos vencimiento = new Vencimientos("","","");
            // Mostramos el arraylist creada en la clase Liquidacion al usuario.
            Liquidacion.mostrarLiquidaciones();
            //Pedimos al usuario que ingrese los datos necesarios.
            String codigo_Liquidacion = JOptionPane.showInputDialog("Ingrese el código de la liquidación: ");
            String codigoLiqDevuelto = Liquidacion.buscarLiquidaciones_Cod(codigo_Liquidacion);
            String NombreLiqDevuelto = Liquidacion.buscarLiquidaciones_Nombre(codigo_Liquidacion);
            String vencimiento_Liquidacion = JOptionPane.showInputDialog("Ingrese el vencimiento: ");

            //Seteamos las variables en los atributos del objeto de tipo Vencimientos.
            vencimiento.setFechaVencimiento(vencimiento_Liquidacion);
            vencimiento.setCodigoLiquidacionEnVencimientos(codigoLiqDevuelto);
            vencimiento.setNombreLiquidacionEnVencimientos(NombreLiqDevuelto);

            //Agregamos el objeto vencimiento al arraylist listaDeVencimientos con el metodo add.
            listaDeVencimientos.add(vencimiento);
            JOptionPane.showMessageDialog(null, "El vencimiento se agrego exitosamente");
        }catch(IllegalArgumentException e){
            System.out.println("No se pudo agregar el cliente");
        }// fin del catch
    }// fin del metodo agregar Vencimientos

    //MOSTRAR VENCIMIENTOS
    public static void mostrarVencimientos(){
        System.out.println("Lista de vencimientos: ");
        // EN caso de que no se hayan agregado vencimientos, este condicional mostrara un mensaje al usuario.
        if(listaDeVencimientos.size() == 0){
            System.out.println("No hay vencimientos agregados actualmente");
        }

        // Con un ciclo for recorremos el arraylist listaDeVencimientos y mostramos los datos de cada vencimiento agregado.
        for(int i = 0;i< listaDeVencimientos.size();i++){
            System.out.println("================================");
            System.out.println("Vencimiento numero: " + (i + 1) + "\nNombre: " + listaDeVencimientos.get(i).getNombreLiquidacionEnVencimientos()
            + "\nCodigo de liquidacion: " + listaDeVencimientos.get(i).getCodigoLiquidacionEnVencimientos() +
                 "\nFecha de Vencimiento: " + listaDeVencimientos.get(i).getFechaVencimiento()
            );
        }
    }

    //BUSCAR VENCIMIENTO POR CODIGO
    public static void buscarVencimientos(){
        try{
            //Guardamos en una variable codigo el valor ingresado por el usuario
            String codigo = JOptionPane.showInputDialog("Ingrese el código de la liquidación asociada al vencimiento que desea buscar: ");
            int position = -1;

            //Recorremos el array list listaDeVencimientos y encontramos un objeto cuyo codigo coincida con el ingresado.
            for(int i = 0; i < listaDeVencimientos.size();i++){
                if(listaDeVencimientos.get(i).getCodigoLiquidacionEnVencimientos().equals(codigo)){
                System.out.println("================================");
                System.out.println("Resultado de la busqueda: ");
                System.out.println("Vencimiento numero: " + (i + 1) + "\nCodigo liquidacion: "+ listaDeVencimientos.get(i).getCodigoLiquidacionEnVencimientos() + "\nNombre: " + listaDeVencimientos.get(i).getNombreLiquidacionEnVencimientos() +
                        "\nFecha de vencimiento: "+ listaDeVencimientos.get(i).getFechaVencimiento()
                );
                position = i;
            }
            }

            //Manejamos el error en el caso de que hayamos ingresado un codigo incorrecto.
            if(position != -1){
            System.out.println("");
            }else{
                throw new IllegalArgumentException(
                    "No se ha encontrado un vencimiento que coincida con el código ingresado"
                );
        }
        }catch(IllegalArgumentException e){
            System.out.printf("Exception: %s\n\n", e.getMessage());
        }// fin del catch
    }// fin del metodo buscar main.Vencimientos


    //MODIFICAR UN VENCIMIENTO
    public static void modificarVencimiento(){
         try{
             //Guardamos en una variable codigo el valor ingresado por el usuario
             String codigo = JOptionPane.showInputDialog("Ingrese el código de la liquidacion asociada al vencimiento que desea modificar: ");
             int position = -1;

             //Recorremos el array list listaDeVencimientos y encontramos un objeto cuyo codigo coincida con el ingresado.
             for(int i = 0; i < listaDeVencimientos.size();i++){
                 if(listaDeVencimientos.get(i).getCodigoLiquidacionEnVencimientos().equals(codigo)){
                     position = i;
                     String fecha = JOptionPane.showInputDialog("Ingrese la nueva fecha: ");
                     listaDeVencimientos.get(i).setFechaVencimiento(fecha);
                     //Mostramos un mensaje al usuario
                     JOptionPane.showMessageDialog(null,"El vencimiento ha sido modificado exitosamente!");
                 }
             }



             //Manejamos el error en el caso de que hayamos ingresado un codigo incorrecto.
             if(position != -1){
                 System.out.println("");
             }else{
                 throw new IllegalArgumentException(
                         "No se ha encontrado un vencimiento que coincida con el codigo ingresado"
                 );
             }
         }catch (IllegalArgumentException e){
             System.out.printf("Exception: %s\n\n", e.getMessage());
         }// fin del catch
    }// fin del metodo modificarVencimiento

    //ELIMINAR UN VENCIMIENTO
    public static void eliminarVencimiento(){
        Vencimientos.mostrarVencimientos();
    try{
        //Guardamos en una variable codigo el valor ingresado por el usuario
        String codigo = JOptionPane.showInputDialog("Ingrese el código de la liquidacion asociada al vencimiento que desea eliminar: ");
        int position = -1;

        //Recorremos el array list listaDeVencimientos y encontramos un objeto cuyo codigo coincida con el ingresado.
        for(int i = 0; i < listaDeVencimientos.size();i++){
            if(listaDeVencimientos.get(i).getCodigoLiquidacionEnVencimientos().equals(codigo)){
                position = i;
                // Quitamos el objeto de la arrayList usando el metodo remove().
                listaDeVencimientos.remove(position);
                JOptionPane.showMessageDialog(null,"El vencimiento ha sido eliminado exitosamente!");
            }
        }


        //Manejamos el error en el caso de que hayamos ingresado un codigo incorrecto.
        if(position != -1){
            System.out.println("");
        }else{
            throw new IllegalArgumentException(
                    "No se ha encontrado una vencimiento que coincida con el codigo ingresado"
            );
        }
    }catch (IllegalArgumentException e){
        System.out.printf("Exception: %s\n\n", e.getMessage());
    }// fin del catch
}// fin del metodo eliminarVencimiento


    //Metodo setter y getter
    public String getNombreLiquidacionEnVencimientos(){
        return nombreLiquidacion;
    }

    public String getCodigoLiquidacionEnVencimientos(){
        return codigoLiquidacion;
    }

    public String getFechaVencimiento(){
        return fechaVencimiento;
    }

    public void setNombreLiquidacionEnVencimientos(String nombre){
        this.nombreLiquidacion = nombre;
    }

    public void setCodigoLiquidacionEnVencimientos(String codigo){
        this.codigoLiquidacion = codigo;
    }

    public void setFechaVencimiento(String vencimiento){
        this.fechaVencimiento = vencimiento;
    }
}