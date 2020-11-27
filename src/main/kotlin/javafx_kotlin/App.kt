package javafx_kotlin

import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage

class App : Application() {
    override fun start(primaryStage: Stage) {
        val view = View()

        primaryStage.run {
            scene = Scene(view)
            show()
        }
    }
}

fun main() {
    launch(App::class.java)
}