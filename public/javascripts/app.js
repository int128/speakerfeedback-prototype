$(function () {
    ws.onmessage = function(message) {
        var e = message.data.split(/,/);
        var itemId = e[0];
        var count = e[1];
        $('.poll .item .count').each(function () {
            if ($(this).data('itemId') == itemId) {
                $(this).text(count);
            }
        });
    };
    $('.poll .item button').click(function(){
        var itemId = $(this).data('itemId');
        ws.send(itemId);
    });
});
