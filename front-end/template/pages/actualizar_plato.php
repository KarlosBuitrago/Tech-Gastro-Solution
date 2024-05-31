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
                  <h4 class="card-title">Crear Plato</h4>
                  <p class="card-description">
                    Crear platos del restaurante
                  </p>
                  <form class="forms-sample">
                    <div class="form-group">
                        <label for="dishes">Nombre Plato</label>
                        <input type="text" class="form-control" id="name" name="name">
                    </div>
                    <div class="form-group">
                      <label for="exampleSelectGender">Categoria</label>
                        <select class="form-control" id="exampleSelectGender">
                          <option>Categoria 1</option>
                          <option>Categoria 2</option>
                          <option>Categoria 3</option>
                          <option>Categoria 4</option>
                        </select>
                      </div>
                    <div class="form-group">
                      <label for="dishes">Nombre Plato</label>
                      <input type="text" class="form-control" id="name" name="name">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputEmail3">Descripcion</label>
                      <input type="email" class="form-control" id="description" name="description">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword4">Precio</label>
                      <input type="number" class="form-control" id="price" name="price">
                    </div>
                    <div class="form-group">
                      <label>Fotos</label>
                      <input type="file" name="img[]" class="file-upload-default">
                      <div class="input-group col-xs-12">
                        <input type="text" class="form-control file-upload-info" disabled placeholder="Upload Image">
                        <span class="input-group-append">
                          <button class="file-upload-browse btn btn-primary" type="button">Cargar Foto</button>
                        </span>
                      </div>
                    </div>
                    <button type="submit" class="btn btn-primary mr-2">Submit</button>
                    <button class="btn btn-light">Cancel</button>
                  </form>
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
