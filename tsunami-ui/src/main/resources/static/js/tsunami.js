
Highcharts.setOptions({
    global: {
        useUTC: false
    }
});


Highcharts.chart('tsunami', {
    chart: {
 /*

 http://jsfiddle.net/gh/get/library/pure/highcharts/highcharts/tree/master/samples/highcharts/series-label/line-chart

        borderColor: '#4572A7',
        plotBorderWidth: 1,
        plotBorderColor: '#CCCCCC',
        backgroundColor: '#222',
        plotShadow: true,*/
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
        text: 'Data input from Tsunamis Kafka Topic'
    },
/*    plotOptions: {
        series: {

            lineWidth: 2,
            shadow: true,
            marker: {
                enabled: false,
                symbol: 'circle',
                states: {
                    hover: {
                        enabled: true
                    }
                }
            }
        }
    },*/
    data: {
        //rowsURL: 'https://demo-live-data.highcharts.com/time-rows.json',
        rowsURL: 'http://localhost:8080/alerts',
        firstRowAsNames: true,
        enablePolling: true,
        name: "waves"
    }
});
