package servlets;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import logica.Control;

/**
 *
 * @author sergi
 */
@WebServlet(name = "SvEmpAlta", urlPatterns = {"/SvEmpAlta"})
@MultipartConfig
public class SvEmpAlta extends HttpServlet {

    Control controladora = new Control();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvEmpAlta</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvEmpAlta at " + request.getContextPath() + "</h1>");
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
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Sueldo tiene caracteres incorrectos');");
            out.println("</script>");
                
        }
        boolean habilitado = true;

        //Se hace el acondicionamiento para la foto
        byte[] foto = null;
        InputStream input = null;
        ByteArrayOutputStream imagen = null;
        try {

            if (request.getPart("foto").getSize() != 0) {

                if (request.getPart("foto").getContentType().contains("image") == true) {

                    //Obtenemos la parte del arvhivo foto
                    Part archivo = request.getPart("foto");

                    //Lo convertimos en un itnputStream y ya queda lsito para enviar
                    input = archivo.getInputStream();

                    // Escalar la imagen
                    BufferedImage imageBuffer = ImageIO.read(input);
                    Image tmp = imageBuffer.getScaledInstance(100, 100, BufferedImage.SCALE_FAST);
                    BufferedImage buffered = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
                    buffered.getGraphics().drawImage(tmp, 0, 0, null);

                    //Convertir imagen al tipo deseado
                    imagen = new ByteArrayOutputStream();
                    foto = imagen.toByteArray();

                }
            }
        } catch (Exception e) {
            System.out.println("Fallo modificacion de foto");
        }

        controladora.crearEmpleado(nombre, apellido, direccion, dni, celular, email, nombreUsu, contrasenia, cargo, sueldo, habilitado, fecha, foto);

        response.sendRedirect("index.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
