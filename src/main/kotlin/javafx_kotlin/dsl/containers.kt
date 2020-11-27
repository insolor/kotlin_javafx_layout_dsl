package javafx_kotlin.dsl

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.*

class ContainerContext {
    private val children: ArrayList<Node> = ArrayList()

    fun node(node: Node) {
        children.add(node)
    }

    fun build(): ArrayList<Node> {
        return children
    }
}

fun layout(init: ContainerContext.() -> Unit): Node {
    return ContainerContext().apply(init).build()[0]
}

fun ContainerContext.pane(paneCreator: () -> Pane, func: ContainerContext.() -> Unit) {
    val context = ContainerContext().apply(func)
    val pane = paneCreator().apply { children.addAll(context.build()) }
    node(pane)
}

fun ContainerContext.vBox(func: ContainerContext.() -> Unit) {
    pane(::VBox, func)
}

fun ContainerContext.hBox(func: ContainerContext.() -> Unit) {
    pane(::HBox, func)
}

fun ContainerContext.flowPane(func: ContainerContext.() -> Unit) {
    pane(::FlowPane, func)
}

fun ContainerContext.tilePane(func: ContainerContext.() -> Unit) {
    pane(::TilePane, func)
}

fun ContainerContext.stackPane(func: ContainerContext.() -> Unit) {
    pane(::StackPane, func)
}

fun ContainerContext.label(text: String) {
    node(Label(text))
}

fun ContainerContext.textField(text: String = "") {
    node(TextField(text))
}

fun ContainerContext.button(text: String, eventHandler: EventHandler<ActionEvent>? = null) {
    node(Button(text).apply { onAction = eventHandler })
}
