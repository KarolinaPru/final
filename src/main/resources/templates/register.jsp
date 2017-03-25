<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quiz Whizz</title>

    <!-- Bootstrap -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.5/validator.min.js">
    </script>

</head>
<body data-spy="scroll" data-target="#navi">
<nav class = "navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a href="#" class="navbar-brand">Quiz Whizz</a>



        <button class="navbar-toggle" data-toggle="collapse" data-target=".navCollapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
    </div>
</nav>

<div class="row" style="padding-top: 100px;">
    <section class="col-md-offset-1 col-md-10 col-md-offset-1 col-sm-offset-1 col-sm-10">
        <!-- Panel 1-->
        <div class="panel-heading"><h1 class="display-3">Rejestracja</h1></div>

        <div class="panel-body">

            <form role="form"  method="post" action="registerServlet">

                <div class="form-group">
                    <label class="control-label" for="inputLogin">Login</label>
                    <input name="inputLogin" class="form-control" data-error="Please enter login field." id="inputLogin" placeholder="Podaj login"  type="text" required />
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <label class="control-label" for="inputName">Imie</label>
                    <input name="inputName"  class="form-control" data-error="Please enter name field." id="inputName" placeholder="Podaj imie"  type="text" required />
                    <div class="help-block with-errors"></div>
                </div>

                <div class="form-group">
                    <label for="inputEmail" class="control-label">Email</label>
                    <input name="inputEmail" type="email" class="form-control" id="inputEmail" placeholder="Podaj email" required>
                    <div class="help-block with-errors"></div>
                </div>

                <div class="form-group">
                    <label for="inputPassword" class="control-label">Hasło</label>
                    <div class="form-group">
                        <input name="inputPassword" type="password" data-minlength="5" class="form-control" id="inputPassword" data-error="must enter minimum of 5 characters" placeholder="Podaj hasło, minium 5 znaków" required>
                        <div class="help-block with-errors"></div>
                    </div>
                </div>

                <div class="form-group">
                    <button class="btn btn-primary" type="submit">
                        Zarejestruj
                    </button>
                    <a href="${pageContext.request.contextPath}/login.jsp" class="btn btn-success" role="button">Przejdz do logowania</a>
                </div>
            </form>

        </div>
    </section>
</div>


<footer class="footer">
    <div class="container">
        <p class="text-muted">QuizWhizz - developed by Michał Nowiński and Karolina Prusaczyk</p>
    </div>
</footer>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>
