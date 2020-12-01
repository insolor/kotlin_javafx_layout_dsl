package javafx_kotlin

import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import tornadofx.*

class ViewTornadoFx : StackPane() {
    private lateinit var textField: TextField
    private var buttons: ArrayList<Button> = ArrayList()

    init {
        paddingAll = 5.0
        for (i in (1..9)) {
            buttons.add(Button(i.toString()).apply {
                setOnAction { textField.text += i.toString() }
            })
        }
        children.add(createLayout())
    }

    private fun createLayout(): Node {
        return VBox(4.0).apply {
            label("Hello, TornadoFx!")

            textField = TextField()
            this += textField

            gridpane {
                hgap = 2.0
                vgap = 2.0

                for (i in (0..2)) {
                    row {
                        for (j in (0..2)) {
                            this += buttons[i * 3 + j]
                        }
                    }
                }

                row {
                    button("0") { setOnAction { textField.text += "0"} }
                    button("C") { setOnAction { textField.text = "" } }
                }
            }
        }
    }
}