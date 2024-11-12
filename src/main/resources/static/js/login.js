$('#username').on('blur', function() {
    const username = $(this).val();
    if (username) {
        console.log("Fetching salt for username: " + username);  // Debug log
        $.ajax({
            url: '/getSalt/' + username,
            type: 'GET',
            dataType: 'json',  // Ensure the response is parsed as JSON
            success: function(data) {
                console.log("Response data: ", data);  // Debug log
                if (data.salt) {
                    $('#salt').val(data.salt);  // Set salt in the hidden input field
                    console.log("Salt fetched: " + data.salt);
                } else {
                    console.log("No salt found for the username.");
                }
            },
            error: function(xhr, status, error) {
                console.error("Error fetching salt:", status, error);  // Log more details in case of error
                alert("Error fetching salt");
            }
        });
    }
});
