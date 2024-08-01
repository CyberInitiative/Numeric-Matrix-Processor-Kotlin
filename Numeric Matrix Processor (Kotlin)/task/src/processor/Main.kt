package processor

fun createMatrix(numOfRows: Int, numOfColumns: Int): Array<DoubleArray> {
    val matrix = Array(numOfRows) { DoubleArray(numOfColumns) }

    for (row in 0 until numOfRows) {
        val rowValues = readln().split(" ").map { it.toDouble() }
        for (column in rowValues.indices) {
            matrix[row][column] = rowValues[column]
        }
    }

    return matrix
}

fun addTwoMatrices(firstMatrix: Array<DoubleArray>, secondMatrix: Array<DoubleArray>): Array<DoubleArray>? {
    if ((firstMatrix.size == secondMatrix.size) && (firstMatrix[0].size == secondMatrix[0].size)) {
        val resultMatrix = Array(firstMatrix.size) { DoubleArray(firstMatrix[0].size) }
        for (row in firstMatrix.indices) {
            for (column in 0 until firstMatrix[0].size) {
                resultMatrix[row][column] = secondMatrix[row][column] + firstMatrix[row][column]
            }
        }
        return resultMatrix
    }
    return null
}

fun multiplyMatrixByAConstant(number: Double, matrix: Array<DoubleArray>) {
    for (row in matrix.indices) {
        for (column in 0 until matrix[0].size) {
            matrix[row][column] = number * matrix[row][column]
        }
    }
}

fun printMatrix(matrix: Array<DoubleArray>) {
    matrix.forEach { row ->
        println(row.joinToString(" "))
    }
}

fun multiplyTwoMatrices(firstMatrix: Array<DoubleArray>, secondMatrix: Array<DoubleArray>): Array<DoubleArray>? {
    if (firstMatrix[0].size == secondMatrix.size) {
        val matrix = Array(firstMatrix.size) { DoubleArray(secondMatrix[0].size) }
        for (row in matrix.indices) {
            for (column in 0 until matrix[0].size) {
                val preResult = mutableListOf<Double>()

                for (i in 0 until firstMatrix[0].size) {
                    preResult.add(firstMatrix[row][i] * secondMatrix[i][column])
                }

                matrix[row][column] = preResult.sum()
            }
        }
        return matrix
    }
    return null
}

fun createTwoMatrices(): Pair<Array<DoubleArray>, Array<DoubleArray>> {
    println("Enter the size of first matrix:")
    val (firstMatrixNumberOfRows, firstMatrixNumberOfColumns) = readln().split(" ").map { it.toInt() }
    println("Enter first matrix:")
    val firstMatrix = createMatrix(firstMatrixNumberOfRows, firstMatrixNumberOfColumns)

    println("Enter the size of second matrix:")
    val (secondMatrixNumberOfRows, secondMatrixNumberOfColumns) = readln().split(" ").map { it.toInt() }
    println("Enter second matrix:")
    val secondMatrix = createMatrix(secondMatrixNumberOfRows, secondMatrixNumberOfColumns)

    return Pair(firstMatrix, secondMatrix)
}

fun addTwoMatricesOption() {
    val (firstMatrix, secondMatrix) = createTwoMatrices()

    val result = addTwoMatrices(firstMatrix, secondMatrix)
    if (result != null) {
        printMatrix(result)
    } else {
        println("ERROR")
    }
}

fun multiplyTwoMatricesOption() {
    val (firstMatrix, secondMatrix) = createTwoMatrices()

    val result = multiplyTwoMatrices(firstMatrix, secondMatrix)
    if (result != null) {
        printMatrix(result)
    } else {
        println("ERROR")
    }
}

fun multiplyMatrixByAConstantOption() {
    println("Enter size of matrix:")
    val (numberOfRows, numberOfColumns) = readln().split(" ").map { it.toInt() }
    println("Enter matrix:")
    val matrix = createMatrix(numberOfRows, numberOfColumns)
    println("Enter constant:")
    val constant = readln().toDouble()
    multiplyMatrixByAConstant(constant, matrix)
    printMatrix(matrix)
}

fun matrixTransposeAlongMainDiagonal(matrix: Array<DoubleArray>): Array<DoubleArray> {
    val transposedMatrix = Array(matrix[0].size) { DoubleArray(matrix.size) }

    for (row in matrix.indices) {
        for (column in 0 until matrix[0].size) {

            transposedMatrix[column][row] = matrix[row][column]
        }
    }
    return transposedMatrix
}

fun matrixTransposeAlongSideDiagonal(matrix: Array<DoubleArray>): Array<DoubleArray> {
    val transposedMatrix = Array(matrix[0].size) { DoubleArray(matrix.size) }

    for (row in matrix.indices) {
        for (column in 0 until matrix[0].size) {

            transposedMatrix[column][row] = matrix[(matrix.size - 1) - row][(matrix[0].size - 1) - column]
        }
    }
    return transposedMatrix
}

fun matrixTransposeAlongVerticalLine(matrix: Array<DoubleArray>): Array<DoubleArray> {
    val transposedMatrix = Array(matrix[0].size) { DoubleArray(matrix.size) }

    for (row in matrix.indices) {
        for (column in 0 until matrix[0].size) {

            transposedMatrix[column][row] = matrix[(matrix.size - 1) - row][column]
        }
    }

    return matrixTransposeAlongSideDiagonal(transposedMatrix)
}

fun matrixTransposeAlongHorizontalLine(matrix: Array<DoubleArray>): Array<DoubleArray> {
    val transposedMatrix = Array(matrix[0].size) { DoubleArray(matrix.size) }

    for (row in matrix.indices) {
        for (column in 0 until matrix[0].size) {

            transposedMatrix[column][row] = matrix[row][(matrix[0].size - 1) - column]
        }
    }

    return matrixTransposeAlongSideDiagonal(transposedMatrix)
}

fun matrixTransposeOption() {
    println(
        "\n1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical diagonal\n" +
                "4. Horizontal diagonal\n" +
                "Your choice:"
    )
    val userChoice = readln().toInt()
    println("Enter size of matrix:")
    val (numberOfRows, numberOfColumns) = readln().split(" ").map { it.toInt() }
    println("Enter matrix:")
    val matrix = createMatrix(numberOfRows, numberOfColumns)
    when (userChoice) {
        1 -> printMatrix(matrixTransposeAlongMainDiagonal(matrix))
        2 -> printMatrix(matrixTransposeAlongSideDiagonal(matrix))
        3 -> printMatrix(matrixTransposeAlongVerticalLine(matrix))
        4 -> printMatrix(matrixTransposeAlongHorizontalLine(matrix))
        else -> println("ERROR")
    }

}

fun findDeterminer(matrix: Array<DoubleArray>): Double {
    val result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]))
    return result
}

fun findDeterminerAdvanced(matrix: Array<DoubleArray>): Double {
    var result = 0.0

    if (matrix[0].size * matrix.size == 4) {
        return findDeterminer(matrix)
    } else {
        for (column in 0 until matrix[0].size) {

            val list = mutableListOf<Double>()
            for (row2 in matrix.indices) {
                for (column2 in 0 until matrix[0].size) {
                    if (row2 != 0 && column2 != column) {
                        list.add(matrix[row2][column2])
                    }
                }
            }

            val matrix2 = Array(matrix.size - 1) { row ->
                DoubleArray(matrix.size - 1) { col ->
                    list[row * (matrix.size - 1) + col]
                }
            }

            if (column % 2 != 0) {
                result -= (matrix[0][column] * findDeterminerAdvanced(matrix2))
            } else if (column % 2 == 0) {
                result += (matrix[0][column] * findDeterminerAdvanced(matrix2))
            }

        }
    }

    return result
}

fun getMinor(matrix: Array<DoubleArray>, row: Int, col: Int): Array<DoubleArray> {
    val minor = Array(matrix.size - 1) { DoubleArray(matrix.size - 1) }
    for (i in matrix.indices) {
        if (i == row) continue
        for (j in matrix[0].indices) {
            if (j == col) continue
            minor[if (i < row) i else i - 1][if (j < col) j else j - 1] = matrix[i][j]
        }
    }
    return minor
}

fun findCofactorMatrix(matrix: Array<DoubleArray>): Array<DoubleArray> {
    val cofactors = Array(matrix.size) { DoubleArray(matrix.size) }
    for (i in matrix.indices) {
        for (j in matrix[0].indices) {
            val minor = getMinor(matrix, i, j)
            val cofactor = findDeterminerAdvanced(minor)
            cofactors[i][j] = if ((i + j) % 2 == 0) cofactor else -cofactor
        }
    }
    return cofactors
}


fun findDeterminerOption() {
    println("Enter size of matrix:")
    val (numberOfRows, numberOfColumns) = readln().split(" ").map { it.toInt() }
    println("Enter matrix:")
    val matrix = createMatrix(numberOfRows, numberOfColumns)
    println("The result is:")
    println(findDeterminerAdvanced(matrix))
}

fun inverseMatrix(matrix: Array<DoubleArray>): Array<DoubleArray>? {
    val determinant = findDeterminerAdvanced(matrix)
    if (determinant == 0.0) {
        return null
    }
    val cofactorMatrix = findCofactorMatrix(matrix)
    val adjugateMatrix = matrixTransposeAlongMainDiagonal(cofactorMatrix)
    val inverseMatrix = Array(matrix.size) { DoubleArray(matrix[0].size) }
    for (i in adjugateMatrix.indices) {
        for (j in adjugateMatrix[0].indices) {
            inverseMatrix[i][j] = adjugateMatrix[i][j] / determinant
        }
    }
    return inverseMatrix
}

fun inverseMatrixOption(){
    println("Enter size of matrix:")
    val (numberOfRows, numberOfColumns) = readln().split(" ").map { it.toInt() }
    println("Enter matrix:")
    val matrix = createMatrix(numberOfRows, numberOfColumns)
    val result = inverseMatrix(matrix)
    if(result != null){
        printMatrix(result)
    } else {
        println("This matrix doesn't have an inverse.\n")
    }
}


fun main() {
    while (true) {
        println(
            "1. Add matrices\n" +
                    "2. Multiply matrix by a constant\n" +
                    "3. Multiply matrices\n" +
                    "4. Transpose matrix\n" +
                    "5. Calculate a determinant\n" +
                    "6. Inverse matrix\n" +
                    "0. Exit\n" +
                    "Your choice:"
        )
        val userInput = readln().toInt()

        when (userInput) {
            1 -> addTwoMatricesOption()
            2 -> multiplyMatrixByAConstantOption()
            3 -> multiplyTwoMatricesOption()
            4 -> matrixTransposeOption()
            5 -> findDeterminerOption()
            6 -> inverseMatrixOption()

            0 -> break
        }
    }
}