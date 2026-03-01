package proyecto1;


import javax.swing.JFrame;

public class Proyecto1 {


    
    public static void main(String[] args) {
        
        Libro [] libros = new Libro[100];
        
        //mostrar libros y crearlos
        
        int contadorLibros = 0;
        
        //aqui creo los libros
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
        
        for (int i = 0; i < contadorLibros; i++) {
            System.out.println(libros[i].codigo + " - "+libros[i].titulo);         
        }
        //Buscar libro
        
        String codigobuscado = "002";
        Libro libroEncontrado = null;   
        for (int i = 0; i < contadorLibros; i++) {
            if (libros[i].codigo.equals(codigobuscado)) {
                libroEncontrado = libros[i];
                break;
            }
            
        }
        
        if(libroEncontrado != null){
            System.out.println("Libro encontrado: " + libroEncontrado.titulo);    
        }else{
            System.out.println("No se encontro el libro ");
        }
        
        //intentar prestar libro
        if (libroEncontrado !=null) {
            
            if (libroEncontrado.cantidadDisponible > 0) {
                
                libroEncontrado.cantidadDisponible--;
                
                System.out.println("Se realizo el prestamo con exito");
                System.out.println("Ahora hay disponibles" + libroEncontrado.cantidadDisponible);
                
            }else{
                System.out.println("no hay de esos libros ahorita");
            }
            
        }
        
        
         JFrame ventana = new JFrame();
         ventana.setTitle("BiblyoSystem");
         ventana.setSize(500,400);
         ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         ventana.setLocationRelativeTo(null);
         ventana.setVisible(true);
    }
    
}
