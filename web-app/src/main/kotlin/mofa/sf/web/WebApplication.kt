package mofa.sf.web

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.http.staticfiles.Location
import io.javalin.plugin.rendering.vue.JavalinVue
import io.javalin.plugin.rendering.vue.VueComponent

fun main() {

    val app = Javalin.create {
        it.enableWebjars()
        it.showJavalinBanner = false
    }.start(7001)
    JavalinVue.rootDirectory("/vue", Location.CLASSPATH)

    app.routes {
        get("/", VueComponent("<dashboard></dashboard>"))
    }
}
