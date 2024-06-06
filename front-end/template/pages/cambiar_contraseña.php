<?php
session_start();
?>

<!DOCTYPE html>
<html lang="en">

<?php include 'partials/header.php'; ?>
<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth px-0">
        <div class="row w-100 mx-0">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left py-5 px-4 px-sm-5">
              <div class="brand-logo">
                <a href="../index.php">
                <img src="../images/logo.png" alt="logo">
                </a>
              </div>
              <h4>Cambiar Contraseña</h4>
              <h6 class="font-weight-light">Complete el formulario para cambiar su contraseña.</h6>
              <form class="pt-3" id="changePasswordForm" action="../php/cambiarContraseña.php" method="post">
                <div class="form-group">
                  <input type="text" class="form-control form-control-lg" id="username" name="username" placeholder="Nombre de usuario" value="" Required>
                </div>
                <div class="form-group">
                  <input type="password" class="form-control form-control-lg" id="oldPassword" name="oldPassword" placeholder="Contraseña actual" value="" Required>
                </div>
                <div class="form-group">
                  <input type="password" class="form-control form-control-lg" id="password" name="password" placeholder="Nueva contraseña" value="" Required>
                </div>
                <div class="mt-3">
                  <button type="submit" class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn">CAMBIAR CONTRASEÑA</button>
                </div> 
                <div class="my-2 d-flex justify-content-between align-items-center">
                  <a href="../index.php" class="auth-link text-black">Volver a iniciar sesión</a>                
              </form>
            </div>
          </div>
        </div>
      </div>
      <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->
  <!-- base:js -->
  <?php include 'partials/scripts.php'; ?>
  <!-- endinject -->
</body>

</html>