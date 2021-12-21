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
import logica.Servicio;

/**
 *
 * @author sergi
 */
@WebServlet(name = "SvCompraS", urlPatterns = {"/SvCompraS"})
public class SvCompraS extends HttpServlet {

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
            out.println("<title>Servlet SvCompraS</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvCompraS at " + request.getContextPath() + "</h1>");
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
        //Servicio
        Servicio ser = null;
        String[] codigoSer = request.getParameterValues("servicio");
        if (!(codigoSer == null)) {
            ser = controladora.hallarServicioDeseado(codigoSer);

        }
        
        misesion.setAttribute("servicio", ser);
        misesion.setAttribute("costoS", String.valueOf(ser.getCosto()));
     
        misesion.setAttribute("fechaS", request.getParameter("fechaS"));
        misesion.setAttribute("destino", request.getParameter("destino"));
        
           response.sendRedirect("CompraClien.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession misesion = request.getSession();
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

        //Cliente
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

        Empleado empleado = (Empleado) misesion.getAttribute("empleado");
        controladora.crearVentaServicio(ser, destino, fechaS, nombre, apellido, direccion, dni, celular, email, fechaN, pago, empleado);

        misesion.setAttribute("clientesmod", null);
        response.sendRedirect("compra.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
