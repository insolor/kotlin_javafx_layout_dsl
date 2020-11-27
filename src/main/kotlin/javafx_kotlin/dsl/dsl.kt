package javafx_kotlin.dsl

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.*

class ParentContext {
    val children: ArrayList<Node> = ArrayList()

    fun node(node: Node) {
        children.add(node)
    }
}

fun layout(init: ParentContext.() -> Unit): Parent {
    return AnchorPane(ParentContext().apply(init).children[0])
}

fun ParentContext.pane(paneCreator: () -> Pane, func: ParentContext.() -> Unit) {
    val context = ParentContext().apply(func)
    val pane = paneCreator().apply { children.addAll(context.children) }
    node(pane)
}

fun ParentContext.vbox(func: ParentContext.() -> Unit) {
    pane(::VBox, func)
}

fun ParentContext.hbox(func: ParentContext.() -> Unit) {
    pane(::HBox, func)
}

fun ParentContext.label(text: String) {
    node(Label(text))
}

fun ParentContext.textField(text: String = "") {
    node(TextField(text))
}

fun ParentContext.button(text: String, eventHandler: EventHandler<ActionEvent>? = null) {
    node(Button(text).apply { onAction = eventHandler })
}

class GridContext {
    val rows: ArrayList<ParentContext> = ArrayList()

    fun row(func: ParentContext.() -> Unit) {
        val context = ParentContext().apply(func)
        rows.add(context)
    }
}

fun ParentContext.gridPane(func: GridContext.() -> Unit) {
    val context = GridContext().apply(func)
    val grid = GridPane()
    for ((i, row) in context.rows.withIndex()) {
        grid.addRow(i, *row.children.toTypedArray())
    }
    node(grid)
}
