

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

    
    <!-- [ auth-signup ] start -->
    <div class="auth-wrapper">
        <div class="auth-content">
            <div class="card">
                <div class="row align-items-center text-center">
                    <div class="col-md-12">
                        <div class="card-body">
                            <img src="assets/images/turo.png" alt="" class="img-fluid mb-4">
                            <h4 class="mb-3 f-w-400">Ingrese sus datos</h4>
                            <form action="SvEmpAlta" method="POST" id="error">
                                <div class="input-group mb-3">
                                    <span class="input-group-text"><i data-feather="archive"></i></span>
                                    <input id="nombre" type="text" class="form-control" placeholder="Nombre" name="nombre" required>

                                </div>

                                <div class="input-group mb-3">
                                    <span class="input-group-text"><i data-feather="archive"></i></span>
                                    <input id="apellido" type="text" class="form-control" placeholder="Apellido" name="apellido" required>
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text"><i data-feather="archive"></i></span>
                                    <input id="direccion"type="text" class="form-control" placeholder="Direccion" name="direccion"required>
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text"><i data-feather="archive"></i></span>
                                    <input id="dni" type="text" class="form-control" placeholder="dni" name="dni" required>
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text"><i data-feather="archive"></i></span>
                                    <input id="celular" type="text" class="form-control" placeholder="celular" name="celular" required>
                                </div>
                               <div class="form-group">
                                <label class="form-label" for="fecha">Fecha de nacimiento</label>

                                <div class="input-group mb-3">

                                    <span class="input-group-text"><i data-feather="calendar"></i></span>
                                    <input id="fecha" type="date" class="form-control" placeholder="Fecha de nacimiento" name="fecha" required>

                                </div>
                            </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text"><i data-feather="mail"></i></span>
                                    <input id="email" type="email" on class="form-control" placeholder="Correo electronico" name="email"required>
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text"><i data-feather="user"></i></span>
                                    <input id="nombreU" type="text" class="form-control" placeholder="Nombre de usuario" name="nombreUsu" required>
                                </div>

                                
                                <div class="input-group mb-4">
                                    <span class="input-group-text"><i data-feather="lock"></i></span>
                                    <input id="contra" type="password" class="form-control" placeholder="Contraseña" name="contrasenia"required>
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text"><i data-feather="briefcase"></i></span>
                                    <input id="cargo" type="text" class="form-control" placeholder="Cargo" name="cargo"required>
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text"><i data-feather="dollar-sign"></i></span>
                                    <input id="sueldo" type="text" class="form-control" placeholder="Sueldo" name="sueldo"required>
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text"><i data-feather="camera"></i></span>
                                    <input  type="file" class="form-control" placeholder="Suba una foto por favor" name="file">
                                </div>




                                <button class="btn btn-primary btn-block mb-4" type="summit" >Registrarse</button>
                            </form>
                            <p class="mb-2">Ya tienes una cuenta? <a href="index.jsp" class="f-w-400">Ingresa</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- [ auth-signup ] end -->

    <!-- Required Js -->
    <script src="assets/js/vendor-all.min.js"></script>
    <script src="assets/js/plugins/bootstrap.min.js"></script>
    <script src="assets/js/plugins/feather.min.js"></script>
    <script src="assets/js/pcoded.min.js"></script>


</body>

</html>
