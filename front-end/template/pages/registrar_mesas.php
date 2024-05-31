<?php

session_start();

if (!isset($_SESSION['jwttoken'])) {
  header('Location: ../index.php');
  exit();
}
?>

<!DOCTYPE html>
<html lang="en">

<?php include 'partials/header.php'; ?>

<body>
  <div class="container-scroller">
    <!-- partial:../../partials/_navbar.html -->
    <?php include 'partials/navbar.php'; ?>    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:../../partials/_settings-panel.html -->
      <?php include 'partials/settings-panel.php'; ?>      
      <!-- partial -->
      <!-- partial:../../partials/_sidebar.html -->
      
      <?php include 'partials/sidebar.php'; ?>    
      <!-- partial -->
      <div class="main-panel">        
        <div class="content-wrapper">
          <div class="row">           
            <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Registrar nueva mesa</h4>
                  <form class="form-sample" action="../php/guardar_mesa.php" method="post">
                    <div class="form-group">
                    <label for="formGroupExampleInput">Nombre</label>
                    <input type="text" class="form-control" id="name" name="name" Required>
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlTextarea1">Descripción</label>
                        <textarea class="form-control" id="description" name="description" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </form>
                <a type="button" class="btn btn-primary" href="lista_mesas.php" >Mesas</a>
              </div>
            </div>
          </div>
        </div>
        <!-- content-wrapper ends -->
        <!-- partial:../../partials/_footer.html -->
        <?php include 'partials/footer.php'; ?> 
        <!-- partial -->
      </div>
      <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->
  <!-- base:js -->
  <?php include 'partials/scripts.php'; ?>
  <!-- End custom js for this page-->
</body>

</html>
