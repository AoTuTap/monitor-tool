// Declare table globally
let table;

document.addEventListener('DOMContentLoaded', () => {
    // Initialize DataTable
    table = new DataTable('#monitor-log-table', {
        select: {
            style: 'multi'
        },
        ordering: false,
        columns: [
            { data: 'alarmName', title: 'Alarm Name' },
            { data: 'alarmType', title: 'Alarm Type' },
            { data: 'alarmLevel', title: 'Alarm Level' },
            { data: 'sqlString', title: 'Sql String' },
            { data: 'alarmValue', title: 'Values' },
            {
                data: 'alarmTime',
                title: 'Alarm Time',
                render: function (data) {
                    if (!data) return ''; // Handle empty or null values

                    let date = new Date(data); // Convert timestamp to Date
                    return date.toLocaleString(); // Convert to readable format
                }
            },
            {
                data: 'id',
                title: 'Action',
                render: function (data) {
                    return `<a href="/monitor/delete/${data}" class="btn btn-warning text-decoration-none">Clear</a>`;
                }
            }
        ],
        columnDefs: [
            { targets: '_all', orderable: false } // Disable sorting for all columns
        ]
    });

    // Function to fetch and update data
    function reloadDataTable() {
        $.ajax({
            url: '/monitor/getLogs', // Replace with your API endpoint
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                if (!Array.isArray(data)) {
                    console.error('Invalid data format:', data);
                    return;
                }

                // Clear existing data and reload
                table.clear();
                table.rows.add(data).draw();
            },
            error: function (xhr, status, error) {
                console.error('Error fetching data:', error);
            }
        });
    }

    // Add row selection
    table.on('click', 'tbody tr', function (e) {
        e.currentTarget.classList.toggle('selected');
    });

    // Initial load
    reloadDataTable();

    // Reload every 5 seconds
    setInterval(reloadDataTable, 60000);
});

function reloadDataTable2() {
    $.ajax({
        url: '/monitor/getLogs', // Replace with your API endpoint
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            if (!Array.isArray(data)) {
                console.error('Invalid data format:', data);
                return;
            }

            // Clear existing data and reload
            table.clear();
            table.rows.add(data).draw();
        },
        error: function (xhr, status, error) {
            console.error('Error fetching data:', error);
        }
    });
}

// Add row selection
if (table) {
    table.on('click', 'tbody tr', function (e) {
        e.currentTarget.classList.toggle('selected');
    });
} else {
    console.error('DataTable is not initialized');
}



// Function to clear selected rows
function clearSelectedRows() {
    if (!table) {
        console.error('Table is not initialized yet.');
        return;
    }

    // Get all selected rows by targeting the rows with the "selected" class
    let selectedRows = $('#monitor-log-table tbody tr.selected');  // Select rows with the "selected" class

    // If no rows are selected
    if (selectedRows.length === 0) {
        console.log('No rows selected.');
        return;
    }

    // Collect data from the selected rows
    let ids = [];
    selectedRows.each(function () {
        // Get the row data using DataTables API
        let rowData = table.row(this).data();
        if (rowData && rowData.id) {
            ids.push(rowData.id);  // Get the ID from each selected row
        }
    });

    // Perform action on selected rows
    console.log('Selected rows:', ids);

    // You can now perform an AJAX call to delete the selected rows
    $.ajax({
        url: '/monitor/deleteSelected',  // Replace with your API endpoint
        type: 'POST',
        data: JSON.stringify(ids),
        contentType: 'application/json',
        success: function (response) {
            console.log('Rows deleted successfully');
            // Optionally reload the table after deletion
            reloadDataTable2();
            showDeleteToast();
        },
        error: function (xhr, status, error) {
            console.error('Error deleting rows:', error);
        }
    });
}


function showDeleteToast() {
    let toastEl = document.getElementById('deleteToast');
    let toast = new bootstrap.Toast(toastEl);

    // Reset progress bar
    let progressBar = document.getElementById('progressBar');
    progressBar.style.width = '100%';

    // Show the toast
    toast.show();

    // Animate progress bar
    let width = 100;
    let interval = setInterval(() => {
        width -= 2; // Reduce width gradually
        progressBar.style.width = width + '%';
        if (width <= 0) clearInterval(interval);
    }, 60); // Runs for ~3s
}