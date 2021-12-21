/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Control;
import logica.Empleado;

/**
 *
 * @author sergi
 */
@WebServlet(name = "SvUsuario", urlPatterns = {"/SvUsuario"})
public class SvUsuario extends HttpServlet {

    Control controladora = new Control();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        try {
            String usuario = request.getParameter("usu");
            String contrasenia = request.getParameter("contra");
            boolean autorizado = controladora.verificarUsuario(usuario, contrasenia);
            Empleado emple = controladora.getUsuarioParticular(controladora.getIdUsuarioByName(usuario)).getEmpleado();
            if (autorizado == true && emple!=null) {
                HttpSession misesion = request.getSession(true);
                misesion.setAttribute("usuario", usuario);
                misesion.setAttribute("contrasenia", contrasenia);
                misesion.setAttribute("empleado", emple);
                response.sendRedirect("home.jsp");

            } else {
                 out.println("<script type=\"text/javascript\">");
                out.println("alert('Usuario o contraseña incorrecta');");
                out.println("</script>");
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Hubo un error al inciar sesion, intente de nuevo');");
            out.println("</script>");
            response.sendRedirect("index.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
