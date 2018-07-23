$(document).ready(function () {
   $('.modal').modal();
   $(".dropdown-trigger").dropdown();
   $('.carousel').carousel();
});

$(window).resize(function () {
    $('.carousel').carousel();
});


var util = {
    showError: function (formName, message) {
        var form = $(document.forms[formName]);
        var errorDisplay = form.find('.error-display');
        if(!errorDisplay.length){
            alert(message);
            return;
        }
        errorDisplay.hide();
        errorDisplay.text(message);
        errorDisplay.slideDown();
    },

    submit: function (formName, callback) {
        var form = $(document.forms[formName]);
        var arr = form.serializeArray();
        var action = form.attr('action');
        var method = form.attr('method');


        action = action ? action : "#";

        method = method ? method.toLowerCase() : "get";

        var obj = {};

        var len = arr.length;

        for(var i = 0; i < len; i++){
            obj[arr[i].name] = arr[i].value;
        }

        if(method === 'get'){
            $.get(action, obj, function (data) {
                if(!data['result'])
                    util.showError(formName, data['message']);
                else{
                    if(callback)
                        callback(data);
                    else
                        window.location.reload();
                }
            });
        }else if(method === 'post'){

            $.post(action, obj, function (data) {
                console.log(data);
                if(!data['result'])
                    util.showError(formName, data['message']);
                else{
                    if(callback)
                        callback(data);
                    else
                        window.location.reload();
                }
            });
        }
    },

    logout: function () {
        $.get('/logout', function () {
            window.location.reload();
        });

    }
};