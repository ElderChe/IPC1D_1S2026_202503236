package proyecto1;

import javax.swing.JFrame;
import java.util.Scanner;

public class Proyecto1 {

    static Libro[] libros = new Libro[100];
    static int contadorLibros = 0;

    static Prestamo[] prestamos = new Prestamo[100];
    static int contadorPrestamos = 0;

    public static void main(String[] args) {

mostrarLogin();
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
    
    static void mostrarVentana() {

        JFrame ventana = new JFrame("BiblyoSystem");
    ventana.setSize(600, 500);
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.setLocationRelativeTo(null);
    ventana.setLayout(null);
    
    javax.swing.JButton btnListar = new javax.swing.JButton("Listar libros");
    btnListar.setBounds(50,50,200,40);
    
    javax.swing.JButton btnPrestar = new javax.swing.JButton("Prestar Libro");
    btnPrestar.setBounds(50, 110, 200, 40);

    javax.swing.JButton btnDevolver = new javax.swing.JButton("Devolver Libro");
    btnDevolver.setBounds(50, 170, 200, 40);
    
    javax.swing.JButton btnPrestamos = new javax.swing.JButton("Ver Préstamos");
btnPrestamos.setBounds(50, 320, 200, 40);

javax.swing.JButton btnAgregar = new javax.swing.JButton("Agregar Libro");
btnAgregar.setBounds(50, 380, 200, 40);
    
    javax.swing.JLabel lblCodigoLibro = new javax.swing.JLabel("Código Libro:");
lblCodigoLibro.setBounds(50, 230, 100, 30);

javax.swing.JTextField txtCodigoLibro = new javax.swing.JTextField();
txtCodigoLibro.setBounds(150, 230, 100, 30);

javax.swing.JLabel lblCodigoPrestamo = new javax.swing.JLabel("Código Préstamo:");
lblCodigoPrestamo.setBounds(50, 270, 120, 30);

javax.swing.JTextField txtCodigoPrestamo = new javax.swing.JTextField();
txtCodigoPrestamo.setBounds(170, 270, 80, 30);

javax.swing.JLabel lblTitulo = new javax.swing.JLabel("Titulo:");
lblTitulo.setBounds(50, 430, 100, 30);

javax.swing.JTextField txtTitulo = new javax.swing.JTextField();
txtTitulo.setBounds(150, 430, 120, 30);

javax.swing.JLabel lblAutor = new javax.swing.JLabel("Autor:");
lblAutor.setBounds(50, 470, 100, 30);

javax.swing.JTextField txtAutor = new javax.swing.JTextField();
txtAutor.setBounds(150, 470, 120, 30);


    javax.swing.JTextArea area = new javax.swing.JTextArea();
    javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(area);
    scroll.setBounds(300, 50, 250, 300);

    ventana.add(btnListar);
    ventana.add(btnPrestar);
    ventana.add(btnDevolver);
    ventana.add(scroll);
    ventana.add(lblCodigoLibro);
ventana.add(txtCodigoLibro);
ventana.add(lblCodigoPrestamo);
ventana.add(txtCodigoPrestamo);

    // EVENTO LISTAR
    btnListar.addActionListener(e -> {
        area.setText("");
        for (int i = 0; i < contadorLibros; i++) {
            area.append(libros[i].codigo + " - " +
                        libros[i].titulo + " | Disponible: " +
                        libros[i].cantidadDisponible + "\n");
        }
    });

    // EVENTO PRESTAR
    btnPrestar.addActionListener(e -> {
String codigo = txtCodigoLibro.getText();
String mensaje = prestarLibro(codigo);
area.setText(mensaje);
    });

    // EVENTO DEVOLVER
    btnDevolver.addActionListener(e -> {
String codigo = txtCodigoPrestamo.getText();
String mensaje = devolverPrestamo(codigo);
area.setText(mensaje);
    });
   //evento agregarlibro 
    btnAgregar.addActionListener(e -> {

    Libro nuevo = new Libro();

    nuevo.codigo = "00" + (contadorLibros + 1);
    nuevo.titulo = txtTitulo.getText();
    nuevo.autor = txtAutor.getText();
    nuevo.cantidadTotal = 5;
    nuevo.cantidadDisponible = 5;

    libros[contadorLibros] = nuevo;
    contadorLibros++;

    area.setText("Libro agregado: " + nuevo.titulo);
});
    //add Agregar 
    ventana.add(btnAgregar);
    //add prestamos
    ventana.add(btnPrestamos);
    //Evento prestamos
    
    ventana.add(lblTitulo);
ventana.add(txtTitulo);
ventana.add(lblAutor);
ventana.add(txtAutor);

    btnPrestamos.addActionListener(e -> {

    area.setText("");

    for (int i = 0; i < contadorPrestamos; i++) {

        if (prestamos[i].estado.equals("ACTIVO")) {

            area.append(
                prestamos[i].codigoPrestamo +
                " | Libro: " +
                prestamos[i].codigoLibro +
                "\n"
            );
        }
    }

});

    ventana.setVisible(true);

}
    
    public static void listarLibros(Libro[] libros, int contadorLibros) {

    System.out.println("\nLISTA DE LIBROS:");

    for (int i = 0; i < contadorLibros; i++) {
        System.out.println("Codigo: " + libros[i].codigo +
                " | Titulo: " + libros[i].titulo +
                " | Disponible: " + libros[i].cantidadDisponible);
    }
}
    
    static void iniciarMenu(){
    
    Scanner sc = new Scanner(System.in);
    int opcion;
    
    
        do {
        System.out.println("\n===== BIBLYOSYSTEM =====");
        System.out.println("1. Listar libros");
        System.out.println("2. Prestar libro");
        System.out.println("3. Listar préstamos activos");
        System.out.println("4. Devolver préstamo");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        
        opcion = sc.nextInt();
        sc.nextLine();
        
       switch (opcion) {

            case 1:
                listarLibros(libros, contadorLibros);
                break;

            case 2:
                System.out.print("Ingrese código del libro: ");
                String codigoLibro = sc.nextLine();
                prestarLibro(codigoLibro);
                break;

            case 3:
                listarPrestamosActivos();
                break;

            case 4:
                System.out.print("Ingrese código del préstamo: ");
                String codigoPrestamo = sc.nextLine();
                devolverPrestamo(codigoPrestamo);
                break;

            case 0:
                System.out.println("Saliendo del sistema...");
                break;

            default:
                System.out.println("Opción inválida.");
        }

            
        } while (opcion != 0);
        
    }
    
    static void mostrarLogin() {

    JFrame ventana = new JFrame("BiblioSystem - Login");
    ventana.setSize(400,300);
    ventana.setLayout(null);
    ventana.setLocationRelativeTo(null);
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    javax.swing.JLabel lblUsuario = new javax.swing.JLabel("Usuario:");
    lblUsuario.setBounds(50,50,100,30);

    javax.swing.JTextField txtUsuario = new javax.swing.JTextField();
    txtUsuario.setBounds(150,50,150,30);

    javax.swing.JLabel lblPass = new javax.swing.JLabel("Contraseña:");
    lblPass.setBounds(50,100,100,30);

    javax.swing.JPasswordField txtPass = new javax.swing.JPasswordField();
    txtPass.setBounds(150,100,150,30);

    javax.swing.JButton btnLogin = new javax.swing.JButton("Iniciar Sesión");
    btnLogin.setBounds(120,150,150,30);

    ventana.add(lblUsuario);
    ventana.add(txtUsuario);
    ventana.add(lblPass);
    ventana.add(txtPass);
    ventana.add(btnLogin);

    ventana.setVisible(true);
}
    
}
