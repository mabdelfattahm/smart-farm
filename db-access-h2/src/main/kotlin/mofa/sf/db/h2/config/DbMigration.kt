package mofa.sf.db.h2.config

import org.flywaydb.core.Flyway

class DbMigration {
    fun migrate() {
        val directory = "${System.getProperty("user.home")}/AppData/Local/Smart-farm/smart_farm"
        Flyway.configure()
            .sqlMigrationSuffixes(".sql", ".SQL")
            .dataSource(
                "jdbc:h2:${directory};AUTO_SERVER=TRUE",
                "smart_farm",
                "smart_farm"
            )
            .load()
            .migrate()
    }
}