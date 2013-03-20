var users_tpl;

$.get('inc/templates.html', function(templates) {
    users_tpl = $(templates).filter('#users-tpl').html();
});

loadUsers('');

$('#search').keyup(function () {
    loadUsers($('#search').val())
});

$('#statsMenu').click(function () {

});

function loadUsers(input) {
    $.getJSON('user.json?input=' + input, function (json) {
        $('#items').html(Mustache.to_html(users_tpl, json));
    });
}
