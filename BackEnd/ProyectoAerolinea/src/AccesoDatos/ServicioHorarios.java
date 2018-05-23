package AccesoDatos;

import LogicaNegocio.Horarios;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.OracleTypes;

public class ServicioHorarios extends Servicio {

    private static final String INSERTARHORARIO = "{call insertarHorario(?,?,?,?,?,?)}";
    private static final String ACTUALIZARHORARIO = "{call actualizarHorario(?,?,?,?,?,?)}";
    private static final String BUSCARHORARIO = "{?=call buscarHorario(?)}";
    private static final String LISTARHORARIO = "{?=call listarHorarios()}";
    private static final String ELIMINARHORARIO = "{call eliminarHorario(?)}";

    public ServicioHorarios() {

    }

    public void insertarHorario(Horarios horarios) throws GlobalException, NoDataException {
        connect();
        CallableStatement pstmt = null;

        try {
            pstmt = cn.prepareCall(INSERTARHORARIO);
            pstmt.setInt(1, horarios.getId());
            pstmt.setString(2, horarios.getDiaSalida());
            pstmt.setString(3, horarios.getDiaLlegada());
            pstmt.setString(4, horarios.getHoraSalida());
            pstmt.setString(5, horarios.getHoraLlegada());
            pstmt.setInt(6, horarios.getPrecio());

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

    public void actualizarHorario(Horarios horarios) throws GlobalException, NoDataException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ACTUALIZARHORARIO);
            pstmt.setInt(1, horarios.getId());
            pstmt.setString(2, horarios.getDiaSalida());
            pstmt.setString(3, horarios.getDiaLlegada());
            pstmt.setString(4, horarios.getHoraSalida());
            pstmt.setString(5, horarios.getHoraLlegada());
            pstmt.setInt(6, horarios.getPrecio());
            
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
    
     public Horarios buscarHorario(int id) throws GlobalException, NoDataException {
        connect();

        ResultSet rs = null;
        Horarios horarios = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(BUSCARHORARIO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                horarios = new Horarios(rs.getInt("id"),
                        rs.getString("diaSalida"),
                        rs.getString("diaLlegada"),
                        rs.getString("horaSalida"),
                        rs.getString("horaLlegada"),
                        rs.getInt("precio"));
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
        if (horarios == null) {
            throw new NoDataException("No hay datos");
        }
        System.out.print(horarios.toString());
        return horarios;
    }
    
    public Collection listarHorario() throws GlobalException, NoDataException {
        connect();

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Horarios horarios = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(LISTARHORARIO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                horarios = new Horarios(rs.getInt("id"),
                        rs.getString("diaSalida"),
                        rs.getString("diaLlegada"),
                        rs.getString("horaSalida"),
                        rs.getString("horaLlegada"),
                        rs.getInt("precio"));
                
                coleccion.add(horarios);
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

    public void eliminarHorario(int id) throws GlobalException, NoDataException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ELIMINARHORARIO);
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
