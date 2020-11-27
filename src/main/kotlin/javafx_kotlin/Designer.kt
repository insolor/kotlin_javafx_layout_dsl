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

            gridPane {
                row {
                    button("1")
                    button("2")
                    button("3")
                }

                row {
                    button("4")
                    button("5")
                    button("6")
                }

                row {
                    button("7")
                    button("8")
                    button("9")
                }
            }
        }
    }
}