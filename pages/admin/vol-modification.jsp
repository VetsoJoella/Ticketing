<%@ include file="/views/template/header.html" %>
<%@ include file="/views/admin/template/navbar.html" %>

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
      <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Liste Vol</h5>

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
    
<%@ include file="/views/template/footer.html" %>
