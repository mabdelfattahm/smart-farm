<template id="controller-component">
    <el-card id="controllers" class="box-card">
        <div slot="header">
            <el-row type="flex">
                <el-col id="title">
                    <h1>Controllers</h1>
                </el-col>
                <el-col id="filter">
                    <filter-component :from-time="this.fromTime" :to-time="this.toTime" :event="this.eventName"></filter-component>
                </el-col>
            </el-row>
        </div>
        <el-row id="charts" type="flex" :gutter="10">
            <el-col>
                <el-card class="box-card" shadow="never">
                    <chart-component v-for="options in this.controllers" :key="options.chart.id" :chart-options="options"></chart-component>
                </el-card>
            </el-col>
        </el-row>
    </el-card>
</template>

<script>
    Vue.component('controller-component', {
        components: {},
        template: `#controller-component`,
        props: {},
        data: function () {
            return {
                fromTime: luxon.DateTime.local().startOf('day').toMillis(),
                toTime: luxon.DateTime.local().startOf('day').plus({days: 1}).toMillis(),
                eventName: 'controller_time_change',
                controllers: []
            }
        },
        created() {
            EventBus.$on('controller_time_change', this.load);
        },
        beforeDestroy() {
            EventBus.$off('controller_time_change', this.load)
        },
        mounted() {
            this.load(this.fromTime, this.toTime);
        },
        methods: {
            load(start, end) {
                let component = this;
                const params = {};
                let url = "http://localhost:7000/control-signals";
                if (start != null && end != null) {
                    params['params'] = {
                        from: Math.round(start / 1000),
                        to: Math.round(end / 1000)
                    };
                    url = url + "/time";
                }
                axios.get(url, params)
                    .then(response => {
                        const result = response.data.reduce(
                            (acc, curr) => {
                                const controller = curr["controller"];
                                const timestamp = curr["timestamp"];
                                const control = curr["value"];
                                if (!acc.hasOwnProperty(controller)) {
                                    acc[controller] = [];
                                }
                                acc[controller].push([timestamp, control]);
                                return acc;
                            }, {}
                        );
                        component.controllers = [];
                        Object.keys(result).forEach(key => {
                            const id = 'Controller - ' + key;
                            component.controllers.push({
                                chart: {
                                    id: id,
                                    type: 'line',
                                    height: 600,
                                },
                                title: {
                                    text: "Signals of " + id,
                                },
                                xAxis: {
                                    type: 'datetime',
                                    min: start,
                                    max: end,
                                    title: {
                                       text: "Time"
                                    }
                                },
                                series: [
                                    {
                                        id: id,
                                        name: "Signals of " + id,
                                        data: result[key]
                                    }
                                ]
                            })
                        });
                    })
            }
        }
    });
</script>

<style>
    #title > h1 {
        font-weight: 600;
        font-size: x-large;
        line-height: 1.7;
        font-family: "Raleway", sans-serif;
    }

    #controllers {
        padding: 25px;
    }

    #filter {
        width: fit-content;
    }

    #controllers > .el-card__header {
        padding: 0 0 10px 0;
    }

    #controllers > .el-card__body {
        padding: 10px 0 0 0;
    }

    #charts {
        margin-top: 10px;
    }
</style>