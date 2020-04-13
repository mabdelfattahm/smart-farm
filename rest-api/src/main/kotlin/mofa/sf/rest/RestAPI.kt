package mofa.sf.rest

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.http.BadRequestResponse
import io.javalin.plugin.json.JavalinJackson
import mofa.sf.db.h2.access.H2ConnectionPool
import mofa.sf.db.h2.config.DbMigration
import mofa.sf.rest.dto.request.ControllerRequest
import mofa.sf.rest.dto.request.ReadingRequest
import mofa.sf.rest.dto.request.SensorRequest
import mofa.sf.rest.dto.request.SignalRequest
import mofa.sf.rest.endpoint.*

fun main() {
    DbMigration().migrate()
    val db = H2ConnectionPool()

    val app = Javalin.create {
        it.showJavalinBanner = false
        it.enableCorsForAllOrigins()
    }.start(7000)

    app.routes {

        get("/control-nodes") { context ->
            context.json(GetControlNode.Default(db).list())
        }

        post("/control-nodes") { context ->
            val json = JavalinJackson.getObjectMapper().readTree(context.body())
            context.json(AddControlNode.Default(db).add(ControllerRequest(json)))
        }

        get("/control-nodes/pages") { context ->
            val page = context.queryParam<Int>("page").get()
            val size = context.queryParam<Int>("size").get()
            try {
                context.json(GetControlNode.Paged(db, page, size).list())
            } catch (ex: IllegalArgumentException) {
                throw BadRequestResponse(ex.message?:"")
            }
        }

        get("/sensor-nodes") { context ->
            context.json(GetSensorNode.Default(db).list())
        }

        post("/sensor-nodes") { context ->
            val json = JavalinJackson.getObjectMapper().readTree(context.body())
            context.json(AddSensorNode.Default(db).add(SensorRequest(json)))
        }

        get("/sensor-nodes/pages") { context ->
            val page = context.queryParam<Int>("page").get()
            val size = context.queryParam<Int>("size").get()
            try {
                context.json(GetSensorNode.Paged(db, page, size).list())
            } catch (ex: IllegalArgumentException) {
                throw BadRequestResponse(ex.message?:"")
            }
        }

        get("/average-humidity") { context ->
            context.json(GetAverageHumidity.Default(db).list())
        }

        get("/average-humidity/time") { context ->
            val from = context.queryParam<Long>("from").get()
            val to = context.queryParam<Long>("to").get()
            try {
                context.json(GetAverageHumidity.FilteredByTime(db, from, to).list())
            } catch (ex: IllegalArgumentException) {
                throw BadRequestResponse(ex.message?:"")
            }
        }

        get("/average-moisture") { context ->
            context.json(GetAverageMoisture.Default(db).list())
        }

        get("/average-moisture/time") { context ->
            val from = context.queryParam<Long>("from").get()
            val to = context.queryParam<Long>("to").get()
            try {
                context.json(GetAverageMoisture.FilteredByTime(db, from, to).list())
            } catch (ex: IllegalArgumentException) {
                throw BadRequestResponse(ex.message?:"")
            }
        }

        get("/average-temperature") { context ->
            context.json(GetAverageTemperature.Default(db).list())
        }

        get("/average-temperature/time") { context ->
            val from = context.queryParam<Long>("from").get()
            val to = context.queryParam<Long>("to").get()
            try {
                context.json(GetAverageTemperature.FilteredByTime(db, from, to).list())
            } catch (ex: IllegalArgumentException) {
                throw BadRequestResponse(ex.message?:"")
            }
        }

        post("/sensor-readings") { context ->
            val json = JavalinJackson.getObjectMapper().readTree(context.body())
            context.json(AddSensorReading.Default(db).add(ReadingRequest(json)))
        }

        get("/sensor-readings/:id") { context ->
            val id = context.pathParam<Int>("id").get()
            context.json(GetSensorReading.FilteredBySensor(db, id).list())
        }

        post("/control-signals") { context ->
            val json = JavalinJackson.getObjectMapper().readTree(context.body())
            context.json(AddControlSignal.Default(db).add(SignalRequest(json)))
        }

        get("/control-signals") { context ->
            context.json(GetControlSignal.Default(db).list())
        }

        get("/control-signals/time") { context ->
            val from = context.queryParam<Long>("from").get()
            val to = context.queryParam<Long>("to").get()
            try {
                context.json(GetControlSignal.FilteredByTime(db, from, to).list())
            } catch (ex: IllegalArgumentException) {
                throw BadRequestResponse(ex.message?:"")
            }
        }

        get("/control-signals/:id") { context ->
            val id = context.pathParam<Int>("id").get()
            context.json(GetControlSignal.FilteredBySensor(db, id).list())
        }
    }
}