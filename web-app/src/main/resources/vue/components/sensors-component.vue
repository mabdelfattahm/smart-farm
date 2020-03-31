<template id="sensors-component">
    <div>
        <filter-component></filter-component>
        <moisture-component :from-time="this.fromTime" :to-time="this.toTime"></moisture-component>
        <humidity-component :from-time="this.fromTime" :to-time="this.toTime"></humidity-component>
        <temperature-component :from-time="this.fromTime" :to-time="this.toTime"></temperature-component>
    </div>
</template>

<script>
    Vue.component('sensors-component', {
        components: {},
        template: `#sensors-component`,
        props: {
            fromTime: Number,
            toTime: Number
        },
        data: function () {
            return {}
        },
        mounted: function () {
        }
    });
</script>

<style>

</style>