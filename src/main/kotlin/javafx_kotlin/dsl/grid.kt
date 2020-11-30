package javafx_kotlin.dsl

import javafx.geometry.HPos
import javafx.geometry.VPos
import javafx.scene.Node
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import javafx.scene.layout.RowConstraints

class CellContext(
    val node: Node, val row: Int, val column: Int,
    val rowspan: Int = 1, val colspan: Int = 1
)

class GridContext: GridPane() {
    private val rows: ArrayList<ContainerContext> = ArrayList()
    private val columns: ArrayList<ContainerContext> = ArrayList()
    private val cells: ArrayList<CellContext> = ArrayList()

    fun row(func: ContainerContext.() -> Unit) {
        rows.add(ContainerContext().apply(func))
    }

    fun column(func: ContainerContext.() -> Unit) {
        columns.add(ContainerContext().apply(func))
    }

    fun cell(node: Node, row: Int, column: Int, rowspan: Int = 1, colspan: Int = 1) {
        cells.add(CellContext(node, row, column, rowspan, colspan))
    }

    fun columnConstraints(func: ColumnConstraintsContext.() -> Unit) {
        columnConstraints.setAll(ColumnConstraintsContext().apply(func).build())
    }

    fun rowConstraints(func: RowConstraintsContext.() -> Unit) {
        rowConstraints.setAll(RowConstraintsContext().apply(func).build())
    }

    fun build(): GridPane {
        for ((i, row) in rows.withIndex()) {
            addRow(i, *row.build().toTypedArray())
        }

        for ((i, columns) in columns.withIndex()) {
            addColumn(i, *columns.build().toTypedArray())
        }

        for (cell in cells) {
            add(cell.node, cell.column, cell.row, cell.colspan, cell.rowspan)
        }

        return this;
    }
}

class ColumnConstraintsContext {
    private val constraintsList: ArrayList<ColumnConstraints> = ArrayList()

    fun constraints() {
        constraintsList.add(ColumnConstraints())
    }

    fun constraints(width: Double) {
        constraintsList.add(ColumnConstraints(width))
    }

    fun constraints(minWidth: Double, prefWidth: Double, maxWidth: Double) {
        constraintsList.add(ColumnConstraints(minWidth, prefWidth, maxWidth))
    }

    fun constraints(
        minWidth: Double, prefWidth: Double, maxWidth: Double,
        hgrow: Priority, halignment: HPos, fillWidth: Boolean
    ) {

        constraintsList.add(
            ColumnConstraints(
                minWidth, prefWidth, maxWidth,
                hgrow, halignment, fillWidth
            )
        )
    }

    fun build(): ArrayList<ColumnConstraints> {
        return constraintsList
    }
}

class RowConstraintsContext {
    private val constraintsList: ArrayList<RowConstraints> = ArrayList()

    fun constraints() {
        constraintsList.add(RowConstraints())
    }

    fun constraints(height: Double) {
        constraintsList.add(RowConstraints(height))
    }

    fun constraints(minHeight: Double, prefHeight: Double, maxHeight: Double) {
        constraintsList.add(RowConstraints(minHeight, prefHeight, maxHeight))
    }

    fun constraints(
        minHeight: Double, prefHeight: Double, maxHeight: Double,
        vgrow: Priority, valignment: VPos, fillHeight: Boolean
    ) {
        constraintsList.add(
            RowConstraints(
                minHeight, prefHeight, maxHeight,
                vgrow, valignment, fillHeight
            )
        )
    }

    fun build(): ArrayList<RowConstraints> {
        return constraintsList
    }
}

fun ContainerContext.gridPane(func: GridContext.() -> Unit) {
    node(GridContext().apply(func).build())
}
