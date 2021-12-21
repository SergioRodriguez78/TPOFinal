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
import java.util.ArrayList;
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
import logica.Paquete;
import logica.Servicio;
import logica.Venta;

/**
 *
 * @author sergi
 */
@WebServlet(name = "SvModificarP", urlPatterns = {"/SvModificarP"})
public class SvModificarP extends HttpServlet {
Control controladora = new Control();
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
            out.println("<title>Servlet SvModificarP</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvModificarP at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        
         HttpSession misesion = request.getSession();
           
        String[] paquete = request.getParameterValues("servicios");
        List<Servicio> servicios = null;
        double costo = 0.0;
        if (!(paquete == null)) {
            
            servicios = controladora.hallarServicios(paquete);
costo = controladora.calcularCostoPaquete(servicios);
           
        }
           
 SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaS= new Date();
        try {
            //Hacemos el cambio del tipo string al date
            fechaS = formato.parse(request.getParameter("fechaS"));
        } catch (ParseException ex) {
            Logger.getLogger(SvEmpAlta.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Servicio> h= new ArrayList<>();
        for (Servicio s : servicios) {
             s.setDestino(request.getParameter("destino"));
             s.setFechaS(fechaS);
             h.add(s);
            }
       
        String pago= request.getParameter("pago");
        Venta venta=(Venta) misesion.getAttribute("ventaM");
        
        Paquete p= venta.getPaquete();
        p.setHabilitado(true);
        p.setCosto(costo);
        p.setServicios(h);
        
        
        venta.setTipoPago(pago);
        venta.setPaquete(p);
        Empleado e= (Empleado)misesion.getAttribute("empleado");
        controladora.updateVenta(venta, e, p);
        
        
        response.sendRedirect("home.jsp");
        

    }

    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
