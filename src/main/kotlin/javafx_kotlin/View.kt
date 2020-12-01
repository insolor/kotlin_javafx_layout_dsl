package javafx_kotlin

import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.StackPane
import javafx_kotlin.dsl.*

class View : StackPane() {
    private lateinit var textField: TextField
    private var buttons: ArrayList<Button> = ArrayList()

    init {
        padding = Insets(5.0)
        for (i in (1..9)) {
            buttons.add(Button(i.toString()).apply {
                setOnAction { textField.text += i.toString() }
            })
        }

        children.add(createLayout())
    }

    private fun createLayout(): Node {
        return layout {
            vBox(4.0) {
                label("Hello, DSL!")

                textField = TextField()
                node(textField)

                gridPane {
                    hgap = 2.0
                    vgap = 2.0

                    for (i in (0..2)) {
                        row {
                            for (j in (0..2)) {
                                node(buttons[i * 3 + j])
                            }
                        }
                    }

                    row {
                        button("0") { textField.text += "0" }
                        button("C") { textField.text = "" }
                    }
                }
            }
        }
    }
}