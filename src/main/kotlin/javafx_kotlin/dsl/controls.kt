package javafx_kotlin.dsl

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField

fun ContainerContext.label(text: String) {
    node(Label(text))
}

fun ContainerContext.textField(text: String = "") {
    node(TextField(text))
}

fun ContainerContext.button(text: String, eventHandler: EventHandler<ActionEvent>? = null) {
    node(Button(text).apply { onAction = eventHandler })
}
