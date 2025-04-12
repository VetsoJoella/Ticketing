<%@ page import="model.vol.reservation.Reservation"%>

<% Reservation[] reservations = (Reservation[])Bloom.out(request, "reservations"); %>


<%@ include file="/views/template/header.jsp" %>
<%@ include file="/views/client/template/navbar.jsp" %>

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
  <%-- <section class="section" >
      <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Liste Vol</h5>

                  <table class="table table-bordered mb-5 text-center">
                    <thead>
                      <tr>
                        <th scope="col">#</th>
                        <th scope="col">Passager</th>
                        <th scope="col">Date reservation</th>
                        <th scope="col">Nombre de place reserve</th>
                      </tr>
                    </thead>
                    <tbody>
                    <% if(reservations!=null ) {
                        for(Reservation reservation : reservations) { %>
                          <tr>
                            <th scope="row"><%= reservation.getId() %></th>
                            <th scope="row"><%= reservation.getPassager().getNom() %></th>
                            <td><%= reservation.getDateReservation() %></td>
                            <td><%= reservation.getNbPlaceReservee() %></td>
                            <td><a href="${pageContext.request.contextPath}/client/reservation?id=<%= reservation.getId() %>">Annuler</a></td>
                          </tr>
                      <%  }
                    } %>
                      
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
      
  </section> --%>

  <section class="section">
    <div class="row">
      <% if(reservations!=null ) {
        for(Reservation reservation : reservations) { %>
          <div class="col-6">
            <div class="card">
              <div class="row">
                <div class="col-4">
                  <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">
                    <img src="http://localhost/CDN-NIceAdmin/img/icon/people.png" alt="Profile" class="rounded-circle" width="150">
                  </div>
                </div>
                <div class="col-8 card-body pt-3">
                  <!-- Bordered Tabs -->
                  <ul class="nav nav-tabs nav-tabs-bordered">

                    <li class="nav-item">
                      <button class="nav-link active" data-bs-toggle="tab" data-bs-target="<%= "#profile-overview"+reservation.getId()  %>">Information</button>
                    </li>

                    <li class="nav-item">
                      <button class="nav-link" data-bs-toggle="tab" data-bs-target="<%= "#profile-edit"+ reservation.getId()  %>">Passport</button>
                    </li>

                  </ul>
                  <div class="tab-content pt-2">

                    <div class="tab-pane fade show active profile-overview" id="<%= "profile-overview"+reservation.getId()  %>">
                      <h5 class="card-title">Description</h5>
                      <p class="small fst-italic">Ceci est un vol reserve.<a href="${pageContext.request.contextPath}/client/reservation?id=<%= reservation.getId() %>">Annuler</a> </p>

                      <h5 class="card-title">Details</h5>

                      <div class="row">
                        <div class="col-lg-6 col-md-4 label ">Reference du vol</div>
                        <div class="col-lg-6 col-md-8"><%= reservation.getId()  %></div>
                      </div>

                      <div class="row">
                        <div class="col-lg-6 col-md-4 label ">Passager</div>
                        <div class="col-lg-6 col-md-8"><%= reservation.getPassager().getNom() %></div>
                      </div>

                      <div class="row">
                        <div class="col-lg-6 col-md-4 label">Date reservation</div>
                        <div class="col-lg-6 col-md-8"><%= reservation.getDateReservation() %></div>
                      </div>

                      <div class="row">
                        <div class="col-lg-6 col-md-4 label">Nombre de siege</div>
                        <div class="col-lg-6 col-md-8"><%= reservation.getNbPlaceReservee() %></div>
                      </div>
                    </div>

                    <div class="tab-pane fade profile-edit pt-3" id= "<%= "profile-edit"+reservation.getId()  %>">

                      <!-- Profile Edit Form -->
                      
                      <div class="row mb-3">
                        <%-- <label for="profileImage" class="col-md-4 col-lg-3 col-form-label">Profile Image</label> --%>
                        <div class="col-md-8 col-lg-9">
                          <img src="<%= "http://localhost/CDN-NIceAdmin/img/ticketing/" + reservation.getImage() %>" alt="Profile" width="200">
                          <%-- <div class="pt-2">
                            <a href="#" class="btn btn-primary btn-sm" title="Upload new profile image"><i class="bi bi-upload"></i></a>
                            <a href="#" class="btn btn-danger btn-sm" title="Remove my profile image"><i class="bi bi-trash"></i></a>
                          </div> --%>
                        </div>
                      </div>

                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
      <% } }%>
    </div>
  </section>
<%@ include file="/views/template/footer.html" %>