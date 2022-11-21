package main;

import java.util.*;
import javax.swing.JOptionPane;


public class Cliente {

    //Definimos los atributos de la clase cliente

    private String codigoCliente;
    private String razonSocial;
    private String nombreFantasia;
    String[] liquidacionesContratadas = {"IIBB", "IVA", "SUELDO", "PRODUCTOS"};
    static ArrayList< Cliente > listaDeClientes = new ArrayList();
    private String email;
    private int cuitPrefijo;
    private int cuitMedio;
    private int cuitSufijo;


    //Method constructor
    public Cliente() {

        //Inicializamos las variables del metodo constructor

        this.codigoCliente = "";
        this.razonSocial = "";
        this.nombreFantasia = "";
        this.email = "";
        this.cuitPrefijo = 0;
        this.cuitMedio = 0;
        this.cuitSufijo = 0;

    }

    //===============================METODOS===================================

    //DESPLEGAMOS MENU DE OPCIONES DE CLIENTE
    public static void opcionesCliente(){

        //Con la clase escanner recibimos lo que el usuario escribe por teclado en sn.
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir)
        {

            System.out.println("====================================");
            System.out.println("MENU DE OPCIONES DE CLIENTE");
            System.out.println("");
            System.out.println("1. Crear nuevo Cliente");
            System.out.println("2. Mostrar Clientes");
            System.out.println("3. Buscar Cliente por código");
            System.out.println("4. Modificar clientes");
            System.out.println("5. Eliminar cliente"); // pronto sera agregado
            System.out.println("6. Salir");
            System.out.println("");
            System.out.println("====================================");

            try {

                System.out.println("Escribe una de las opciones: \n");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        Cliente.agregarCliente();
                        break;
                    case 2:
                        Cliente.mostrarCliente();
                        break;
                    case 3:
                        Cliente.buscarClientes();
                        break;
                    case 4:
                        Cliente.modificarCliente();
                        break;
                    case 5:
                        Cliente.eliminarCliente();
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
            }// fin del catch
        }// fin del ciclo while
    }// fin del metodo opcionesCliente

    //Metodo para agregar una nueva instancia de la clase main.Cliente.
    public static void agregarCliente(){
        try{
            // creamos el objeto cliente.
            Cliente cliente = new Cliente();

            // Tomamos los valores ingresados por el usuario con JOptionPane y el metodo showInputDialog.
            String codigo_Cliente = JOptionPane.showInputDialog("Ingrese el codigo del main.Cliente: ");

            String nombre_Fantasia = JOptionPane.showInputDialog("Ingrese el Nombre completo: ");

            String razon_social = JOptionPane.showInputDialog("Ingrese la Razon social: ");

            String _email = JOptionPane.showInputDialog("Ingrese el correo electronico: ");

            int cuit_Prefijo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el prefijo del Cuit: "));

            int cuit_Medio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el termino medio del Cuit: "));

            int cuit_Sufijo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el sufijo del Cuit: "));

            // Con los metodos set, establecemos los valores de los atributos luego de que el usuario ingresa los datos.
            cliente.setCodigoCliente(codigo_Cliente);
            cliente.setNombreFantasia(nombre_Fantasia);
            cliente.setEmail(_email);
            cliente.setRazonSocial(razon_social);
            cliente.setCuitPrefijo(cuit_Prefijo);
            cliente.setCuitMedio(cuit_Medio);
            cliente.setCuitSufijo(cuit_Sufijo);

            // Añadimos al nuevo cliente a la array de listaDeClientes.
            listaDeClientes.add(cliente);

            //Mostramos un mensaje al usuario.
            JOptionPane.showMessageDialog(null,"El main.Cliente ha sido agregado exitosamente!");
        }catch(IllegalArgumentException e){
            System.out.println("No se pudo agregar el cliente");
        }// fin del catch
    }// fin del metodo agregar cliente


    //MOSTRAR CLIENTES.
    public static void mostrarCliente(){
    System.out.println("Lista de clientes: ");
    // EN caso de que no se hayan agregado clientes, este condicional mostrara un mensaje al usuario.
        if(listaDeClientes.size() == 0){
            System.out.println("No hay clientes agregados actualmente");
        }

        // Con un ciclo for recorremos el arraylist listaDeClientes y mostramos los datos de cada cliente agregado.
        for(int i = 0;i< listaDeClientes.size();i++){
            System.out.println("================================");
            System.out.println("main.Cliente numero: " + i + "\nNombre: " + listaDeClientes.get(i).obtenerNombre() + "\n" + "Email: " + listaDeClientes.get(i).obtenerEmail() + "\n" + "Razon social: " + listaDeClientes.get(i).obtenerRazonSocial() + "\n" + "CUIT: " + listaDeClientes.get(i).obtenerCuitPrefijo()+"-"+listaDeClientes.get(i).obtenerCuitMedio()+"-"+listaDeClientes.get(i).obtenerCuitSufijo());
        }
    }// fin metodo mostrar clientes

    //MODIFICAR CLIENTES
    public static void modificarCliente(){
        Cliente.mostrarCliente();
        try{
            //Guardamos en una variable codigo el valor ingresado por el usuario
            String codigo = JOptionPane.showInputDialog("Ingrese el código de el cliente que desea modificar: ");
            int position = -1;

            //Recorremos el array list listaDeVencimientos y encontramos un objeto cuyo codigo coincida con el ingresado.
            for(int i = 0; i < listaDeClientes.size();i++){
                if(listaDeClientes.get(i).obtenerCodigoCliente().equals(codigo)){
                    position = i;
                    //Con la clase escanner recibimos lo que el usuario escribe por teclado en sn.
                    Scanner sn = new Scanner(System.in);
                    boolean salir = false;
                    int opcion; //Guardaremos la opcion del usuario

                    while (!salir)
                    {

                        System.out.println("====================================");
                        System.out.println("QUE DESEA MODIFICAR?");
                        System.out.println("");
                        System.out.println("1. Nombre completo de cliente");
                        System.out.println("2. Razon social");
                        System.out.println("3. Email");
                        System.out.println("4. Prefijo Cuit");
                        System.out.println("5. Cuit Medio");
                        System.out.println("6. Sufijo Cuit");
                        System.out.println("7. Salir");
                        System.out.println("");
                        System.out.println("====================================");

                        try {

                            System.out.println("Escribe una de las opciones: \n");
                            opcion = sn.nextInt();

                            switch (opcion) {
                                case 1:
                                    String nombreCompleto = JOptionPane.showInputDialog("Ingrese el nuevo nombre: ");
                                    listaDeClientes.get(i).setNombreFantasia(nombreCompleto);
                                    //Mostramos un mensaje al usuario.
                                    JOptionPane.showMessageDialog(null,"El main.Cliente ha sido modificado exitosamente!");
                                    break;
                                case 2:
                                    String razonSocial = JOptionPane.showInputDialog("Ingrese la nueva razon social: ");
                                    listaDeClientes.get(i).setRazonSocial(razonSocial);
                                    //Mostramos un mensaje al usuario.
                                    JOptionPane.showMessageDialog(null,"El main.Cliente ha sido modificado exitosamente!");
                                    break;
                                case 3:
                                    String emailCliente = JOptionPane.showInputDialog("Ingrese el nuevo email: ");
                                    listaDeClientes.get(i).setEmail(emailCliente);
                                    //Mostramos un mensaje al usuario.
                                    JOptionPane.showMessageDialog(null,"El main.Cliente ha sido modificado exitosamente!");
                                    break;
                                case 4:
                                    int prefijoCuit = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo prefijo: "));
                                    listaDeClientes.get(i).setCuitPrefijo(prefijoCuit);
                                    //Mostramos un mensaje al usuario.
                                    JOptionPane.showMessageDialog(null,"El main.Cliente ha sido modificado exitosamente!");
                                    break;
                                case 5:
                                    int medioCuit = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo cuit medio: "));
                                    listaDeClientes.get(i).setCuitMedio(medioCuit);
                                    //Mostramos un mensaje al usuario.
                                    JOptionPane.showMessageDialog(null,"El main.Cliente ha sido modificado exitosamente!");
                                    break;
                                case 6:
                                    int sufijoCuit = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo sufijo: "));
                                    listaDeClientes.get(i).setCuitSufijo(sufijoCuit);
                                    //Mostramos un mensaje al usuario.
                                    JOptionPane.showMessageDialog(null,"El main.Cliente ha sido modificado exitosamente!");
                                    break;
                                case 7:
                                    salir = true;
                                    break;
                                default:
                                    System.out.println("Solo números entre 1 y 7");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Debes insertar un número");
                            sn.next();
                        }
                    }
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
        }catch(IllegalArgumentException e){
            System.out.printf("Exception: %s\n\n", e.getMessage());
        }// fin del catch
    }// fin del metodo modificar clientes

    //BUSCAR CLIENTE
    public static void buscarClientes(){

        try{
            //Guardamos en una variable codigo el valor ingresado por el usuario
            String codigo = JOptionPane.showInputDialog("Ingrese el código el codigo del cliente que desea buscar: ");
            int position = -1;

            //Recorremos el array list listaDeVencimientos y encontramos un objeto cuyo codigo coincida con el ingresado.
            for(int i = 0; i < listaDeClientes.size();i++){
                if(listaDeClientes.get(i).obtenerCodigoCliente().equals(codigo)){
                    System.out.println("================================");
                    System.out.println("Resultado de la busqueda: ");
                    System.out.println("main.Cliente numero: " + i + "\nCodigo de cliente: " + listaDeClientes.get(i).obtenerCodigoCliente() + "\nNombre Completo: " + listaDeClientes.get(i).obtenerNombre() + "\n" + "Email: " + listaDeClientes.get(i).obtenerEmail() + "\n" + "Razon social: " + listaDeClientes.get(i).obtenerRazonSocial() + "\n" + "CUIT: " + listaDeClientes.get(i).obtenerCuitPrefijo()+"-"+listaDeClientes.get(i).obtenerCuitMedio()+"-"+listaDeClientes.get(i).obtenerCuitSufijo());
                    position = i;
                }
            }

            //Manejamos el error en el caso de que hayamos ingresado un codigo incorrecto.
            if(position != -1){
                System.out.println("");
            }else{
                throw new IllegalArgumentException(
                        "No se ha encontrado un cliente que coincida con el código ingresado"
                );
            }
        }catch(IllegalArgumentException e){
            System.out.printf("Exception: %s\n\n", e.getMessage());
        }// fin del catch
    }// fin del metodo buscar cliente

    //ELIMINAR UN CLIENTE
    public static void eliminarCliente(){
        Cliente.mostrarCliente();
        try{
            //Guardamos en una variable codigo el valor ingresado por el usuario
            String codigo = JOptionPane.showInputDialog("Ingrese el código de el cliente que desea eliminar: ");
            int position = -1;

            //Recorremos el array list listaDeVencimientos y encontramos un objeto cuyo codigo coincida con el ingresado.
            for(int i = 0; i < listaDeClientes.size();i++){
                if(listaDeClientes.get(i).obtenerCodigoCliente().equals(codigo)){
                    position = i;
                    // Quitamos el objeto de la arrayList usando el metodo remove().
                    listaDeClientes.remove(position);
                    JOptionPane.showMessageDialog(null,"El main.Cliente ha sido eliminado exitosamente!");
                }
            }


            //Manejamos el error en el caso de que hayamos ingresado un codigo incorrecto.
            if(position != -1){
                System.out.println("");
            }else{
                throw new IllegalArgumentException(
                        "No se ha encontrado un  cliente que coincida con el codigo ingresado"
                );
            }
        }catch(IllegalArgumentException e){
            System.out.printf("Exception: %s\n\n", e.getMessage());
        }// fin del catch
    }// fin del metodo eliminar cliente


    //Metodos settler:



    public void setNombreFantasia(String NombreFantasia){
        this.nombreFantasia = NombreFantasia;
    }


    public void setRazonSocial(String RazonSocial){
        this.razonSocial = RazonSocial;
    }


    public void setCodigoCliente(String CodigoCliente){
        this.codigoCliente = CodigoCliente;
    }


    public void setEmail(String Email){
        this.email = Email;
    }

    public void setCuitPrefijo(int CuitPre){
        this.cuitPrefijo = CuitPre;
    }

    public void setCuitMedio(int CuitMedio){
        this.cuitMedio = CuitMedio;
    }

    public void setCuitSufijo(int CuitSufijo){
        this.cuitSufijo = CuitSufijo;
    }

    //Metodos getter:


    public String obtenerCodigoCliente(){

        return codigoCliente;
    }
    public  String obtenerNombre(){

        return nombreFantasia;
    }
    public String obtenerRazonSocial(){

        return razonSocial;
    }
    public String obtenerEmail(){

        return email;
    }
    public int obtenerCuitPrefijo(){

        return cuitPrefijo;
    }
    public int obtenerCuitMedio(){
        return cuitMedio;
    }
    public int obtenerCuitSufijo(){
        return cuitSufijo;
    }

} // fin de clase main.Cliente
