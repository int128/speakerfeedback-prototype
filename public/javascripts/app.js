$(function () {
    ws.onmessage = function(message) {
        var itemId = message.data;
        $('.poll .item .count').each(function () {
            if ($(this).data('itemId') == itemId) {
                $(this).text(parseInt($(this).text()) + 1);
            }
        });
    };
    $('.poll .item button').click(function(){
        var itemId = $(this).data('itemId');
        ws.send(itemId);
    });
});
