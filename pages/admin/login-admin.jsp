<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="view.*"%>
<% Object message = Bloom.out(request, "message") ; 
    out.println(message);
  if(message!=null){ %>
    <script> alert("<%= (String)message %>") </script>
  <% } 
  
%>

<%@ include file="/views/template/header.html" %>

<body>

  <main>
    <div class="container">

      <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

              <div class="d-flex justify-content-center py-4">
                <a href="#" class="logo d-flex align-items-center w-auto">
                  <img src="" alt="">
                  <span class="d-none d-lg-block">Abroad</span>
                </a>
              </div><!-- End Logo -->

              <div class="card mb-3">

                <div class="card-body">

                  <div class="pt-4 pb-2">
                    <h5 class="card-title text-center pb-0 fs-4">Connection admin</h5>
                  </div>

                  <form class="row g-3 needs-validation" action="admin" method="post" novalidate>
                    <div class="col-12 form-floating">
                      <input type="text" class="form-control" id="yourUsername" placeholder="Numero téléphone" name="admin.login" required>
                      <label for="yourUsername">Login</label>
                      <div class="invalid-feedback">Insérer votre numéro de login!</div>
                    </div>
                  
                    <div class="col-12 form-floating">
                      <input type="password" class="form-control" id="yourPassword" placeholder="Nom" name="admin.mdp" required>
                      <label for="yourPassword">Mot de passe</label>
                      <div class="invalid-feedback">Insérer votre mot de passe!</div>
                    </div>
                  
                    <div class="col-12">
                      <button class="btn btn-primary w-100" type="submit">Login</button>
                    </div>
                  </form>
                  

                </div>
              </div>

            </div>
          </div>
        </div>

      </section>

    </div>

<%@ include file="/views/template/footer.html" %>
