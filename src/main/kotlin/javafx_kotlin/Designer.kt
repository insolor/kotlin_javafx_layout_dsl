package javafx_kotlin

import javafx.scene.Parent

fun createLayout(): Parent {
    return layout {
        vbox {
            label("Hello, World!")
            textField()
            button("OK") { println("Hello!") }
        }
    }
}