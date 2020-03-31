<template id="temperature-component">
    <div>
        <filter-component></filter-component>
        <chart-component chart-type="line" chart-height="400" :chart-options="this.options" :chart-series="this.series" ></chart-component>
    </div>
</template>

<script>
    Vue.component('temperature-component', {
        components: {},
        template: `#temperature-component`,
        props: {
            fromTime: Number,
            toTime: Number
        },
        data: function () {
            return {
                options: {
                    chart: {
                        id: 'temperature',
                        type: 'line',
                        height: 400
                    },
                    colors: ['#008FFB'],
                    yaxis: {
                        labels: {
                            minWidth: 40
                        }
                    },
                    xaxis: {
                        type: 'datetime',
                        min: this.fromTime,
                        max: this.toTime,
                        dateTimeUTC: false,
                        labels: {
                            datetimeFormatter: {
                                year: 'yyyy',
                                month: 'MMM yyyy',
                                day: 'dd MMM',
                                hour: 'HH:mm',
                                minute: 'HH:mm:ss'
                            }
                        }
                    },
                },
                series: []
            }
        },
        mounted: function () {
            if(this.fromTime != null && this.toTime != null) {
                this.loadWithFilter(this, this.fromTime, this.toTime)
            } else {
                this.load(this)
            }
        },
        methods: {
            load(component) {
                axios.get("http://localhost:7000/average-temperature")
                    .then(response => {
                        component.series = [];
                        component.series.push({
                            data: response.data.map(object => [object["timestamp"], object["temperature"]])
                        });
                    });
            }, loadWithFilter(component, timeFrom, timeTo) {
                axios.get(
                    "http://localhost:7000/average-temperature/time",
                    { params: { from: Math.round(timeFrom/1000), to: Math.round(timeTo/1000) } }
                ).then(response => {
                        component.series = [];
                        component.series.push({
                            data: response.data.map(object => [object["timestamp"], object["temperature"]])
                        });
                    });
            }
        }
    });
</script>

<style>

</style>