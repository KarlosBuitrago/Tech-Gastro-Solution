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
                  <h4 class="card-title">Lista de Platos</h4>
                  <div class="table-responsive pt-3">
                    <div class="mt-3">
                      <a class="btn btn-primary btn-lg font-weight-medium auth-form-btn" href="crear_plato.php">Nuevo platillo</a>
                    </div>
                    <div class="mt-3">                      
                    </div>
                    <table class="table table-bordered">
                      <thead>
                        <tr>
                          <th>
                            #
                          </th>
                          <th>
                            Categoria
                          </th>
                          <th>
                            Nombre de plato
                          </th>
                          <th>
                            Descripcion
                          </th>
                          <th>
                            Precion
                          </th>
                          <th>
                            Fotos
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
                                    echo "<td>{$empleado['country']}</td>";
                                    echo "<td>{$empleado['birthdate']}</td>";
                                    echo "<td>{$empleado['age']}</td>";
                                    echo "<td>{$empleado['username']}</td>";
                                    echo "<td>{$empleado['role']}</td>";
                                    echo "<td>{$empleado['status']}</td>"; 
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