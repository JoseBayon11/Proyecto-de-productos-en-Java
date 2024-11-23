/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import utilidades.Conexion;
import utilidades.Producto;
import utilidades.ProductoDAO;
import vista.VentanaPrincipal;
import java.sql.*;

/**
 *
 * @author josebayon
 */
public class ControladorPrograma implements ActionListener {//implementa ActionListener para detectar los botones pulsados 

    //Atributos
    private VentanaPrincipal ventanaPrincipal;
    private ArrayList<Producto> listaProducto;
    private ProductoDAO productoDAO;
    private Producto producto;
    private Connection conn;

    //Constructores
    public ControladorPrograma() {
        ventanaPrincipal = new VentanaPrincipal();
        ventanaPrincipal.setActionListener(this);
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setVisible(true);
        
        listaProducto = new ArrayList<>();
        productoDAO = new ProductoDAO();
        producto = new Producto();
        
        //Cargamos la lista de productos nada mas abrir el programa
        conn = Conexion.obtenerConexion();
        //Obetenemos la lista de productos de la BBDD
        listaProducto = productoDAO.buscarTodosLosProductos(conn);
        //Cargamos datos en la tabla
        ventanaPrincipal.cargarDatosTabla(listaProducto);
        
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Insertar":
                //1. Limpiamos la tabla antes de mostrar nada
                ventanaPrincipal.limpiarDatosTabla();
                //2. Abrimos la conexion
                conn = Conexion.obtenerConexion();
                //3. Obetenemos el producto para insertar
                producto = ventanaPrincipal.obtenerProducto();
                //4. Insertamos el producto
                productoDAO.insertarPRoductos(producto, conn);
                // 5. Obtenemos los productos para mostrar en la lista
                listaProducto = productoDAO.buscarTodosLosProductos(conn);
                //6. Cargamos la lista de los productos en la tabla
                ventanaPrincipal.cargarDatosTabla(listaProducto);
                //7. Cerramos la conexion
                Conexion.cerrarConexion(conn);
                break;

            case "Actualizar":
                System.out.println("ACtualizar");
                break;

            case "Eliminar":
                System.out.println("Eliminar");
                break;
                
                case "Buscar":
                System.out.println("Buscar");
                break;
            default:
                throw new AssertionError();
        }

    }
    

}
