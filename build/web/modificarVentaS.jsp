
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logica.*"   %>

<%  HttpSession misesion = request.getSession();

    Empleado emple = (Empleado) misesion.getAttribute("empleado");
Control controladora= new Control();
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Turism</title>
        <!-- HTML5 Shim and Respond.js IE11 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 11]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
        <!-- Meta -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="description" content="DashboardKit is made using Bootstrap 5 design framework. Download the free admin template & use it for your project.">
        <meta name="keywords" content="DashboardKit, Dashboard Kit, Dashboard UI Kit, Bootstrap 5, Admin Template, Admin Dashboard, CRM, CMS, Free Bootstrap Admin Template">
        <meta name="author" content="DashboardKit ">


        <!-- Favicon icon -->
        <link rel="icon" href="assets/images/favi.png" type="image/x-icon">

        <!-- font css -->
        <link rel="stylesheet" href="assets/fonts/feather.css">
        <link rel="stylesheet" href="assets/fonts/fontawesome.css">
        <link rel="stylesheet" href="assets/fonts/material.css">

        <!-- vendor css -->
        <link rel="stylesheet" href="assets/css/style.css" id="main-style-link">

    </head>

    <body >
        <%             
            String usu = (String) misesion.getAttribute("usuario");
            if (usu == null) {
                response.sendRedirect("index.jsp");
            } else {

        %>

        <script src="assets/js/miprogramacion.js"></script>
        <div class="loader-bg">
            <div class="loader-track">
                <div class="loader-fill"></div>
            </div>
        </div>
        <!-- Final carga de aspectos iniciales -->
        <!<!-- Barra lateral inicial-->
        <div class="pc-mob-header pc-header">
            <div class="pcm-logo">
                <img src="assets/images/turo.png" alt="" class="logo logo-lg">
            </div>
            <div class="pcm-toolbar">
                <a href="#!" class="pc-head-link" id="mobile-collapse">
                    <div class="hamburger hamburger--arrowturn">
                        <div class="hamburger-box">
                            <div class="hamburger-inner"></div>
                        </div>
                    </div>
                </a>
                <a href="#!" class="pc-head-link" id="headerdrp-collapse">
                    <i data-feather="align-right"></i>
                </a>
                <a href="#!" class="pc-head-link" id="header-collapse">
                    <i data-feather="more-vertical"></i>
                </a>
            </div>
        </div>
        <!-- final comienzo barra lateral-->

        <!-- comienoz del meni -->
        <nav class="pc-sidebar ">
            <div class="navbar-wrapper">
                <div class="m-header">
                    <a href="home.jsp" class="b-brand">
                        <!-- ========   change your logo hear   ============ -->
                        <img src="assets/images/turo.png" alt="" class="logo logo-lg">
                        <img src="assets/images/turo.png" alt="" class="logo logo-sm">
                    </a>
                </div>
                <div class="navbar-content">
                    <ul class="pc-navbar">
                        <li class="pc-item pc-caption">
                            <label>Opciones</label>
                        </li>
                        <li class="pc-item">
                            <a href="home.jsp" class="pc-link "><span class="pc-micon"><i class="material-icons-two-tone">home</i></span><span class="pc-mtext">Pagina principal</span></a>
                        </li>
                        <li class="pc-item pc-caption">
                            <label>Utilidades</label>

                        </li>

                        <li class="pc-item">
                            <a href="compra.jsp" class="pc-link "><span class="pc-micon"><i class="material-icons-two-tone">edit</i></span><span class="pc-mtext">Nueva compra</span></a>
                        </li>
                         <li class="pc-item">
                            <a href="servicios.jsp" class="pc-link "><span class="pc-micon"><i class="material-icons-two-tone">edit</i></span><span class="pc-mtext">Servicios</span></a>
                        </li>
                         <li class="pc-item">
                            <a href="clientes.jsp" class="pc-link "><span class="pc-micon"><i class="material-icons-two-tone">cloud</i></span><span class="pc-mtext">Mis clientes</span></a>
                        </li>
                       
                     



                    </ul>
                </div>
            </div>
        </nav>
        <!-- Final del menu-->
        <!-- comienzo de barra superior de datos -->
        <header class="pc-header ">
            <div class="header-wrapper">
                <div class="mr-auto pc-mob-drp">
                    <ul class="list-unstyled">
                        <li class="dropdown pc-h-item">
                            <a class="pc-head-link active dropdown-toggle arrow-none mr-0" data-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
                                Opciones
                            </a>
                            <div class="dropdown-menu pc-h-dropdown">


                                <a href="modificarE.jsp" class="dropdown-item">
                                    <i class="material-icons-two-tone">settings</i>
                                    <span>Configuracion</span>
                                </a>
                                <a href="soporte.jsp" class="dropdown-item">
                                    <i class="material-icons-two-tone">support</i>
                                    <span>Soporte</span>
                                </a>

                                <a href="index.jsp" class="dropdown-item">
                                    <i class="material-icons-two-tone">chrome_reader_mode</i>
                                    <span>Salir</span>
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="ml-auto">
                    <ul class="list-unstyled">
                        <li class="dropdown pc-h-item">
                            <a class="pc-head-link dropdown-toggle arrow-none mr-0" data-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
                                <i class="material-icons-two-tone">search</i>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right pc-h-dropdown drp-search">
                                <form class="px-3">
                                    <div class="form-group mb-0 d-flex align-items-center">
                                        <i data-feather="search"></i>
                                        <input type="search" class="form-control border-0 shadow-none" placeholder="Search here. . .">
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!<!-- Foto y opciones de usuario  agregar metodos con get -->
                        <li class="dropdown pc-h-item">
                            <a class="pc-head-link dropdown-toggle arrow-none mr-0" data-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
                                <img src="assets/images/user/avatar-2.jpg" alt="user-image" class="user-avtar">
                                <span>
                                    <span class="user-name"><%= emple.getNombre() + " "+ emple.getApellido()  %></span>
                                    <span class="user-desc"><%= emple.getCargo() %></span>
                                </span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right pc-h-dropdown">
                                <a href="modificarE.jsp"  class="dropdown-item"   >

                                    <i class="material-icons-two-tone">settings</i>
                                    <span> Configuracion </span>

                                </a>




                                <a href="index.jsp" class="dropdown-item">
                                    <i class="material-icons-two-tone">chrome_reader_mode</i>
                                    <span>Salir</span>
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>

            </div>
        </header>

        <section class="pc-container">
            <div class="pcoded-content">
                <!-- Comienzo barra de posicionameinto -->
                <div class="page-header">
                    <div class="page-block">
                        <div class="row align-items-center">
                            <div class="col-md-12">
                                <div class="page-header-title">
                                    <h5 class="m-b-10">Venta</h5>
                                </div>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="compra.jsp">Pagina principal</a></li>
                                    <li class="breadcrumb-item"><a href="#!">Modificar venta servicio</a></li>

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- fin barra posicionamiento -->
                <!-- contenido principal-->
                
                    <!-- [ form-element ] start -->
                    


                    <!-- Segunda tarjeta  -->
                    <div class="row">
                        <div class="card" id="tarjeta2" >

                            <div class="card-header">
                                <h5>Elija un servicio</h5>
                            </div>

                            <div class="card-body" >
                                <form action="SvModificarS" method="POST">
                                    
                                    <% List<Servicio> srvs= controladora.getServicios();
                                    Venta venta=(Venta) misesion.getAttribute("ventaM");
                                    String fecha= controladora.convertiirFecha(venta.getServicio().getFechaS());
                                    for(Servicio s: srvs){
                                    String estado="";
                            String id= String.valueOf(s.getCodigoS());
                            String nombre= s.getNombre();
                            if(venta.getServicio().getCodigoS()==s.getCodigoS())
{
    estado="checked";
    }
%>
                                    
                                    <div class="form-check">
                                        <input class="form-check-input position-static" onclick="" type="radio" name="servicio" id="blankRadio1" value="<%=id%>" aria-label="..." <%=estado%>>
                                        <label class="form-check-label" for="defaultCheck1">
                                            <%=nombre%>
                                        </label>
                                    </div>
                                    
                                    <% } %>
                                    
                                    
                                    
                                    
                                   
                                    <hr size="3px" color="black" />

                                    <div class="form-group">
                                        <label class="form-label">Fecha de servicio <br></label>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text"><i data-feather="calendar"></i></span>
                                            <input id="fecha" type="date" class="form-control" placeholder="FechaS" name="fechaS" value="<%=fecha %>" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="form-label" for="exampleSelect1">Destino</label>
                                        <select class="form-control" id="exampleSelect1" name="destino">
                                            <option>Cancun</option>
                                            <option>Dubai</option>
                                            <option>Nueva York</option>
                                            <option>Madrid</option>
                                            <option>Inglaterra</option>
                                        </select>
                                    </div>
                                         <div class="form-group">
                                        <label class="form-label" for="exampleSelect1">Medio de pago</label>
                                        <select class="form-control" id="exampleSelect1" name="pago">
                                            <option>Efectivo</option>
                                            <option>Tarjeta de credito</option>
                                            <option>Tarjeta de debito</option>
                                            <option>Monedero virtual</option>
                                            <option>Transferencia</option>
                                        </select>
                                    </div>

                                    <button type="submit"  class="btn btn-primary btn-block mb-4" >Continuar compra</button>
                                    </form>
                                
                            </div>
                            
                            
                        </div>
                        </div>
                        <!-- Tarjeta compra servicio -->
                         
                    <!-- tercera tarjeta -->

                  
                   
                    <!-- fin tercera tarjeta -->
                    <!-- tarjeta commpra paquete -->




                
            </div>
            <!-- [ form-element ] end -->

            <!-- [ Main Content ] end -->

        </section>
        <!-- [ Main Content ] end -->
        <!-- Warning Section start -->
        <!-- Older IE warning message -->
        <!--[if lt IE 11]>
            <div class="ie-warning">
                <h1>Warning!!</h1>
                <p>You are using an outdated version of Internet Explorer, please upgrade
                   <br/>to any of the following web browsers to access this website.
                </p>
                <div class="iew-container">
                    <ul class="iew-download">
                        <li>
                            <a href="http://www.google.com/chrome/">
                                <img src="assets/images/browser/chrome.png" alt="Chrome">
                                <div>Chrome</div>
                            </a>
                        </li>
                        <li>
                            <a href="https://www.mozilla.org/en-US/firefox/new/">
                                <img src="assets/images/browser/firefox.png" alt="Firefox">
                                <div>Firefox</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://www.opera.com">
                                <img src="assets/images/browser/opera.png" alt="Opera">
                                <div>Opera</div>
                            </a>
                        </li>
                        <li>
                            <a href="https://www.apple.com/safari/">
                                <img src="assets/images/browser/safari.png" alt="Safari">
                                <div>Safari</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
                                <img src="assets/images/browser/ie.png" alt="">
                                <div>IE (11 & above)</div>
                            </a>
                        </li>
                    </ul>
                </div>
                <p>Sorry for the inconvenience!</p>
            </div>
        <![endif]-->
        <!-- Warning Section Ends -->
        <!-- Required Js -->
        <script src="assets/js/vendor-all.min.js"></script>
        <script src="assets/js/plugins/bootstrap.min.js"></script>
        <script src="assets/js/plugins/feather.min.js"></script>
        <script src="assets/js/pcoded.min.js"></script>
        <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/highlight.min.js"></script> -->
        <!-- <script src="assets/js/plugins/clipboard.min.js"></script> -->
        <!-- <script src="assets/js/uikit.min.js"></script> -->

        <script>

        </script>

        <% }%>
    </body>

</html>
