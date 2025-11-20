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
import modelo.Ayuda;

public class AyudaDAO {
    
    private Connection con;
   

    public AyudaDAO() {
        try {
            this.con = new conexion().obtener_conexion();
        } catch (SQLException ex) {
            Logger.getLogger(AyudaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearMensaje(Ayuda ayuda){
        String sql = "INSERT INTO AYUDA (PREGUNTA, RESPUESTA) VALUES (?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, ayuda.getPregunta());
            ps.setString(2, ayuda.getRespuesta());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Ayuda obtenerPorId(String id) {
        Ayuda ayuda = null;

        String sql = "SELECT * FROM inventario WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ayuda = new Ayuda();

                ayuda.setId(rs.getInt("id"));
                ayuda.setPregunta(rs.getString("pregunta"));
                ayuda.setRespuesta(rs.getString("respuesta"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AyudaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ayuda;
    }
    
    public void ActualizarPregunta(Ayuda ayuda) {
        String sql = "UPDATE AYUDA SET PREGUNTA =?, RESPUESTA =? WHERE ID = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);


            ps.setString(1, ayuda.getPregunta());
            ps.setString(2, ayuda.getRespuesta());
            ps.setInt(3, ayuda.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminarMensaje(Ayuda ayuda) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM AYUDA WHERE ID = ?");
        ps.setInt(1, ayuda.getId());

        return ps.executeUpdate();
    }



    public List<Ayuda> listaAyuda() throws SQLException {
        
            List<Ayuda> ayudaList = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM AYUDA");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                Ayuda a = new Ayuda();
                a.setId(rs.getInt("id"));
                a.setPregunta(rs.getString("pregunta"));
                a.setRespuesta(rs.getString("respuesta"));
                
                ayudaList.add(a);
            }
        return ayudaList;
    }


}
