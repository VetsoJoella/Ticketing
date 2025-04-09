<%@ page import="model.vol.reservation.Reservation"%>

<% Reservation[] reservations = (Reservation[])Bloom.out(request, "reservations"); %>



<%@ include file="/views/template/header.jsp" %>
<%@ include file="/views/admin/template/navbar.jsp" %>

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
                        <th scope="col">Passager</th>
                        <th scope="col">Date reservation</th>
                        <th scope="col">Nombre de place reservé</th>
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
                            <td><a href="${pageContext.request.contextPath}/admin/reservation?id=<%= reservation.getId() %>">Annuler</a></td>
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
      
  </section>

<%@ include file="/views/template/footer.html" %>