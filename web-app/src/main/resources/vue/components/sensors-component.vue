<template id="sensors-component">
    <el-card id="sensors" class="box-card">
        <div slot="header">
            <el-row type="flex">
                <el-col id="title">
                    <h1>Sensors</h1>
                </el-col>
                <el-col id="filter">
                    <filter-component :from-time="this.fromTime" :to-time="this.toTime" :event="this.eventName"></filter-component>
                </el-col>
            </el-row>
        </div>
        <el-row id="charts" type="flex" :gutter="20">
            <el-col>
                <moisture-component :from-time="this.fromTime" :to-time="this.toTime"></moisture-component>
            </el-col>
            <el-col>
                <humidity-component :from-time="this.fromTime" :to-time="this.toTime"></humidity-component>
            </el-col>
            <el-col>
                <temperature-component :from-time="this.fromTime" :to-time="this.toTime"></temperature-component>
            </el-col>
        </el-row>
    </el-card>
</template>

<script>
    Vue.component('sensors-component', {
        components: {},
        template: `#sensors-component`,
        props: {},
        data: function () {
            return {
                fromTime: luxon.DateTime.local().startOf('day').toMillis(),
                toTime: luxon.DateTime.local().startOf('day').plus({days: 1}).toMillis(),
                eventName: 'sensor_time_change'
            }
        },
        mounted: function () {
            EventBus.$on(this.eventName, this.updateTime)
        },
        beforeDestroy() {
            EventBus.$off(this.eventName);
        },
        methods: {
            updateTime(start, end) {
                this.fromTime = start;
                this.toTime = end;
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

    #sensors {
        padding: 25px;
    }

    #filter {
        width: fit-content;
    }

    #sensors > .el-card__header {
        padding: 0 0 10px 0;
    }

    #sensors > .el-card__body {
        padding: 10px 0 0 0;
    }

    #charts {
        margin-top: 10px;
    }
</style>