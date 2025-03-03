document.addEventListener('DOMContentLoaded', () => {
    // Initialize DataTable
    let table = new DataTable('#monitor-log-table', {
        columns: [
            { data: 'alarmName', title: 'Alarm Name' },
            { data: 'alarmType', title: 'Alarm Type' },
            { data: 'alarmLevel', title: 'Alarm Level' },
            { data: 'sqlString', title: 'Sql String' },
            { data: 'alarmValue', title: 'Values' },
            {
                data: 'alarmTime',
                title: 'Alarm Time',
                render: function (data, type, row) {
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

    // Initial load
    reloadDataTable();

    // Reload every 5 seconds
    setInterval(reloadDataTable, 5000);
});
