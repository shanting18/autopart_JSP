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
import modelo.Vendedor;

public class VendedorDAO {

    Connection con;
    Vendedor vendedor;

    public VendedorDAO() {
        try {
            this.con = new conexion().obtener_conexion();
            this.vendedor = new Vendedor();
        } catch (SQLException ex) {
            Logger.getLogger(VendedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearVendedor(Vendedor vendedor) {
        String sql = "INSERT INTO vendedor (NOMBRE_TIENDA, DESCRIPCION, REPUTACION, FECHA_REGISTRO) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, vendedor.getNombre_tienda());
            ps.setString(2, vendedor.getDescripcion());
            ps.setDouble(3, vendedor.getReputacion());
            ps.setDate(4, Date.valueOf(vendedor.getFecha_registro()));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vendedor obtenerPorId(String id) {
        Vendedor vendedor = null;

        String sql = "SELECT * FROM vendedor WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                vendedor = new Vendedor();
                vendedor.setId(rs.getInt("id"));
                vendedor.setNombre_tienda(rs.getString("nombre_tienda"));
                vendedor.setDescripcion(rs.getString("descripcion"));
                vendedor.setReputacion(rs.getDouble("reputacion"));
                vendedor.setFecha_registro(rs.getDate("fecha_registro").toLocalDate());
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vendedor;
    }

    public void actualizarVendedor(Vendedor vendedor) {
        String sql = "UPDATE vendedor SET NOMBRE_TIENDA =?, DESCRIPCION =?, REPUTACION =?, FECHA_REGISTRO =? WHERE ID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, vendedor.getNombre_tienda());
            ps.setString(2, vendedor.getDescripcion());
            ps.setDouble(3, vendedor.getReputacion());
            ps.setDate(4, Date.valueOf(vendedor.getFecha_registro()));
            ps.setInt(5, vendedor.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminarVendedor(Vendedor vendedor) throws SQLException {

        PreparedStatement ps = con.prepareStatement("DELETE FROM vendedor WHERE ID = ?");
        ps.setInt(1, vendedor.getId());

        return ps.executeUpdate();
    }

    public List<Vendedor> lista() throws SQLException {

        List<Vendedor> vendedoresList = new ArrayList<>();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM vendedor");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Vendedor v = new Vendedor();
            v.setId(rs.getInt("id"));
            v.setNombre_tienda(rs.getString("nombre_tienda"));
            v.setDescripcion(rs.getString("descripcion"));
            v.setReputacion(rs.getDouble("reputacion"));
            v.setFecha_registro(rs.getDate("fecha_registro").toLocalDate());

            vendedoresList.add(v);
        }
        return vendedoresList;
    }
}
