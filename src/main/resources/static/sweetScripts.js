function deleteSweetAndRedirectWithConfirmationCheck(sweetId) {
    if (confirm("Are you sure?")) {
        deleteSweetAndRedirect(sweetId);
    }
    return false;
}

function deleteSweetAndRedirect(sweetId) {
    let deleteUrl = "/sweet/" + sweetId;
    $.ajax({
        url: deleteUrl,
        type: "DELETE",
    }).done(function () {
        window.location.replace("/sweets")
    });

}