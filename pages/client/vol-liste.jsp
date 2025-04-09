<%@ page import="model.vol.Vol"%>
<%@ page import="model.vol.ville.Ville"%>
<%@ page import="model.avion.Avion"%>
<%@ page import="model.avion.classe.Classe"%>

<% Vol[] vols = (Vol[])Bloom.out(request, "vols") ;   
   Ville[] villes = (Ville[])Bloom.out(request, "villes") ;   
   Avion[] avions = (Avion[])Bloom.out(request, "avions") ;    
%>

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
    <section class="section" >
      <div class="row mb-5">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Recherche des vols</h5>

            <form method="get" action="${pageContext.request.contextPath}/client/vols">
              <div class="row mb-3">
                <label for="inputEmail3" class="col-sm-2 col-form-label">Ville départ</label>
                <div class="col-sm-10">
                  <select class="form-select" name="depart.id" >
                    <option value=""></option>
                    <% if(villes!=null && villes.length>0) {
                        for(Ville ville : villes) { %>
                          <option value="<%= ville.getId() %>"><%= ville.getNom() %></option>
                    <%  }
                  } %>
                  </select>
                </div>
              </div>
              <div class="row mb-3">
                <label for="inputEmail3" class="col-sm-2 col-form-label">Ville arrivée</label>
                <div class="col-sm-10">
                  <select class="form-select" name="destination.id" >
                    <option value=""></option>

                    <% if(villes!=null && villes.length>0) {
                        for(Ville ville : villes) { %>
                          <option value="<%= ville.getId() %>"><%= ville.getNom() %></option>
                      <%  }
                    } %>
                  </select>
                </div>
              </div>
              <div class="row mb-3">
                <label for="inputPassword3" class="col-sm-2 col-form-label">Date min décollage</label>
                <div class="col-sm-10">
                  <input type="datetime-local" class="form-control" name="dateMin" id="inputPassword">
                </div>
              </div>
              <div class="row mb-3">
                <label for="inputPassword3" class="col-sm-2 col-form-label">Date max décollage</label>
                <div class="col-sm-10">
                  <input type="datetime-local" class="form-control" name="dateMax" id="inputPassword">
                </div>
              </div>
              <div class="row mb-3">
                <label for="inputPassword3" class="col-sm-2 col-form-label">Avion</label>
                <div class="col-sm-10">
                  <select class="form-select" name="avion.id" >
                    <option value=""></option>
                    <% if(avions!=null && avions.length>0) {
                          for(Avion avion : avions) { %>
                            <option value="<%= avion.getId() %>"><%= avion.getNom() %></option>
                      <%  }
                    } %>
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

                    <% if(vols!=null && vols.length> 0) { 
                        for(Vol vol : vols) { %>
                          <tr>
                            <th scope="row"><%= vol.getId() %></th>
                            <th scope="row"><%= vol.getAvion().getNom() %></th>
                            <td><%= vol.getVilleDepart().getNom() %></td>
                            <td><%= vol.getVilleDestination().getNom() %></td>
                            <td><%= vol.getDateHeureDecollage() %></td>
                            <td><a href="${pageContext.request.contextPath}/client/vol?id=<%= vol.getId() %>">Voir détail</a></td>
                          </tr>
                        <% }

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
