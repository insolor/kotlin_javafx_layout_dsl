package javafx_kotlin.dsl

import javafx.geometry.HPos
import javafx.geometry.VPos
import javafx.scene.Node
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import javafx.scene.layout.RowConstraints

class CellContext(val node: Node, val row: Int, val column: Int,
                  val rowspan: Int = 1, val colspan: Int = 1)

class GridContext {
    val rows: ArrayList<ContainerContext> = ArrayList()
    val columns: ArrayList<ContainerContext> = ArrayList()
    val cells: ArrayList<CellContext> = ArrayList()
    var columnConstraintsList: ArrayList<ColumnConstraints>? = null
    var rowConstraintsList: ArrayList<RowConstraints>? = null

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
    
    fun columnConstraints(func: ColumnConstraintsContext.() -> Unit) {
        val context = ColumnConstraintsContext().apply(func)
        columnConstraintsList = context.constraintsList
    }

    fun rowConstraints(func: RowConstraintsContext.() -> Unit) {
        val context = RowConstraintsContext().apply(func)
        rowConstraintsList = context.constraintsList
    }
}

class ColumnConstraintsContext {
    val constraintsList: ArrayList<ColumnConstraints> = ArrayList()

    fun constraints() {
        constraintsList.add(ColumnConstraints())
    }

    fun constraints(width: Double) {
        constraintsList.add(ColumnConstraints(width))
    }

    fun constraints(minWidth: Double, prefWidth: Double, maxWidth: Double) {
        constraintsList.add(ColumnConstraints(minWidth, prefWidth, maxWidth))
    }

    fun constraints(minWidth: Double, prefWidth: Double, maxWidth: Double,
                    hgrow: Priority, halignment: HPos, fillWidth: Boolean) {
        
        constraintsList.add(
            ColumnConstraints(minWidth, prefWidth, maxWidth,
                hgrow, halignment, fillWidth))
    }
}

class RowConstraintsContext {
    val constraintsList: ArrayList<RowConstraints> = ArrayList()

    fun constraints() {
        constraintsList.add(RowConstraints())
    }

    fun constraints(height: Double) {
        constraintsList.add(RowConstraints(height))
    }

    fun constraints(minHeight: Double, prefHeight: Double, maxHeight: Double) {
        constraintsList.add(RowConstraints(minHeight, prefHeight, maxHeight))
    }

    fun constraints(minHeight: Double, prefHeight: Double, maxHeight: Double,
                    vgrow: Priority, valignment: VPos, fillHeight: Boolean) {

        constraintsList.add(
            RowConstraints(minHeight, prefHeight, maxHeight,
                vgrow, valignment, fillHeight))
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
    
    if(context.columnConstraintsList != null) {
        grid.columnConstraints.setAll(context.columnConstraintsList)
    }

    if(context.rowConstraintsList != null) {
        grid.rowConstraints.setAll(context.rowConstraintsList)
    }

    node(grid)
}
