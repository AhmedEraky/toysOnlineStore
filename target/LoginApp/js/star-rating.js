
// Up<a href="https://www.jqueryscript.net/time-clock/">date</a> the rating by setting a value via javascript.
// The method accepts a rating value as a parameter.
$('#input-id').rating('update', 3);

// Example: Call the method below in rating.change event to disable the rating and
// hide the clear button.
$('#input-id').rating('refresh', {disabled: true, showClear: false, showCaption: true});

// Reset the rating.
$('#input-id').rating('reset');

// Clear the rating.
$('#input-id').rating('clear');

// Destroy the rating.
$('#input-id').rating('destroy');

// Re-creates the rating (after a destroy).
$('#input-id').rating('create');