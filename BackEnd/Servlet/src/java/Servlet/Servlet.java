
package Servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AccesoDatos.*;
import LogicaNegocio.*;

/**
 *
 * @author Giancarlo
 */
public class Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
            //String para guardar el JSON generaro por al libreria GSON
            String json;
            String json2;
           
            //Se crea el objeto
            Usuario u = new Usuario();
            TipoAviones ta = new TipoAviones();
            Rutas r = new Rutas();
            Horarios h = new Horarios();
            Aviones a = new Aviones();
            
            ServicioUsuario su = new ServicioUsuario();
            ServicioTipoAviones sta = new ServicioTipoAviones();
            ServicioRutas sr = new ServicioRutas();
            ServicioHorarios sh = new ServicioHorarios();
            ServicioAviones sa = new ServicioAviones();
            
            Thread.sleep(500);
            
            String accion = request.getParameter("accion");
            switch (accion) {
                case "agregarUsuario":
                          u.setUsername(request.getParameter("username"));
                          u.setNombre(request.getParameter("nombre"));
                          u.setApellidos(request.getParameter("apellidos"));
                          u.setEmail(request.getParameter("email"));
                          u.setClave(request.getParameter("password"));
                          u.setFechaNac(request.getParameter("fechaNac"));
                          u.setDireccion(request.getParameter("direccion"));
                          u.setTelefono(request.getParameter("telefono"));
                          u.setRol(Integer.parseInt(request.getParameter("rol"))); 
                          
                          su.insertarUsuario(u);

                          out.print("C~El objeto fue ingresado correctamente");
                          break;

                case "setUsuario":
                        u.setUsername(request.getParameter("username"));
                        u.setNombre(request.getParameter("nombre"));
                        u.setApellidos(request.getParameter("apellidos"));
                        u.setEmail(request.getParameter("email"));
                        u.setClave(request.getParameter("password"));
                        u.setFechaNac(request.getParameter("fechaNac"));
                        u.setDireccion(request.getParameter("direccion"));
                        u.setTelefono(request.getParameter("telefono")); 

                        su.actualizarUsuario(u);

                        out.print("C~El objeto fue actualizado correctamente");
                        break;

                case "consultarUsuario":

                    List<Usuario> list = new ArrayList(su.listarUsuario());
                    json = new Gson().toJson(list);    
                    out.print(json);
                    break;

                case "deleteUsuario":
                    String user = request.getParameter("nombre");
                    su.eliminarUsuario(user);
                    out.print("C~El objeto fue eliminado correctamente");
                    break;      
                    
                /* --------------------------------------------------------------------------------------- */
                    
                case "agregarTipoAvion":
                          ta.setId(Integer.parseInt(request.getParameter("id")));
                          ta.setAnnio(request.getParameter("annio"));
                          ta.setModelo(request.getParameter("modelo"));
                          ta.setMarca(request.getParameter("marca"));
                          ta.setCapacidad(Integer.parseInt(request.getParameter("capacidad")));
                          ta.setFilas(Integer.parseInt(request.getParameter("filas")));
                          ta.setAsientosXFila(Integer.parseInt(request.getParameter("asientos")));
                          
                          sta.insertarTipoAvion(ta);

                          out.print("C~El objeto fue ingresado correctamente");
                          break;

                case "setTipoAvion":
                        ta.setId(Integer.parseInt(request.getParameter("id")));
                        ta.setAnnio(request.getParameter("annio"));
                        ta.setModelo(request.getParameter("modelo"));
                        ta.setMarca(request.getParameter("marca"));
                        ta.setCapacidad(Integer.parseInt(request.getParameter("capacidad")));
                        ta.setFilas(Integer.parseInt(request.getParameter("filas")));
                        ta.setAsientosXFila(Integer.parseInt(request.getParameter("asientos")));

                        sta.actualizarTipoAvion(ta);

                        out.print("C~El objeto fue actualizado correctamente");
                        break;

                case "consultarTipoAvion":

                    List<TipoAviones> listTipo = new ArrayList(sta.listarTipoAvion());
                    json = new Gson().toJson(listTipo);   
                    out.print(json);
                    break;

                case "deleteTipoAvion":
                    int tipo = Integer.parseInt(request.getParameter("id"));
                    sta.eliminarTipoAvion(tipo);
                    out.print("C~El objeto fue eliminado correctamente");
                    break;
                    
                /* --------------------------------------------------------------------------------------- */
                    
                case "agregarRuta":
                          r.setId(Integer.parseInt(request.getParameter("id")));
                          r.setRuta(request.getParameter("ruta"));
                          r.setDuraccion(request.getParameter("duracion"));
                          
                          sr.insertarRuta(r);

                          out.print("C~El objeto fue ingresado correctamente");
                          break;

                case "setRuta":
                        r.setId(Integer.parseInt(request.getParameter("id")));
                        r.setRuta(request.getParameter("ruta"));
                        r.setDuraccion(request.getParameter("duracion"));
                        
                        sr.actualizarRuta(r);

                        out.print("C~El objeto fue actualizado correctamente");
                        break;

                case "consultarRuta":

                    List<Rutas> listRuta = new ArrayList(sr.listarRutas());
                    json = new Gson().toJson(listRuta);   
                    out.print(json);
                    break;

                case "deleteRuta":
                    int ruta = Integer.parseInt(request.getParameter("id"));
                    sr.eliminarRuta(ruta);
                    out.print("C~El objeto fue eliminado correctamente");
                    break;
                    
                /* --------------------------------------------------------------------------------------- */
                    
                case "agregarHorario":
                          h.setId(Integer.parseInt(request.getParameter("id")));
                          h.setDiaSalida(request.getParameter("diaSalida"));
                          h.setDiaLlegada(request.getParameter("diaLlegada"));
                          h.setHoraSalida(request.getParameter("horaSalida"));
                          h.setHoraLlegada(request.getParameter("horaLlegada"));
                          h.setPrecio(Integer.parseInt(request.getParameter("precio")));
                          
                          sh.insertarHorario(h);

                          out.print("C~El objeto fue ingresado correctamente");
                          break;

                case "setHorario":
                        h.setId(Integer.parseInt(request.getParameter("id")));
                        h.setDiaSalida(request.getParameter("diaSalida"));
                        h.setDiaLlegada(request.getParameter("diaLlegada"));
                        h.setHoraSalida(request.getParameter("horaSalida"));
                        h.setHoraLlegada(request.getParameter("horaLlegada"));
                        h.setPrecio(Integer.parseInt(request.getParameter("precio")));
                          
                        sh.actualizarHorario(h);

                        out.print("C~El objeto fue actualizado correctamente");
                        break;

                case "consultarHorario":

                    List<Horarios> listHora = new ArrayList(sh.listarHorario());
                    json = new Gson().toJson(listHora);   
                    out.print(json);
                    break;

                case "deleteHorario":
                    int hora = Integer.parseInt(request.getParameter("id"));
                    sh.eliminarHorario(hora);
                    out.print("C~El objeto fue eliminado correctamente");
                    break;
                    
                /* --------------------------------------------------------------------------------------- */
                    
                case "agregarAvion":
                          a.setId(Integer.parseInt(request.getParameter("id")));
                          a.setRuta(Integer.parseInt(request.getParameter("ruta")));
                          a.setHorario(Integer.parseInt(request.getParameter("horario")));
                          a.setTipoAvion(Integer.parseInt(request.getParameter("tipoAvion")));
                          
                          sa.insertarAvion(a);

                          out.print("C~El objeto fue ingresado correctamente");
                          break;

                case "setAvion":
                        a.setId(Integer.parseInt(request.getParameter("id")));
                        a.setRuta(Integer.parseInt(request.getParameter("ruta")));
                        a.setHorario(Integer.parseInt(request.getParameter("horario")));
                        a.setTipoAvion(Integer.parseInt(request.getParameter("tipoAvion")));
                           
                        sa.actualizarAvion(a);

                        out.print("C~El objeto fue actualizado correctamente");
                        break;

                case "consultarAvion":

                    List<Aviones> listAvion = new ArrayList(sa.listarAviones());
                    json = new Gson().toJson(listAvion);   
                    out.print(json);
                    break;

                case "deleteAvion":
                    int avion = Integer.parseInt(request.getParameter("id"));
                    sa.eliminarAvion(avion);
                    out.print("C~El objeto fue eliminado correctamente");
                    break;

                default:
                    out.print("E~No se indicó la acción que se desea realizare");
                    break;
                  }

        } catch (NumberFormatException e) {
            out.print("E~" + e.getMessage());
        } catch (Exception e) {
            out.print("E~" + e.getMessage() );
        } 
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
