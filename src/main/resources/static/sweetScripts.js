function deleteSweetAndRedirectWithConfirmationCheck(sweetId) {
    if (confirm("Are you sure?")) {
        deleteSweetAndRedirect(sweetId);
    }
    return false;
}

function deleteSweetAndRedirect(sweetId) {
    let deleteUrl = "/rest/sweet/" + sweetId;
    $.ajax({
        url: deleteUrl,
        type: "DELETE",
    });
    window.location.replace("/sweets")
}