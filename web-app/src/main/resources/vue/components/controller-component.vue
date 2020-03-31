<template id="controller-component">
    <div>
        <h2>Controller</h2>
        <chart-component :chart-options="options" v-for="[key, value] in Object.entries(this.signals)" :key="key"
            chart-height="400" chart-type="line" :chart-series="[{data: value}]"></chart-component>
    </div>
</template>

<script>
    Vue.component('controller-component', {
        components: {},
        template: `#controller-component`,
        props: {
            fromTime: Number,
            toTime: Number
        },
        data: function () {
            const minDate = this.fromTime;
            const maxDate = this.toTime;
            return {
                signals: {},
                options: {
                    chart: {
                        id: 'control',
                        type: 'line',
                        height: 400
                    },
                    colors: ['#008FFB'],
                    yaxis: {
                        forceNiceScale: true,
                        labels: {
                            minWidth: 40
                        }
                    },
                    xaxis: {
                        type: 'datetime',
                        min: minDate,
                        max: maxDate,
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
                    tooltip: {
                        x: {
                            format: 'yyyy-MM-dd HH:mm:ss.fff'
                        }
                    }
                },
            }
        },
        mounted: function () {
            const controller = this;
            axios.get("http://localhost:7000/control-signals/")
                .then(response => {
                    const result = response.data.reduce(
                        (acc, curr) => {
                            const controller = curr["controller"];
                            const timestamp = new Date(curr["timestamp"]);
                            const control = curr["value"];
                            if (!acc.hasOwnProperty(controller)) {
                                acc[controller] = [];
                            }
                            acc[controller].push([timestamp, control]);
                            return acc;
                        }, {}
                    );
                    controller.signals = Object.assign({}, controller.signals, result);
                })
        }
    });
</script>

<style>

</style>