package AccesoDatos;

import LogicaNegocio.TipoAviones;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.OracleTypes;

public class ServicioTipoAviones extends Servicio {

    private static final String INSERTARTIPOAVION = "{call insertarTipoAvion(?,?,?,?,?,?,?)}";
    private static final String ACTUALIZARTIPOAVION = "{call actualizarTipoAvion(?,?,?,?,?,?,?)}";
    private static final String BUSCARTIPOAVION = "{?=call buscarTipoAvion(?)}";
    private static final String LISTARTIPOAVION = "{?=call listarTipoAvion()}";
    private static final String ELIMINARTIPOAVION = "{call eliminarTipoAvion(?)}";

    public ServicioTipoAviones() {

    }

    public void insertarTipoAvion(TipoAviones tipoAviones) throws GlobalException, NoDataException {
        connect();
        CallableStatement pstmt = null;

        try {
            pstmt = cn.prepareCall(INSERTARTIPOAVION);
            pstmt.setInt(1, tipoAviones.getId());
            pstmt.setString(2, tipoAviones.getAnnio());
            pstmt.setString(3, tipoAviones.getModelo());
            pstmt.setString(4, tipoAviones.getMarca());
            pstmt.setInt(5, tipoAviones.getCapacidad());
            pstmt.setInt(6, tipoAviones.getFilas());
            pstmt.setInt(7, tipoAviones.getAsientosXFila());

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

    public void actualizarTipoAvion(TipoAviones tipoAviones) throws GlobalException, NoDataException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ACTUALIZARTIPOAVION);
            pstmt.setInt(1, tipoAviones.getId());
            pstmt.setString(2, tipoAviones.getAnnio());
            pstmt.setString(3, tipoAviones.getModelo());
            pstmt.setString(4, tipoAviones.getMarca());
            pstmt.setInt(5, tipoAviones.getCapacidad());
            pstmt.setInt(6, tipoAviones.getFilas());
            pstmt.setInt(7, tipoAviones.getAsientosXFila());
            
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
    
     public TipoAviones buscarTipoAvion(int id) throws GlobalException, NoDataException {
        connect();

        ResultSet rs = null;
        TipoAviones tipoAvion = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(BUSCARTIPOAVION);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                tipoAvion = new TipoAviones(rs.getInt("id"),
                        rs.getString("annio"),
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getInt("capacidad"),
                        rs.getInt("filas"),
                        rs.getInt("asientosXFila"));
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
        if (tipoAvion == null) {
            throw new NoDataException("No hay datos");
        }
        System.out.print(tipoAvion.toString());
        return tipoAvion;
    }
    
    public Collection listarTipoAvion() throws GlobalException, NoDataException {
        connect();

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        TipoAviones tipoAvion = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(LISTARTIPOAVION);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                tipoAvion = new TipoAviones(rs.getInt("id"),
                        rs.getString("annio"),
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getInt("capacidad"),
                        rs.getInt("filas"),
                        rs.getInt("asientosXFila"));
                
                coleccion.add(tipoAvion);
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

    public void eliminarTipoAvion(int id) throws GlobalException, NoDataException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ELIMINARTIPOAVION);
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
