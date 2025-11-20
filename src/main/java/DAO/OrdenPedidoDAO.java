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
import modelo.OrdenPedido;

public class OrdenPedidoDAO {

    Connection con;

    public OrdenPedidoDAO() {
        try {
            this.con = new conexion().obtener_conexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void CrearOrden(OrdenPedido pedido) {
        String sql = "INSERT INTO ORDEN (FECHA, ESTADO, TOTAL) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDate(1, Date.valueOf(pedido.getFecha()));
            ps.setString(2, pedido.getEstado());
            ps.setDouble(3, pedido.getTotal());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public OrdenPedido obtenerPorId(String id) {
        OrdenPedido pedido = null;

        String sql = "SELECT * FROM orden WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                 pedido= new OrdenPedido();
                pedido.setId(rs.getInt("id"));
                pedido.setFecha(rs.getDate("fecha").toLocalDate());
                pedido.setEstado(rs.getNString("estado"));
                pedido.setTotal(rs.getDouble("total"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedido;
    }

    public void actualizarOrden(OrdenPedido pedido) {
        String sql = "UPDATE ORDEN SET FECHA =?, ESTADO =?, TOTAL =? WHERE ID = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDate(1, Date.valueOf(pedido.getFecha()));
            ps.setString(2, pedido.getEstado());
            ps.setDouble(3, pedido.getTotal());
            ps.setInt(4, pedido.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminarOrden(OrdenPedido pedido) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM ORDEN WHERE ID = ?");
        ps.setInt(1, pedido.getId());

        return ps.executeUpdate();
    }

    public List<OrdenPedido> listaOrden() throws SQLException {

        List<OrdenPedido> ordenPedidosList = new ArrayList<>();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM ORDEN");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            OrdenPedido ordenPedido = new OrdenPedido();
            ordenPedido.setId(rs.getInt("id"));
            ordenPedido.setFecha(rs.getDate("fecha").toLocalDate());
            ordenPedido.setEstado(rs.getNString("estado"));
            ordenPedido.setTotal(rs.getDouble("total"));

            ordenPedidosList.add(ordenPedido);

        }
        return ordenPedidosList;
    }
}
