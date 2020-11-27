package javafx_kotlin.dsl

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class ParentContext {
    val children: ArrayList<Node> = ArrayList()

    fun add(node: Node) {
        children.add(node)
    }
}

fun layout(init: ParentContext.() -> Unit): Parent {
    return AnchorPane(ParentContext().apply(init).children[0])
}

fun ParentContext.vbox(func: ParentContext.() -> Unit) {
    val context = ParentContext().apply(func)
    val vbox = VBox().apply { children.addAll(context.children) }
    add(vbox)
}

fun ParentContext.hbox(func: ParentContext.() -> Unit) {
    val context = ParentContext().apply(func)
    val vbox = HBox().apply { children.addAll(context.children) }
    add(vbox)
}

fun ParentContext.label(text: String) {
    add(Label(text))
}

fun ParentContext.textField(text: String = "") {
    add(TextField(text))
}

fun ParentContext.button(text: String, eventHandler: EventHandler<ActionEvent>? = null) {
    add(Button(text).apply { onAction = eventHandler })
}

class GridContext {
    val rows: ArrayList<ParentContext> = ArrayList()

    fun add(row: ParentContext) {
        rows.add(row)
    }
}

fun ParentContext.gridPane(func: GridContext.() -> Unit) {
    val context = GridContext().apply(func)
    val grid = GridPane()
    for ((i, row) in context.rows.withIndex()) {
        grid.addRow(i, *row.children.toTypedArray())
    }
    add(grid)
}

fun GridContext.row(func: ParentContext.() -> Unit) {
    val context = ParentContext().apply(func)
    add(context)
}
