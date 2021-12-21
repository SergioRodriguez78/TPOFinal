

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logica.*"   %>

<%  HttpSession misesion = request.getSession();

    Empleado emple = (Empleado) misesion.getAttribute("empleado");
    Control controladora = new Control();
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

    <body>
        <%     String usu = (String) misesion.getAttribute("usuario");
            if (usu == null) {
                response.sendRedirect("index.jsp");
            } else {

        %>
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
                                    <i  class="material-icons-two-tone">chrome_reader_mode</i>
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
                                    <span class="user-name"><%= emple.getNombre() + " " + emple.getApellido()%></span>
                                    <span class="user-desc"><%= emple.getCargo()%></span>
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
        <!-- Fin barra superior de informacion-->

        <!-- Contenido principal-->
        <div class="pc-container">
            <div class="pcoded-content">
                <!-- Barrita de donde estamos -->
                <div class="page-header">
                    <div class="page-block">
                        <div class="row align-items-center">
                            <div class="col-md-6">
                                <div class="page-header-title">
                                    <h5 class="m-b-10">Datos de venta</h5>
                                </div>
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="home.jsp">Pagina principal</a></li>
                                    <li class="breadcrumb-item">Datos de venta</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- final barrita de donde estamos -->
                <!-- ahora si los datos -->
                <div class="row">

                    <div class="card flat-card">
                        <div class="row-table">
                            <div class="col-sm-6 card-body br">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <i class="material-icons-two-tone text-primary mb-1">group</i>
                                    </div>
                                    <div class="col-sm-8 text-md-center">
                                        <h5><%=controladora.getClientes().size()%> </h5>
                                        <span>Clientes</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 d-none d-md-table-cell d-lg-table-cell d-xl-table-cell card-body br">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <i class="material-icons-two-tone text-primary mb-1">language</i>
                                    </div>
                                    <div class="col-sm-8 text-md-center">
                                        <%  List<Venta> dinero = emple.getVentas();
                                            double dine = 0;

                                            for (Venta di : dinero) {
                                                if (di.isHabilitado()) {
                                                    if (di.getServicio() == null) {
                                                        dine += di.getPaquete().getCosto();

                                                    } else {
                                                        dine += di.getServicio().getCosto();
                                                    }
                                                }
                                            }


                                        %>
                                        <h5><%= dine%></h5>
                                        <span>Dinero ventas mes</span>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6 card-body">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <i class="material-icons-two-tone text-primary mb-1">shopping_cart</i>
                                    </div>
                                    <div class="col-sm-8 text-md-center">
                                        <h5><%=emple.getVentas().size()%></h5>
                                        <span>Ventas mes</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card flat-card">
                        <div class="row-table">
                            <div class="col-sm-6 card-body br">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <%
                                            double dineDebito = 0;
                                            double dineCredito = 0;
                                            double dineTrans = 0;
                                            for (Venta di : dinero) {
                                                if (di.isHabilitado()) {
try{
                                                    if (di.getServicio() == null) {
                                                        if (di.getTipoPago().equals("Tarjeta de credito")) {

                                                            dineCredito += di.getPaquete().getCosto() * 0.09;
                                                        }
                                                        if (di.getTipoPago().equals("Tarjeta de debito")) {

                                                            dineDebito += di.getPaquete().getCosto() * 0.03;
                                                        }
                                                        if (di.getTipoPago().equals("Transferencia")) {

                                                            dineTrans += di.getPaquete().getCosto() * 0.0245;
                                                        }
                                                    } else {
                                                        if (di.getTipoPago().equals("Tarjeta de credito")) {

                                                            dineCredito += di.getServicio().getCosto() * 0.09;
                                                        }
                                                        if (di.getTipoPago().equals("Tarjeta de debito")) {

                                                            dineDebito += di.getServicio().getCosto() * 0.03;
                                                        }
                                                        if (di.getTipoPago().equals("Transferencia")) {

                                                            dineTrans += di.getServicio().getCosto() * 0.0245;
                                                        }

                                                    } }catch(Exception e){}
                                                }
                                            }


                                        %>
                                        <i class="material-icons-two-tone text-primary mb-1">unarchive</i>
                                    </div>
                                    <div class="col-sm-8 text-md-center">
                                        <h5><%=dineDebito%> </h5>
                                        <span>Ganancias T.Debito</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 d-none d-md-table-cell d-lg-table-cell d-xl-table-cell card-body br">
                                <div class="row">
                                    <div class="col-sm-4">

                                        <i class="material-icons-two-tone text-primary mb-1">unarchive</i>
                                    </div>
                                    <div class="col-sm-8 text-md-center">

                                        <h5><%=dineCredito%></h5>
                                        <span>Ganancias T.Credito</span>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6 card-body">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <i class="material-icons-two-tone text-primary mb-1">unarchive</i>
                                    </div>
                                    <div class="col-sm-8 text-md-center">
                                        <h5><%=dineTrans%></h5>
                                        <span>Ganancias Transferencia</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-12">
                        <div class="card" id="card">
                            <div class="card-header">
                                <h5>Mis Ventas</h5>

                            </div>
                            <div class="card-body table-border-style">
                                <div class="table-responsive">
                                    <table class="table table-striped ">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Nombre cliente</th>
                                                <th>valor</th>
                                                <th>servicio</th>
                                                <th>paquete</th>
                                                <th>tipo de pago</th>
                                                <th> editar</th>
                                                <th>eliminar</th>


                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%

                                                List<Venta> ventas = emple.getVentas();

                                                if (!(ventas.isEmpty())) {

                                                    for (Venta v : ventas) {
                                                        if (v.isHabilitado()) {
                                                            String costo = "";
                                                            String servicio = "";
                                                            String cli = "";
                                                            String paquete = " ";
                                                            String idV = " ";
                                                            idV = String.valueOf(v.getIdV());
                                                            if (v.getServicio() == null) {

                                                                costo = String.valueOf(v.getPaquete().getCosto());
                                                                servicio = "No aplica";
                                                                try {
                                                                    cli = v.getCliente().getNombre() + " " + v.getCliente().getApellido();

                                                                    List<Servicio> s = v.getPaquete().getServicios();
                                                                    for (Servicio a : s) {
                                                                        paquete += a.getNombre() + ". \n";
                                                                    }

                                                                } catch (Exception e) {
                                                                }
                                                            } else {
                                                                try {
                                                                    costo = String.valueOf(v.getServicio().getCosto());
                                                                    servicio = v.getServicio().getNombre();
                                                                    cli = v.getCliente().getNombre() + " " + v.getCliente().getApellido();
                                                                    paquete = "No aplica";
                                                                } catch (Exception e) {
                                                                }
                                                            }
                                                            String pago= v.getTipoPago();
                                            %>
                                            <tr>
                                                <td><%=idV%></td>
                                                <td><%=cli%></td>
                                                <td><%=costo%></td>
                                                <td><%=servicio%></td>
                                                <td><%=paquete%></td>
                                                <td><%=pago%></td>



                                        <form action="SvModificarV" method="POST">
                                            <input type="hidden" name="idV" value="<%=idV%>">
                                            <td>  <button  type="submit"  style="  border: none "  ><i class="material-icons-two-tone">edit_3</i> </button> </td>
                                        </form>

                                        <form action="SvEliminarV" method="POST">
                                            <input type="hidden" name="idV" value="<%=idV%>">
                                            <td><button  type="submit" style="  border:  none "  onclick="confirm('Estas seguro de eliminar a esta venta')" ><i class="material-icons-two-tone">delete</i> </button></td>

                                        </form>

                                        </tr>

                                        <% }
                                                }
                                            }%>
                                        </tbody>

                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>




                <!-- [ Main Content ] end -->
            </div>
        </div>
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

        <!-- Apex Chart -->
        <script src="assets/js/plugins/apexcharts.min.js"></script>


        <!-- custom-chart js -->
        <script src="assets/js/pages/dashboard-sale.js"></script>
        <% }%>
    </body>


</html>
