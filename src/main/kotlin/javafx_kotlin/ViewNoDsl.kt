package javafx_kotlin

import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox

/**
 * An example of how to create the same GUI layout as in View.kt in pure Kotlin without DSL
 */
class ViewNoDsl : StackPane() {
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
        return VBox(4.0).apply {
            children.run {
                add(Label("Hello, World!"))

                textField = TextField()
                add(textField)

                add(GridPane().apply {
                    hgap = 2.0
                    vgap = 2.0

                    for (i in (0..2)) {
                        for (j in (0..2)) {
                            add(buttons[i * 3 + j], i, j)
                        }
                    }

                    addRow(3,
                        Button("0").apply { setOnAction { textField.text += "0" } },
                        Button("C").apply { setOnAction { textField.text = "" } }
                    )
                })
            }
        }
    }
}