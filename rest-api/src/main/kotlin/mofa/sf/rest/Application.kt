package mofa.sf.rest

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.http.BadRequestResponse
import mofa.sf.postgres.config.DbConnectionProvider
import mofa.sf.rest.endpoint.*

fun main() {
    val db = DbConnectionProvider()

    val app = Javalin.create {
        it.showJavalinBanner = false
        it.enableCorsForAllOrigins()
    }.start(7000)

    app.routes {

        get("/control-nodes") { context ->
            context.json(ControlNodes.Default(db).list())
        }

        get("/control-nodes/pages") { context ->
            val page = context.queryParam<Int>("page").get()
            val size = context.queryParam<Int>("size").get()
            try {
                context.json(ControlNodes.Paged(db, page, size).list())
            } catch (ex: IllegalArgumentException) {
                throw BadRequestResponse(ex.message?:"")
            }
        }

        get("/sensor-nodes") { context ->
            context.json(SensorNodes.Default(db).list())
        }

        get("/sensor-nodes/pages") { context ->
            val page = context.queryParam<Int>("page").get()
            val size = context.queryParam<Int>("size").get()
            try {
                context.json(SensorNodes.Paged(db, page, size).list())
            } catch (ex: IllegalArgumentException) {
                throw BadRequestResponse(ex.message?:"")
            }
        }

        get("/average-humidity") { context ->
            context.json(AverageHumidity.Default(db).list())
        }

        get("/average-humidity/time") { context ->
            val from = context.queryParam<Long>("from").get()
            val to = context.queryParam<Long>("to").get()
            try {
                context.json(AverageHumidity.FilteredByTime(db, from, to).list())
            } catch (ex: IllegalArgumentException) {
                throw BadRequestResponse(ex.message?:"")
            }
        }

        get("/average-moisture") { context ->
            context.json(AverageMoisture.Default(db).list())
        }

        get("/average-moisture/time") { context ->
            val from = context.queryParam<Long>("from").get()
            val to = context.queryParam<Long>("to").get()
            try {
                context.json(AverageMoisture.FilteredByTime(db, from, to).list())
            } catch (ex: IllegalArgumentException) {
                throw BadRequestResponse(ex.message?:"")
            }
        }

        get("/average-temperature") { context ->
            context.json(AverageTemperature.Default(db).list())
        }

        get("/average-temperature/time") { context ->
            val from = context.queryParam<Long>("from").get()
            val to = context.queryParam<Long>("to").get()
            try {
                context.json(AverageTemperature.FilteredByTime(db, from, to).list())
            } catch (ex: IllegalArgumentException) {
                throw BadRequestResponse(ex.message?:"")
            }
        }

        get("/sensor-readings/:id") { context ->
            val id = context.pathParam<Int>("id").get()
            context.json(SensorReadings.FilteredBySensor(db, id).list())
        }

        get("/control-signals") { context ->
            context.json(ControlSignals.Default(db).list())
        }

        get("/control-signals/time") { context ->
            val from = context.queryParam<Long>("from").get()
            val to = context.queryParam<Long>("to").get()
            try {
                context.json(ControlSignals.FilteredByTime(db, from, to).list())
            } catch (ex: IllegalArgumentException) {
                throw BadRequestResponse(ex.message?:"")
            }
        }

        get("/control-signals/:id") { context ->
            val id = context.pathParam<Int>("id").get()
            context.json(ControlSignals.FilteredBySensor(db, id).list())
        }
    }
}