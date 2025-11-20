package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.conexion;
import modelo.Repuesto;

public class RepuestoDAO {
    
    Connection con;

    public RepuestoDAO() {
        try {
            this.con = new conexion().obtener_conexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void crearRepuesto(Repuesto repuesto){
        String sql = "INSERT INTO REPUESTO (NOMBRE, DESCRIPCION, PRECIO, CATEGORIA, MARCA, MODELO) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, repuesto.getNombre());
            ps.setString(2, repuesto.getDescripcion());
            ps.setDouble(3, repuesto.getPrecio());
            ps.setString(4, repuesto.getCategoria());
            ps.setString(5, repuesto.getMarca());
            ps.setString(6, repuesto.getModelo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Repuesto obtenerPorId(String id) {
        Repuesto repuesto = null;

        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                repuesto = new Repuesto();
                repuesto.setId(rs.getInt("id"));
                repuesto.setNombre(rs.getNString("nombre"));
                repuesto.setDescripcion(rs.getString("descripcion"));
                repuesto.setPrecio(rs.getDouble("precio"));
                repuesto.setCategoria(rs.getString("categoria"));
                repuesto.setMarca(rs.getString("marca"));
                repuesto.setModelo(rs.getString("modelo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepuestoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repuesto;
    }

    public void actualizarRepuesto(Repuesto repuesto) {
        String sql = "UPDATE REPUESTO SET NOMBRE =?, DESCRIPCION =?, PRECIO =?, CATEGORIA =?, MARCA=?, MODELO=? WHERE ID = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, repuesto.getNombre());
            ps.setString(2, repuesto.getDescripcion());
            ps.setDouble(3, repuesto.getPrecio());
            ps.setString(4, repuesto.getCategoria());
            ps.setString(5, repuesto.getMarca());
            ps.setString(6, repuesto.getModelo());
            ps.setInt(7, repuesto.getId());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminarRepuesto(Repuesto repuesto) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM repuesto WHERE ID = ?");
        ps.setInt(1, repuesto.getId());

        return ps.executeUpdate();
    }

    public List<Repuesto> listaRepuesto() throws SQLException {

        List<Repuesto> repuestoList = new ArrayList<>();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM repuesto");
        ResultSet rs = ps.executeQuery();

            while (rs.next()){
                
                Repuesto r = new Repuesto();
                
                r.setId(rs.getInt("id"));
                r.setNombre(rs.getNString("nombre"));
                r.setDescripcion(rs.getString("descripcion"));
                r.setPrecio(rs.getDouble("precio"));
                r.setCategoria(rs.getString("categoria"));
                r.setMarca(rs.getString("marca"));
                r.setModelo(rs.getString("modelo"));
                
                repuestoList.add(r);
            }
        return repuestoList;
       
    }
}
