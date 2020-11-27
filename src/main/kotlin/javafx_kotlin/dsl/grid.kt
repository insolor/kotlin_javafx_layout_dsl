package javafx_kotlin.dsl

import javafx.scene.Node
import javafx.scene.layout.GridPane

class CellContext(val node: Node, val row: Int, val column: Int,
                  val rowspan: Int = 1, val colspan: Int = 1)

class GridContext {
    val rows: ArrayList<ContainerContext> = ArrayList()
    val columns: ArrayList<ContainerContext> = ArrayList()
    val cells: ArrayList<CellContext> = ArrayList()

    fun row(func: ContainerContext.() -> Unit) {
        val context = ContainerContext().apply(func)
        rows.add(context)
    }

    fun column(func: ContainerContext.() -> Unit) {
        val context = ContainerContext().apply(func)
        columns.add(context)
    }

    fun cell(node: Node, row: Int, column: Int, rowspan: Int = 1, colspan: Int = 1) {
        cells.add(CellContext(node, row, column, rowspan, colspan))
    }
}

fun ContainerContext.gridPane(func: GridContext.() -> Unit) {
    val context = GridContext().apply(func)
    val grid = GridPane()

    for ((i, row) in context.rows.withIndex()) {
        grid.addRow(i, *row.children.toTypedArray())
    }

    for ((i, columns) in context.columns.withIndex()) {
        grid.addColumn(i, *columns.children.toTypedArray())
    }

    for (cell in context.cells) {
        grid.add(cell.node, cell.column, cell.row, cell.colspan, cell.rowspan)
    }

    node(grid)
}
