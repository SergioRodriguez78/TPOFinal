/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
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

/**
 *
 * @author sergi
 */
@WebServlet(name = "SvCompraP", urlPatterns = {"/SvCompraP"})
public class SvCompraP extends HttpServlet {

    Control controladora = new Control();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvCompraP</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvCompraP at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

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
            fechaS = formato.parse(request.getParameter("fecha1"));
        } catch (ParseException ex) {
            Logger.getLogger(SvEmpAlta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (Servicio s : servicios) {
             s.setDestino(request.getParameter("destino"));
             s.setFechaS(fechaS);
            }

        

        misesion.setAttribute("costoP", String.valueOf(costo));
        misesion.setAttribute("serviciosP", servicios);

        response.sendRedirect("CompraClienP.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession misesion = request.getSession();
        
         String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String dni = request.getParameter("dni");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        String pago = request.getParameter("pago");
       SimpleDateFormat formaton = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaN = null;
        try {
            //Hacemos el cambio del tipo string al date
            fechaN = formaton.parse(request.getParameter("fechaN"));
        } catch (ParseException ex) {
            Logger.getLogger(SvEmpAlta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Empleado empleado= (Empleado)misesion.getAttribute("empleado");
        List<Servicio> servicios= (List<Servicio>)misesion.getAttribute("serviciosP");
        
        controladora.crearVentaPaquete(nombre, apellido, direccion, dni, celular, email, pago, fechaN, empleado, servicios);
        
        response.sendRedirect("compra.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
