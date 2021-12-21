

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

<!-- [ auth-signin ] start -->
<div class="auth-wrapper">
	<div class="auth-content">
		<div class="card">
			<div class="row align-items-center text-center">
				<div class="col-md-12">
					<div class="card-body">
						<img src="assets/images/turo.png" alt="" class="img-fluid mb-4">
						<h4 class="mb-3 f-w-400">Inicio sesion empleado</h4>
                                                
                                                <form action="SvUsuario" method="POST">
						<div class="input-group mb-3">
							<span class="input-group-text"><i data-feather="user"></i></span>
							<input type="text" class="form-control" placeholder="Ingrese su usuraio" name="usu">
						</div>
						<div class="input-group mb-4">
							<span class="input-group-text"><i data-feather="lock"></i></span>
                                                        <input type="password" class="form-control" placeholder="Ingrese contraseña" name="contra">
						</div>
						<div class="form-group text-left mt-2">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
								<label class="form-check-label" for="flexCheckChecked">
									Guardar contraseña
								</label>
							</div>
						</div>
                                                    <button type="submit" class="btn btn-block btn-primary mb-4">Ingresar</button> 
                                                </form>
                                                
                                               
						<p class="mb-0 text-muted">Nuevo empleado? <a href="crearUsu.jsp" class="f-w-400">Registrate</a></p>
                                                <hr size="4px" color="black" />
                                                <p style="display: none" class="mb-0 text-muted">Nuevo administrador? <a href="crearAdmin.jsp" class="f-w-400">Registrate</a></p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- [ auth-signin ] end -->

<!-- Required Js -->
<script src="assets/js/vendor-all.min.js"></script>
<script src="assets/js/plugins/bootstrap.min.js"></script>
<script src="assets/js/plugins/feather.min.js"></script>
<script src="assets/js/pcoded.min.js"></script>



</body>

</html>
