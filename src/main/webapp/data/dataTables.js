var init = false;
var zarPerBTC;
$(document).ready(function () {
    updateBitcoinRates();
    setInterval(function(){
       updateBitcoinRates(); 
    },60000);
    setInterval(function(){
        $.ajax({
            url: '../nicehash/NicehashService/getMiningStats',
            success: function(data){
                var table = "<table class='table table-striped table-bordered dataTable no-footer'><thead><tr><th>Algorithm</th><th>Accepted Speed</th><th>Profitability</th><th>Unpaid Balance</th></tr></thead>";
                var totalProf=0.0;
                var totalUnpaid=0.0;
                for(var i = 0; i < data.result.current.length; i++){
                    var style;
                    if(i % 2 === 0){
                        style = "even";
                    }else {
                        style = "odd";
                    }
                    
                    totalProf+=parseFloat(data.result.current[i].profitability)*parseFloat(data.result.current[i].speed.a);
                    totalUnpaid+=parseFloat(data.result.current[i].balance);
                    table += "<tr class='"+style+"'><td>" + data.result.current[i].name+"</td><td>" + data.result.current[i].speed.a + " " + data.result.current[i].suffix + "</td><td>" + Math.round((parseFloat(data.result.current[i].profitability)*parseFloat(data.result.current[i].speed.a))*10000000)/10000000 + " BTC/day</td><td>" + data.result.current[i].balance + " BTC</td>";
                };
                table+="<tr><td></td><td></td><td>" + Math.round(totalProf*10000000)/10000000 + " BTC/day</br>ZAR " + Math.round(((Math.round(totalProf*10000000)/10000000)*zarPerBTC)*100)/100 + " /day</td><td>" + Math.round(totalUnpaid*10000000)/10000000 + " BTC</br>ZAR " + Math.round(((Math.round(totalUnpaid*10000000)/10000000)*zarPerBTC)*100)/100 + "</td>";
                table+="</table>";
                document.getElementById("miningStats").innerHTML = table;
            }
        });
    }, 5000);
    function updateBitcoinRates(){
        $.ajax({
            url: "https://api.coindesk.com/v1/bpi/currentprice/ZAR.json",
            success: function(data){
                zarPerBTC = JSON.parse(data).bpi.ZAR.rate_float;
            }
        });
    }
    setInterval(function () {
        $.ajax({
            url: '../nicehash/NicehashService/getWorkers',
            //async: false,
            success: function (data) {
                if (data.results.length > 0) {
                    if (!init) {
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
                        $('#nicehashWorkers').DataTable().ajax.reload();
                        $('#nicehashWorkers').DataTable().draw();
                    }
                }
            }
        });
    }, 5000);
});


