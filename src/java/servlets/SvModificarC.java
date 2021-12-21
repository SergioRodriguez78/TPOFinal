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
import logica.Cliente;
import logica.Control;

/**
 *
 * @author sergi
 */
@WebServlet(name = "SvModificarC", urlPatterns = {"/SvModificarC"})
public class SvModificarC extends HttpServlet {
    Control controladora= new Control();
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvModificarC</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvModificarC at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession misesion = request.getSession();
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String dni = request.getParameter("dni");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        int id= Integer.parseInt(request.getParameter("idC"));
                
        SimpleDateFormat formaton = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaN = null;
        try {
            //Hacemos el cambio del tipo string al date
            fechaN = formaton.parse(request.getParameter("fechaN"));
        } catch (ParseException ex) {
            Logger.getLogger(SvEmpAlta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        controladora.modificarCliente(nombre, apellido, direccion, dni, celular, email, id, fechaN);
        response.sendRedirect("clientes.jsp");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
         HttpSession misesion = request.getSession();
         
         int id = Integer.parseInt(request.getParameter("idC"));
         Cliente cliente= controladora.getClienteParticular(id);
         misesion.setAttribute("clienteM", cliente);
         response.sendRedirect("modificarCliente.jsp");
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
