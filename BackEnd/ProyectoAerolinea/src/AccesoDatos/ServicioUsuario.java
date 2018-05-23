package AccesoDatos;

import LogicaNegocio.Usuario;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.OracleTypes;

public class ServicioUsuario extends Servicio {

    private static final String INSERTARUSUARIO = "{call insertarUsuario(?,?,?,?,?,?,?,?,?)}";
    private static final String ACTUALIZARUSUARIO = "{call actualizarUsuario(?,?,?,?,?,?,?,?)}";
    private static final String BUSCARUSUARIO = "{?=call buscarUsuario(?)}";
    private static final String LISTARUSUARIO = "{?=call listarUsuario()}";
    private static final String ELIMINARUSUARIO = "{call eliminarUsuario(?)}";

    public ServicioUsuario() {

    }

    public void insertarUsuario(Usuario usuario) throws GlobalException, NoDataException {
        connect();
        CallableStatement pstmt = null;

        try {
            pstmt = cn.prepareCall(INSERTARUSUARIO);
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellidos());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setString(5, usuario.getClave());
            pstmt.setString(6, usuario.getFechaNac());
            pstmt.setString(7, usuario.getDireccion());
            pstmt.setString(8, usuario.getTelefono());
            pstmt.setInt(9, usuario.getRol());

            boolean resultado = pstmt.execute();

            if (resultado == true) {
                throw new NoDataException("No se realizo la inserción");
            } else {
                System.out.println("\nInserción Satisfactoria!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }
    
    public void actualizarUsuario(Usuario usuario) throws GlobalException, NoDataException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ACTUALIZARUSUARIO);
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellidos());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setString(5, usuario.getClave());
            pstmt.setString(6, usuario.getFechaNac());
            pstmt.setString(7, usuario.getDireccion());
            pstmt.setString(8, usuario.getTelefono());
            boolean resultado = pstmt.execute();

            if (resultado == true) {
                throw new NoDataException("No se realizo la actualización");
            } else {
                System.out.println("\nModificación Satisfactoria!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }
    
        public Usuario buscarUsuario(String nombre) throws GlobalException, NoDataException {
        connect();

        ResultSet rs = null;
        Usuario usuario = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(BUSCARUSUARIO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, nombre);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                usuario = new Usuario(rs.getString("username"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("email"),
                        rs.getString("clave"),
                        rs.getString("fechaNac"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getInt("rol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (usuario == null) {
            throw new NoDataException("No hay datos");
        }
        System.out.print(usuario.toString());
        return usuario;
    }
        
    public Collection listarUsuario() throws GlobalException, NoDataException {
        connect();

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Usuario usuario = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(LISTARUSUARIO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                usuario = new Usuario(rs.getString("username"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("email"),
                        rs.getString("clave"),
                        rs.getString("fechaNac"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getInt("rol"));
                coleccion.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
       
        return coleccion;
    }

    public void eliminarUsuario(String username) throws GlobalException, NoDataException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ELIMINARUSUARIO);
            pstmt.setString(1, username);

            boolean resultado = pstmt.execute();

            if (resultado == true) {
                throw new NoDataException("No se realizo el borrado");
            } else {
                System.out.println("\nEliminación Satisfactoria!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }    
}
