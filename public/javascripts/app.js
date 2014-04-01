$(function () {
    ws.onmessage = function(message) {
        $('<li />').text(message.data).appendTo('#messages')
    };
    $('#feedback').submit(function(){
        ws.send($('#message').val());
        $('#message').val("").focus();
        return false;
    });
});
