<#import "ui.ftl" as ui/>
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
            z-index: 1;
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
            margin-left: 150px; /* Same as the width of the sidenav */
            margin-top: 100px; /*More then width of the header */
            font-size: 14px; /* Increased text to enable scrolling */
            padding: 0px 10px;
        }

        .central {
            margin-left: 10px;
            border-radius: 0px 0px 4px 4px;
        }

        .add-form {
            margin-left: auto;
            margin-top: auto;
            padding-bottom: 80px;
            padding-top: 40px;
            text-align: center;
            background-color: #eae9e9;
        }

        body {
            background-color: #ffffff;
        }

        label {
            font-weight: bold;
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
            <a href="/list">List of users</a>
            <a href="/admin/add" class="active">Add user</a>
            <a href="/kids">Age list</a>
        </div>
        <div class="add-form">
            <form id="user-save">
                <label>Логин</label> <br/>
                <input id="login-input" type="text" name="login" > <br/><br/>
                <label>Пароль</label><br/>
                <input id="password-input" type="text" name="password"  > <br/><br/>
                <label>Возраст</label><br/>
                <input id="age-input" type="text" name="age" > <br/><br/>
                <label>Профиль</label><br/>
                <input id="vk-input" type="text" name="profileId" > <br/><br/>

                <button id="modal__save-button" class="btn btn-primary"/>
                Сохранить</button>
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
        mailRoleString()


        $('#modal__save-button').click(function (event) {
            event.preventDefault();
            var form = $('#user-save');
            var formInput = form[0];
            console.log(formInput);
            sendAjaxForm(formInput);
        });

        function sendAjaxForm(form) {
            $.ajax({
                type: 'POST',
                data: JSON.stringify({
                    login: form[0].value,
                    password: form[1].value,
                    age: +form[2].value,
                    profileId: form[3].value
                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                url: 'http://localhost:8081/save/rest',
                success: function (form) {
                    console.log(form);
                }
            });
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

                    aTeg.innerHTML = 'User: ' + login;

                }
            });
        }

    });
</script>


</div>
</body>
</html>