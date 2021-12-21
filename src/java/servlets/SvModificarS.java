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
import java.util.List;
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
import logica.Servicio;
import logica.Venta;

/**
 *
 * @author sergi
 */
@WebServlet(name = "SvModificarS", urlPatterns = {"/SvModificarS"})
public class SvModificarS extends HttpServlet {
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
            out.println("<title>Servlet SvModificarS</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvModificarS at " + request.getContextPath() + "</h1>");
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
        //Servicio
        Servicio ser = null;
        Venta v=(Venta)misesion.getAttribute("ventaM");
        String[] codigoSer = request.getParameterValues("servicio");
        if (!(codigoSer == null)) {
            ser = controladora.hallarServicioDeseado(codigoSer);

        }
        ser.setDestino(request.getParameter("destino"));
         SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaS = null;
        try {
            //Hacemos el cambio del tipo string al date
            fechaS = formato.parse((String)misesion.getAttribute("fechaS"));
        } catch (ParseException ex) {
            Logger.getLogger(SvEmpAlta.class.getName()).log(Level.SEVERE, null, ex);
        }
        ser.setFechaS(fechaS);
        
        v.setServicio(ser);
        v.setTipoPago(request.getParameter("pago"));
        
        
        Empleado emple=(Empleado)misesion.getAttribute("empleado");
       
        
        controladora.updateVenta(v,emple,null );
        
        
        response.sendRedirect("home.jsp");
        
        
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
