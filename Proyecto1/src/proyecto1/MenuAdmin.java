package proyecto1;

import javax.swing.JFrame;

public class MenuAdmin extends JFrame {

    public MenuAdmin(){

        setTitle("BiblioSystem - Administrador");
        setSize(600,500);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
         javax.swing.JButton btnRegistrarOperador = new javax.swing.JButton("Registrar Operador");
        btnRegistrarOperador.setBounds(50,10,200,40);
        
        javax.swing.JButton btnListar = new javax.swing.JButton("Listar libros");
        btnListar.setBounds(50,50,200,40);

        javax.swing.JButton btnPrestar = new javax.swing.JButton("Prestar Libro");
        btnPrestar.setBounds(50,110,200,40);

        javax.swing.JButton btnDevolver = new javax.swing.JButton("Devolver Libro");
        btnDevolver.setBounds(50,170,200,40);
        
        javax.swing.JButton btnAgregar = new javax.swing.JButton("Agregar Libro");
btnAgregar.setBounds(50,330,200,40);

javax.swing.JButton btnPrestamos = new javax.swing.JButton("Ver Préstamos");
btnPrestamos.setBounds(50,280,200,40);

        javax.swing.JLabel lblCodigoLibro = new javax.swing.JLabel("Código Libro:");
        lblCodigoLibro.setBounds(50,230,100,30);

        javax.swing.JTextField txtCodigoLibro = new javax.swing.JTextField();
        txtCodigoLibro.setBounds(150,230,100,30);

        javax.swing.JLabel lblCodigoPrestamo = new javax.swing.JLabel("Código Préstamo:");
        lblCodigoPrestamo.setBounds(50,270,120,30);

        javax.swing.JTextField txtCodigoPrestamo = new javax.swing.JTextField();
        txtCodigoPrestamo.setBounds(170,270,80,30);

        javax.swing.JTextArea area = new javax.swing.JTextArea();
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(area);
        scroll.setBounds(300,50,250,300);
        
        javax.swing.JLabel lblTitulo = new javax.swing.JLabel("Título:");
lblTitulo.setBounds(50,380,100,30);

javax.swing.JTextField txtTitulo = new javax.swing.JTextField();
txtTitulo.setBounds(120,380,120,30);

javax.swing.JLabel lblAutor = new javax.swing.JLabel("Autor:");
lblAutor.setBounds(50,420,100,30);

javax.swing.JTextField txtAutor = new javax.swing.JTextField();
txtAutor.setBounds(120,420,120,30);

        add(btnRegistrarOperador);
        add(btnListar);
        add(btnPrestar);
        add(btnDevolver);
        add(btnAgregar);
add(lblTitulo);
add(txtTitulo);
add(lblAutor);
add(txtAutor);
        add(scroll);
        add(lblCodigoLibro);
        add(txtCodigoLibro);
        add(lblCodigoPrestamo);
        add(txtCodigoPrestamo);
        add(btnPrestamos);
        
        

        // EVENTO LISTAR
        btnListar.addActionListener(e -> {

            area.setText("");

            for (int i = 0; i < Proyecto1.contadorLibros; i++) {

                area.append(
                    Proyecto1.libros[i].codigo + " - " +
                    Proyecto1.libros[i].titulo +
                    " | Disponible: " +
                    Proyecto1.libros[i].cantidadDisponible +
                    "\n"
                );
            }

        });
        
        //Evento Crear Operador
        btnRegistrarOperador.addActionListener(e -> {

    RegistrarOperador ventana = new RegistrarOperador();
    ventana.setVisible(true);

        });

        // EVENTO PRESTAR
        btnPrestar.addActionListener(e -> {

            String codigo = txtCodigoLibro.getText();
            String mensaje = Proyecto1.prestarLibro(codigo);
            area.setText(mensaje);

        });

        // EVENTO DEVOLVER
        btnDevolver.addActionListener(e -> {

            String codigo = txtCodigoPrestamo.getText();
            String mensaje = Proyecto1.devolverPrestamo(codigo);
            area.setText(mensaje);

        });
        
        //Evento de agregar
        btnAgregar.addActionListener(e -> {

    Libro nuevo = new Libro();

    nuevo.codigo = "00" + (Proyecto1.contadorLibros + 1);
    nuevo.titulo = txtTitulo.getText();
    nuevo.autor = txtAutor.getText();
    nuevo.cantidadTotal = 5;
    nuevo.cantidadDisponible = 5;

    Proyecto1.libros[Proyecto1.contadorLibros] = nuevo;
    Proyecto1.contadorLibros++;

    area.setText("Libro agregado: " + nuevo.titulo);

});
        
        //evento listar prestamos
        btnPrestamos.addActionListener(e -> {

    area.setText("");

    for (int i = 0; i < Proyecto1.contadorPrestamos; i++) {

        if (Proyecto1.prestamos[i].estado.equals("ACTIVO")) {

            area.append(
                Proyecto1.prestamos[i].codigoPrestamo +
                " | Libro: " +
                Proyecto1.prestamos[i].codigoLibro +
                "\n"
            );
        }
    }

});

    }

}