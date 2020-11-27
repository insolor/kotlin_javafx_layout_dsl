package javafx_kotlin

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.VBox

class LayoutBuilder {
    lateinit var root: Parent

    fun add(parent: Parent) {
         root = parent
    }
}

class ParentContext {
    val children: ArrayList<Node> = ArrayList()

    fun add(node: Node) {
        children.add(node)
    }
}

fun layout(init: LayoutBuilder.() -> Unit): Parent {
    return LayoutBuilder().apply(init).root
}

fun LayoutBuilder.vbox(func: ParentContext.() -> Unit) {
    val context = ParentContext().apply(func)
    val vbox = VBox().apply { children.addAll(context.children) }
    add(vbox)
}

fun ParentContext.label(text: String) {
    add(Label(text))
}

fun ParentContext.textField(text: String = "") {
    add(TextField(text))
}

fun ParentContext.button(text: String, eventHandler: EventHandler<ActionEvent>? = null) {
    val button = Button(text).apply { onAction = eventHandler }
    add(button)
}