<template id="moisture-component">
    <el-card class="box-card" shadow="never">
        <chart-component ref="chart" :chart-options="this.options"></chart-component>
    </el-card>
</template>

<script>
    Vue.component('moisture-component', {
        template: `#moisture-component`,
        components: {},
        props: {
            fromTime: Number,
            toTime: Number
        },
        data: function () {
            return {
                options: {
                    chart: {
                        id: 'moisture',
                        type: 'line',
                        height: 600,
                    },
                    title: {
                        text: "Average Moisture",
                    },
                    yAxis: {
                        title: {
                            text: "Average Moisture"
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
                }
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
                let url = "http://localhost:7000/average-moisture";
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
                            id: "Moisture",
                            name: "Moisture",
                            data: response.data.map(object => [object["timestamp"], object["moisture"]])
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