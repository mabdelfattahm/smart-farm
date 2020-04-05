<template id="filter-component">
    <el-date-picker v-model="dateRange" type="datetimerange" @change="emit" align="center"
        range-separator="To" start-placeholder="Start date" end-placeholder="End date">
    </el-date-picker>
</template>
<script>
    Vue.component('filter-component', {
        template: `#filter-component`,
        props: {
            event: String,
            fromTime: Number,
            toTime: Number,
        },
        data: function() {
            return {
                dateRange: [this.fromTime, this.toTime]
            }
        },
        computed: {
            fromTimeMillis(){
                if(this.dateRange === null) { return null; }
                return luxon.DateTime.fromJSDate(this.dateRange[0]).toUTC().toMillis();
            },
            toTimeMillis(){
                if(this.dateRange === null) { return null; }
                return luxon.DateTime.fromJSDate(this.dateRange[1]).toUTC().toMillis();
            }
        },
        methods: {
            emit() {
                EventBus.$emit(this.event, this.fromTimeMillis, this.toTimeMillis);
            }
        }
    });
</script>
<style>
    .el-picker-panel, .el-button, .el-range-separator {
        font-family: "Raleway", sans-serif;
        font-weight: 600;
    }
    .el-input__inner, .el-range-input {
        font-family: "Raleway", sans-serif;
        font-weight: 500;
    }
</style>