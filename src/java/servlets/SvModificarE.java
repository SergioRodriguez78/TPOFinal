/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Control;
import logica.Empleado;
import logica.Usuario;

/**
 *
 * @author sergi
 */
@WebServlet(name = "SvModificarE", urlPatterns = {"/SvModificarE"})
public class SvModificarE extends HttpServlet {
 Control controladora= new Control();
           

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvModificarE</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvModificarE at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
     response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession misesion = request.getSession();
        //Se declara el formato en el cual queremos dejar la fecha
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            //Hacemos el cambio del tipo string al date
            fecha = formato.parse(request.getParameter("fecha"));
        } catch (ParseException ex) {
            Logger.getLogger(SvEmpAlta.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(request.getParameter("fecha"));

        //Se definen las variables que contendran los resultados de cada unput del html
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String dni = request.getParameter("dni");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        String nombreUsu = request.getParameter("nombreUsu");
        String contrasenia = request.getParameter("contrasenia");
        String cargo = request.getParameter("cargo");
        double sueldo=0;
        try{
         sueldo = Double.valueOf(request.getParameter("sueldo"));}
        catch(Exception e){
            
                
        }
        
        Empleado emple= (Empleado)misesion.getAttribute("empleado");
        emple.setNombre(nombre);
        emple.setApellido(apellido);
        emple.setDireccion(direccion);
        emple.setDni(dni);
        emple.setCelular(celular);
        emple.setEmail(email);
        emple.setCargo(cargo);
        emple.setSueldo(sueldo);
        Usuario usu= emple.getUsuario();
        usu.setnUsuario(nombreUsu);
        usu.setContrasenia(contrasenia);
        
        controladora.updateEmpleado(emple, usu);
        response.sendRedirect("home.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
