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
                  <h4 class="card-title">Registrar Métodos de Pago</h4>
                  <form class="form-sample" id="registrarMetodoPagoForm" action="php/guardar_metodo_pago.php" method="post">
                    <p class="card-description">
                    </p>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Método de Pago</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" id="name" name="name" Required/>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-12">
                        <div class="form-group row">
                          <div class="col-sm-9">
                            <button type="submit" class="btn btn-primary">Guardar</button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </form>
                  <div class="table-responsive pt-3">
                    <div class="mt-3">                      
                    </div>
                    <table class="table table-bordered">
                      <thead>
                        <tr>
                          <th>
                            #
                          </th>
                          <th>
                            Metodo de pago
                          </th>
                          <th>
                            Editar
                          </th>
                          <th>
                            Eliminar
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                      <?php
                        if (is_array($responseData)){
                            foreach ($responseData as $empleado) {
                                if (is_array($empleado)) {
                                    echo "<tr>";                                   
                                    echo "<td>{$empleado['name']}</td>"; 
                                    echo "<td><a href='actualizar_empleado.php?id={$empleado['id']}' class='btn btn-warning btn-sm'>Editar <i class='fa fa-pencil-square-o' aria-hidden='true'></i></a></td>";
                                    echo "<td><a href='../php/eliminar_empleado.php?id={$empleado['id']}' class='btn btn-danger btn-sm'> Borrar<i class='fa fa-trash-o' aria-hidden='true'></i></a></td>";
                                    echo "</tr>";
                                }else{
                                  echo "No llega un array, llego una cadena ";                        
                                
                                }
                            }  
                          } else {
                              echo "<tr><td colspan='10'>No hay personas registradas.</td></tr>";
                          }
                        ?>
                        
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- content-wrapper ends -->
        <!-- partial:../../partials/_footer.html -->
        <footer class="footer">
          <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="text-center text-sm-left d-block d-sm-inline-block">Copyright © <a href="https://www.bootstrapdash.com/" target="_blank">bootstrapdash.com</a> 2020</span>
            <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Free <a href="https://www.bootstrapdash.com/" target="_blank">Bootstrap dashboard </a>templates from Bootstrapdash.com</span>
          </div>
        </footer>
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
  <script>
    function editarMetodoPago() {
      // Lógica para editar el método de pago
    }
    
    function eliminarMetodoPago() {
      // Lógica para eliminar el método de pago
    }
  </script>
</body>

</html>
