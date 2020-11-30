package javafx_kotlin.dsl

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Orientation
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

fun ContainerContext.pane(pane: Pane, func: ContainerContext.() -> Unit) {
    val context = ContainerContext().apply(func)
    node(pane.apply { children.addAll(context.build()) })
}

fun ContainerContext.vBox(spacing: Double = 0.0, func: ContainerContext.() -> Unit) {
    pane(VBox(spacing), func)
}

fun ContainerContext.hBox(spacing: Double = 0.0, func: ContainerContext.() -> Unit) {
    pane(HBox(spacing), func)
}

fun ContainerContext.flowPane(
    orientation: Orientation = Orientation.HORIZONTAL,
    hGap: Double = 0.0,
    vGap: Double = 0.0,
    func: ContainerContext.() -> Unit
) {
    pane(FlowPane(orientation, hGap, vGap), func)
}

fun ContainerContext.tilePane(func: ContainerContext.() -> Unit) {
    pane(TilePane(), func)
}

fun ContainerContext.stackPane(func: ContainerContext.() -> Unit) {
    pane(StackPane(), func)
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
