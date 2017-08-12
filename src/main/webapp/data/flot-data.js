Highcharts.setOptions({
    global: {
        useUTC: false
    }
});
var config;
var miners;
var miner;
var decLabel = "";
var ethLabel = "";
var decRejects = 0;
var ethRejects = 0;
var ethShares = 0;
var decShares = 0;
var totalEthHash = 0;
var totalDecHash = 0;
var minerEthHash = [];
var minerDecHash = [];
var altCoin;
var nicehash = "https://api.nicehash.com/api";
var statsex = "stats.provider.ex&addr=";
var stats = "stats.provider&addr=";
var algos = "multialgo.info"
var algorithms;
$(document).ready(function () {
    $.ajax({
        url: nicehash,
        dataType: 'jsonp',
        success: function (data) {
            console.log(data);
        },
        error: function (xhr, ajaxOptions, thrownError) {

        }
    });
    setInterval(function () {
    }, 15000);
});

function getMiningData() {
    totalEthHash = 0;
    totalDecHash = 0;
    ethShares = 0;
    decShares = 0;
    ethRejects = 0;
    decRejects = 0;
    minerEthHash = [];
    minerDecHash = [];
    minerUptime = [];

    if (miners !== undefined) {
        document.getElementById("uptimes").innerHTML = "";
        var table = "<table>";
        for (var x = 0; x < miners.length; x++) {
            $.ajax({
                url: "../miner/MinerService/getMinerStats/" + miners[x].name,
                async: false,
                success: function (data) {
                    miner = data;
                    if (miner.minerResult !== null) {
                        totalEthHash += parseFloat(miner.minerResult.totalEthHashrate);
                        ethShares += parseFloat(miner.minerResult.totalEthShares);
                        ethRejects += parseFloat(miner.minerResult.totalEthRejects);
                        if (miner.minerResult.totalDecHashrate !== null) {
                            totalDecHash += parseFloat(miner.minerResult.totalDecHashrate);
                            decShares += parseFloat(miner.minerResult.totalDecShares);
                            minerEthHash.push(parseFloat(miner.minerResult.totalEthHashrate));
                            minerDecHash.push(parseFloat(miner.minerResult.totalDecHashrate));
                            decRejects += parseFloat(miner.minerResult.totalDecRejects);
                            table += "<tr><td>Uptime: " + miner.name + "&nbsp;&nbsp;</td><td>=&gt; " + miner.minerResult.uptime + "&nbsp;&nbsp;</td><td>GPU's =&gt; " + miner.minerResult.gpuStats.length + "</td></tr>";
                            for (var i = 0; i < miner.minerResult.gpuStats.length; i++) {
                                table += "<tr><td>GPU: " + i + "</td><td>Temp: " + miner.minerResult.gpuStats[i].gpuTemp + "&deg;C</td><td>Fan " + miner.minerResult.gpuStats[i].gpuFanSpeed + "%</td></tr>"
                            }
                        } else {
                            totalDecHash += 0;
                            minerEthHash.push(0);
                            minerDecHash.push(0);
                        }
                    }

                },
                error: function (xhr, ajaxOptions, thrownError) {
                    miner = undefined;
                }
            });

        }
        table += "</table>";
        document.getElementById("uptimes").innerHTML = table;
        //document.getElementById("ethHashrate").innerHTML = parseFloat(Math.round(totalEthHash * 1000) / 1000).toFixed(3) + "<br/>MH/s";
        //document.getElementById("decHashrate").innerHTML = parseFloat(Math.round(totalDecHash * 1000) / 1000).toFixed(3) + "<br/>MH/s";
        //document.getElementById("ethRejects").innerHTML = ethShares + " Shares<br/>" + ethRejects + " Rejects";
        //document.getElementById("decRejects").innerHTML = decShares + " Shares<br/>" + decRejects + " Rejects";
    }
}
// Create the chart
var ethHashrates = Highcharts.stockChart('flot-ethereum', {
    chart: {
        events: {
            load: function () {
                $.ajax({
                    url: '../miner/MinerService',
                    //async: false,
                    success: function (data) {
                        config = data;
                        altCoin = config.altcoin;
                        miners = config.miners;
                        var c;
                        for (c = 0; c < miners.length; c++) {
                            ethHashrates.addSeries({
                                name: miners[c].name,
                                data: function () {
                                    var setupData = [],
                                            time = (new Date()).getTime(),
                                            i;
                                    for (i = -86400; i <= 0; i += 1) {
                                        setupData.push([time + i * 1000, 0]);
                                    }
                                    return setupData;
                                }()
                            });
                        }
                    }
                });
                var series = this.series;
                var totalSeries = this.series[0];
                setInterval(function () {
                    getMiningData();
                    var x = (new Date()).getTime();
                    totalSeries.addPoint([x, totalEthHash], true, true);
                    for (var k = 1; k < series.length - 1; k++) {
                        var y = minerEthHash[k - 1];//totalEthHash;
                        series[k].addPoint([x, y], true, true);
                    }
                }, 1000);
            }
        }
    },
    rangeSelector: {
        buttons: [{
                count: 1,
                type: 'minute',
                text: '1m'
            }, {
                count: 5,
                type: 'minute',
                text: '5m'
            }, {
                count: 30,
                type: 'minute',
                text: '30m'
            }, {
                count: 1,
                type: 'hour',
                text: '1hr'
            }, {
                count: 6,
                type: 'hour',
                text: '6hr'
            }, {
                count: 12,
                type: 'hour',
                text: '12hr'
            }, {
                type: 'all',
                text: 'All'
            }],
        inputEnabled: false,
        selected: 0
    },
    title: {
        text: 'Ethereum Hashrates'
    },
    legend: {
        enabled: true
    },
    series: [{
            name: 'Total Ethereum Hashrate',
            data: (function () {
                // generate an array of random data
                var data = [],
                        time = (new Date()).getTime(),
                        i;
                for (i = -86400; i <= 0; i += 1) {
                    data.push([time + i * 1000, 0]);
                }
                return data;
            }())
        }]
});

var decHashrates = Highcharts.stockChart('flot-decred', {
    chart: {
        events: {
            load: function () {
                var y = 0;
                $.ajax({
                    url: '../miner/MinerService',
                    //async: false,
                    success: function (data) {
                        config = data;
                        miners = config.miners;
                        var c;
                        for (c = 0; c < miners.length; c++) {
                            decHashrates.addSeries({
                                name: miners[c].name,
                                data: function () {
                                    var setupData = [],
                                            time = (new Date()).getTime(),
                                            i;
                                    for (i = -86400; i <= 0; i += 1) {
                                        setupData.push([time + i * 1000, 0]);
                                    }
                                    return setupData;
                                }()
                            });
                        }
                    }
                });
                var series = this.series;
                var totalSeries = this.series[0];
                setInterval(function () {
                    getMiningData();
                    var x = (new Date()).getTime();
                    totalSeries.addPoint([x, totalDecHash], true, true);
                    for (var k = 1; k < series.length - 1; k++) {
                        var y = minerDecHash[k - 1];//totalEthHash;
                        series[k].addPoint([x, y], true, true);
                    }

                    decHashrates.setTitle({text: altCoin + " Hashrates"});
                }, 1000);
            }
        }
    },
    rangeSelector: {
        buttons: [{
                count: 1,
                type: 'minute',
                text: '1m'
            }, {
                count: 5,
                type: 'minute',
                text: '5m'
            }, {
                count: 30,
                type: 'minute',
                text: '30m'
            }, {
                count: 1,
                type: 'hour',
                text: '1hr'
            }, {
                count: 6,
                type: 'hour',
                text: '6hr'
            }, {
                count: 12,
                type: 'hour',
                text: '12hr'
            }, {
                type: 'all',
                text: 'All'
            }],
        inputEnabled: false,
        selected: 0
    },
    title: {
        text: ' Hashrates',
    },
    legend: {
        enabled: true
    },
    series: [{
            name: 'Hashrate',
            data: (function () {
                // generate an array of random data
                var data = [],
                        time = (new Date()).getTime(),
                        i;
                for (i = -86400; i <= 0; i += 1) {
                    data.push([time + i * 1000, 0]);
                }
                return data;
            }())
        }]
});

var ethSpeed = Highcharts.chart('ethHashrate', {
    chart: {
        events: {
            load: function () {
                setInterval(function () {
                    var point = ethSpeed.series[0].points[0];
                    point.update(Math.round(totalEthHash * 1000) / 1000);
                }, 1000);
            }
        },
        type: 'gauge',
        plotShadow: false
    },
    title: {
        text: 'Ethereum Hashrate',
    },
    // the value axis
    yAxis: {
        min: 0,
        max: 120,
        minorTickInterval: 'auto',
        minorTickWidth: 1,
        minorTickLength: 10,
        minorTickPosition: 'inside',
        minorTickColor: '#FFF',
        tickPixelInterval: 30,
        tickWidth: 2,
        tickPosition: 'inside',
        tickLength: 15,
        tickColor: '#FFF',
        labels: {
            step: 2,
            rotation: 'auto'
        },
        title: {
            text: 'MH/s'
        },
        plotBands: [{
                from: 0,
                to: 60,
                color: '#55BF3B' // green
            }, {
                from: 60,
                to: 90,
                color: '#DDDF0D' // yellow
            }, {
                from: 90,
                to: 120,
                color: '#DF5353' // red
            }]
    },
    series: [{
            name: 'Ethereum Hashrate',
            dial: {
                backgroundColor: "#efefef",
                baseLength: "70%",
                baseWidth: 3,
                borderColor: "#2b908f",
                borderWidth: 1,
                radius: "80%",
                rearLength: "10%",
                topWidth: 1
            },
            data: [0],
            tooltip: {
                valueSuffix: ' MH/s'
            }
        }]
});

var decSpeed = Highcharts.chart('decHashrate', {
    chart: {
        events: {
            load: function () {
                setInterval(function () {
                    var point = decSpeed.series[0].points[0];
                    decSpeed.series[0].name = altCoin + " Hashrate";
                    point.update(Math.round(totalDecHash * 1000) / 1000);
                    decSpeed.setTitle({text: altCoin + " Hashrate"});
                }, 1000);
            }
        },
        type: 'gauge',
    },
    title: {
        text: ' Hashrate'
    },
    // the value axis
    yAxis: {
        min: 0,
        max: 2600,
        minorTickInterval: 'auto',
        minorTickWidth: 1,
        minorTickLength: 10,
        minorTickPosition: 'inside',
        minorTickColor: '#FFF',
        tickPixelInterval: 30,
        tickWidth: 2,
        tickPosition: 'inside',
        tickLength: 15,
        tickColor: '#FFF',
        labels: {
            step: 2,
            rotation: 'auto'
        },
        title: {
            text: 'MH/s'
        },
        plotBands: [{
                from: 0,
                to: 1000,
                color: '#55BF3B' // green
            }, {
                from: 1000,
                to: 1600,
                color: '#DDDF0D' // yellow
            }, {
                from: 1600,
                to: 2600,
                color: '#DF5353' // red
            }]
    },
    
    series: [{
            name: 'Hashrate',
            dial: {
                backgroundColor: "#efefef",
                baseLength: "70%",
                baseWidth: 3,
                borderColor: "#2b908f",
                borderWidth: 1,
                radius: "80%",
                rearLength: "10%",
                topWidth: 1
            },
            data: [0],
            tooltip: {
                valueSuffix: ' MH/s'
            }
        }]
});

var ethRejectChart = Highcharts.chart('ethRejects', {
    chart: {
        events: {
            load: function () {
                setInterval(function () {
                    var seriesData = [];
                    $.each(ethRejectChart.series[0].data, function (i, item) {
                        if (item.name === 'Ethereum Shares') {
                            item.y = ethShares;
                        } else if (item.name === 'Ethereum Rejects') {
                            item.y = ethRejects;
                        }
                        seriesData.push(item);
                    });
                    ethRejectChart.series[0].setData(seriesData, true);
                }, 1000);
            }
        },
        name: "ethRejectsChart",
        type: 'pie'
    },
    title: {
        text: 'Ethereum Shares/Rejects Ratio'
    },
    series: [{
            name: 'Ethereum Shares/Rejects',
            colorByPoint: true,
            data: [{
                    name: 'Ethereum Shares',
                    y: 100,
                    sliced: true,
                    selected: true
                }, {
                    name: 'Ethereum Rejects',
                    y: 0
                }]
        }]
});

var decRejectChart = Highcharts.chart('decRejects', {
    chart: {
        events: {
            load: function () {
                setInterval(function () {
                    var seriesData = [];
                    $.each(decRejectChart.series[0].data, function (i, item) {
                        if (item.name === 'Shares' || item.name === altCoin + ' Shares') {
                            item.y = decShares;
                            item.name = altCoin + ' Shares';
                        } else if (item.name === 'Rejects' || item.name === altCoin + ' Rejects') {
                            item.y = decRejects;
                            item.name = altCoin + ' Rejects';
                        }
                        seriesData.push(item);
                    });
                    ethRejectChart.series[0].setData(seriesData, true);
                    decRejectChart.setTitle({text: altCoin + " Shares/Rejects Ratio"});
                }, 1000);
            }
        },
        name: "decRejectsChart",
        type: 'pie'
    },
    title: {
        text: ' Shares/Rejects Ratio'
    },
    series: [{
            name: 'Shares/Rejects',
            colorByPoint: true,
            data: [{
                    name: 'Shares',
                    y: 100,
                    sliced: true,
                    selected: true
                }, {
                    name: 'Rejects',
                    y: 0
                }]
        }]
});