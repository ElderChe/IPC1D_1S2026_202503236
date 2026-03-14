package proyecto1;

import javax.swing.JFrame;
import java.util.Scanner;

public class Proyecto1 {
    
    static Usuario[] usuarios = new Usuario[100];
    static int contadorUsuarios = 0;

    static Libro[] libros = new Libro[100];
    static int contadorLibros = 0;

    static Prestamo[] prestamos = new Prestamo[100];
    static int contadorPrestamos = 0;

    public static void main(String[] args) {

    crearAdmin();
    crearLibrosIniciales();
    cargarUsuarios();

    Login login = new Login();
    login.setVisible(true);
    }
    
    
    
    
    static void crearAdmin() {

    Usuario admin = new Usuario();

    admin.usuario = "admin";
    admin.contraseña = "admin";
    admin.rol = "ADMIN";

    usuarios[contadorUsuarios] = admin;
    contadorUsuarios++;

}
    
    static void crearLibrosIniciales() {

    libros[contadorLibros] = new Libro();
    libros[contadorLibros].codigo = "001";
    libros[contadorLibros].titulo = "Cheee";
    libros[contadorLibros].autor = "Anuel";
    libros[contadorLibros].cantidadTotal = 5;
    libros[contadorLibros].cantidadDisponible = 5;
    contadorLibros++;

    libros[contadorLibros] = new Libro();
    libros[contadorLibros].codigo = "002";
    libros[contadorLibros].titulo = "Elderrr";
    libros[contadorLibros].autor = "Masreal";
    libros[contadorLibros].cantidadTotal = 3;
    libros[contadorLibros].cantidadDisponible = 3;
    contadorLibros++;
}
    


    
    static String prestarLibro(String codigoBuscado) {

    Libro libroEncontrado = null;

    for (int i = 0; i < contadorLibros; i++) {
        if (libros[i].codigo.equals(codigoBuscado)) {
            libroEncontrado = libros[i];
            break;
        }
    }

    if (libroEncontrado != null && libroEncontrado.cantidadDisponible > 0) {

    libroEncontrado.cantidadDisponible--;
    
    escribirBitacora("Se prestó el libro: " + codigoBuscado);

    Prestamo nuevoPrestamo = new Prestamo();
    nuevoPrestamo.codigoPrestamo = "P" + (contadorPrestamos + 1);
    nuevoPrestamo.codigoLibro = libroEncontrado.codigo;
    nuevoPrestamo.estado = "ACTIVO";

    prestamos[contadorPrestamos] = nuevoPrestamo;
    contadorPrestamos++;

    return "Préstamo registrado: " + nuevoPrestamo.codigoPrestamo;

} else {
    return "No se pudo realizar el préstamo.";
}
}
    
    static void listarPrestamosActivos() {

    System.out.println("\n--- PRESTAMOS ACTIVOS ---");

    for (int i = 0; i < contadorPrestamos; i++) {
        if (prestamos[i].estado.equals("ACTIVO")) {
            System.out.println(prestamos[i].codigoPrestamo +
                               " - Libro: " + prestamos[i].codigoLibro);
        }
    }
}
    
  static String devolverPrestamo(String codigoPrestamoBuscar) {

    Prestamo prestamoEncontrado = null;

    for (int i = 0; i < contadorPrestamos; i++) {
        if (prestamos[i].codigoPrestamo.equals(codigoPrestamoBuscar)) {
            prestamoEncontrado = prestamos[i];
            break;
        }
    }

    if (prestamoEncontrado != null &&
    prestamoEncontrado.estado.equals("ACTIVO")) {

    prestamoEncontrado.estado = "DEVUELTO";
    escribirBitacora("Se devolvió el préstamo: " + codigoPrestamoBuscar);

    for (int i = 0; i < contadorLibros; i++) {
        if (libros[i].codigo.equals(prestamoEncontrado.codigoLibro)) {
            libros[i].cantidadDisponible++;
            break;
        }
    }

    return "Devolución realizada correctamente.";
}

return "No se encontró el préstamo o ya estaba devuelto.";
}
    
    
    public static void listarLibros(Libro[] libros, int contadorLibros) {

    System.out.println("\nLISTA DE LIBROS:");

    for (int i = 0; i < contadorLibros; i++) {
        System.out.println("Codigo: " + libros[i].codigo +
                " | Titulo: " + libros[i].titulo +
                " | Disponible: " + libros[i].cantidadDisponible);
    }
}
    
    
    
    //aqui va la bitacoration
    
    static void escribirBitacora(String texto){

    try{

        java.io.FileWriter fw = new java.io.FileWriter("bitacora.txt", true);
        java.io.PrintWriter pw = new java.io.PrintWriter(fw);

        pw.println(texto);

        pw.close();
        fw.close();

    }catch(Exception e){
        System.out.println("Error escribiendo bitacora");
    }

}
    
    static void guardarUsuarioArchivo(Usuario u){

    try{

        java.io.FileWriter fw = new java.io.FileWriter("cuentas.txt", true);
        java.io.PrintWriter pw = new java.io.PrintWriter(fw);

        pw.println(u.rol + ";" + u.usuario + ";" + u.contraseña + ";" + u.nombre);

        pw.close();
        fw.close();

    }catch(Exception e){
        System.out.println("Error guardando usuario");
    }

}
    
    static void cargarUsuarios(){

    try{

        java.io.BufferedReader br = new java.io.BufferedReader(
                new java.io.FileReader("cuentas.txt"));

        String linea;

        while((linea = br.readLine()) != null){

            String[] partes = linea.split(";");

            Usuario u = new Usuario();
            u.rol = partes[0];
            u.usuario = partes[1];
            u.contraseña = partes[2];
            u.nombre = partes[3];

            usuarios[contadorUsuarios] = u;
            contadorUsuarios++;

        }

        br.close();

    }catch(Exception e){
        System.out.println("No se pudo cargar cuentas.txt");
    }

}
    
    static void eliminarOperador(String usuario){

    for(int i = 0; i < contadorUsuarios; i++){

        if(usuarios[i].usuario.equals(usuario)){

            for(int j = i; j < contadorUsuarios - 1; j++){
                usuarios[j] = usuarios[j + 1];
            }

            contadorUsuarios--;

            guardarUsuariosArchivo();

            break;
        }

    }

}
    
    static void guardarUsuariosArchivo(){

    try{

        java.io.PrintWriter pw = new java.io.PrintWriter("cuentas.txt");

        for(int i = 0; i < contadorUsuarios; i++){

            Usuario u = usuarios[i];

            pw.println(
                u.rol + ";" +
                u.usuario + ";" +
                u.contraseña + ";" +
                u.nombre
            );

        }

        pw.close();

    }catch(Exception e){
        System.out.println("Error guardando cuentas");
    }

}
    
}
