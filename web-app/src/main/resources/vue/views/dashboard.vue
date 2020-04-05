<template id="dashboard">
    <el-container>
        <el-header>
            <el-menu default-active="1" class="el-menu-demo" mode="horizontal">
                <el-menu-item index="1">
                    <h3 id="brand">Smart Farm Dashboard</h3>
                </el-menu-item>
<!--                <el-submenu index="2">-->
<!--                    <template slot="title">Nodes</template>-->
<!--                    <el-menu-item index="2-1">Sensors</el-menu-item>-->
<!--                    <el-menu-item index="2-2">Controllers</el-menu-item>-->
<!--                </el-submenu>-->
            </el-menu>
        </el-header>
        <el-main>
            <sensors-component id="sensors" :from-time="this.fromTime" :to-time="this.toTime"></sensors-component>
            <controller-component id="controllers" :from-time="this.fromTime" :to-time="this.toTime"></controller-component>
        </el-main>
    </el-container>
</template>
<script>
    Vue.component('dashboard', {
        template: `#dashboard`,
        components: {},
        data: function() {
            return {
                dateRange: [
                    luxon.DateTime.local().startOf('day').minus({days: 1}).toJSDate(),
                    luxon.DateTime.local().endOf('day').toJSDate()
                ]
            }
        },
        computed: {
            fromTime(){
                luxon.DateTime.local().startOf('day').minus({days: 1}).toJSDate();
            },
            toTime(){
                luxon.DateTime.local().endOf('day').toJSDate()
            }
        },
        methods: {
            emit() {
                EventBus.$emit('search', this.fromTime, this.toTime);
            }
        }
    });
</script>
<style>
    #brand {
        font-weight: bold;
        font-size: xx-large;
        font-family: "Raleway", sans-serif;
    }
    #sensors {
        margin: 15px 0;
    }
    #controllers {
        margin: 15px 0;
    }
</style>