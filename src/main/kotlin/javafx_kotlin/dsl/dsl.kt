package javafx_kotlin.dsl

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox

class ContainerContext {
    val children: ArrayList<Node> = ArrayList()

    fun node(node: Node) {
        children.add(node)
    }
}

fun layout(init: ContainerContext.() -> Unit): Node {
    return ContainerContext().apply(init).children[0]
}

fun ContainerContext.pane(paneCreator: () -> Pane, func: ContainerContext.() -> Unit) {
    val context = ContainerContext().apply(func)
    val pane = paneCreator().apply { children.addAll(context.children) }
    node(pane)
}

fun ContainerContext.vbox(func: ContainerContext.() -> Unit) {
    pane(::VBox, func)
}

fun ContainerContext.hbox(func: ContainerContext.() -> Unit) {
    pane(::HBox, func)
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
