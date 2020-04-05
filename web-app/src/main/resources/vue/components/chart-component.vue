<template id="chart-component">
    <div>
        <highcharts :options="chartOptions"></highcharts>
    </div>
</template>

<script>
    Vue.component('chart-component', {
        template: `#chart-component`,
        components: {},
        props: {
            chartOptions: Object,
        },
        data: function(){
            return {
            }
        },
        mounted: function() {}
    });
</script>

<style>

</style>