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
import logica.Empleado;
import logica.Servicio;

/**
 *
 * @author sergi
 */
@WebServlet(name = "SvCdirectaS", urlPatterns = {"/SvCdirectaS"})
public class SvCdirectaS extends HttpServlet {
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
            out.println("<title>Servlet SvCdirectaS</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvCdirectaS at " + request.getContextPath() + "</h1>");
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
        Cliente cl = (Cliente)misesion.getAttribute("clientesmod"); 
        //Servicio
        Servicio ser =(Servicio) misesion.getAttribute("servicio");
        
        //Fecha del servicio 
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaS = null;
        try {
            //Hacemos el cambio del tipo string al date
            fechaS = formato.parse((String)misesion.getAttribute("fechaS"));
        } catch (ParseException ex) {
            Logger.getLogger(SvEmpAlta.class.getName()).log(Level.SEVERE, null, ex);
        }
        String destino = (String)misesion.getAttribute("destino");
        

        Empleado empleado = (Empleado) misesion.getAttribute("empleado");
        String pago = request.getParameter("pago");
       controladora.crearVentaDS(cl, ser, fechaS, destino, empleado, pago);

        misesion.setAttribute("clientesmod", null);
        response.sendRedirect("compra.jsp");
        
        
        
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
