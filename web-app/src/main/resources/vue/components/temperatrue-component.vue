<template id="temperature-component">
    <el-card class="box-card" shadow="never">
        <chart-component chart-type="line" chart-height="400" :chart-options="this.options" :chart-series="this.series" ></chart-component>
    </el-card>
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
                        height: 600,
                    },
                    title: {
                        text: "Average Temperature",
                    },
                    yAxis: {
                        title: {
                            text: "Average Temperature"
                        }
                    },
                    xAxis: {
                        type: 'datetime',
                        min: this.fromTime,
                        max: this.toTime,
                        title: {
                            text: "Time"
                        }
                    },
                    series: []
                },
            }
        },
        created() {
            EventBus.$on('sensor_time_change', this.load);
        },
        beforeDestroy() {
            EventBus.$off('sensor_time_change', this.load)
        },
        mounted() {
            this.load(this.fromTime, this.toTime);
        },
        methods: {
            load(start, end) {
                let component = this;
                const params = {};
                let url = "http://localhost:7000/average-temperature";
                if (start != null && end != null) {
                    params['params'] = {
                        from: Math.round(start / 1000),
                        to: Math.round(end / 1000)
                    };
                    url = url + "/time";
                }
                axios.get(url, params)
                    .then(response => {
                        const series = {
                            id: "Temperature",
                            name: "Temperature",
                            data: response.data.map(object => [object["timestamp"], object["temperature"]])
                        };
                        component.options.xAxis.min = start;
                        component.options.xAxis.max = end;
                        component.options.series = [series];
                    });
            }
        }
    });
</script>

<style>

</style>