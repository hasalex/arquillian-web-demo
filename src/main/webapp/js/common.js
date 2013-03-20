$.get('inc/navbar.html', function(result) {
    $('.navbar').html($(result).html());
    $("[href='" + getPage() + "']").parent().addClass("active");
});

function getPage() {
    var loc = window.location;
    return loc.pathname.substring(loc.pathname.lastIndexOf('/') + 1, loc.length);
}

function getPageWithoutExtension() {
    var page = getPage();
    return page.substring(0, page.lastIndexOf('.'));
}

Array.prototype.chunk = function(chunkSize) {
    var array=this;
    return [].concat.apply([],
        array.map(function(elem,i) {
            return i%chunkSize ? [] : [array.slice(i,i+chunkSize)];
        })
    );
}
