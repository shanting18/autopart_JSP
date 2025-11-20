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
import modelo.Usuario;

public class UsuarioDAO {

    Connection con;

    public UsuarioDAO() {
        try {
            this.con = new conexion().obtener_conexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (NOMBRE, CORREO, CONTRASENA, DIRECCION, TELEFONO) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getContrasena());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getTelefono());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario obtenerPorId(String id) {
        Usuario usuario = null;

        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getString("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setTelefono(rs.getString("telefono"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public void actualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET NOMBRE =?, CORREO =?, CONTRASENA =?, DIRECCION =?, TELEFONO =? WHERE ID = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getContrasena());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminar(Usuario usuario) throws SQLException {

        PreparedStatement ps = con.prepareStatement("DELETE FROM usuario WHERE ID = ?");
        ps.setString(1, usuario.getId());

        return ps.executeUpdate();
    }

    public List<Usuario> lista() throws SQLException {

        List<Usuario> usuariosList = new ArrayList<>();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Usuario usuario = new Usuario();
            
            usuario.setId(rs.getString("id"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setCorreo(rs.getString("correo"));
            usuario.setContrasena(rs.getString("contrasena"));
            usuario.setDireccion(rs.getString("direccion"));
            usuario.setTelefono(rs.getString("telefono"));

            usuariosList.add(usuario);
        }
        return usuariosList;
    }
}
