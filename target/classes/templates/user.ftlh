<!DOCTYPE html>
<html>
<head>
    <title>User</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>

        .header {
            float: top;
            height: 60px;
            width: 100%;
            position: fixed;
            z-index: 2;
            top: 0;
            left: 0;
            background-color: #212121;
            overflow-x: hidden;
            padding-top: 20px;
            color: #ffffff;
        }

        .header a {
            padding: 6px 8px 6px 16px;
            text-decoration: none;
            font-size: 16px;
            color: #000000;
            display: block;
        }

        .sidenav {
            height: 90%;
            width: 140px;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            margin-top: 60px;
            background-color: #ffffff;
            overflow-x: hidden;
            padding-top: 20px;
        }

        .sidenav a {
            padding: 6px 8px 6px 16px;
            text-decoration: none;
            font-size: 16px;
            color: #212121;
            display: block;
        }

        .sidenav a:hover {
            color: #1070b9;
        }

        .sidenav a.is-active {
            color: #fff;
            background-color: #1070b9;
        }


        .panel a {
            text-decoration: none; /*убираем подчеркивание текста ссылок*/
            background: #d0cece; /*добавляем фон к пункту меню*/
            color: #414141; /*меняем цвет ссылок*/
            padding: 10px; /*добавляем отступ*/
            border-radius: 4px 4px 0px 0px; /*добавляем скругление*/
        }

        .panel a.active {
            color: #1070b9;
            background-color: #eae9e9;
        }

        .main {
            position: absolute;
            margin-left: 150px; /* Same as the width of the sidenav */
            margin-top: 100px; /*More then width of the header */
            font-size: 14px; /* Increased text to enable scrolling */
            padding: 0px 10px;
        }

        .central {
            margin-left: 10px;
        }

        body {
            position: absolute;
            background-color: #ffffff;
        }

        @media screen and (max-height: 450px) {
            .sidenav {
                padding-top: 15px;
            }

            .sidenav a {
                font-size: 18px;
            }
        }

        .visible-bar {
            visibility: hidden;
        }

    </style>

</head>
<body>

<div class="header">
    <a id="mail-role"></a>
</div>

<div class="main">

    <div class="sidenav admin-bar">
        <a href="/list" class="admin-panel">Admin</a> <br>
        <a href="/user" class="is-active">User</a>
    </div>
    <div class="sidenav user-bar">
        <a href="/user" class="is-active">User</a>
    </div>
    <div class="central">
        <div>
            <table class="table table-hover table-striped">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Login</th>
                    <th scope="col">Password</th>
                    <th scope="col">Age</th>
                    <th scope="col">Roles</th>
                </tr>
                <tbody id="table">
                </tbody>
            </table>
        </div>
    </div>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.admin-panel').addClass('visible-admin');
            function mainAjaxForm() {
                $.ajax({
                    type: 'GET',
                    data: {},
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    url: "http://localhost:8081/user/rest",
                    success: function (data) {
                            $('#table').empty();

                            var row = document.createElement("tr");
                            var id = document.createElement('td');
                            var login = document.createElement('td');
                            var password = document.createElement('td');
                            var age = document.createElement('td');
                            var role = document.createElement('td');

                            id.innerHTML = data.id;
                            login.innerHTML = data.login;
                            password.innerHTML = data.password;
                            age.innerHTML = data.age;
                            role.innerHTML = data.role;

                        console.log(role);

                        $('.user-bar').addClass('visible-bar');

                            row.appendChild(id);
                            row.appendChild(login);
                            row.appendChild(password);
                            row.appendChild(age);
                            row.appendChild(role);

                            $('#table').append(row);

                    }


                });
                mailRoleString()
            }

            function mailRoleString() {
                $.ajax({
                    type: 'GET',
                    data: {},
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    url: "http://localhost:8081/user/rest",
                    success: function (data) {
                        var aTeg = document.getElementById('mail-role');

                        var login = data.login;
                        var role = data.role;

                        aTeg.innerHTML = 'User: '+ login + '; role: '+role;

                    }
                });
            }

            mainAjaxForm();

        });
    </script>
</div>
</body>
</html>