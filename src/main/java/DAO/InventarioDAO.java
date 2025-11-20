package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.conexion;
import modelo.Inventario;

public class InventarioDAO {

    Connection con;
    Inventario inventario;

    public InventarioDAO() {
        try {
            this.con = new conexion().obtener_conexion();
            this.inventario = new Inventario();
        } catch (SQLException ex) {
            Logger.getLogger(InventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearInvenrtario(Inventario inventario) {
        String sql = "INSERT INTO INVENTARIO (REPUESTO, CANTIDAD, ULTIMA_ACTUALIZACION) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, inventario.getNombreRepuesto());
            ps.setInt(2, inventario.getCantidad());
            ps.setDate(3, Date.valueOf(inventario.getUltimaActualizacion()));

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Inventario obtenerPorId(String id) {
        Inventario inventario = null;

        String sql = "SELECT * FROM inventario WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                inventario = new Inventario();

                inventario.setId(rs.getInt("id"));
                inventario.setNombreRepuesto(rs.getNString("repuesto"));
                inventario.setCantidad(rs.getInt("cantidad"));
                inventario.setUltimaActualizacion(rs.getDate("ultima_actualizacion").toLocalDate());
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventario;
    }

    public void actualizarInventario(Inventario inventario) {
        String sql = "UPDATE INVENTARIO SET REPUESTO =?, CANTIDAD =?, ULTIMA_ACTUALIZACION =? WHERE ID = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, inventario.getNombreRepuesto());
            ps.setInt(2, inventario.getCantidad());
            ps.setDate(3, Date.valueOf(inventario.getUltimaActualizacion()));
            ps.setInt(4, inventario.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminarInventario(Inventario inventario) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM INVENTARIO WHERE ID = ?");
        ps.setInt(1, inventario.getId());

        return ps.executeUpdate();
    }

    public List<Inventario> listaInventario() throws SQLException {

        List<Inventario> inventariosList = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM inventario");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            
            Inventario i = new Inventario();
            i.setId(rs.getInt("id"));
            i.setNombreRepuesto(rs.getNString("repuesto"));
            i.setCantidad(rs.getInt("cantidad"));
            i.setUltimaActualizacion(rs.getDate("ultima_actualizacion").toLocalDate());

            inventariosList.add(i);
        }
        return inventariosList;
    }
}
