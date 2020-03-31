<template id="dashboard">
    <div>
        <sensors-component :from-time="this.fromTime" :to-time="this.toTime"></sensors-component>
        <controller-component :from-time="this.fromTime" :to-time="this.toTime"></controller-component>
    </div>
</template>
<script>
    Vue.component('dashboard', {
        template: `#dashboard`,
        components: {},
        data: function() {
            return {
                fromTime: luxon.DateTime.local().startOf('day').minus({days: 1}).toMillis(),
                toTime: luxon.DateTime.local().endOf('day').toMillis()
            }
        }
    });
</script>
<style>

</style>