<#import "ui.ftl" as ui/>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Users</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <style>
        .modal {
            position: absolute;
            visibility: hidden;
            opacity: 0;
            transition: .3s;
            top: -1000px;
            right: 0;
            bottom: 0;
            left: 0;
            z-index: 1000;
            background: rgba(0, 0, 0, 0.6);
            display: flex;
            justify-content: center;
            align-items: flex-start;
            overflow-y: scroll;
            padding: 60px 15px;
        }

        .modal_active {
            position: fixed;
            top: 5px;
            visibility: visible;
            opacity: 1;
            transition: .3s;
        }

        .modal__content {
            max-width: 500px;
            padding: 50px;
            background: #f9f9f9;
            border-radius: 3px;
            position: relative;
            box-shadow: 0 5px 15px black;
        }

        .modal__form {
            margin-left: auto;
            margin-top: auto;
            padding-bottom: 80px;
            padding-top: 40px;
            text-align: center;
        }

        .modal__close-button {
            background: #ff0000;
            height: 30px;
            width: 30px;
            display: flex;
            justify-content: center;
            align-items: center;
            border: none;
            position: absolute;
            right: 0;
            top: 0;
            background: none;
            cursor: pointer;
            transition: .3s;
            outline: none;
        }

        .modal__close-button:hover {
            transition: .3s;
            transform: rotate(360deg);
        }

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

        .panel {
            list-style: none;
            margin-bottom: 9px;
            margin-left: 0px;
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

    </style>

</head>
<body>

<div class="header">
    <a id="mail-role"></a>
</div>

<div class="main">

    <div class="sidenav">
        <a href="/list" class="is-active">Admin</a> <br>
        <a href="/user">User</a>
    </div>

    <div class="central">

        <h2>Admin panel</h2>

        <div class="panel">
            <a href="/list" class="active">List of users</a>
            <a href="/admin/add">Add user</a>
            <a href="/kids">Age list</a>
        </div>

        <div>
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Login</th>
                    <th scope="col">Password</th>
                    <th scope="col">Age</th>
                    <th scope="col">Roles</th>
                    <th scope="col">Edit</th>
                    <th scope="col">Delete</th>
                </tr>
                <thead>
                <tbody id="table">
                </tbody>
            </table>
        </div>
    </div>
</div>


<div class="modal">
    <div class="modal__content">
        <div class="modal__form">
            <button class="modal__close-button close" type="button" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <form id="user-edit">
                <input id="id-input" type="hidden" name="id">
                <label>Логин</label> <br/>
                <input id="login-input" type="text" name="login"> <br/><br/>
                <label>Пароль</label><br/>
                <input id="password-input" type="text" name="password"> <br/><br/>
                <label>Возраст</label><br/>
                <input id="age-input" type="text" name="age"> <br/><br/>

                <button id="modal__edit-button" class="btn btn-primary"/>
                Сохранить изменения</button>
            </form>
        </div>
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
    $(function () {

        function mainAjaxForm() {
            $.ajax({
                type: 'GET',
                data: {},
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                url: "http://localhost:8081/list/rest",
                success: function (data) {
                    $('#table').empty();

                    data.forEach(element => {

                        var row = document.createElement("tr");
                        var id = document.createElement('td');
                        var login = document.createElement('td');
                        var password = document.createElement('td');
                        var age = document.createElement('td');
                        var role = document.createElement('td');
                        var editBtn = document.createElement('td');
                        var deleteBtn = document.createElement('td');

                        var index = element.id;

                        id.innerHTML = element.id;
                        login.innerHTML = element.login;
                        password.innerHTML = element.password;
                        age.innerHTML = element.age;
                        role.innerHTML = element.role;

                        editBtn.innerHTML = '<button class="btn btn-primary callback-button" data-id="' + index + '">Edit</button>';

                        $('.callback-button').click(function () {
                            var $this = $(this),
                                id = $this.attr('data-id');
                            $.ajax({
                                type: 'GET',
                                data: {},
                                contentType: "application/json; charset=utf-8",
                                dataType: "json",
                                url: "http://localhost:8081/admin/edit/rest/" + id + "",
                                success: function (data) {
                                    $('#id-input').attr('value', data.id);
                                    $('#login-input').attr('value', data.login);
                                    $('#password-input').attr('value', data.password);
                                    $('#age-input').attr('value', data.age);
                                }
                            });
                            $('.modal').addClass('modal_active');
                        });

                        deleteBtn.innerHTML = '<button class="btn btn-danger delete-user-button" data-id="' + index + '" >Delete</button>';

                        $('.delete-user-button').click(function () {
                            var $this = $(this),
                                id = $this.attr('data-id');
                            $.ajax({
                                type: 'DELETE',
                                data: {},
                                contentType: "application/json; charset=utf-8",
                                dataType: "json",
                                url: "http://localhost:8081/admin/delete/rest/" + id + "",
                                success: function (data) {
                                    mainAjaxForm();
                                }
                            });
                        });

                        row.appendChild(id);
                        row.appendChild(login);
                        row.appendChild(password);
                        row.appendChild(age);
                        row.appendChild(role);
                        row.appendChild(editBtn);
                        row.appendChild(deleteBtn);

                        $('#table').append(row);
                    });
                },

                error: function (errorMessage) {
                    console.log(errorMessage);
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

                    aTeg.innerHTML = 'User: ' + login + '; role: ' + role;

                }
            });
        }


        mainAjaxForm();


        $('#modal__edit-button').click(function (event) {
            event.preventDefault();
            var form = $('#user-edit');
            var formInput = form[0];
            console.log(formInput);
            sendAjaxForm(formInput);
        });

        function sendAjaxForm(form) {
            $.ajax({
                type: 'PUT',
                data: JSON.stringify({
                    id: +form[0].value,
                    login: form[1].value,
                    password: form[2].value,
                    age: +form[3].value
                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                url: 'http://localhost:8081/admin/edit/rest',
                success: function (form) {
                    mainAjaxForm();
                    $('.modal').removeClass('modal_active');
                }
            });
        }

        $('.modal__close-button').click(function () {
            $('.modal').removeClass('modal_active');
        });

    });
</script>

</body>
</html>