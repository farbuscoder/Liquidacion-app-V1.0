package main;
import javax.swing.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import static files.manageFiles.*;

public class LiquidacionesImportes {
    //private String key;
    private String codCliente;
    private String nomCliente;
    private int nroVencimiento;
    private String fechaVencimiento;
    private String nombreLiquidacion;
    private float importeLiquidacion;
    static private ArrayList< LiquidacionesImportes > listaDeImportes = new ArrayList();

    //METODO CONSTRUCTOR
    public LiquidacionesImportes(String nombre, String codigo, String vencimiento, String cCliente, String nCliente, int nVencimiento, float importe){
        this.codCliente = cCliente;
        this.nomCliente = nCliente;
        this.nroVencimiento = nVencimiento;
        this.fechaVencimiento =  vencimiento;
        this.nombreLiquidacion = nombre;
        this.importeLiquidacion = importe;

    }



    //DESPLEGAMOS MENU DE OPCIONES EN VENCIMIENTOS.

    public static void opcionesLiquidacionesImportes(){

        //Con la clase scanner recibimos lo que el usuario escribe por teclado en sn.
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir)
        {

            System.out.println("====================================");
            System.out.println("MENU DE OPCIONES DE IMPORTE");
            System.out.println("");
            System.out.println("1. Agregar importe");
            System.out.println("2. Mostrar Clientes, Vencimientos e Importes");
            System.out.println("3. Quitar importe");
            System.out.println("4. Enviar e-mail");
            System.out.println("5. Modificar importe");
            System.out.println("6. Salir");
            System.out.println("");
            System.out.println("====================================");

            try {

                System.out.println("Escribe una de las opciones: \n");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        LiquidacionesImportes.agregarImporte();
                        break;
                    case 2:
                        LiquidacionesImportes.mostrarImportesVencimientos();

                        break;
                    case 3:
                        LiquidacionesImportes.quitarImporte();
                        break;
                    case 4:
                        LiquidacionesImportes.enviaremail();
                        break;
                    case 5:
                        LiquidacionesImportes.modificarImporte();
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }

    //AGREGAR IMPORTE
    public static void agregarImporte() {
        try{

            //Instanciamos la clase Vencimientos con parametros en blanco.
            LiquidacionesImportes importes = new LiquidacionesImportes("","","","","",0,0);
            // Mostramos el arraylist creada en la clase Cliente al usuario.
            Cliente.mostrarCliente();
            //Pedimos al usuario que ingrese los datos necesarios para buscar el Cliente.
            String codigo_Cliente = JOptionPane.showInputDialog("Ingrese el código de Cliente para cargar el importe: ");
            String codigoCliDevuelto = Cliente.buscarCliente_Cod(codigo_Cliente);
            String NombreCliDevuelto = Cliente.buscarCliente_Nombre(codigoCliDevuelto);
            // Mostramos el arraylist creada en la clase LiquidacionesVencimientos al usuario.
            Vencimientos.mostrarVencimientos();
            //Pedimos al usuario que ingrese los datos necesarios para buscar el vencimiento.
            int numero_Vencimiento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero del Vencimiento para cargar el importe: "));
            int numeroVencDevuelto = Vencimientos.buscarVencimientos_Cod(numero_Vencimiento);
            String nombreVencDevuelto = Vencimientos.buscarVencimientos_Nombre(numeroVencDevuelto);
            String fechaVencDevuelto = Vencimientos.buscarVencimientos_Fecha(numeroVencDevuelto);
            Float importeLiq = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el Importe: "));

            //Seteamos las variables en los atributos del objeto de tipo LiquidacionesImportes.
            importes.setCodigoCli(codigoCliDevuelto);
            importes.setNombreCli(NombreCliDevuelto);
            importes.setNroLiq(numeroVencDevuelto);
            importes.setNombreLiq(nombreVencDevuelto);
            importes.setVencimientoLiq(fechaVencDevuelto);
            importes.ImpLiq(importeLiq);

            //Agregamos el objeto vencimiento al arraylist listaDeVencimientos con el metodo add.
            listaDeImportes.add(importes);

            //Generamos una nueva entrada en el log importes.
            escribirArchivo("src\\files\\log\\log_importes.txt"," Un importe para el cliente de código: " + codigoCliDevuelto + ", ha sido agregado");

            JOptionPane.showMessageDialog(null, "El importe se agrego exitosamente");
        }catch(IllegalArgumentException e){
            System.out.println("No se pudo agregar el importe");
        }// fin del catch
    }// fin del metodo agregar Vencimientos

    //MOSTRAR IMPORTES VENCIMIENTOS
    public static void mostrarImportesVencimientos(){
        System.out.println("Lista de vencimientos, importes y clientes: ");
        // EN caso de que no se hayan agregado importes, este condicional mostrara un mensaje al usuario.
        if(listaDeImportes.size() == 0){
            System.out.println("No hay importes agregados actualmente");
        }

        // Con un ciclo for recorremos el arraylist listaDeImportes y mostramos los datos de cada cliente, vencimiento e importes agregados.
        for(int i = 0;i< listaDeImportes.size();i++){
            System.out.println("================================");
            System.out.println("Importe numero: " + (i + 1)
                    + "\nCod Cliente: " + listaDeImportes.get(i).getCodigoCli()
                    + "\nCliente: " + listaDeImportes.get(i).getNombreCli()
                    + "\nLiquidacion: " + listaDeImportes.get(i).getNombreLiq()
                    + "\nVencimiento: " + listaDeImportes.get(i).getVencimientoLiq()
                    + "\nImporte: $" + listaDeImportes.get(i).ImpLiq()
            );
        }
    }





    public static void modificarImporte(){
        LiquidacionesImportes.mostrarImportesVencimientos();
        try{
            //Guardamos en una variable codigo el valor ingresado por el usuario
            String numero = JOptionPane.showInputDialog("Ingrese el numero del importe que desea modificar: ");
            int position = -1;

            int numeroInteger = Integer.parseInt(numero);

            //Recorremos el array list listaImportes y encontramos un objeto cuyo codigo coincida con el ingresado.
            for(int i = 0; i < listaDeImportes.size();i++){
                if(i==numeroInteger - 1){
                    position = i;
                    String importe = JOptionPane.showInputDialog("Ingrese el nuevo importe: ");
                    listaDeImportes.get(i).ImpLiq(Float.parseFloat(importe));
                    //Mostramos un mensaje al usuario
                    JOptionPane.showMessageDialog(null,"El importe ha sido modificado exitosamente!");
                    //Generamos una entrada en el log de clientes:
                    escribirArchivo("src\\files\\log\\log_vencimientos.txt"," Un importe de numero: " + numero + ", ha sido modificado");
                }
            }



            //Manejamos el error en el caso de que hayamos ingresado un codigo incorrecto.
            if(position != -1){
                System.out.println("");
            }else{
                throw new IllegalArgumentException(
                        "No se ha encontrado un importe que coincida con el numero ingresado"
                );
            }
        }catch (IllegalArgumentException e){
            System.out.printf("Exception: %s\n\n", e.getMessage());
        }// fin del catch
    }



    public static void quitarImporte(){
        LiquidacionesImportes.opcionesLiquidacionesImportes();
        try{
            //Guardamos en una variable codigo el valor ingresado por el usuario
            String numero = JOptionPane.showInputDialog("Ingrese el numero del importe que desea eliminar: ");
            int position = -1;
            int numeroInteger = Integer.parseInt(numero);

            //Recorremos el array list listaDeImportes y encontramos un objeto cuyo codigo coincida con el ingresado.
            for(int i = 0; i < listaDeImportes.size();i++){
                if(i == numeroInteger - 1){
                    //Generamos una entrada en el log de clientes:
                    escribirArchivo("src\\files\\log\\log_vencimientos.txt"," Un importe de numero: " + numero + ", ha sido eliminado");
                    position = i;
                    // Quitamos el objeto de la arrayList usando el metodo remove().
                    listaDeImportes.remove(position);
                    JOptionPane.showMessageDialog(null,"El importe ha sido eliminado exitosamente!");
                }
            }


            //Manejamos el error en el caso de que hayamos ingresado un codigo incorrecto.
            if(position != -1){
                System.out.println("");
            }else{
                throw new IllegalArgumentException(
                        "No se ha encontrado un importe con el numero ingresado"
                );
            }
        }catch (IllegalArgumentException e){
            System.out.printf("Exception: %s\n\n", e.getMessage());
        }// fin del catch
    }


    public static void enviaremail(){
    }


    //Metodo setter y getter

    public void setCodigoCli(String cCliente) {
        this.codCliente = cCliente;
    }

    public void setNombreCli(String nCliente) {
        this.nomCliente = nCliente;
    }


    public void setNroLiq(int lcodigo) {
        this.nroVencimiento = lcodigo;
    }


    public void setNombreLiq(String nombreLiqVenc) {
        this.nombreLiquidacion = nombreLiqVenc;
    }


    public void setVencimientoLiq(String fechaVenc) {
        this.fechaVencimiento = fechaVenc;
    }

    //como set
    public void ImpLiq(float importe) {
        this.importeLiquidacion = importe;
    }

    //como get
    public Float ImpLiq() {
        return importeLiquidacion;
    }

    public String getCodigoCli() {
        return codCliente;
    }

    public String getNombreCli() {
        return nomCliente;
    }


    public int getNroLiq() {
        return nroVencimiento;
    }


    public String getNombreLiq() {
        return nombreLiquidacion;
    }


    public String getVencimientoLiq() {
        return fechaVencimiento;
    }



}
