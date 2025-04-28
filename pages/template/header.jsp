<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="view.*"%>
<% Object message = Bloom.out(request, "message") ; 
  if(message!=null){ %>
    <script> alert("<%= (String)message %>") </script>
  <% } 
  
%>


<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Ticketing</title>

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
