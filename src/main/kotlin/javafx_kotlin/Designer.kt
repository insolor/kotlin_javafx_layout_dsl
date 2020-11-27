package javafx_kotlin

import javafx.scene.Parent
import javafx_kotlin.dsl.*

fun createLayout(): Parent {
    return layout {
        vbox {
            label("Hello, World!")
            textField()
            button("OK") { println("Hello!") }

            hbox {
                label("Hello, World!")
                textField()
                button("OK") { println("Hello!") }
            }
        }
    }
}