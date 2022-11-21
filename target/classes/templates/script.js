$(function () {


    $.ajax({
        type: 'GET',
        data: {},
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        url: "http://localhost:8081/list/rest",
        success: function (data) {
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
                            $('#id-input').attr({value: data.id, type: 'hidden'});
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
                            alert(data);
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


    $('.modal__close-button').click(function () {
        $('.modal').removeClass('modal_active');
    });


    $('#modal__edit-button').click(function () {
        var form = $('#user-edit');
        var dataForm = form.serialize();
        $.ajax({
            type: 'POST',
            data: {user: dataForm},
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            url: "http://localhost:8081/admin/edit/rest",
            success: function (data) {
                alert(data);
            }
        });
    });


});
