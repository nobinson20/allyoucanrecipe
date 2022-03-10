var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $("#btn-update").on('click', function () {
            _this.update();
        });
        $("#btn-delete").on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        var data = {
            ingredient : $("#ingredient").val(),
            author : $("#author").val(),
            recipes : $("#recipes").val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/posts',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('Posting successful!');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    update : function () {
        var data = {
            ingredient : $("#ingredient").val(),
            recipes : $("#recipes").val()
        };

        var id = $("#id").val();

        $.ajax({
            type : 'PUT',
            url : '/api/v1/posts/'+ id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('Update successful!');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },
    delete : function () {
        var id = $("#id").val();

        $.ajax({
            type : 'DELETE',
            url : '/api/v1/posts/'+ id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8'
        }).done(function () {
            alert('Post is deleted.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();