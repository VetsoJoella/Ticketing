<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Insertion vol</title>

  <link href="http:/localhost/CDN-NiceAdmin/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="http://localhost/CDN-NiceAdmin//css/fonts.googleapis.css" rel="stylesheet">
 
  <!-- Vendor CSS Files -->
  <link href="http://localhost/CDN-NiceAdmin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="http://localhost/CDN-NiceAdmin/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="http://localhost/CDN-NiceAdmin/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="http://localhost/CDN-NiceAdmin/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="http://localhost/CDN-NiceAdmin/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="http://localhost/CDN-NiceAdmin/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="http://localhost/CDN-NiceAdmin/vendor/simple-datatables/style.css" rel="stylesheet">

   <!-- Template Main CSS File -->
  <link href="http://localhost/CDN-NiceAdmin/css/style.css" rel="stylesheet">
  
</head>

<body>

  <!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
      <a href="index.html" class="logo d-flex align-items-center">
        <img src="" alt="">
        <span class="d-none d-lg-block">NiceAdmin</span>
      </a>
      <i class="bi bi-list toggle-sidebar-btn"></i>
    </div><!-- End Logo -->

  </header><!-- End Header -->

  <!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">

      <li class="nav-item">
        <a class="nav-link collapsed" href="#">
          <i class="bi bi-grid"></i>
          <span>Abroad</span>
        </a>
      </li><!-- End Dashboard Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#components-nav" data-bs-toggle="collapse" href="#">
          <i class="ri-plane-line"></i><span> Vol </span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="components-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <a href="vol-liste.html">
              <i class="bi bi-circle"></i><span>Liste</span>
            </a>
          </li>
        </ul>
      </li><!-- End Components Nav -->

      <li class="nav-item">
        <a class="nav-link " data-bs-target="#forms-nav" data-bs-toggle="collapse" href="#">
          <i class="ri-lock-2-fill"></i><span>Réservation</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="forms-nav" class="nav-content collapse show" data-bs-parent="#sidebar-nav">
          <li>
            <a href="reservation-vol.html">
              <i class="bi bi-circle"></i><span>Liste</span>
            </a>
          </li>
        </ul>
      </li><!-- End Forms Nav -->


      <li class="nav-item">
        <a class="nav-link collapsed" href="login-client.html">
          <i class="bi bi-box-arrow-in-right"></i>
          <span>Deconnection</span>
        </a>
      </li><!-- End Login Page Nav -->

    </ul>

  </aside><!-- End Sidebar-->


  <main id="main" class="main">

    <div class="pagetitle">
      <h1>Vol</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="#">Accueil</a></li>
          <li class="breadcrumb-item"><a href="#">Vol</a></li>
          <li class="breadcrumb-item active">Liste Vol</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->
    <section class="section" >
      <div class="row mb-5">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Recherche des vols</h5>

            <form method="get" action="#">
              <div class="row mb-3">
                <label for="inputEmail3" class="col-sm-2 col-form-label">Ville départ</label>
                <div class="col-sm-10">
                  <select class="form-select" name="idVilleDepart" >
                    <option value="">Antananarivo</option>
                    <option value="">Paris</option>
                    <option value="">Maurice</option>
                  </select>
                </div>
              </div>
              <div class="row mb-3">
                <label for="inputEmail3" class="col-sm-2 col-form-label">Ville arrivée</label>
                <div class="col-sm-10">
                  <select class="form-select" name="idVilleArrive" >
                    <option value="">Antananarivo</option>
                    <option value="">Paris</option>
                    <option value="">Maurice</option>
                  </select>
                </div>
              </div>
              <div class="row mb-3">
                <label for="inputPassword3" class="col-sm-2 col-form-label">Date min décollage</label>
                <div class="col-sm-10">
                  <input type="datetime" class="form-control" name="dateMin" id="inputPassword">
                </div>
              </div>
              <div class="row mb-3">
                <label for="inputPassword3" class="col-sm-2 col-form-label">Date max décollage</label>
                <div class="col-sm-10">
                  <input type="datetime" class="form-control" name="dateMax" id="inputPassword">
                </div>
              </div>
              <div class="row mb-3">
                <label for="inputPassword3" class="col-sm-2 col-form-label">Avion</label>
                <div class="col-sm-10">
                  <select class="form-select" name="idVilleArrive" >
                    <option value="">Avion 1</option>
                    <option value="">Avion 2</option>
                    <option value="">Avion 3</option>
                  </select>              
                </div>
              </div>
              <div class="text-center">
                <button type="submit" class="btn btn-primary">Rechercher</button>
              </div>
            </form><!-- End Horizontal Form -->
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                  <h5 class="card-title">Liste des vols</h5>

                  <table class="table table-bordered mb-5 text-center">
                    <thead>
                      <tr>
                        <th scope="col">#</th>
                        <th scope="col">Avion</th>
                        <th scope="col">Ville départ</th>
                        <th scope="col">Ville destination</th>
                        <th scope="col">Date decollage</th>
                        <th scope="col"></th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope="row">VOL0001</th>
                        <th scope="row">Air Madagascar 737</th>
                        <td>Antananarivo</td>
                        <td>France</td>
                        <td>2016-05-25 11:30:00</td>
                        <td><a href="vol-detail.html">Voir détail</a></td>
                      </tr>
                      <tr>
                        <th scope="row">VOL0001</th>
                        <th scope="row">Air Madagascar 737</th>
                        <td>Antananarivo</td>
                        <td>France</td>
                        <td>2016-05-25 11:30:00</td>
                        <td><a href="vol-detail.html">Voir détail</a></td>
                      </tr>
                      <tr>
                        <th scope="row">VOL0001</th>
                        <th scope="row">Air Madagascar 737</th>
                        <td>Antananarivo</td>
                        <td>France</td>
                        <td>2016-05-25 11:30:00</td>
                        <td><a href="vol-detail.html">Voir détail</a></td>
                      </tr>
                      <tr>
                        <th scope="row">VOL0001</th>
                        <th scope="row">Air Madagascar 737</th>
                        <td>Antananarivo</td>
                        <td>France</td>
                        <td>2016-05-25 11:30:00</td>
                        <td><a href="vol-detail.html">Voir détail</a></td>
                      </tr>
                    </tbody>
                  </table>
        
                      <!-- Disabled and active states -->
                    <nav aria-label="...">
                        <ul class="pagination justify-content-center">
                          <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                          </li>
                          <li class="page-item"><a class="page-link" href="#">1</a></li>
                          <li class="page-item active" aria-current="page">
                            <a class="page-link" href="#">2</a>
                          </li>
                          <li class="page-item"><a class="page-link" href="#">3</a></li>
                          <li class="page-item">
                            <a class="page-link" href="#">Next</a>
                          </li>
                        </ul>
                    </nav><!-- End Disabled and active states -->
        
                   
                </div>
            </div>
        </div>
      </div>
      
    </section>

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer" class="footer">
  </footer><!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <script src="http://localhost/CDN-NiceAdmin//vendor/apexcharts/apexcharts.min.js"></script>
  <script src="http://localhost/CDN-NiceAdmin//vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="http://localhost/CDN-NiceAdmin//vendor/chart.js/chart.umd.js"></script> 
  <script src="http://localhost/CDN-NiceAdmin//vendor/echarts/echarts.min.js"></script> 
  <script src="http://localhost/CDN-NiceAdmin/assets/vendor/quill/quill.js"></script> 
  <script src="http://localhost/CDN-NiceAdmin/assets/vendor/simple-datatables/simple-datatables.js"></script> 
  <script src="http://localhost/CDN-NiceAdmin//vendor/tinymce/tinymce.min.js"></script>
  <script src="http://localhost/CDN-NiceAdmin/assets/vendor/php-email-form/validate.js"></script> 
  <script src="http://localhost/CDN-NiceAdmin//js/main.js"></script>


</body>

</html>