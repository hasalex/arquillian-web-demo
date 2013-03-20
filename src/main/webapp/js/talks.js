var result;
var page = 0;
var talk_tpl;

$.get('inc/templates.html', function(templates) {
    talk_tpl = $(templates).filter('#talks-tpl').html();
    waiting_tpl = $(templates).filter('#waiting-tpl').html();
    loadTalks();
});

$('#prev').click(function () {
    if (page > 0) {
        page--;
        $('#data').html(Mustache.to_html(talk_tpl, result[page]));
    }
});
$('#next').click(function () {
    if (page < result.length-1) {
        page++;
        $('#data').html(Mustache.to_html(talk_tpl, result[page]));
    }
});
$('#titleSearch').keyup(function () {
    loadTalks()
});
$('#speakerSearch').keyup(function () {
    loadTalks()
});

var previousXHR, counter=0;
function loadTalks() {
    if ($("#data-loaded")) $("#data-loaded").remove();
    var conf = getPageWithoutExtension();
    if (previousXHR) previousXHR.abort();
    var token = ++counter;
    $.getJSON(conf + '/talk.json?speakerSearch=' + $("#speakerSearch").val() + '&titleSearch=' + $("#titleSearch").val(), function (json) {
        if (token != counter) return;

        result = json.chunk(10);
        page = 0;
        $('#data').html(Mustache.to_html(talk_tpl, result[page]));
    });
}
