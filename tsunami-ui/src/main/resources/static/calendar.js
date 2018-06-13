
Highcharts.setOptions({
    global: {
        useUTC: false
    }
});

/*Highcharts.chart('container', {
    chart: {
        type: 'spline'
    },
    title: {
        text: 'Live Data (CSV)'
    },

    subtitle: {
        text: 'Data input from a remote CSV file'
    },

    data: {
        csvURL: 'https://demo-live-data.highcharts.com/time-data.csv',
        enablePolling: true
    }
});*/


Highcharts.chart('container', {
    chart: {
        type: 'spline'
    },
    title: {
        text: 'Tsunami Data'
    },

    yAxis: {
        type: 'logarithmic',
        title: {
            enabled: true,
            text: 'Wave height',
            style: {
                fontWeight: 'normal'
            }
        }
        //minorTickInterval: 0.1
    },

    xAxis: {
        title: {
            enabled: true,
            text: 'Year-Month',
            style: {
                fontWeight: 'normal'
            }
        }
    },

    subtitle: {
        text: 'Data input from Kafka Topic'
    },


    data: {
        //rowsURL: 'https://demo-live-data.highcharts.com/time-rows.json',
        rowsURL: 'http://localhost:8080/data',
        firstRowAsNames: true,
        enablePolling: true
    }

  /*  data: {
        csvURL: 'https://demo-live-data.highcharts.com/time-data.csv',
        enablePolling: true
    }*/
});


/*
Highcharts.chart('container', {
    chart: {
        type: 'spline',
        animation: Highcharts.svg, // don't animate in old IE
        marginRight: 10,
        events: {
            load: function () {

                // set up the updating of the chart each second
                var series = this.series[0];
                setInterval(function () {
                    var x = (new Date()).getTime(), // current time
                        y = Math.random()*10;
                    series.addPoint([x, y], true, true);
                }, 1000);
            }
        }
    },
    title: {
        text: 'Tsunami Stream'
    },
    xAxis: {
        type: 'datetime',
        tickPixelInterval: 150
    },
    yAxis: {
        title: {
            text: 'Value'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    },
    tooltip: {
        formatter: function () {
            return '<b>' + this.series.name + '</b><br/>' +
                Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                Highcharts.numberFormat(this.y, 2);
        }
    },
    legend: {
        enabled: false
    },
    exporting: {
        enabled: false
    },

/!*    series: [{
        data: elevationData,
        lineColor: Highcharts.getOptions().colors[1],
        color: Highcharts.getOptions().colors[2],
        fillOpacity: 0.5,
        name: 'Elevation',
        marker: {
            enabled: false
        },
        threshold: null
    }]*!/

    series: [{
        name: 'Random data',
        lineColor: Highcharts.getOptions().colors[1],
        color: Highcharts.getOptions().colors[7],
        fillOpacity: 0.5,
        data: (function () {
            // generate an array of random data
            var data = [],
                time = (new Date()).getTime(),
                i;

            for (i = -19; i <= 0; i += 1) {
                data.push({
                    x: time + i * 1000,
                    y: Math.random()
                });
            }
            return data;
        }())
    }]
});*/
