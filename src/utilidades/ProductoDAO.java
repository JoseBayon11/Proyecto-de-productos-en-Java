/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author josebayon
 */
public class ProductoDAO {

    //Metodo para insertar producto
    public void insertarPRoductos(Producto producto, Connection conn) {
        String sqlInsertar = "INSERT INTO productos (nombre, precio, categoria) VALUES (?,?,?)";
        
        PreparedStatement ps;
        int filasInsertadas = 0;
        
        try {
            ps = conn.prepareStatement(sqlInsertar);
            
            if (producto != null) {
                ps.setString(1, producto.getNombre());
                ps.setDouble(2, producto.getPrecio());
                ps.setString(3, producto.getCategoria());
                
                filasInsertadas = ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Productos Insertados Correctamente: " + filasInsertadas);
                ps.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    //Actualizar productos
    public void actualizarProductos(Producto producto, Connection conn) {
        String sqlActualizar = "UPDATE productos SET nombre = ?, precio =?, categoria=? WHERE id = ?";
        
        PreparedStatement ps;
        int filasActaulizadas = 0;
        
        try {
            
            ps = conn.prepareCall(sqlActualizar);
            
            if (producto != null) {
                ps.setString(1, producto.getNombre());
                ps.setDouble(2, producto.getPrecio());
                ps.setString(3, producto.getCategoria());
                ps.setInt(4, producto.getId());
                
                filasActaulizadas = ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Productos Actualizados Correctamente: " + filasActaulizadas);
                ps.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodo para borrar productos
    public void eliminarProductos(Producto producto, Connection conn) {
        String sqlEliminar = "DELETE FROM productos WHERE id=?";
        
        PreparedStatement ps;
        int filasEliminadas = 0;
        
        try {
            
            ps = conn.prepareStatement(sqlEliminar);
            
            if (producto != null) {
                ps.setInt(1, producto.getId());
                
                filasEliminadas = ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Productos Eliminados Correctamente: " + filasEliminadas);
                ps.close();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodo para buscar por nombre
    public ArrayList<Producto> buscarPorNombreProducto(String nombreProducto, Connection conn) {
        String sqlBuscar = "SELECT * FROM productos WHERE nombre LIKE CONCAT('%',?,'%')";
        ArrayList<Producto> listaProducto = new ArrayList<>();
        
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            
            ps = conn.prepareStatement(sqlBuscar);
            ps.setString(1, nombreProducto);

            //Guardamos la consulta en el ResultSet
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("Precio"),
                        rs.getString("Categoria"));
                
                listaProducto.add(producto);
            }
            return listaProducto;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //Metodo para buscar todos los productos
    public ArrayList<Producto> buscarTodosLosProductos(Connection conn) {
        String sqlBuscar = "SELECT * FROM productos";
        ArrayList<Producto> listaProducto = new ArrayList<>();
        
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            
            ps = conn.prepareStatement(sqlBuscar);

            //Guardamos la consulta en el ResultSet
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("Precio"),
                        rs.getString("Categoria"));
                
                listaProducto.add(producto);
            }
            return listaProducto;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
