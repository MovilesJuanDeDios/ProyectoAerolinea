package AccesoDatos;

import LogicaNegocio.Aviones;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.OracleTypes;

public class ServicioAviones extends Servicio {

    private static final String INSERTARAVION = "{call insertarAvion(?,?,?,?)}";
    private static final String ACTUALIZARAVION = "{call actualizarAvion(?,?,?,?)}";
    private static final String BUSCARAVION = "{?=call buscarAvion(?)}";
    private static final String LISTARAVION = "{?=call listarAviones()}";
    private static final String ELIMINARAVION = "{call eliminarAvion(?)}";

    public ServicioAviones() {

    }

    public void insertarAvion(Aviones aviones) throws GlobalException, NoDataException {
        connect();
        CallableStatement pstmt = null;

        try {
            pstmt = cn.prepareCall(INSERTARAVION);
            pstmt.setInt(1, aviones.getId());
            pstmt.setInt(2, aviones.getRuta());
            pstmt.setInt(3, aviones.getHorario());
            pstmt.setInt(4, aviones.getTipoAvion());

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

    public void actualizarAvion(Aviones aviones) throws GlobalException, NoDataException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ACTUALIZARAVION);
            pstmt.setInt(1, aviones.getId());
            pstmt.setInt(2, aviones.getRuta());
            pstmt.setInt(3, aviones.getHorario());
            pstmt.setInt(4, aviones.getTipoAvion());
            
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
    
     public Aviones buscarAvion(int id) throws GlobalException, NoDataException {
        connect();

        ResultSet rs = null;
        Aviones aviones = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(BUSCARAVION);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                aviones = new Aviones(rs.getInt("id"),
                        rs.getInt("ruta"),
                        rs.getInt("horario"),
                        rs.getInt("tipoAvion"));
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
        if (aviones == null) {
            throw new NoDataException("No hay datos");
        }
        System.out.print(aviones.toString());
        return aviones;
    }
    
    public Collection listarAviones() throws GlobalException, NoDataException {
        connect();

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Aviones aviones = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(LISTARAVION);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                aviones = new Aviones(rs.getInt("id"),
                        rs.getInt("ruta"),
                        rs.getInt("horario"),
                        rs.getInt("tipoAvion"));
                
                coleccion.add(aviones);
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

    public void eliminarAvion(int id) throws GlobalException, NoDataException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ELIMINARAVION);
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
