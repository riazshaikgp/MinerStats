var init = false;
$(document).ready(function () {
    setInterval(function () {

        $.ajax({
            url: '../nicehash/NicehashService/getWorkers',
            //async: false,
            success: function (data) {
                console.log(data);
                if (data.results.length > 0) {
                    if (!init) {
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
                        
                        $('#nicehashWorkers').DataTable({
                            ajax: {
                                url: '../nicehash/NicehashService/getWorkers',
                                dataSrc: "results"
                            },
                            columns: [
                                {data: 'name'},
                                {data: 'algoName'},
                                {data: 'speeds.a'},
                                {data: 'speeds.rs'},
                                {data: 'difficulty'},
                                {data: 'time'}
                            ]
                        });

                        init = true;
                    } else {
                        $('#algorithms').DataTable().ajax.reload();
                        $('#algorithms').DataTable().draw();
                        $('#nicehashWorkers').DataTable().ajax.reload();
                        $('#nicehashWorkers').DataTable().draw();
                    }
                }
            }
        });
    }, 15000);
});


