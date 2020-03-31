<template id="chart-component">
    <div>
        <h3 id="title"></h3>
        <apexchart :type="this.chartType" :height="this.chartHeight" :options="this.chartOptions" :series="this.chartSeries"></apexchart>
    </div>
</template>

<script>
    Vue.component('chart-component', {
        template: `#chart-component`,
        components: {
            apexchart: VueApexCharts,
        },
        props: {
            chartType: String,
            chartHeight: String,
            chartOptions: Object,
            chartSeries: Array
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