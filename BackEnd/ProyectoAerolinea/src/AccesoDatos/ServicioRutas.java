package AccesoDatos;

import LogicaNegocio.Rutas;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.OracleTypes;

public class ServicioRutas extends Servicio {

    private static final String INSERTARRUTA = "{call insertarRuta(?,?,?)}";
    private static final String ACTUALIZARRUTA = "{call actualizarRuta(?,?,?)}";
    private static final String BUSCARRUTA = "{?=call buscarRuta(?)}";
    private static final String LISTARRUTA = "{?=call listarRutas()}";
    private static final String ELIMINARRUTA = "{call eliminarRuta(?)}";

    public ServicioRutas() {

    }

    public void insertarRuta(Rutas rutas) throws GlobalException, NoDataException {
        connect();
        CallableStatement pstmt = null;

        try {
            pstmt = cn.prepareCall(INSERTARRUTA);
            pstmt.setInt(1, rutas.getId());
            pstmt.setString(2, rutas.getRuta());
            pstmt.setString(3, rutas.getDuraccion());

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

    public void actualizarRuta(Rutas rutas) throws GlobalException, NoDataException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ACTUALIZARRUTA);
            pstmt.setInt(1, rutas.getId());
            pstmt.setString(2, rutas.getRuta());
            pstmt.setString(3, rutas.getDuraccion());
            
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
    
     public Rutas buscarRuta(int id) throws GlobalException, NoDataException {
        connect();

        ResultSet rs = null;
        Rutas rutas = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(BUSCARRUTA);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                rutas = new Rutas(rs.getInt("id"),
                        rs.getString("ruta"),
                        rs.getString("duraccion"));
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
        if (rutas == null) {
            throw new NoDataException("No hay datos");
        }
        System.out.print(rutas.toString());
        return rutas;
    }
    
    public Collection listarRutas() throws GlobalException, NoDataException {
        connect();

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Rutas rutas = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(LISTARRUTA);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                rutas = new Rutas(rs.getInt("id"),
                        rs.getString("ruta"),
                        rs.getString("duracion"));
                
                coleccion.add(rutas);
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

    public void eliminarRuta(int id) throws GlobalException, NoDataException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ELIMINARRUTA);
            pstmt.setInt(1, id);

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

