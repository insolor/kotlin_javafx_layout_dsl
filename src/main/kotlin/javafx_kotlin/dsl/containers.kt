package javafx_kotlin.dsl

import javafx.geometry.Orientation
import javafx.scene.Node
import javafx.scene.layout.*

interface ContainerContext {
    fun node(node: Node)
}

class MultipleItemContainer : ContainerContext {
    private val children: ArrayList<Node> = ArrayList()

    override fun node(node: Node) {
        children.add(node)
    }

    fun build(): ArrayList<Node> {
        return children
    }
}

class SingleItemContainer: ContainerContext {
    private lateinit var item: Node
    
    override fun node(node: Node) {
        item = node
    }

    fun build(): Node {
        return item
    }
}

/**
 * Root layout container can contain only one item (other container or control).
 */
fun layout(init: SingleItemContainer.() -> Unit): Node {
    return SingleItemContainer().apply(init).build()
}

/**
 * Panes like VBox, HBox, FlowPane, etc., can be placed in any container type and contain multiple items.
 */
fun ContainerContext.pane(pane: Pane, func: MultipleItemContainer.() -> Unit) {
    val context = MultipleItemContainer().apply(func)
    node(pane.apply { children.addAll(context.build()) })
}

fun ContainerContext.vBox(spacing: Double = 0.0, func: MultipleItemContainer.() -> Unit) {
    pane(VBox(spacing), func)
}

fun ContainerContext.hBox(spacing: Double = 0.0, func: MultipleItemContainer.() -> Unit) {
    pane(HBox(spacing), func)
}

fun ContainerContext.flowPane(
    orientation: Orientation = Orientation.HORIZONTAL,
    hgap: Double = 0.0,
    vgap: Double = 0.0,
    func: MultipleItemContainer.() -> Unit
) {
    pane(FlowPane(orientation, hgap, vgap), func)
}

fun ContainerContext.tilePane(func: MultipleItemContainer.() -> Unit) {
    pane(TilePane(), func)
}

fun ContainerContext.stackPane(func: MultipleItemContainer.() -> Unit) {
    pane(StackPane(), func)
}
