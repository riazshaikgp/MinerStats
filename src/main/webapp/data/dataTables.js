$(document).ready(function() {
    $('#algorithms').DataTable({
            ajax: {
                url: '../nicehash/NicehashService/getAlgos',
                dataSrc: 'result.simplemultialgo'
            },
            columns: [
                {data: 'name'},
                {data: 'port'},
                {data: 'paying'},
                {data: 'algo'}
            ]
        });
} );


